package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.Arrays;
import java.util.List;

public class InstOffset extends Inst {
    public Object src;
    public Object offset;

    public InstOffset(Object dst, Object src, Object offset) {
        super(dst);
        this.src = src;
        this.offset = offset;
    }

    @Override
    public void accept(PizzaIRPartBaseVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return dst + " = " + src + " offset " + offset;
    }

    @Override
    public List<Object> getObjects() {
        return Arrays.asList(src, offset);
    }
}
