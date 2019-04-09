package com.github.espylapiza.compiler_mxstar.pizza_ir;

public class TypeFunc extends TypeSingle {
    public TypeFunc(String typeName, Class typeClass) {
        super(typeName, typeClass);
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TypeFunc)) {
            return false;
        }
        return false;
    }
}