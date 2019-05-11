package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.Arrays;
import java.util.List;

public final class InstMov extends Inst {
    public Object src;

    @Override
    public void accept(PizzaIRPartBaseVisitor visitor) {
        visitor.visit(this);
    }

    public InstMov(Object dst, Object src) {
        super(dst);
        this.src = src;
    }

    @Override
    public String toString() {
        return dst + " = " + src;
    }

    @Override
    public List<Object> getObjects() {
        return Arrays.asList(src);
    }
}
