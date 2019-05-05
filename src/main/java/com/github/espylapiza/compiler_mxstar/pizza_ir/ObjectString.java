package com.github.espylapiza.compiler_mxstar.pizza_ir;

public final class ObjectString extends ObjectConstant {
    public String value;

    public ObjectString(FuncDefinition belong, String name, TypeString type, String value) {
        super(belong, name, type);
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
