package com.github.espylapiza.compiler_mxstar.pizza_ir;

public final class InstStore extends Inst {
    public ObjectPointer dst;
    public Object src;

    @Override
    public void accept(PizzaIRPartBaseVisitor visitor) {
        visitor.visit(this);
    }

    public InstStore(ObjectPointer dst, Object src) {
        super();
        this.dst = dst;
        this.src = src;
    }

    @Override
    public String toString() {
        return "[ " + dst + " ] = " + src;
    }
}
