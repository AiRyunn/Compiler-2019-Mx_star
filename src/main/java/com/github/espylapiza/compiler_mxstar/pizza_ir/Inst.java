package com.github.espylapiza.compiler_mxstar.pizza_ir;

public abstract class Inst implements PizzaIRPart {
    @Override
    public void accept(PizzaIRPartBaseVisitor visitor) {
        visitor.visit(this);
    }

    Inst() {
    }
}
