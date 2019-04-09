package com.github.espylapiza.compiler_mxstar.pizza_ir;

public class ObjectString extends ObjectConstant {
    private String value;

    public ObjectString(Class owner, String name, Type type, String value) {
        super(owner, name, type);
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}