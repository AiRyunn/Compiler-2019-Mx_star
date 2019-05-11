package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.Arrays;
import java.util.List;

public class InstLoad extends Inst {
    public Object src;

    public InstLoad(Object dst, Object src) {
        super(dst);
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

    @Override
    public List<Object> getObjects() {
        return Arrays.asList(src);
    }
}
