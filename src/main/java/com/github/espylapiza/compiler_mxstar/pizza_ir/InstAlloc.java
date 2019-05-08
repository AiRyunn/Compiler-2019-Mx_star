package com.github.espylapiza.compiler_mxstar.pizza_ir;

public class InstAlloc extends Inst {
    public Object dst;
    public Object size;

    public InstAlloc(Object dst, Object size) {
        this.dst = dst;
        this.size = size;
    }

    @Override
    public void accept(PizzaIRPartBaseVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return dst + " = alloc " + size;
    }
}
