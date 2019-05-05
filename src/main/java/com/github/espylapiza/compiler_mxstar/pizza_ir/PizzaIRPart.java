package com.github.espylapiza.compiler_mxstar.pizza_ir;

public interface PizzaIRPart {
    public void accept(PizzaIRPartBaseVisitor visitor);
}
