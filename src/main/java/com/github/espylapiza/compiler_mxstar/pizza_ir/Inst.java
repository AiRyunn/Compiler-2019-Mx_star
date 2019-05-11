package com.github.espylapiza.compiler_mxstar.pizza_ir;

public abstract class Inst implements PizzaIRPart {
    public boolean valid = true;
    public Object dst = null;

    @Override
    public void accept(PizzaIRPartBaseVisitor visitor) {
        visitor.visit(this);
    }

    Inst() {
    }

    Inst(Object dst) {
        this.dst = dst;
    }
}
