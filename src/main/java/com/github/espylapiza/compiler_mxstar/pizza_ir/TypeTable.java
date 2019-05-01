package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.HashMap;

public class TypeTable {
    private transient HashMap<String, TypeSingle> typeTable;

    TypeTable() {
        typeTable = new HashMap<String, TypeSingle>();
    }

    public void add(TypeSingle type) {
        if (typeTable.containsKey(type.getName())) {
            assert false;
        }
        typeTable.put(type.getName(), type);
    }

    public Type get(String name) {
        return typeTable.get(name);
    }
}