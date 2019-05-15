package com.github.espylapiza.compiler_mxstar.back_end;

import java.util.Arrays;
import java.util.List;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionAdd;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionAnd;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionCall;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionCmp;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionCqo;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionDB;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionDec;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionIdiv;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionImul;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionInc;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionJmp;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionJz;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionMov;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionMovzx;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionNot;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionOr;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionPop;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionPush;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionRet;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionSar;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionSete;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionSetg;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionSetge;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionSetl;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionSetle;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionSetne;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionShl;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionSub;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionTest;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionXor;
import com.github.espylapiza.compiler_mxstar.nasm.Label;
import com.github.espylapiza.compiler_mxstar.nasm.NASM;
import com.github.espylapiza.compiler_mxstar.nasm.Operand;
import com.github.espylapiza.compiler_mxstar.nasm.OperandDBAddr;
import com.github.espylapiza.compiler_mxstar.nasm.OperandFuncAddr;
import com.github.espylapiza.compiler_mxstar.nasm.OperandInt;
import com.github.espylapiza.compiler_mxstar.nasm.OperandMem;
import com.github.espylapiza.compiler_mxstar.nasm.OperandRegister;
import com.github.espylapiza.compiler_mxstar.nasm.OperandRegister64Bit;
import com.github.espylapiza.compiler_mxstar.nasm.OperandString;
import com.github.espylapiza.compiler_mxstar.nasm.RegisterSet;
import com.github.espylapiza.compiler_mxstar.nasm.SectionItem;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Func;
import com.github.espylapiza.compiler_mxstar.pizza_ir.FuncAddr;
import com.github.espylapiza.compiler_mxstar.pizza_ir.FuncBuiltin;
import com.github.espylapiza.compiler_mxstar.pizza_ir.FuncExtern;
import com.github.espylapiza.compiler_mxstar.pizza_ir.FuncExtra;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Inst;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstAlloc;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstBr;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstCall;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstJump;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstLoad;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstMov;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstOffset;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstRet;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstStore;
import com.github.espylapiza.compiler_mxstar.pizza_ir.PizzaIRPartBaseVisitor;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Object;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectBool;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectInt;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectNull;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectPtr;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectString;
import com.github.espylapiza.compiler_mxstar.pizza_ir.PizzaIR;
import com.github.espylapiza.compiler_mxstar.pizza_ir.BasicBlock;
import com.github.espylapiza.compiler_mxstar.pizza_ir.BlockType;

public class PizzaIRVisitor extends PizzaIRPartBaseVisitor {
    private NASM nasm;

    private final List<OperandRegister> regParams = Arrays.asList(RegisterSet.rdi, RegisterSet.rsi, RegisterSet.rdx,
            RegisterSet.rcx, RegisterSet.r8, RegisterSet.r9);

    private GreedyRegisterAllocator allocator;

    PizzaIRVisitor(NASM nasm) {
        this.nasm = nasm;
        allocator = new GreedyRegisterAllocator(nasm);
    }

    @Override
    public void visit(PizzaIR ir) {
        FuncExtra initFunc = (FuncExtra) ir.funcList.get(FuncAddr.createGlobalFuncAddr("_init"));

        allocator.allocateGlobal(initFunc);

        for (Func func : ir.funcList) {
            if (func instanceof FuncExtern) {
                addDirectiveExtern(func.getAddr().toString());
                continue;
            }
            if (func instanceof FuncBuiltin) {
                continue;
            }

            beAccept((FuncExtra) func);
        }
    }

    @Override
    public void visit(FuncExtra func) {
        int stackSize = 0;

        allocator.enterFunc(func);

        for (BasicBlock blk : func.getBlocks()) {
            addInstruction(new Label(blk.getLabel()));
            allocator.enterBlock(blk);

            if (blk.getType() == BlockType.FUNC_ENTRANCE) {
                addInstruction(new InstructionPush(RegisterSet.rbp));
                addInstruction(new InstructionMov(RegisterSet.rbp, RegisterSet.rsp));
                addInstruction(new InstructionSub(RegisterSet.rsp, stackSize));
            }

            for (Inst inst : blk) {
                beAccept(inst);
                allocator.unfreeze_all();
            }

            allocator.exitBlock(blk);
        }

        allocator.exitFunc();
    }

    @Override
    public void visit(InstCall inst) {
        if (inst.func instanceof FuncBuiltin) {
            if (inst.params.count() == 0) {
                addBuiltin0(inst.func, inst.dst, inst.objThis);
            } else if (inst.params.count() == 1) {
                // unary operator
                Object obj = inst.params.get(0);
                addBuiltin1(inst.func, inst.dst, obj, inst.objThis);
            } else if (inst.params.count() == 2) {
                // binary operator
                addBuiltin2(inst.func, inst.dst, inst.params.get(0), inst.params.get(1), inst.objThis);
            } else {
                assert false;
            }
        } else {
            int cntPush = 0;
            int index = 0;
            if (inst.objThis != null) {
                Operand operand = getOperand(inst.objThis);
                allocator.request(regParams.get(index));
                addInstruction(new InstructionMov(regParams.get(index), operand));
                index++;
            }
            for (Object param : inst.params) {
                Operand operand = getOperand(param);
                if (index < 6) {
                    allocator.request(regParams.get(index));
                    addInstruction(new InstructionMov(regParams.get(index), operand));
                } else {
                    addInstruction(new InstructionPush(operand));
                    cntPush++;
                }
                index++;
            }
            allocator.freeze_all();
            addInstruction(new InstructionCall(new OperandFuncAddr(inst.func.getAddr().toString())));
            allocator.unfreeze_all();
            if (cntPush != 0) {
                addInstruction(new InstructionAdd(RegisterSet.rsp, 8 * cntPush));
            }
            if (inst.dst != null) {
                allocator.requestMov(inst.dst, RegisterSet.rax);
                // addInstruction(new InstructionMov(getOperand(inst.dst, RegisterSet.rax), RegisterSet.rax));
            }
        }
    }

    @Override
    public void visit(InstRet inst) {
        if (inst.obj != null) {
            allocator.requestMov(RegisterSet.rax, inst.obj);
        }
        addInstruction(new InstructionMov(RegisterSet.rsp, RegisterSet.rbp));
        addInstruction(new InstructionPop(RegisterSet.rbp));
        addInstruction(new InstructionRet());
    }

    @Override
    public void visit(InstMov inst) {
        Operand dst = getOperand(inst.dst), src = getOperand(inst.src);
        addMov(dst, src);
    }

    @Override
    public void visit(InstStore inst) {
        Operand dst = getOperand(inst.addr), src = getOperand(inst.src);
        if (src instanceof OperandMem) {
            allocator.request(RegisterSet.rax);
            OperandRegister src_reg = RegisterSet.rax;
            addInstruction(new InstructionMov(src_reg, src));
            src = src_reg;
        }
        if (dst instanceof OperandInt || dst instanceof OperandRegister) {
            addInstruction(new InstructionMov(new OperandMem((OperandRegister) dst, 0), src));
        } else {
            allocator.request(RegisterSet.rax);
            addInstruction(new InstructionMov(RegisterSet.rax, dst));
            addInstruction(new InstructionMov(new OperandMem(RegisterSet.rax, 0), src));
        }
    }

    @Override
    public void visit(InstBr inst) {
        Operand cdt = getOperand(inst.obj);
        // if (cdt instanceof OperandMem) {
        //     addInstruction(new InstructionMov(RegisterSet.rax, getOperand(inst.obj)));
        //     cdt = RegisterSet.rax;
        // }

        addInstruction(new InstructionTest(cdt, cdt));
        addInstruction(new InstructionJz(inst.scpIfFalse));
        addInstruction(new InstructionJmp(inst.scpIfTrue));
    }

    @Override
    public void visit(InstJump inst) {
        addInstruction(new InstructionJmp(inst.scp));
    }

    @Override
    public void visit(InstAlloc inst) {
        allocator.request(RegisterSet.rdi);
        addInstruction(new InstructionMov(RegisterSet.rdi, getOperand(inst.size)));
        addInstruction(new InstructionInc(RegisterSet.rdi));
        addInstruction(new InstructionShl(RegisterSet.rdi, 3));
        allocator.freeze_all();
        addInstruction(new InstructionCall(new OperandFuncAddr("malloc")));
        allocator.unfreeze_all();
        allocator.requestMov(inst.dst, RegisterSet.rax);
        // allocator.request(RegisterSet.rax);
        // addInstruction(new InstructionMov(getOperand(inst.dst), RegisterSet.rax));
    }

    @Override
    public void visit(InstOffset inst) {
        Operand src = getOperand(inst.src);
        Operand offset = getOperand(inst.offset);
        Operand dst = getOperand(inst.dst);
        addInstruction(new InstructionMov(dst, offset));
        addInstruction(new InstructionShl(dst, 3));
        addInstruction(new InstructionAdd(dst, src));
    }

    @Override
    public void visit(InstLoad inst) {
        OperandRegister src = (OperandRegister) getOperand(inst.src);
        Operand dst = getOperand(inst.dst);
        addInstruction(new InstructionMov(dst, new OperandMem(src, 0)));
    }

    @Override
    public void visit(Inst inst) {
        if (inst instanceof Inst) {
            System.out.println(inst.getClass());
            assert false;
        }
    }

    @Override
    public void visit(BasicBlock scope) {
        System.out.println("Displaying Inst.");
    }

    private void addBuiltin0(Func func, Object dst, Object objThis) {
        if (dst == null) {
            return;
        }
        switch (func.getAddr().toString()) {
        case "_MS_string.parseInt": {
            allocator.requestMov(RegisterSet.rdi, objThis);
            allocator.freeze_all();
            addInstruction(new InstructionCall(new OperandFuncAddr("_string_parseInt")));
            allocator.unfreeze_all();
            allocator.requestMov(dst, RegisterSet.rax);
            break;
        }
        case "_MS_string.length": {
            // Ref: https://stackoverflow.com/questions/11177137/why-do-x86-64-instructions-on-32-bit-registers-zero-the-upper-part-of-the-full-6
            Operand opThis = getOperand(objThis);
            addInstruction(new InstructionMov(RegisterSet.trans64_32((OperandRegister64Bit) getOperand(dst)),
                    new OperandMem((OperandRegister) opThis, 0, 32)));
            break;
        }
        case "_MS___array__.size": {
            Operand opThis = getOperand(objThis);
            Operand opDst = getOperand(dst);
            addInstruction(new InstructionMov(opDst, opThis));
            addInstruction(new InstructionMov(opDst, new OperandMem((OperandRegister) opDst, 0)));
            break;
        }
        default:
            System.out.println(func.getName());
            assert false;
        }
    }

    private void addBuiltin1(Func func, Object dst, Object obj, Object objThis) {
        if (dst == null) {
            switch (func.getAddr().toString()) {
            case "_MS_int.__preinc__":
            case "_MS_int.__postinc__":
                addInstruction(new InstructionInc(getOperand(obj)));
                break;
            case "_MS_int.__predec__":
            case "_MS_int.__postdec__":
                addInstruction(new InstructionDec(getOperand(obj)));
                break;
            }
            return;
        }

        switch (func.getAddr().toString()) {
        case "_MS_string.ord": {
            allocator.requestMov(regParams.get(0), objThis);
            allocator.requestMov(regParams.get(1), obj);
            allocator.freeze_all();
            addInstruction(new InstructionCall(new OperandFuncAddr("_string_ord")));
            allocator.unfreeze_all();
            allocator.requestMov(dst, RegisterSet.rax);
            break;
        }
        case "_MS_bool.__lgcnot__": {
            allocator.request(RegisterSet.rax);
            Operand op = getOperand(obj);
            Operand opDst = getOperand(dst);
            if (op instanceof OperandInt) {
                addInstruction(new InstructionMov(opDst, new OperandInt(((OperandInt) op).getValue() == 0 ? 1 : 0)));
            } else {
                if (op instanceof OperandMem) {
                    addInstruction(new InstructionMov(RegisterSet.rcx, op));
                    op = RegisterSet.rcx;
                }
                addInstruction(new InstructionTest(op, op));
                addInstruction(new InstructionSete(RegisterSet.al));
                addInstruction(new InstructionMovzx(RegisterSet.rax, RegisterSet.al));
                addInstruction(new InstructionMov(opDst, RegisterSet.rax));
            }
            break;
        }
        case "_MS_int.__pos__":
            break;
        case "_MS_int.__neg__": {
            Operand op = getOperand(obj);
            Operand opDst = getOperand(dst);
            addInstruction(new InstructionXor(opDst, opDst));
            addInstruction(new InstructionSub(opDst, op));
            break;
        }
        case "_MS_int.__bitinv__": {
            Operand op = getOperand(obj);
            Operand opDst = getOperand(dst);
            addInstruction(new InstructionMov(opDst, op));
            addInstruction(new InstructionNot(opDst));
            break;
        }
        case "_MS_int.__preinc__": {
            Operand op = getOperand(obj);
            Operand opDst = getOperand(dst);
            addInstruction(new InstructionInc(op));
            addInstruction(new InstructionMov(opDst, op));
            break;
        }
        case "_MS_int.__predec__": {
            Operand op = getOperand(obj);
            Operand opDst = getOperand(dst);
            addInstruction(new InstructionDec(op));
            addInstruction(new InstructionMov(opDst, op));
            break;
        }
        case "_MS_int.__postinc__": {
            Operand op = getOperand(obj);
            Operand opDst = getOperand(dst);
            addInstruction(new InstructionMov(opDst, op));
            addInstruction(new InstructionInc(op));
            break;
        }
        case "_MS_int.__postdec__": {
            Operand op = getOperand(obj);
            Operand opDst = getOperand(dst);
            addInstruction(new InstructionMov(opDst, op));
            addInstruction(new InstructionDec(op));
            break;
        }
        default:
            assert false;
        }
    }

    private void addBuiltin2(Func func, Object dst, Object lhs, Object rhs, Object opThis) {
        if (dst == null) {
            return;
        }
        if (lhs == null || rhs == null) {
            assert false;
        }
        switch (func.getAddr().toString()) {
        case "_MS_addrEq":
            allocator.request(RegisterSet.rax);
            addInstruction(new InstructionMov(RegisterSet.rax, getOperand(lhs)));
            addInstruction(new InstructionCmp(RegisterSet.rax, getOperand(rhs)));
            addInstruction(new InstructionSete(RegisterSet.al));
            addInstruction(new InstructionMovzx(RegisterSet.rax, RegisterSet.al));
            addInstruction(new InstructionMov(getOperand(dst), RegisterSet.rax));
            break;
        case "_MS_addrNe":
            allocator.request(RegisterSet.rax);
            addInstruction(new InstructionMov(RegisterSet.rax, getOperand(lhs)));
            addInstruction(new InstructionCmp(RegisterSet.rax, getOperand(rhs)));
            addInstruction(new InstructionSetne(RegisterSet.al));
            addInstruction(new InstructionMovzx(RegisterSet.rax, RegisterSet.al));
            addInstruction(new InstructionMov(getOperand(dst), RegisterSet.rax));
            break;
        case "_MS_string.substring":
            allocator.request(RegisterSet.rax);
            addInstruction(new InstructionMov(regParams.get(0), getOperand(opThis)));
            addInstruction(new InstructionMov(regParams.get(1), getOperand(lhs)));
            addInstruction(new InstructionMov(regParams.get(2), getOperand(rhs)));
            allocator.freeze_all();
            addInstruction(new InstructionCall(new OperandFuncAddr("_string_substring")));
            allocator.unfreeze_all();
            allocator.requestMov(dst, RegisterSet.rax);
            // addInstruction(new InstructionMov(getOperand(dst), RegisterSet.rax));
            break;
        case "_MS_string.__add__":
            allocator.request(RegisterSet.rax);
            allocator.request(RegisterSet.rdi);
            allocator.request(RegisterSet.rsi);
            addInstruction(new InstructionMov(RegisterSet.rdi, getOperand(lhs)));
            addInstruction(new InstructionMov(RegisterSet.rsi, getOperand(rhs)));
            allocator.freeze_all();
            addInstruction(new InstructionCall(new OperandFuncAddr("_string___add__")));
            allocator.unfreeze_all();
            allocator.requestMov(dst, RegisterSet.rax);
            // addInstruction(new InstructionMov(getOperand(dst), RegisterSet.rax));
            break;
        case "_MS_string.__eq__":
            allocator.request(RegisterSet.rax);
            allocator.request(RegisterSet.rdi);
            allocator.request(RegisterSet.rsi);
            addInstruction(new InstructionMov(RegisterSet.rdi, getOperand(lhs)));
            addInstruction(new InstructionMov(RegisterSet.rsi, getOperand(rhs)));
            addInstruction(new InstructionCall(new OperandFuncAddr("_strcmp")));
            allocator.requestMov(dst, RegisterSet.rax);
            // addInstruction(new InstructionMov(getOperand(dst), RegisterSet.rax));
            break;
        case "_MS_string.__ne__":
            allocator.request(RegisterSet.rax);
            allocator.request(RegisterSet.rdi);
            allocator.request(RegisterSet.rsi);
            addInstruction(new InstructionMov(RegisterSet.rdi, getOperand(lhs)));
            addInstruction(new InstructionMov(RegisterSet.rsi, getOperand(rhs)));
            addInstruction(new InstructionCall(new OperandFuncAddr("_strcmp")));
            addInstruction(new InstructionTest(RegisterSet.rax, RegisterSet.rax));
            addInstruction(new InstructionSetne(RegisterSet.al));
            addInstruction(new InstructionMovzx(RegisterSet.rax, RegisterSet.al));
            allocator.requestMov(dst, RegisterSet.rax);
            // addInstruction(new InstructionMov(getOperand(dst), RegisterSet.rax));
            break;
        case "_MS_string.__lt__":
            allocator.request(RegisterSet.rax);
            allocator.request(RegisterSet.rdi);
            allocator.request(RegisterSet.rsi);
            addInstruction(new InstructionMov(RegisterSet.rdi, getOperand(lhs)));
            addInstruction(new InstructionMov(RegisterSet.rsi, getOperand(rhs)));
            addInstruction(new InstructionCall(new OperandFuncAddr("_strcmp")));
            addInstruction(new InstructionCmp(RegisterSet.rax, new OperandInt(0)));
            addInstruction(new InstructionSetl(RegisterSet.al));
            addInstruction(new InstructionMovzx(RegisterSet.rax, RegisterSet.al));
            allocator.requestMov(dst, RegisterSet.rax);
            // addInstruction(new InstructionMov(getOperand(dst), RegisterSet.rax));
            break;
        case "_MS_string.__gt__":
            allocator.request(RegisterSet.rax);
            allocator.request(RegisterSet.rdi);
            allocator.request(RegisterSet.rsi);
            addInstruction(new InstructionMov(RegisterSet.rdi, getOperand(lhs)));
            addInstruction(new InstructionMov(RegisterSet.rsi, getOperand(rhs)));
            addInstruction(new InstructionCall(new OperandFuncAddr("_strcmp")));
            addInstruction(new InstructionCmp(RegisterSet.rax, new OperandInt(0)));
            addInstruction(new InstructionSetg(RegisterSet.al));
            addInstruction(new InstructionMovzx(RegisterSet.rax, RegisterSet.al));
            allocator.requestMov(dst, RegisterSet.rax);
            // addInstruction(new InstructionMov(getOperand(dst), RegisterSet.rax));
            break;
        case "_MS_string.__le__":
            allocator.request(RegisterSet.rax);
            allocator.request(RegisterSet.rdi);
            allocator.request(RegisterSet.rsi);
            addInstruction(new InstructionMov(RegisterSet.rdi, getOperand(lhs)));
            addInstruction(new InstructionMov(RegisterSet.rsi, getOperand(rhs)));
            addInstruction(new InstructionCall(new OperandFuncAddr("_strcmp")));
            addInstruction(new InstructionCmp(RegisterSet.rax, new OperandInt(0)));
            addInstruction(new InstructionSetle(RegisterSet.al));
            addInstruction(new InstructionMovzx(RegisterSet.rax, RegisterSet.al));
            allocator.requestMov(dst, RegisterSet.rax);
            // addInstruction(new InstructionMov(getOperand(dst), RegisterSet.rax));
            break;
        case "_MS_string.__ge__":
            allocator.request(RegisterSet.rax);
            allocator.request(RegisterSet.rdi);
            allocator.request(RegisterSet.rsi);
            addInstruction(new InstructionMov(RegisterSet.rdi, getOperand(lhs)));
            addInstruction(new InstructionMov(RegisterSet.rsi, getOperand(rhs)));
            addInstruction(new InstructionCall(new OperandFuncAddr("_strcmp")));
            addInstruction(new InstructionCmp(RegisterSet.rax, new OperandInt(0)));
            addInstruction(new InstructionSetge(RegisterSet.al));
            addInstruction(new InstructionMovzx(RegisterSet.rax, RegisterSet.al));
            allocator.requestMov(dst, RegisterSet.rax);
            // addInstruction(new InstructionMov(getOperand(dst), RegisterSet.rax));
            break;
        case "_MS_bool.__lgcand__":
            addInstruction(new InstructionMov(getOperand(dst), getOperand(lhs)));
            addInstruction(new InstructionAnd(getOperand(dst), getOperand(rhs)));
            break;
        case "_MS_bool.__lgcor__":
            addInstruction(new InstructionMov(getOperand(dst), getOperand(lhs)));
            addInstruction(new InstructionOr(getOperand(dst), getOperand(rhs)));
            break;
        case "_MS_bool.__eq__":
            allocator.request(RegisterSet.rax);
            addInstruction(new InstructionMov(RegisterSet.rax, getOperand(lhs)));
            addInstruction(new InstructionCmp(RegisterSet.rax, getOperand(rhs)));
            addInstruction(new InstructionSete(RegisterSet.al));
            addInstruction(new InstructionMov(getOperand(dst), RegisterSet.rax));
            break;
        case "_MS_bool.__ne__":
            allocator.request(RegisterSet.rax);
            addInstruction(new InstructionMov(RegisterSet.rax, getOperand(lhs)));
            addInstruction(new InstructionCmp(RegisterSet.rax, getOperand(rhs)));
            addInstruction(new InstructionSetne(RegisterSet.al));
            addInstruction(new InstructionMov(getOperand(dst), RegisterSet.rax));
            break;
        case "_MS_int.__add__":
            addInstruction(new InstructionMov(getOperand(dst), getOperand(lhs)));
            addInstruction(new InstructionAdd(getOperand(dst), getOperand(rhs)));
            break;
        case "_MS_int.__sub__":
            addInstruction(new InstructionMov(getOperand(dst), getOperand(lhs)));
            addInstruction(new InstructionSub(getOperand(dst), getOperand(rhs)));
            break;
        case "_MS_int.__mul__":
            addInstruction(new InstructionMov(getOperand(dst), getOperand(lhs)));
            addInstruction(new InstructionImul(getOperand(dst), getOperand(rhs)));
            break;
        case "_MS_int.__div__": {
            allocator.request(RegisterSet.rax);
            allocator.request(RegisterSet.rdx);
            Operand opRhs = getOperand(rhs);
            if (opRhs instanceof OperandInt) {
                allocator.request(RegisterSet.rcx);
                addInstruction(new InstructionMov(RegisterSet.rcx, opRhs));
                opRhs = RegisterSet.rcx;
            }
            addInstruction(new InstructionMov(RegisterSet.rax, getOperand(lhs)));
            addInstruction(new InstructionCqo());
            addInstruction(new InstructionIdiv(opRhs));
            addInstruction(new InstructionMov(getOperand(dst), RegisterSet.rax));
            break;
        }
        case "_MS_int.__mod__": {
            allocator.request(RegisterSet.rax);
            allocator.request(RegisterSet.rdx);
            Operand opRhs = getOperand(rhs);
            if (opRhs instanceof OperandInt) {
                allocator.request(RegisterSet.rcx);
                addInstruction(new InstructionMov(RegisterSet.rcx, opRhs));
                opRhs = RegisterSet.rcx;
            }
            System.out.println("rhs: " + rhs);
            System.out.println("opRhs: " + opRhs);
            addInstruction(new InstructionMov(RegisterSet.rax, getOperand(lhs)));
            addInstruction(new InstructionCqo());
            addInstruction(new InstructionIdiv(opRhs));
            addInstruction(new InstructionMov(getOperand(dst), RegisterSet.rdx));
            break;
        }
        case "_MS_int.__shl__":
            allocator.request(RegisterSet.rcx);
            addInstruction(new InstructionMov(getOperand(dst), getOperand(lhs)));
            addInstruction(new InstructionMov(RegisterSet.rcx, getOperand(rhs)));
            addInstruction(new InstructionShl(getOperand(dst), RegisterSet.cl));
            break;
        case "_MS_int.__shr__":
            allocator.request(RegisterSet.rcx);
            addInstruction(new InstructionMov(getOperand(dst), getOperand(lhs)));
            addInstruction(new InstructionMov(RegisterSet.rcx, getOperand(rhs)));
            addInstruction(new InstructionSar(getOperand(dst), RegisterSet.cl));
            break;
        case "_MS_int.__bitand__":
            addInstruction(new InstructionMov(getOperand(dst), getOperand(lhs)));
            addInstruction(new InstructionAnd(getOperand(dst), getOperand(rhs)));
            break;
        case "_MS_int.__bitxor__":
            addInstruction(new InstructionMov(getOperand(dst), getOperand(lhs)));
            addInstruction(new InstructionXor(getOperand(dst), getOperand(rhs)));
            break;
        case "_MS_int.__bitor__":
            addInstruction(new InstructionMov(getOperand(dst), getOperand(lhs)));
            addInstruction(new InstructionOr(getOperand(dst), getOperand(rhs)));
            break;
        case "_MS_int.__lt__": {
            allocator.request(RegisterSet.rax);
            Operand opLhs = getOperand(lhs), opRhs = getOperand(rhs);
            if (!(opLhs instanceof OperandInt) && (opRhs instanceof OperandInt || opRhs instanceof OperandRegister)) {
                addInstruction(new InstructionCmp(opLhs, opRhs));
            } else {
                addInstruction(new InstructionMov(RegisterSet.rax, opLhs));
                addInstruction(new InstructionCmp(RegisterSet.rax, opRhs));
            }
            addInstruction(new InstructionSetl(RegisterSet.al));
            addInstruction(new InstructionMovzx(RegisterSet.rax, RegisterSet.al));
            addInstruction(new InstructionMov(getOperand(dst), RegisterSet.rax));
            break;
        }
        case "_MS_int.__gt__": {
            allocator.request(RegisterSet.rax);
            Operand opLhs = getOperand(lhs), opRhs = getOperand(rhs);
            if (!(opLhs instanceof OperandInt) && (opRhs instanceof OperandInt || opRhs instanceof OperandRegister)) {
                addInstruction(new InstructionCmp(opLhs, opRhs));
            } else {
                addInstruction(new InstructionMov(RegisterSet.rax, opLhs));
                addInstruction(new InstructionCmp(RegisterSet.rax, opRhs));
            }
            addInstruction(new InstructionSetg(RegisterSet.al));
            addInstruction(new InstructionMovzx(RegisterSet.rax, RegisterSet.al));
            addInstruction(new InstructionMov(getOperand(dst), RegisterSet.rax));
            break;
        }
        case "_MS_int.__le__": {
            allocator.request(RegisterSet.rax);
            Operand opLhs = getOperand(lhs), opRhs = getOperand(rhs);
            if (!(opLhs instanceof OperandInt) && (opRhs instanceof OperandInt || opRhs instanceof OperandRegister)) {
                addInstruction(new InstructionCmp(opLhs, opRhs));
            } else {
                addInstruction(new InstructionMov(RegisterSet.rax, opLhs));
                addInstruction(new InstructionCmp(RegisterSet.rax, opRhs));
            }
            addInstruction(new InstructionSetle(RegisterSet.al));
            addInstruction(new InstructionMovzx(RegisterSet.rax, RegisterSet.al));
            addInstruction(new InstructionMov(getOperand(dst), RegisterSet.rax));
            break;
        }
        case "_MS_int.__ge__": {
            allocator.request(RegisterSet.rax);
            Operand opLhs = getOperand(lhs), opRhs = getOperand(rhs);
            if (!(opLhs instanceof OperandInt) && (opRhs instanceof OperandInt || opRhs instanceof OperandRegister)) {
                addInstruction(new InstructionCmp(opLhs, opRhs));
            } else {
                addInstruction(new InstructionMov(RegisterSet.rax, opLhs));
                addInstruction(new InstructionCmp(RegisterSet.rax, opRhs));
            }
            addInstruction(new InstructionSetge(RegisterSet.al));
            addInstruction(new InstructionMovzx(RegisterSet.rax, RegisterSet.al));
            addInstruction(new InstructionMov(getOperand(dst), RegisterSet.rax));
            break;
        }
        case "_MS_int.__eq__": {
            allocator.request(RegisterSet.rax);
            Operand opLhs = getOperand(lhs), opRhs = getOperand(rhs);
            if (!(opLhs instanceof OperandInt) && (opRhs instanceof OperandInt || opRhs instanceof OperandRegister)) {
                addInstruction(new InstructionCmp(opLhs, opRhs));
            } else {
                addInstruction(new InstructionMov(RegisterSet.rax, opLhs));
                addInstruction(new InstructionCmp(RegisterSet.rax, opRhs));
            }
            addInstruction(new InstructionSete(RegisterSet.al));
            addInstruction(new InstructionMovzx(RegisterSet.rax, RegisterSet.al));
            addInstruction(new InstructionMov(getOperand(dst), RegisterSet.rax));
            break;
        }
        case "_MS_int.__ne__": {
            allocator.request(RegisterSet.rax);
            Operand opLhs = getOperand(lhs), opRhs = getOperand(rhs);
            if (!(opLhs instanceof OperandInt) && (opRhs instanceof OperandInt || opRhs instanceof OperandRegister)) {
                addInstruction(new InstructionCmp(opLhs, opRhs));
            } else {
                addInstruction(new InstructionMov(RegisterSet.rax, opLhs));
                addInstruction(new InstructionCmp(RegisterSet.rax, opRhs));
            }
            addInstruction(new InstructionSetne(RegisterSet.al));
            addInstruction(new InstructionMovzx(RegisterSet.rax, RegisterSet.al));
            addInstruction(new InstructionMov(getOperand(dst), RegisterSet.rax));
            break;
        }
        default:
            assert false;
        }
    }

    private void addDirectiveExtern(String strFunc) {
        // nasm.addDirective(new DirectiveExtern(strFunc));
    }

    void addInstruction(SectionItem item) {
        nasm.sectionText.addItem(item);
    }

    Operand getOperand(Object obj, OperandRegister prefer) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof ObjectInt) {
            return new OperandInt(((ObjectInt) obj).value);
        } else if (obj instanceof ObjectBool) {
            return new OperandInt(((ObjectBool) obj).value);
        } else if (obj instanceof ObjectNull) {
            return new OperandInt(0);
        } else if (obj instanceof ObjectString) {
            OperandString operandStr = new OperandString(((ObjectString) obj).value);
            Label label = Label.newDB();
            nasm.sectionData.addItem(label);
            nasm.sectionData.addItem(new InstructionDB(operandStr));
            return new OperandDBAddr(label);
        } else if (obj instanceof ObjectPtr) {
            Operand op = getOperand(((ObjectPtr) obj).obj);
            addInstruction(new InstructionMov(op, getOperand(((ObjectPtr) obj).obj)));
            return new OperandMem((OperandRegister) op, 0);
        } else {
            Operand result = allocator.get(obj, prefer);
            if (result == null) {
                assert false;
            }
            System.out.println("!!" + result);
            return result;
        }
    }

    Operand getOperand(Object obj) {
        return getOperand(obj, null);
    }

    private void addMov(Operand dst, Operand src) {
        if (dst.equals(src)) {
            return;
        }
        if (src instanceof OperandInt || src instanceof OperandRegister || dst instanceof OperandRegister) {
            addInstruction(new InstructionMov(dst, src));
        } else {
            addInstruction(new InstructionMov(RegisterSet.rax, src));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
        }
    }
}
