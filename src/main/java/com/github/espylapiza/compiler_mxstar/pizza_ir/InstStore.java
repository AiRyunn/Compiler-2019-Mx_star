package com.github.espylapiza.compiler_mxstar.pizza_ir;

public final class InstStore extends Inst {
    public Object addr;
    public Object src;

    @Override
    public void accept(PizzaIRPartBaseVisitor visitor) {
        visitor.visit(this);
    }

    public InstStore(Object addr, Object src) {
        super();
        this.addr = addr;
        this.src = src;
    }

    @Override
    public String toString() {
        return "[ " + addr + " ] = " + src;
    }
}
