package com.github.espylapiza.compiler_mxstar.pizza_ir;

public class ObjectBool extends ObjectConstant {
    private Boolean value;

    public ObjectBool(Class owner, String name, Type type, Boolean value) {
        super(owner, name, type);
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}