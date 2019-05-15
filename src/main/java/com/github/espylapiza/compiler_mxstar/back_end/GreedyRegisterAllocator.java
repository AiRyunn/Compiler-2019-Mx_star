package com.github.espylapiza.compiler_mxstar.back_end;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.github.espylapiza.compiler_mxstar.nasm.Instruction;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionBaseJump;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionDB;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionMov;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionResq;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionRet;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionSub;
import com.github.espylapiza.compiler_mxstar.nasm.Label;
import com.github.espylapiza.compiler_mxstar.nasm.NASM;
import com.github.espylapiza.compiler_mxstar.nasm.Operand;
import com.github.espylapiza.compiler_mxstar.nasm.OperandDBAddr;
import com.github.espylapiza.compiler_mxstar.nasm.OperandInt;
import com.github.espylapiza.compiler_mxstar.nasm.OperandMem;
import com.github.espylapiza.compiler_mxstar.nasm.OperandRegister;
import com.github.espylapiza.compiler_mxstar.nasm.OperandString;
import com.github.espylapiza.compiler_mxstar.nasm.RegisterSet;
import com.github.espylapiza.compiler_mxstar.nasm.SectionItem;
import com.github.espylapiza.compiler_mxstar.pizza_ir.BasicBlock;
import com.github.espylapiza.compiler_mxstar.pizza_ir.FuncExtra;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Inst;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstAlloc;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstBr;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstCall;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstLoad;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstMov;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstOffset;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstRet;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstStore;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Object;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectBool;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectInt;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectNull;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectPtr;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectString;

class GreedyRegisterAllocator {
    /**
     * 
     */
    private final List<OperandRegister> regAvailable = Arrays.asList(RegisterSet.rax, RegisterSet.rcx, RegisterSet.rdx,
            RegisterSet.rsi, RegisterSet.rdi, RegisterSet.r8, RegisterSet.r9, RegisterSet.r10, RegisterSet.r11,
            RegisterSet.r12, RegisterSet.r13, RegisterSet.r14, RegisterSet.r15);
    private final List<OperandRegister> regParams = Arrays.asList(RegisterSet.rdi, RegisterSet.rsi, RegisterSet.rdx,
            RegisterSet.rcx, RegisterSet.r8, RegisterSet.r9);

    private Map<Object, Operand> objAddr = new HashMap<Object, Operand>();
    private Map<Object, OperandRegister> objMap = new HashMap<Object, OperandRegister>();
    private Map<OperandRegister, Object> regObj = new HashMap<OperandRegister, Object>();
    private Set<Object> dirty = new HashSet<Object>();

    private List<OperandRegister> freeRegs;
    private List<OperandRegister> activeRegs;
    private Set<OperandRegister> frozenRegs;

    private NASM nasm;
    private int stackSize;

    public GreedyRegisterAllocator(NASM nasm) {
        this.nasm = nasm;

        activeRegs = new ArrayList<OperandRegister>();
        freeRegs = new ArrayList<OperandRegister>();
        frozenRegs = new HashSet<OperandRegister>();
        for (OperandRegister reg : regAvailable) {
            freeRegs.add(reg);
        }
    }

    OperandRegister get(Object obj, OperandRegister prefer) {
        Operand op = objAddr.get(obj);
        if (op == null) {
            if (obj.name != null) {
                allocateStack(obj);
            }
        }

        // hit
        OperandRegister reg = objMap.get(obj);
        if (reg != null) {
            activeRegs.remove(reg);
            activeRegs.add(reg);
            return reg;
        }

        // miss
        if (op == null) {
            allocateStack(obj);
            reg = allocate(prefer);
        } else {
            reg = allocate(prefer);
            nasm.sectionText.addItem(new InstructionMov(reg, op));
        }

        objMap.put(obj, reg);
        regObj.put(reg, obj);

        return reg;
    }

    private Operand allocateStack(Object obj) {
        OperandMem addr = new OperandMem(RegisterSet.rbp, null);
        objAddr.put(obj, addr);
        return addr;
    }

    private OperandRegister allocate(OperandRegister prefer) {
        OperandRegister result = tryAllocate();
        if (result == null) {
            result = deallocate();
        }
        return result;
    }

    private OperandRegister tryAllocate(OperandRegister prefer) {
        if (freeRegs.isEmpty()) {
            return null;
        }

        OperandRegister result = null;
        for (OperandRegister freeReg : freeRegs) {
            if (!frozenRegs.contains(freeReg) && freeReg == prefer) {
                result = freeReg;
                break;
            }
        }
        for (OperandRegister freeReg : freeRegs) {
            if (!frozenRegs.contains(freeReg)) {
                result = freeReg;
                break;
            }
        }
        if (result != null) {
            assert !frozenRegs.contains(result);
            freeRegs.remove(result);
            activeRegs.add(result);
        }
        return result;
    }

    private OperandRegister tryAllocate() {
        return tryAllocate(null);
    }

    private OperandRegister deallocate() {
        OperandRegister reg = activeRegs.get(0);

        release_register(reg);
        if (reg == null) {
            assert false;
        }
        activeRegs.add(reg);
        freeRegs.remove(reg);
        return reg;
    }

    private void writeBack(OperandRegister reg, boolean globalOnly, boolean nameOnly) {
        Object obj = regObj.get(reg);
        if (obj == null) {
            return;
        }
        boolean write = dirty.contains(obj);

        Operand addr = objAddr.get(obj);
        if (addr == null) {
            addr = allocateStack(obj);
        }
        if (addr instanceof OperandMem) {
            if (((OperandMem) addr).offset == null) {
                ((OperandMem) addr).offset = -8 * (++stackSize);
            }
        }
        if (write) {
            addInstruction(new InstructionMov(addr, reg));
            if (addr instanceof OperandRegister) {
                objMap.put(obj, (OperandRegister) addr);
                regObj.put((OperandRegister) addr, obj);
            }
            dirty.remove(obj);
        }
        regObj.remove(reg);
        objMap.remove(obj);
    }

    void enterFunc(FuncExtra func) {
        stackSize = 0;
        int cntParams = func.getParams().count(), index = 0;
        if (func.getOwnerClass() != null) {
            cntParams++;
            objMap.put(func.getVarList().get(0), regParams.get(index));
            regObj.put(regParams.get(index), func.getVarList().get(0));
            dirty.add(func.getVarList().get(0));
            activeRegs.add(regParams.get(index));
            freeRegs.remove(regParams.get(index));
            index++;
        }

        for (Object obj : func.getParams()) {
            if (index < 6) {
                objMap.put(obj, regParams.get(index));
                regObj.put(regParams.get(index), obj);
                dirty.add(obj);
                activeRegs.add(regParams.get(index));
                freeRegs.remove(regParams.get(index));
            } else {
                objAddr.put(obj, new OperandMem(RegisterSet.rbp, 8 * (cntParams - index + 1)));
            }
            index++;
        }

        /*int opCnt = 0;
        for (Object obj : func.getVarList()) {
            if (!(obj instanceof ObjectConstant)) {
                opCnt++;
            }
        }
        if (opCnt <= 8) {
            int i = 0;
            for (Object obj : func.getVarList()) {
                if (!(obj instanceof ObjectConstant)) {
                    objAddr.put(obj, regAvailable.get(i + 2));
                    freeRegs.remove(regAvailable.get(i + 2));
                    i++;
                }
            }
        }*/
    }

    void exitFunc() {
        int size = nasm.sectionText.items.size();
        for (int i = size - 1; i >= 0; i--) {
            SectionItem item = nasm.sectionText.items.get(i - 1);
            if (item instanceof InstructionMov) {
                if (((InstructionMov) item).dst == RegisterSet.rbp) {
                    ((InstructionSub) nasm.sectionText.items.get(i)).imm = 16 * ((stackSize + 1) / 2);
                    break;
                }
            }
        }
        objAddr.entrySet().removeIf(e -> !e.getKey().isGlobal());
        objMap.clear();
        regObj.clear();
    }

    void enterBlock(BasicBlock blk) {
    }

    void exitBlock(BasicBlock blk) {
        boolean isRet = false;
        Stack<SectionItem> stk = new Stack<SectionItem>();
        int size = nasm.sectionText.items.size();
        for (int i = size - 1; i >= 0; i--) {
            SectionItem lastItem = nasm.sectionText.items.get(i);
            if (lastItem instanceof InstructionRet) {
                isRet = true;
            }
            if (isRet && i < size - 3) {
                break;
            }
            if (!isRet && !(lastItem instanceof InstructionBaseJump)) {
                break;
            }
            nasm.sectionText.items.remove(i);
            stk.add(lastItem);
        }

        Iterator<OperandRegister> it = activeRegs.iterator();
        while (it.hasNext()) {
            OperandRegister reg = it.next();
            writeBack(reg, isRet, true);
            assert !freeRegs.contains(reg);
            freeRegs.add(reg);
            it.remove();
        }

        while (!stk.empty()) {
            SectionItem item = stk.pop();
            nasm.sectionText.items.add(item);
        }
        objMap.clear();
        regObj.clear();
        assert dirty.isEmpty();
    }

    void arrange_fixed_register(Inst inst) {
        if (inst instanceof InstAlloc) {
            freeze(RegisterSet.rdi);
            freeze(RegisterSet.rax);

            release_register(RegisterSet.rdi);
            release_register(RegisterSet.rax);
        } else if (inst instanceof InstBr) {
        } else if (inst instanceof InstCall) {
        } else if (inst instanceof InstLoad) {
        } else if (inst instanceof InstMov) {
        } else if (inst instanceof InstOffset) {
            freeze(RegisterSet.rax);
            release_register(RegisterSet.rax);
        } else if (inst instanceof InstRet) {
        } else if (inst instanceof InstStore) {
            freeze(RegisterSet.rax);
            release_register(RegisterSet.rax);
        }
        unfreeze_all();
    }

    private void freeze(OperandRegister reg) {
        frozenRegs.add(reg);
    }

    private void unfreeze(OperandRegister reg) {
        frozenRegs.remove(reg);
    }

    void unfreeze_all() {
        frozenRegs.clear();
    }

    private void release_register(OperandRegister reg) {
        if (!activeRegs.contains(reg)) {
            return;
        }

        OperandRegister dst = tryAllocate();
        if (dst != null) {
            addInstruction(new InstructionMov(dst, reg));
            Object obj = regObj.get(reg);
            objMap.put(obj, dst);
            regObj.put(dst, obj);
            regObj.remove(reg);
        } else {
            writeBack(reg, false, false);
        }
        activeRegs.remove(reg);
        freeRegs.add(reg);
    }

    void allocateGlobal(FuncExtra initFunc) {
        for (Object obj : initFunc.getDefinedVariables()) {
            nasm.sectionBSS.addItem(new InstructionResq("$" + obj.name));
            OperandMem operand = new OperandMem("$" + obj.name, 0);
            objAddr.put(obj, operand);
        }
    }

    void request(OperandRegister reg) {
        freeze(reg);
        release_register(reg);
    }

    void requestMov(OperandRegister dst, Object obj) {
        Operand now;
        if (obj instanceof ObjectInt) {
            now = new OperandInt(((ObjectInt) obj).value);
        } else if (obj instanceof ObjectBool) {
            now = new OperandInt(((ObjectBool) obj).value);
        } else if (obj instanceof ObjectNull) {
            now = new OperandInt(0);
        } else if (obj instanceof ObjectString) {
            OperandString operandStr = new OperandString(((ObjectString) obj).value);
            Label label = Label.newDB();
            nasm.sectionData.addItem(label);
            nasm.sectionData.addItem(new InstructionDB(operandStr));
            now = new OperandDBAddr(label);
        } else if (obj instanceof ObjectPtr) {
            now = null;
            assert false;
        } else {
            now = get(obj, dst);
            if (now == dst) {
                return;
            }
        }
        freeze(dst);
        release_register(dst);
        addInstruction(new InstructionMov(dst, now));
    }

    void requestMov(Object obj, OperandRegister src) {
        Operand dst;
        if (obj instanceof ObjectPtr) {
            dst = null;
            assert false;
        } else {
            freeze(src);
            dst = get(obj, src);
            if (dst != src) {
                addInstruction(new InstructionMov(dst, src));
            }
            unfreeze(src);
        }
    }

    void freeze_all() {
        for (OperandRegister reg : regAvailable) {
            freeze(reg);
        }
        for (OperandRegister reg : regAvailable) {
            release_register(reg);
        }
    }

    public void write(Operand dst) {
        Object obj = regObj.get(dst);
        if (obj == null) {
            return;
        }
        dirty.add(obj);
    }

    void addInstruction(SectionItem item) {
        if (item instanceof Instruction) {
            write(((Instruction) item).dst);
        }
        nasm.sectionText.addItem(item);
    }
}