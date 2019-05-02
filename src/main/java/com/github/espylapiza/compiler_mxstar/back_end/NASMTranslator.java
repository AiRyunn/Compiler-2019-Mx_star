package com.github.espylapiza.compiler_mxstar.back_end;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.github.espylapiza.compiler_mxstar.nasm.DirectiveExtern;
import com.github.espylapiza.compiler_mxstar.nasm.DirectiveGlobal;
import com.github.espylapiza.compiler_mxstar.nasm.InstructionMov;
import com.github.espylapiza.compiler_mxstar.nasm.Label;
import com.github.espylapiza.compiler_mxstar.nasm.NASM;
import com.github.espylapiza.compiler_mxstar.nasm.Operand;
import com.github.espylapiza.compiler_mxstar.nasm.OperandMemory;
import com.github.espylapiza.compiler_mxstar.nasm.OperandRegister;
import com.github.espylapiza.compiler_mxstar.nasm.OperandRegister64Bit;
import com.github.espylapiza.compiler_mxstar.nasm.Operands;
import com.github.espylapiza.compiler_mxstar.nasm.RegisterSet;
import com.github.espylapiza.compiler_mxstar.nasm.SectionData;
import com.github.espylapiza.compiler_mxstar.nasm.SectionItem;
import com.github.espylapiza.compiler_mxstar.nasm.SectionText;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Func;
import com.github.espylapiza.compiler_mxstar.pizza_ir.FuncAddr;
import com.github.espylapiza.compiler_mxstar.pizza_ir.FuncExtern;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Inst;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstCall;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Object;
import com.github.espylapiza.compiler_mxstar.pizza_ir.PizzaIR;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Scope;

public class NASMTranslator {
    private NASM nasm;

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
        SectionText sectionText = new SectionText();
        SectionData sectionData = new SectionData();

        Func initFunc = ir.funcList.get(FuncAddr.createGlobalFuncAddr("__init__"));
        // TODO: process global variables here

        for (Func func : ir.funcList) {
            if (func instanceof FuncExtern) {
                addDirectiveExtern(func.getAddr().toString());
                continue;
            }
            if (func.getScps().isEmpty()) {
                // some builtin funcs of PizzaIR
                continue;
            }
            // TODO: register allocation
            // calculate stack space & offset for each variable
            List<OperandRegister> regs = Arrays.asList(RegisterSet.rdi, RegisterSet.rsi,
                    RegisterSet.rdx, RegisterSet.rcx, RegisterSet.r8, RegisterSet.r9);
            int index = 0;
            Map<Object, OperandMemory> maddr = new HashMap<Object, OperandMemory>();
            for (Object obj : func.getVarList()) {
                maddr.put(obj, new OperandMemory(RegisterSet.rbp, 8 * index));
                index++;
            }

            for (Scope scp : func.getScps()) {
                sectionText.addItem(new SectionItem(new Label(scp.getLabel()), null, null));
                for (Inst inst : scp) {
                    addInst(sectionText, sectionData, inst, maddr);
                }
            }
        }

        nasm.addSection(sectionText);
        nasm.addSection(sectionData);
    }

    private void addInst(SectionText sectionText, SectionData sectionData, Inst inst,
            Map<Object, OperandMemory> maddr) {
        if (inst instanceof InstCall) {

            InstCall instCall = (InstCall) inst;
            List<OperandRegister> regs = Arrays.asList(RegisterSet.rdi, RegisterSet.rsi,
                    RegisterSet.rdx, RegisterSet.rcx, RegisterSet.r8, RegisterSet.r9);

            int index = 0;
            for (Object param : instCall.params) {
                if (index < 6) {
                    // System.out.println(param.getID());
                    // sectionText.addItem(new SectionItem(null, new InstructionMov(),
                    //         new Operands(regs.get(index), param)));
                } else {

                }
            }
        }
    }
}
