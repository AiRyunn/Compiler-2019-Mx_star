package com.github.espylapiza.compiler_mxstar.pizza_ir;

public final class ObjectBool extends ObjectConstant {
    private Boolean value;

    public ObjectBool(Func belong, String name, Type type, Boolean value) {
        super(belong, name, type);
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}