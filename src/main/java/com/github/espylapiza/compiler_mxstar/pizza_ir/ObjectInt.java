package com.github.espylapiza.compiler_mxstar.pizza_ir;

public final class ObjectInt extends ObjectConstant {
    private Integer value;

    public ObjectInt(Class owner, String name, Type type, Integer value) {
        super(owner, name, type);
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}