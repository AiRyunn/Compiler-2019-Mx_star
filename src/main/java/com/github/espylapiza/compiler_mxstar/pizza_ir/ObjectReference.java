package com.github.espylapiza.compiler_mxstar.pizza_ir;

public final class ObjectReference extends Object {

    public ObjectReference(FuncDefinition belong, String name, Type type) {
        super(belong, name, type);
    }

    public String addr() {
        if (belong == null) {
            return id.toString();
        } else {
            return belong.getAddr() + "." + id;
        }
    }

    @Override
    public String toString() {
        if (belong == null) {
            return "[ " + id.toString() + " ]";
        } else {
            return "[ " + belong.getAddr() + "." + id + " ]";
        }
    }
}
