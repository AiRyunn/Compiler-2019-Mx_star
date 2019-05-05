package com.github.espylapiza.compiler_mxstar.pizza_ir;

public final class ObjectBool extends ObjectConstant {
    public Boolean value;

    public ObjectBool(FuncDefinition belong, String name, TypeBool type, Boolean value) {
        super(belong, name, type);
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
