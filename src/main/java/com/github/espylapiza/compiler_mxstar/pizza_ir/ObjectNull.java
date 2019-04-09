package com.github.espylapiza.compiler_mxstar.pizza_ir;

public final class ObjectNull extends ObjectConstant {
    public ObjectNull(Class owner, String name, Type type) {
        super(owner, name, type);
    }

    @Override
    public String toString() {
        return "null";
    }
}