package com.github.espylapiza.compiler_mxstar.pizza_ir;

public final class ObjectString extends ObjectConstant {
    private String value;

    public ObjectString(Func belong, String name, Type type, String value) {
        super(belong, name, type);
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}