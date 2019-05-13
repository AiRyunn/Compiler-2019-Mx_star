package com.github.espylapiza.compiler_mxstar.back_end;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionDB;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionResq;
import com.github.espylapiza.compiler_mxstar.nasm.Label;
import com.github.espylapiza.compiler_mxstar.nasm.NASM;
import com.github.espylapiza.compiler_mxstar.nasm.Operand;
import com.github.espylapiza.compiler_mxstar.nasm.OperandDBAddr;
import com.github.espylapiza.compiler_mxstar.nasm.OperandInt;
import com.github.espylapiza.compiler_mxstar.nasm.OperandMem;
import com.github.espylapiza.compiler_mxstar.nasm.OperandRegister;
import com.github.espylapiza.compiler_mxstar.nasm.OperandString;
import com.github.espylapiza.compiler_mxstar.nasm.RegisterSet;
import com.github.espylapiza.compiler_mxstar.pizza_ir.FuncExtra;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Inst;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Object;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectBool;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectConstant;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectInt;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectNull;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectString;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Scope;

// (8 * (top + 1) + 15) / 16 * 16

abstract class RegisterAllocator {
    abstract void cache(Object obj);

    abstract Operand get(Object obj);
}

class StaticRegisterAllocator extends RegisterAllocator {
    private Map<Object, Operand> maddr = new HashMap<Object, Operand>();
    private int stackSize;

    StaticRegisterAllocator() {
    }

    /**
     * @return the stackSize
     */
    public int getStackSize() {
        return stackSize;
    }

    void noneAllocate(NASM nasm, FuncExtra func) {
        int top = 0; // 1 base

        if (func.getOwnerClass() != null) {
            if (!exists(func.getVarList().get(0))) {
                Operand operand;
                operand = new OperandMem(RegisterSet.rbp, -8 * (++top));
                put(func.getVarList().get(0), operand);
            }
        }

        for (Object obj : func.getParams()) {
            if (!exists(obj)) {
                Operand operand;
                operand = new OperandMem(RegisterSet.rbp, -8 * (++top));
                put(obj, operand);
            }
        }

        for (Object obj : func.getVarList()) {
            if (!exists(obj)) {
                Operand operand;
                if (obj instanceof ObjectConstant) {
                    if (obj instanceof ObjectBool) {
                        operand = new OperandInt(((ObjectBool) obj).value);
                    } else if (obj instanceof ObjectInt) {
                        operand = new OperandInt(((ObjectInt) obj).value);
                    } else if (obj instanceof ObjectString) {
                        OperandString operandStr = new OperandString(((ObjectString) obj).value);
                        Label label = Label.newDB();
                        nasm.sectionData.addItem(label);
                        nasm.sectionData.addItem(new InstructionDB(operandStr));
                        operand = new OperandDBAddr(label);
                    } else if (obj instanceof ObjectNull) {
                        operand = new OperandInt(0);
                    } else {
                        assert false;
                        operand = null;
                    }
                } else {
                    operand = new OperandMem(RegisterSet.rbp, -8 * (++top));
                }
                put(obj, operand);
            }
        }

        for (Object obj : func.getVarList()) {
            if (get(obj) == null) {
                assert false;
            }
        }
        stackSize = (8 * top + 15) / 16 * 16;
    }

    void linearScanningAllocate(NASM nasm, FuncExtra func) {
        final List<OperandRegister> regTemp = Arrays.asList(/*RegisterSet.rdi, RegisterSet.rsi, RegisterSet.rax,*/
                RegisterSet.r10, RegisterSet.r11);
        final int cntRegTemp = 1;

        Map<Object, Interval> intervals = new HashMap<Object, Interval>();

        Integer lineNum = 0;

        if (func.getOwnerClass() != null) {
            Object obj = func.getVarList().get(0);
            if (!exists(obj)) {
                intervals.put(obj, new Interval(lineNum, lineNum, obj));
            }
        }

        for (Object obj : func.getParams()) {
            if (!exists(obj)) {
                intervals.put(obj, new Interval(lineNum, lineNum, obj));
            }
        }

        for (Scope scp : func.getScps()) {
            for (Inst inst : scp.getInsts()) {
                lineNum++;

                List<Object> objs = new ArrayList<Object>();
                for (Object obj : inst.getObjects()) {
                    objs.add(obj);
                }
                if (inst.dst != null) {
                    objs.add(inst.dst);
                }

                for (Object obj : objs) {
                    if (obj != null && obj.belong != null) {
                        if (obj instanceof ObjectConstant) {
                            continue;
                        }

                        if (!intervals.containsKey(obj)) {
                            intervals.put(obj, new Interval(lineNum, lineNum, obj));
                        }
                        intervals.get(obj).r = lineNum;
                    }
                }
            }
        }

        List<Interval> unhandledList = new ArrayList<Interval>();
        Interval[] active = new Interval[cntRegTemp];

        for (Map.Entry<Object, Interval> entry : intervals.entrySet()) {
            Interval interval = entry.getValue();
            System.out.println(interval.l + " " + interval.r + " " + interval.obj);

            unhandledList.add(interval);
        }

        unhandledList.sort((lhs, rhs) -> lhs.l.compareTo(rhs.l));

        for (Interval interval : unhandledList) {
            Integer curl = interval.l;

            for (int i = 0; i < cntRegTemp; i++) {
                if (active[i] != null && active[i].r < curl) {
                    active[i] = null;
                }
            }

            boolean spill = true;
            for (int i = 0; i < cntRegTemp; i++) {
                if (active[i] == null) {
                    active[i] = interval;
                    spill = false;

                    maddr.put(interval.obj, regTemp.get(i));
                    break;
                }
            }

            if (spill) {
                int maxpos = 0;
                for (int i = 0; i < cntRegTemp; i++) {
                    if (active[i].l > active[maxpos].l) {
                        maxpos = i;
                    }
                }
                if (active[maxpos].r > interval.r) {
                    maddr.remove(active[maxpos].obj);
                    active[maxpos] = interval;
                    maddr.put(interval.obj, regTemp.get(maxpos));
                }
            }
        }

        System.out.println("****{");
        for (Map.Entry<Object, Operand> entry : maddr.entrySet()) {
            System.out.println("---");
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
        System.out.println("}****");
        noneAllocate(nasm, func);
    }

    void simpleAllocate(NASM nasm, FuncExtra func) {
        Map<Object, Interval> intervals = new HashMap<Object, Interval>();

        Integer lineNum = 0;

        if (func.getOwnerClass() != null) {
            Object obj = func.getVarList().get(0);
            if (!exists(obj)) {
                intervals.put(obj, new Interval(-1, -1, obj));
            }
        }

        for (Object obj : func.getParams()) {
            if (!exists(obj)) {
                intervals.put(obj, new Interval(-1, -1, obj));
            }
        }

        for (Scope scp : func.getScps()) {
            for (Inst inst : scp.getInsts()) {
                lineNum++;

                List<Object> objs = new ArrayList<Object>();
                for (Object obj : inst.getObjects()) {
                    objs.add(obj);
                }
                if (inst.dst != null) {
                    objs.add(inst.dst);
                }

                for (Object obj : objs) {
                    if (obj != null && obj.belong != null) {
                        if (obj instanceof ObjectConstant) {
                            continue;
                        }

                        if (!intervals.containsKey(obj)) {
                            intervals.put(obj, new Interval(lineNum, lineNum, obj));
                        }
                        intervals.get(obj).r = lineNum;
                    }
                }
            }
        }

        for (Map.Entry<Object, Interval> entry : intervals.entrySet()) {
            Interval interval = entry.getValue();
            if (interval.l + 1 == interval.r) {
                maddr.put(interval.obj, RegisterSet.r10);
            }
        }

        noneAllocate(nasm, func);
    }

    public void bssAllocate(NASM nasm, FuncExtra initFunc) {
        for (Object obj : initFunc.getDefinedVariables()) {
            nasm.sectionBSS.addItem(new InstructionResq("$" + obj.name));
            Operand operand = new OperandMem("$" + obj.name, 0);
            put(obj, operand);
        }
    }

    private void put(Object object, Operand operand) {
        maddr.put(object, operand);
    }

    private boolean exists(Object obj) {
        return maddr.containsKey(obj);
    }

    @Override
    Operand get(Object obj) {
        Operand result = maddr.get(obj);
        return result;
    }

    @Override
    void cache(Object obj) {

    }
}

class Interval {
    Integer l, r;
    Object obj;

    Interval(Integer l, Integer r, Object obj) {
        this.l = l;
        this.r = r;
        this.obj = obj;
    }
}

class DynamicRegisterAllocator extends RegisterAllocator {
    NASM nasm;

    DynamicRegisterAllocator(NASM nasm) {
        this.nasm = nasm;
    }

    @Override
    void cache(Object obj) {
        // nasm.sectionText.addItem(new InstructionPush())

    }

    @Override
    Operand get(Object obj) {
        return null;
    }
}