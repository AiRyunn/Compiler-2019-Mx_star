package com.github.espylapiza.compiler_mxstar.pizza_ir;

public class Object extends ProgramFragment {
    public Func belong;
    public final String name;
    public final Type type;
    protected ObjectID id;

    public Object(Func belong, String name, Type type) {
        this.belong = belong;
        this.name = name;
        this.type = type;
    }

    public void setID(ObjectID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        if (id == null) {
            return null;
        }
        return id.toString();
    }
}

abstract class ObjectConstant extends Object {
    ObjectConstant(Func belong, String name, Type type) {
        super(belong, name, type);
    }

    @Override
    public abstract String toString();
}
