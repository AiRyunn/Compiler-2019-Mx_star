package com.github.espylapiza.compiler_mxstar.pizza_ir;

public final class ObjectFunction extends Object {
    public Func func;

    public ObjectFunction(Func func, FuncDefinition belong, String name, Type type) {
        super(belong, name, type);
        this.func = func;
    }
}
