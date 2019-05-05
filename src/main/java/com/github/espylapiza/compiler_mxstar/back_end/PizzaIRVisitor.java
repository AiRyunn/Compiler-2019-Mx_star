package com.github.espylapiza.compiler_mxstar.back_end;

import java.util.Arrays;
import java.util.List;
import com.github.espylapiza.compiler_mxstar.nasm.DirectiveExtern;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionCall;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionMov;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionPop;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionPush;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionRet;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionSub;
import com.github.espylapiza.compiler_mxstar.nasm.Label;
import com.github.espylapiza.compiler_mxstar.nasm.NASM;
import com.github.espylapiza.compiler_mxstar.nasm.Operand;
import com.github.espylapiza.compiler_mxstar.nasm.OperandFuncAddr;
import com.github.espylapiza.compiler_mxstar.nasm.OperandRegister;
import com.github.espylapiza.compiler_mxstar.nasm.RegisterSet;
import com.github.espylapiza.compiler_mxstar.nasm.SectionItem;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Func;
import com.github.espylapiza.compiler_mxstar.pizza_ir.FuncAddr;
import com.github.espylapiza.compiler_mxstar.pizza_ir.FuncExtern;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Inst;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstCall;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstRet;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstStore;
import com.github.espylapiza.compiler_mxstar.pizza_ir.PizzaIRPartBaseVisitor;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Object;
import com.github.espylapiza.compiler_mxstar.pizza_ir.PizzaIR;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Scope;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ScopeType;

public class PizzaIRVisitor extends PizzaIRPartBaseVisitor {
    private NASM nasm;

    private final List<OperandRegister> regParams = Arrays.asList(RegisterSet.rdi, RegisterSet.rsi,
            RegisterSet.rdx, RegisterSet.rcx, RegisterSet.r8, RegisterSet.r9);

    private RegisterAllocator allocator = new RegisterAllocator();

    PizzaIRVisitor(NASM nasm) {
        this.nasm = nasm;
    }

    @Override
    public void visit(PizzaIR ir) {
        Func initFunc = ir.funcList.get(FuncAddr.createGlobalFuncAddr("_init"));
        // TODO: process global variables here

        for (Func func : ir.funcList) {
            if (func instanceof FuncExtern) {
                addDirectiveExtern(func.getAddr().toString());
                continue;
            }
            if (func.getScps().isEmpty()) {
                continue;
            }

            beAccept(func);;
        }
    }

    @Override
    public void visit(Func func) {
        allocator.naiveAllocate(nasm, func);

        int stackSize = allocator.getStackSize();
        int index;
        for (Scope scp : func.getScps()) {
            nasm.sectionText.addItem(new SectionItem(new Label(scp.getLabel()), null));
            if (scp.getType() == ScopeType.FUNC) {
                // enter the func
                nasm.sectionText
                        .addItem(new SectionItem(null, new InstructionPush(RegisterSet.rbp)));
                nasm.sectionText.addItem(new SectionItem(null,
                        new InstructionMov(RegisterSet.rbp, RegisterSet.rsp)));
                nasm.sectionText.addItem(
                        new SectionItem(null, new InstructionSub(RegisterSet.rsp, stackSize)));

                index = 0;
                for (Object obj : func.getParams()) {
                    if (index < 6) {
                        Operand operand = allocator.get(obj);
                        nasm.sectionText.addItem(new SectionItem(null,
                                new InstructionMov(operand, regParams.get(index))));
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
        int index = 0;
        for (Object param : inst.params) {
            Operand operand = allocator.get(param);
            if (index < 6) {
                nasm.sectionText.addItem(
                        new SectionItem(null, new InstructionMov(regParams.get(index), operand)));
            } else {
                nasm.sectionText.addItem(new SectionItem(null, new InstructionPush(operand)));
            }
            index++;
        }
        nasm.sectionText.addItem(new SectionItem(null,
                new InstructionCall(new OperandFuncAddr(inst.getAddr().toString()))));
        if (inst.dst != null) {
            nasm.sectionText.addItem(new SectionItem(null,
                    new InstructionMov(allocator.get(inst.dst), RegisterSet.rax)));
        }
    }

    @Override
    public void visit(InstRet inst) {
        if (inst.obj != null) {
            nasm.sectionText.addItem(new SectionItem(null,
                    new InstructionMov(RegisterSet.rax, allocator.get(inst.obj))));
        }
        nasm.sectionText.addItem(
                new SectionItem(null, new InstructionMov(RegisterSet.rsp, RegisterSet.rbp)));
        nasm.sectionText.addItem(new SectionItem(null, new InstructionPop(RegisterSet.rbp)));
        nasm.sectionText.addItem(new SectionItem(null, new InstructionRet()));
    }

    @Override
    public void visit(InstStore inst) {
        nasm.sectionText.addItem(new SectionItem(null,
                new InstructionMov(allocator.get(inst.dst), allocator.get(inst.src))));
    }

    @Override
    public void visit(Inst inst) {
        if (inst instanceof Inst) {
            System.out.println(inst.getClass());
            // assert false;
        }
    }

    @Override
    public void visit(Scope scope) {
        System.out.println("Displaying Inst.");
    }

    private void addDirectiveExtern(String strFunc) {
        nasm.addDirective(new DirectiveExtern(strFunc));
    }
}
