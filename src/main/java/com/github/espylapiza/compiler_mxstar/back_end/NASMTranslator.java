package com.github.espylapiza.compiler_mxstar.back_end;

import com.github.espylapiza.compiler_mxstar.nasm.NASM;
import com.github.espylapiza.compiler_mxstar.pizza_ir.PizzaIR;

public class NASMTranslator {

    public NASMTranslator(PizzaIR ir) {
    }

    public NASM getNASM() {
        return new NASM();
    }
}