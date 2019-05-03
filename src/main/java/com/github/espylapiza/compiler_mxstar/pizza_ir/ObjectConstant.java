package com.github.espylapiza.compiler_mxstar.pizza_ir;

public abstract class ObjectConstant extends Object {
    ObjectConstant(Func belong, String name, Type type) {
        super(belong, name, type);
    }

    @Override
    public abstract String toString();
}
