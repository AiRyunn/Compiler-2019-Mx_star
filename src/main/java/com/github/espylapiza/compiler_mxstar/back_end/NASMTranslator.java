package com.github.espylapiza.compiler_mxstar.back_end;

import com.github.espylapiza.compiler_mxstar.nasm.DirectiveGlobal;
import com.github.espylapiza.compiler_mxstar.nasm.NASM;
import com.github.espylapiza.compiler_mxstar.pizza_ir.PizzaIR;


public class NASMTranslator {
    private NASM nasm;


    /**
     * 
     * @param ir
     */
    public NASMTranslator(PizzaIR ir) {
        nasm = new NASM();
        nasm.addDirective(new DirectiveGlobal("main"));

        PizzaIRVisitor visitor = new PizzaIRVisitor(nasm);
        ir.accept(visitor);
    }

    /**
     * 
     * @return nasm
     */
    public NASM getNASM() {
        return nasm;
    }
}
