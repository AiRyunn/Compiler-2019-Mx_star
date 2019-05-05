package com.github.espylapiza.compiler_mxstar.back_end;

import java.util.Arrays;
import java.util.List;
import com.github.espylapiza.compiler_mxstar.nasm.DirectiveExtern;
import com.github.espylapiza.compiler_mxstar.nasm.DirectiveGlobal;
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
import com.github.espylapiza.compiler_mxstar.pizza_ir.Object;
import com.github.espylapiza.compiler_mxstar.pizza_ir.PizzaIR;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Scope;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ScopeType;


public class NASMTranslator {
    private NASM nasm;

    private final List<OperandRegister> regParams = Arrays.asList(RegisterSet.rdi, RegisterSet.rsi,
            RegisterSet.rdx, RegisterSet.rcx, RegisterSet.r8, RegisterSet.r9);

    /**
     * 
     * @param ir
     */
    public NASMTranslator(PizzaIR ir) {
        nasm = new NASM();
        addDirectiveGlobal();
        addSections(ir);
    }

    /**
     * 
     * @return nasm
     */
    public NASM getNASM() {
        return nasm;
    }

    private void addDirectiveGlobal() {
        nasm.addDirective(new DirectiveGlobal("main"));
    }

    private void addDirectiveExtern(String strFunc) {
        nasm.addDirective(new DirectiveExtern(strFunc));
    }

    private void addSections(PizzaIR ir) {
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

            addFunc(func);
        }

    }


    private void addInst(Inst inst, RegisterAllocator allocator) {
        if (inst instanceof InstCall) {
            InstCall instCall = (InstCall) inst;
            int index = 0;
            for (Object param : instCall.params) {
                Operand operand = allocator.get(param);
                if (index < 6) {
                    nasm.sectionText.addItem(new SectionItem(null,
                            new InstructionMov(regParams.get(index), operand)));
                } else {
                    nasm.sectionText.addItem(new SectionItem(null, new InstructionPush(operand)));
                }
                index++;
            }
            nasm.sectionText.addItem(new SectionItem(null,
                    new InstructionCall(new OperandFuncAddr(instCall.getAddr().toString()))));
            if (instCall.dst != null) {
                nasm.sectionText.addItem(new SectionItem(null,
                        new InstructionMov(allocator.get(instCall.dst), RegisterSet.rax)));
            }
        } else if (inst instanceof InstRet) {
            InstRet instRet = (InstRet) inst;
            if (instRet.obj != null) {
                nasm.sectionText.addItem(new SectionItem(null,
                        new InstructionMov(RegisterSet.rax, allocator.get(instRet.obj))));
            }
            nasm.sectionText.addItem(
                    new SectionItem(null, new InstructionMov(RegisterSet.rsp, RegisterSet.rbp)));
            nasm.sectionText.addItem(new SectionItem(null, new InstructionPop(RegisterSet.rbp)));
            nasm.sectionText.addItem(new SectionItem(null, new InstructionRet()));
        }
    }

    static Integer db_index = 0;

    private void addFunc(Func func) {
        RegisterAllocator allocator = new RegisterAllocator();
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
                addInst(inst, allocator);
            }
        }
    }
}
