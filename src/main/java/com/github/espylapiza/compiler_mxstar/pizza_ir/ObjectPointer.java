package com.github.espylapiza.compiler_mxstar.pizza_ir;

public final class ObjectPointer extends Object {

    public ObjectPointer(Func belong, String name, Type type) {
        super(belong, name, type);
    }

    // private String getName() {
    //     if (belong == null) {
    //         return id.toString();
    //     } else {
    //         return belong.getAddr() + "." + id;
    //     }
    // }

    // @Override
    // public String toString() {
    //     String result = "[ ";
    //     result += getName();
    //     result += " ]";
    //     return result;
    // }

    @Override
    public String toString() {
        if (belong == null) {
            return id.toString();
        } else {
            return belong.getAddr() + "." + id;
        }
    }
}