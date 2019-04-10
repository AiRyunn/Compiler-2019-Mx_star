package com.github.espylapiza.compiler_mxstar.pizza_ir;

public final class ObjectFunction extends Object {
    public Func func;

    public ObjectFunction(Func func, Func belong, String name, Type type) {
        super(belong, name, type);
        this.func = func;
    }
}