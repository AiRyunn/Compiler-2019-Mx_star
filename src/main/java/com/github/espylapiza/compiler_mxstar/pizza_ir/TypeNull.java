package com.github.espylapiza.compiler_mxstar.pizza_ir;

public final class TypeNull extends TypeSingle implements NullComparable {
    TypeNull(String typeName) {
        super(typeName, null);
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TypeNull)) {
            return false;
        }
        return true;
    }
}