package com.github.espylapiza.compiler_mxstar.back_end;

import com.github.espylapiza.compiler_mxstar.nasm.DirectiveGlobal;
import com.github.espylapiza.compiler_mxstar.nasm.NASM;
import com.github.espylapiza.compiler_mxstar.nasm.SectionData;
import com.github.espylapiza.compiler_mxstar.nasm.SectionText;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Func;
import com.github.espylapiza.compiler_mxstar.pizza_ir.FuncAddr;
// import com.github.espylapiza.compiler_mxstar.pizza_ir.FuncExtra;
import com.github.espylapiza.compiler_mxstar.pizza_ir.PizzaIR;

public class NASMTranslator {
    private NASM nasm;

    public NASMTranslator(PizzaIR ir) {
        nasm = new NASM();
        addDirectiveGlobal();
        addSection(ir);
    }

    public NASM getNASM() {
        return nasm;
    }

    private void addDirectiveGlobal() {
        nasm.addDirective(new DirectiveGlobal("main"));
    }

    private void addSection(PizzaIR ir) {
        SectionBuilder builder = new SectionBuilder();

        builder.buildFromPizzaIR(ir);

        nasm.addSection(builder.getSectionText());
        nasm.addSection(builder.getSectionData());
    }
}

class SectionBuilder {
    private SectionText sectionText = new SectionText();
    private SectionData sectionData = new SectionData();

    SectionText getSectionText() {
        return sectionText;
    }

    SectionData getSectionData() {
        return sectionData;
    }

    void buildFromPizzaIR(PizzaIR ir) {
        Func initFunc = ir.funcList.get(new FuncAddr().add("__init__"));
        System.out.println(initFunc.getAddr());
        // TODO: process global variables here

        for (Func func : ir.funcList) {
            // if (func instanceof FuncExtra) {
            String funcAddr = func.getAddr().toString();
            sectionText.addLabel(funcAddr);
            // TODO: FuncExtra

            // }
        }
    }
}