package com.github.espylapiza.compiler_mxstar.pizza_ir;

public class ObjectID {
    private Integer id;

    public ObjectID(int id) {
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
