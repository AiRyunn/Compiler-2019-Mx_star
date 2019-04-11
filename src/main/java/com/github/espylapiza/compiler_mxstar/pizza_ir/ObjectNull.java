package com.github.espylapiza.compiler_mxstar.pizza_ir;

public final class ObjectNull extends ObjectConstant {
    public ObjectNull(Func belong, String name, TypeNull type) {
        super(belong, name, type);
    }

    @Override
    public String toString() {
        return "null";
    }
}