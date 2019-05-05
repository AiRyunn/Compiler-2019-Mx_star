package com.github.espylapiza.compiler_mxstar.pizza_ir;

public final class ObjectPointer extends Object {

    public ObjectPointer(FuncDefinition belong, String name, Type type) {
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

    public String addr() {
        if (belong == null) {
            assert false;
            return id.toString();
        } else {
            return belong.getAddr() + "." + id;
        }
    }

    @Override
    public String toString() {
        if (belong == null) {
            assert false;
            return "[ " + id.toString() + " ]";
        } else {
            return "[ " + belong.getAddr() + "." + id + " ]";
        }
    }
}
