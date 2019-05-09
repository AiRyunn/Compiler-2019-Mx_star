package com.github.espylapiza.compiler_mxstar.pizza_ir;

public class ObjectMethod extends ObjectFunction {
    public Func func;
    public Object who;

    public ObjectMethod(Func func, FuncDefinition belong, String name, Type type, Object who) {
        super(func, belong, name, type);
        this.func = func;
        this.who = who;
    }
}
