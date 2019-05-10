package com.github.espylapiza.compiler_mxstar.back_end;

import java.util.Arrays;
import java.util.List;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionAdd;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionAnd;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionCall;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionCmp;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionCqo;
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
import com.github.espylapiza.compiler_mxstar.nasm.OperandFuncAddr;
import com.github.espylapiza.compiler_mxstar.nasm.OperandInt;
import com.github.espylapiza.compiler_mxstar.nasm.OperandMem;
import com.github.espylapiza.compiler_mxstar.nasm.OperandRegister;
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
import com.github.espylapiza.compiler_mxstar.pizza_ir.PizzaIR;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Scope;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ScopeType;

public class PizzaIRVisitor extends PizzaIRPartBaseVisitor {
    private NASM nasm;

    private final List<OperandRegister> regParams = Arrays.asList(RegisterSet.rdi, RegisterSet.rsi, RegisterSet.rdx,
            RegisterSet.rcx, RegisterSet.r8, RegisterSet.r9);

    private RegisterAllocator allocatorGlobal = new RegisterAllocator();
    private RegisterAllocator allocator;

    PizzaIRVisitor(NASM nasm) {
        this.nasm = nasm;
    }

    @Override
    public void visit(PizzaIR ir) {
        FuncExtra initFunc = (FuncExtra) ir.funcList.get(FuncAddr.createGlobalFuncAddr("_init"));

        allocatorGlobal.bssAllocate(nasm, initFunc);

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
        allocator = allocatorGlobal;
        allocator.naiveAllocate(nasm, func);

        int stackSize = allocator.getStackSize();
        int index;
        for (Scope scp : func.getScps()) {
            addInstruction(new Label(scp.getLabel()));
            if (scp.getType() == ScopeType.FUNC) {
                // enter the func
                addInstruction(new InstructionPush(RegisterSet.rbp));
                addInstruction(new InstructionMov(RegisterSet.rbp, RegisterSet.rsp));
                addInstruction(new InstructionSub(RegisterSet.rsp, stackSize));

                index = 0;
                if (func.getOwnerClass() != null) {
                    Operand operand = getOperand(func.getVarList().get(0));
                    addInstruction(new InstructionMov(operand, regParams.get(index)));
                    index++;
                }

                for (Object obj : func.getParams()) {
                    if (index < 6) {
                        Operand operand = getOperand(obj);
                        addInstruction(new InstructionMov(operand, regParams.get(index)));
                    }
                    index++;
                }
            }
            for (Inst inst : scp) {
                beAccept(inst);
            }
        }
    }

    @Override
    public void visit(InstCall inst) {
        if (inst.func instanceof FuncBuiltin) {
            if (inst.params.count() == 0) {
                addBuiltin0(inst.func, getOperand(inst.dst), getOperand(inst.objThis));
            } else if (inst.params.count() == 1) {
                // unary operator
                Object obj = inst.params.get(0);
                if (obj instanceof ObjectPtr) {
                    addInstruction(new InstructionMov(RegisterSet.r10, getOperand(((ObjectPtr) obj).obj)));
                    addBuiltin1(inst.func, getOperand(inst.dst), new OperandMem(RegisterSet.r10, 0),
                            getOperand(inst.objThis));
                } else {
                    addBuiltin1(inst.func, getOperand(inst.dst), getOperand(obj), getOperand(inst.objThis));
                }
            } else if (inst.params.count() == 2) {
                // binary operator
                addBuiltin2(inst.func, getOperand(inst.dst), getOperand(inst.params.get(0)),
                        getOperand(inst.params.get(1)), getOperand(inst.objThis));
            } else {
                assert false;
            }
        } else {
            int cntPush = 0;
            int index = 0;
            if (inst.objThis != null) {
                Operand operand = getOperand(inst.objThis);
                addInstruction(new InstructionMov(regParams.get(index), operand));
                index++;
            }
            for (Object param : inst.params) {
                Operand operand = getOperand(param);
                if (index < 6) {
                    addInstruction(new InstructionMov(regParams.get(index), operand));
                } else {
                    addInstruction(new InstructionPush(operand));
                    cntPush++;
                }
                index++;
            }
            addInstruction(new InstructionCall(new OperandFuncAddr(inst.func.getAddr().toString())));
            if (cntPush != 0) {
                addInstruction(new InstructionAdd(RegisterSet.rsp, 8 * cntPush));
            }
            if (inst.dst != null) {
                addInstruction(new InstructionMov(getOperand(inst.dst), RegisterSet.rax));
            }
        }
    }

    @Override
    public void visit(InstRet inst) {
        if (inst.obj != null) {
            addInstruction(new InstructionMov(RegisterSet.rax, getOperand(inst.obj)));
        }
        addInstruction(new InstructionMov(RegisterSet.rsp, RegisterSet.rbp));
        addInstruction(new InstructionPop(RegisterSet.rbp));
        addInstruction(new InstructionRet());
    }

    @Override
    public void visit(InstMov inst) {
        Operand dst = getOperand(inst.dst), src = getOperand(inst.src);
        if (dst instanceof OperandRegister) {
            addInstruction(new InstructionMov(dst, src));
        } else {
            addInstruction(new InstructionMov(RegisterSet.rax, src));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
        }
    }

    @Override
    public void visit(InstStore inst) {
        Operand dst = getOperand(inst.dst), src = getOperand(inst.src);
        if (src instanceof OperandMem) {
            OperandRegister src_reg = RegisterSet.rdx;
            addInstruction(new InstructionMov(src_reg, src));
            src = src_reg;
        }
        if (dst instanceof OperandRegister) {
            addInstruction(new InstructionMov(new OperandMem((OperandRegister) dst, 0), src));
        } else {
            addInstruction(new InstructionMov(RegisterSet.rax, dst));
            addInstruction(new InstructionMov(new OperandMem(RegisterSet.rax, 0), src));
        }
    }

    @Override
    public void visit(InstBr inst) {
        addInstruction(new InstructionMov(RegisterSet.rax, getOperand(inst.obj)));
        addInstruction(new InstructionTest(RegisterSet.rax, RegisterSet.rax));
        addInstruction(new InstructionJz(inst.scpIfFalse));
        addInstruction(new InstructionJmp(inst.scpIfTrue));
    }

    @Override
    public void visit(InstJump inst) {
        addInstruction(new InstructionJmp(inst.scp));
    }

    @Override
    public void visit(InstAlloc inst) {
        addInstruction(new InstructionMov(RegisterSet.rdi, getOperand(inst.size)));
        addInstruction(new InstructionInc(RegisterSet.rdi));
        addInstruction(new InstructionImul(RegisterSet.rdi, 8));
        addInstruction(new InstructionCall(new OperandFuncAddr("malloc")));
        addInstruction(new InstructionMov(getOperand(inst.dst), RegisterSet.rax));
    }

    @Override
    public void visit(InstOffset inst) {
        addInstruction(new InstructionMov(RegisterSet.rdx, getOperand(inst.offset)));
        addInstruction(new InstructionImul(RegisterSet.rdx, 8));
        addInstruction(new InstructionMov(RegisterSet.rax, getOperand(inst.src)));
        addInstruction(new InstructionAdd(RegisterSet.rax, RegisterSet.rdx));
        addInstruction(new InstructionMov(getOperand(inst.dst), RegisterSet.rax));
    }

    @Override
    public void visit(InstLoad inst) {
        addInstruction(new InstructionMov(RegisterSet.rax, getOperand(inst.src)));
        addInstruction(new InstructionMov(RegisterSet.rax, new OperandMem(RegisterSet.rax, 0)));
        addInstruction(new InstructionMov(getOperand(inst.dst), RegisterSet.rax));
    }

    @Override
    public void visit(Inst inst) {
        if (inst instanceof Inst) {
            System.out.println(inst.getClass());
            assert false;
        }
    }

    @Override
    public void visit(Scope scope) {
        System.out.println("Displaying Inst.");
    }

    private void addBuiltin0(Func func, Operand dst, Operand opThis) {
        switch (func.getAddr().toString()) {
        case "_MS_string.parseInt":
            addInstruction(new InstructionMov(RegisterSet.rdi, opThis));
            addInstruction(new InstructionCall(new OperandFuncAddr("_string_parseInt")));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        case "_MS_string.length":
            addInstruction(new InstructionMov(RegisterSet.rax, opThis));
            addInstruction(new InstructionMov(RegisterSet.rax, new OperandMem(RegisterSet.rax, 0)));
            addInstruction(new InstructionMov(RegisterSet.rcx, new OperandInt(0)));
            addInstruction(new InstructionMov(RegisterSet.ecx, RegisterSet.eax));
            addInstruction(new InstructionMov(dst, RegisterSet.rcx));
            break;
        case "_MS___array__.size":
            addInstruction(new InstructionMov(RegisterSet.rax, opThis));
            addInstruction(new InstructionMov(RegisterSet.rax, new OperandMem(RegisterSet.rax, 0)));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        default:
            System.out.println(func.getName());
            assert false;
        }
    }

    private void addBuiltin1(Func func, Operand dst, Operand op, Operand opThis) {
        switch (func.getAddr().toString()) {
        case "_MS_string.ord":
            addInstruction(new InstructionMov(regParams.get(0), opThis));
            addInstruction(new InstructionMov(regParams.get(1), op));
            addInstruction(new InstructionCall(new OperandFuncAddr("_string_ord")));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        case "_MS_bool.__lgcnot__":
            if (op instanceof OperandInt) {
                addInstruction(new InstructionMov(dst, new OperandInt(((OperandInt) op).getValue() == 0 ? 1 : 0)));
            } else {
                if (op instanceof OperandMem) {
                    addInstruction(new InstructionMov(RegisterSet.rcx, op));
                    op = RegisterSet.rcx;
                }
                addInstruction(new InstructionTest(op, op));
                addInstruction(new InstructionSete(RegisterSet.al));
                addInstruction(new InstructionMovzx(RegisterSet.rax, RegisterSet.al));
                addInstruction(new InstructionMov(dst, RegisterSet.rax));
            }
            break;
        case "_MS_int.__pos__":
            break;
        case "_MS_int.__neg__":
            addInstruction(new InstructionXor(RegisterSet.rax, RegisterSet.rax));
            addInstruction(new InstructionSub(RegisterSet.rax, op));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        case "_MS_int.__bitinv__":
            addInstruction(new InstructionMov(RegisterSet.rax, op));
            addInstruction(new InstructionNot(RegisterSet.rax));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        case "_MS_int.__preinc__":
            addInstruction(new InstructionInc(op));
            addInstruction(new InstructionMov(RegisterSet.rax, op));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        case "_MS_int.__predec__":
            addInstruction(new InstructionDec(op));
            addInstruction(new InstructionMov(RegisterSet.rax, op));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        case "_MS_int.__postinc__":
            if (op == null) {
                assert false;
            }
            addInstruction(new InstructionMov(RegisterSet.rax, op));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            addInstruction(new InstructionInc(op));
            break;
        case "_MS_int.__postdec__":
            addInstruction(new InstructionMov(RegisterSet.rax, op));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            addInstruction(new InstructionDec(op));
            break;
        default:
            assert false;
        }
    }

    private void addBuiltin2(Func func, Operand dst, Operand lhs, Operand rhs, Operand opThis) {
        if (lhs == null || rhs == null) {
            assert false;
        }
        switch (func.getAddr().toString()) {
        case "_MS_addrEq":
            addInstruction(new InstructionMov(RegisterSet.rax, lhs));
            addInstruction(new InstructionCmp(RegisterSet.rax, rhs));
            addInstruction(new InstructionSete(RegisterSet.al));
            addInstruction(new InstructionMovzx(RegisterSet.rax, RegisterSet.al));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        case "_MS_addrNe":
            addInstruction(new InstructionMov(RegisterSet.rax, lhs));
            addInstruction(new InstructionCmp(RegisterSet.rax, rhs));
            addInstruction(new InstructionSetne(RegisterSet.al));
            addInstruction(new InstructionMovzx(RegisterSet.rax, RegisterSet.al));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        case "_MS_string.substring":
            addInstruction(new InstructionMov(regParams.get(0), opThis));
            addInstruction(new InstructionMov(regParams.get(1), lhs));
            addInstruction(new InstructionMov(regParams.get(2), rhs));
            addInstruction(new InstructionCall(new OperandFuncAddr("_string_substring")));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        case "_MS_string.__add__":
            addInstruction(new InstructionMov(RegisterSet.rdi, lhs));
            addInstruction(new InstructionMov(RegisterSet.rsi, rhs));
            addInstruction(new InstructionCall(new OperandFuncAddr("_string___add__")));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        case "_MS_string.__eq__":
            addInstruction(new InstructionMov(RegisterSet.rdi, lhs));
            addInstruction(new InstructionMov(RegisterSet.rsi, rhs));
            addInstruction(new InstructionCall(new OperandFuncAddr("_strcmp")));
            addInstruction(new InstructionTest(RegisterSet.rax, RegisterSet.rax));
            addInstruction(new InstructionSete(RegisterSet.al));
            addInstruction(new InstructionMovzx(RegisterSet.rax, RegisterSet.al));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        case "_MS_string.__ne__":
            addInstruction(new InstructionMov(RegisterSet.rdi, lhs));
            addInstruction(new InstructionMov(RegisterSet.rsi, rhs));
            addInstruction(new InstructionCall(new OperandFuncAddr("_strcmp")));
            addInstruction(new InstructionTest(RegisterSet.rax, RegisterSet.rax));
            addInstruction(new InstructionSetne(RegisterSet.al));
            addInstruction(new InstructionMovzx(RegisterSet.rax, RegisterSet.al));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        case "_MS_string.__lt__":
            addInstruction(new InstructionMov(RegisterSet.rdi, lhs));
            addInstruction(new InstructionMov(RegisterSet.rsi, rhs));
            addInstruction(new InstructionCall(new OperandFuncAddr("_strcmp")));
            addInstruction(new InstructionCmp(RegisterSet.rax, new OperandInt(0)));
            addInstruction(new InstructionSetl(RegisterSet.al));
            addInstruction(new InstructionMovzx(RegisterSet.rax, RegisterSet.al));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        case "_MS_string.__gt__":
            addInstruction(new InstructionMov(RegisterSet.rdi, lhs));
            addInstruction(new InstructionMov(RegisterSet.rsi, rhs));
            addInstruction(new InstructionCall(new OperandFuncAddr("_strcmp")));
            addInstruction(new InstructionCmp(RegisterSet.rax, new OperandInt(0)));
            addInstruction(new InstructionSetg(RegisterSet.al));
            addInstruction(new InstructionMovzx(RegisterSet.rax, RegisterSet.al));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        case "_MS_string.__le__":
            addInstruction(new InstructionMov(RegisterSet.rdi, lhs));
            addInstruction(new InstructionMov(RegisterSet.rsi, rhs));
            addInstruction(new InstructionCall(new OperandFuncAddr("_strcmp")));
            addInstruction(new InstructionCmp(RegisterSet.rax, new OperandInt(0)));
            addInstruction(new InstructionSetle(RegisterSet.al));
            addInstruction(new InstructionMovzx(RegisterSet.rax, RegisterSet.al));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        case "_MS_string.__ge__":
            addInstruction(new InstructionMov(RegisterSet.rdi, lhs));
            addInstruction(new InstructionMov(RegisterSet.rsi, rhs));
            addInstruction(new InstructionCall(new OperandFuncAddr("_strcmp")));
            addInstruction(new InstructionCmp(RegisterSet.rax, new OperandInt(0)));
            addInstruction(new InstructionSetge(RegisterSet.al));
            addInstruction(new InstructionMovzx(RegisterSet.rax, RegisterSet.al));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        case "_MS_bool.__lgcand__":
            addInstruction(new InstructionMov(RegisterSet.rax, lhs));
            addInstruction(new InstructionAnd(RegisterSet.rax, rhs));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        case "_MS_bool.__lgcor__":
            addInstruction(new InstructionMov(RegisterSet.rax, lhs));
            addInstruction(new InstructionOr(RegisterSet.rax, rhs));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        case "_MS_bool.__eq__":
            addInstruction(new InstructionMov(RegisterSet.rax, lhs));
            addInstruction(new InstructionCmp(RegisterSet.rax, rhs));
            addInstruction(new InstructionSete(RegisterSet.al));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        case "_MS_bool.__ne__":
            addInstruction(new InstructionMov(RegisterSet.rax, lhs));
            addInstruction(new InstructionCmp(RegisterSet.rax, rhs));
            addInstruction(new InstructionSetne(RegisterSet.al));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        case "_MS_int.__add__":
            addInstruction(new InstructionMov(RegisterSet.rax, lhs));
            addInstruction(new InstructionAdd(RegisterSet.rax, rhs));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        case "_MS_int.__sub__":
            addInstruction(new InstructionMov(RegisterSet.rax, lhs));
            addInstruction(new InstructionSub(RegisterSet.rax, rhs));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        case "_MS_int.__mul__":
            addInstruction(new InstructionMov(RegisterSet.rax, lhs));
            addInstruction(new InstructionImul(RegisterSet.rax, rhs));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        case "_MS_int.__div__":
            if (rhs instanceof OperandInt) {
                addInstruction(new InstructionMov(RegisterSet.rcx, rhs));
                rhs = RegisterSet.rcx;
            }
            addInstruction(new InstructionMov(RegisterSet.rax, lhs));
            addInstruction(new InstructionCqo());
            addInstruction(new InstructionIdiv(rhs));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        case "_MS_int.__mod__":
            if (rhs instanceof OperandInt) {
                addInstruction(new InstructionMov(RegisterSet.rcx, rhs));
                rhs = RegisterSet.rcx;
            }
            addInstruction(new InstructionMov(RegisterSet.rax, lhs));
            addInstruction(new InstructionCqo());
            addInstruction(new InstructionIdiv(rhs));
            addInstruction(new InstructionMov(dst, RegisterSet.rdx));
            break;
        case "_MS_int.__shl__":
            addInstruction(new InstructionMov(RegisterSet.rax, lhs));
            addInstruction(new InstructionMov(RegisterSet.rcx, rhs));
            addInstruction(new InstructionShl(RegisterSet.rax, RegisterSet.cl));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        case "_MS_int.__shr__":
            addInstruction(new InstructionMov(RegisterSet.rax, lhs));
            addInstruction(new InstructionMov(RegisterSet.rcx, rhs));
            addInstruction(new InstructionSar(RegisterSet.rax, RegisterSet.cl));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        case "_MS_int.__bitand__":
            addInstruction(new InstructionMov(RegisterSet.rax, lhs));
            addInstruction(new InstructionAnd(RegisterSet.rax, rhs));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        case "_MS_int.__bitxor__":
            addInstruction(new InstructionMov(RegisterSet.rax, lhs));
            addInstruction(new InstructionXor(RegisterSet.rax, rhs));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        case "_MS_int.__bitor__":
            addInstruction(new InstructionMov(RegisterSet.rax, lhs));
            addInstruction(new InstructionOr(RegisterSet.rax, rhs));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        case "_MS_int.__lt__":
            addInstruction(new InstructionMov(RegisterSet.rax, lhs));
            addInstruction(new InstructionCmp(RegisterSet.rax, rhs));
            addInstruction(new InstructionSetl(RegisterSet.al));
            addInstruction(new InstructionMovzx(RegisterSet.rax, RegisterSet.al));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        case "_MS_int.__gt__":
            addInstruction(new InstructionMov(RegisterSet.rax, lhs));
            addInstruction(new InstructionCmp(RegisterSet.rax, rhs));
            addInstruction(new InstructionSetg(RegisterSet.al));
            addInstruction(new InstructionMovzx(RegisterSet.rax, RegisterSet.al));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        case "_MS_int.__le__":
            addInstruction(new InstructionMov(RegisterSet.rax, lhs));
            addInstruction(new InstructionCmp(RegisterSet.rax, rhs));
            addInstruction(new InstructionSetle(RegisterSet.al));
            addInstruction(new InstructionMovzx(RegisterSet.rax, RegisterSet.al));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        case "_MS_int.__ge__":
            addInstruction(new InstructionMov(RegisterSet.rax, lhs));
            addInstruction(new InstructionCmp(RegisterSet.rax, rhs));
            addInstruction(new InstructionSetge(RegisterSet.al));
            addInstruction(new InstructionMovzx(RegisterSet.rax, RegisterSet.al));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        case "_MS_int.__eq__":
            addInstruction(new InstructionMov(RegisterSet.rax, lhs));
            addInstruction(new InstructionCmp(RegisterSet.rax, rhs));
            addInstruction(new InstructionSete(RegisterSet.al));
            addInstruction(new InstructionMovzx(RegisterSet.rax, RegisterSet.al));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        case "_MS_int.__ne__":
            addInstruction(new InstructionMov(RegisterSet.rax, lhs));
            addInstruction(new InstructionCmp(RegisterSet.rax, rhs));
            addInstruction(new InstructionSetne(RegisterSet.al));
            addInstruction(new InstructionMovzx(RegisterSet.rax, RegisterSet.al));
            addInstruction(new InstructionMov(dst, RegisterSet.rax));
            break;
        default:
            assert false;
        }
    }

    private void addDirectiveExtern(String strFunc) {
        // nasm.addDirective(new DirectiveExtern(strFunc));
    }

    private void addInstruction(SectionItem item) {
        nasm.sectionText.addItem(item);
    }

    private Operand getOperand(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof ObjectInt) {
            return new OperandInt(((ObjectInt) obj).value);
        } else if (obj instanceof ObjectBool) {
            return new OperandInt(((ObjectBool) obj).value);
        } else if (obj instanceof ObjectNull) {
            return new OperandInt(0);
        } else {
            return allocator.get(obj);
        }
    }
}
