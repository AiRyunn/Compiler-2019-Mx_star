package com.github.espylapiza.compiler_mxstar.optimizers;

import com.github.espylapiza.compiler_mxstar.pizza_ir.PizzaIR;

public class PizzaIROptimizer {
    private PizzaIR ir;

    public PizzaIROptimizer(PizzaIR ir) {
        this.ir = ir;
    }

    public void optimize() {
    }

    public PizzaIR getIR() {
        return ir;
    }
}
