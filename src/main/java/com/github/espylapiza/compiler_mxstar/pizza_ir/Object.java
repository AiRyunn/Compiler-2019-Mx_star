package com.github.espylapiza.compiler_mxstar.pizza_ir;

public class Object extends ProgramFragment implements Cloneable {
    public FuncDefinition belong;
    public String name;
    public final Type type;
    protected ObjectID id;

    public Object(FuncDefinition belong, String name, Type type) {
        this.belong = belong;
        this.name = name;
        this.type = type;
    }

    public void setID(ObjectID id) {
        this.id = id;
    }

    public ObjectID getID() {
        return id;
    }

    @Override
    public String toString() {
        if (id == null) {
            return null;
        }
        return id.toString();
    }
}
