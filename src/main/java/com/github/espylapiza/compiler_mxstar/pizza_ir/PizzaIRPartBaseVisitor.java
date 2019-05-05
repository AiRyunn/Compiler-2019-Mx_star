package com.github.espylapiza.compiler_mxstar.pizza_ir;

public abstract class PizzaIRPartBaseVisitor {
    public final void beAccept(PizzaIRPart part) {
        part.accept(this);
    }

    public abstract void visit(PizzaIR ir);

    public abstract void visit(Func func);

    public abstract void visit(Scope scope);

    public abstract void visit(Inst inst);

    public abstract void visit(InstCall inst);

    public abstract void visit(InstRet inst);

    public abstract void visit(InstStore inst);
}
