package com.github.espylapiza.compiler_mxstar.pizza_ir;

public final class ObjectInMemory extends Object {
    public ObjectInMemory(Func belong, String name, Type type) {
        super(belong, name, type);
    }

    @Override
    public String toString() {
        String result = new String();
        if (belong == null) {
            result += "[ addr " + id + " ]";
        } else {
            result += "[ addr " + belong.getAddr() + "." + id + " ]";
        }
        return result;
    }
}