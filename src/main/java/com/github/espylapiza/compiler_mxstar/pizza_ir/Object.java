package com.github.espylapiza.compiler_mxstar.pizza_ir;

public class Object extends ProgramFragment {
    public Class owner;
    public String name;
    public Type type;
    private ObjectID id;

    public Object(Class owner, String name, Type type) {
        this.owner = owner;
        this.name = name;
        this.type = type;
    }

    void setID(ObjectID id) {
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
    ObjectConstant(Class owner, String name, Type type) {
        super(owner, name, type);
    }

    @Override
    public abstract String toString();
}

class ObjectID {
    private Integer id;

    ObjectID(int id) {
        this.id = id;
    }

    Integer getInt() {
        return id;
    }

    boolean equals(ObjectID other) {
        return id.equals(other.getInt());
    }

    @Override
    public String toString() {
        return "$" + id;
    }
}
