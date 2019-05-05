package com.github.espylapiza.compiler_mxstar.pizza_ir;

public final class ObjectInt extends ObjectConstant {
    public Integer value;

    public ObjectInt(FuncDefinition belong, String name, TypeInt type, Integer value) {
        super(belong, name, type);
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
