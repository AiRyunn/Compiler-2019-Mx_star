package com.github.espylapiza.compiler_mxstar.pizza_ir;

public abstract class Type {
    private String typeName;
    protected Class typeClass;

    Type(String typeName, Class typeClass) {
        this.typeName = typeName;
        this.typeClass = typeClass;
    }

    public String getName() {
        return typeName;
    };

    public Class getTypeClass() {
        return typeClass;
    };

    @Override
    public abstract boolean equals(java.lang.Object o);

    @Override
    public String toString() {
        return typeName;
    }
}
