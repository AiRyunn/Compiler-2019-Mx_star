package com.github.espylapiza.compiler_mxstar.pizza_ir;

public class ObjectPtr extends Object {
    public Object obj;

    public ObjectPtr(Object obj) {
        super(obj.belong, obj.name, obj.type);
        this.obj = obj;
    }

    public String addr() {
        return obj.toString();
    }

    @Override
    public String toString() {
        // assert false;
        return "[ " + obj.toString() + " ]";
    }
}
