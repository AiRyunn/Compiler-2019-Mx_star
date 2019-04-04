package com.github.espylapiza.compiler_mxstar.pizza_ir;

import com.google.gson.annotations.Expose;

interface NullComparable {
}

abstract class Type {
    @Expose
    private String typeName;
    protected transient Class typeClass;

    Type(String typeName, Class typeClass) {
        this.typeName = typeName;
        this.typeClass = typeClass;
    }

    String getName() {
        return typeName;
    };

    Class getTypeClass() {
        return typeClass;
    };

    abstract boolean equals(Type other);
}