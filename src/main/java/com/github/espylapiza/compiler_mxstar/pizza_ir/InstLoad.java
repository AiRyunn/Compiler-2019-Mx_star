package com.github.espylapiza.compiler_mxstar.pizza_ir;

public class InstLoad extends Inst {
    public Object dst;
    public Object src;

    public InstLoad(Object dst, Object src) {
        super();
        this.dst = dst;
        this.src = src;
    }

    @Override
    public void accept(PizzaIRPartBaseVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return dst + " = [ " + src + " ]";
    }
}
