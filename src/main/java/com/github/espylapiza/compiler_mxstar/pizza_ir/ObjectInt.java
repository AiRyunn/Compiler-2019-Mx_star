package com.github.espylapiza.compiler_mxstar.pizza_ir;

public final class ObjectInt extends ObjectConstant {
    private Integer value;

    public ObjectInt(Func belong, String name, Type type, Integer value) {
        super(belong, name, type);
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}