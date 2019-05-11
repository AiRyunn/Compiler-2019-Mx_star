package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.Arrays;
import java.util.List;

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

    @Override
    public List<Object> getObjects() {
        return Arrays.asList(addr, src);
    }
}
