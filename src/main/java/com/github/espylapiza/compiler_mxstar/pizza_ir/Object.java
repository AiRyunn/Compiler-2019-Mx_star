package com.github.espylapiza.compiler_mxstar.pizza_ir;

class Object extends ProgramFragment {
    Class owner;
    String name;
    Type type;
    ObjectID id;
    boolean temp = true;

    Object(Class owner, String name, Type type) {
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

abstract class ConstantObject extends Object {
    ConstantObject(Class owner, String name, Type type) {
        super(owner, name, type);
    }

    @Override
    abstract public String toString();
}

class NullObject extends ConstantObject {
    NullObject(Class owner, String name, Type type) {
        super(owner, name, type);
    }

    @Override
    public String toString() {
        return "null";
    }
}

class BoolObject extends ConstantObject {
    Boolean value;

    BoolObject(Class owner, String name, Type type, Boolean value) {
        super(owner, name, type);
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}

class IntObject extends ConstantObject {
    Integer value;

    IntObject(Class owner, String name, Type type, Integer value) {
        super(owner, name, type);
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}

class StringObject extends ConstantObject {
    String value;

    StringObject(Class owner, String name, Type type, String value) {
        super(owner, name, type);
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
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
