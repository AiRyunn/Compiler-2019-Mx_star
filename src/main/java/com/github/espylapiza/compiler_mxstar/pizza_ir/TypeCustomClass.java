package com.github.espylapiza.compiler_mxstar.pizza_ir;

public final class TypeCustomClass extends TypeCustom implements NullComparable {
    public TypeCustomClass(String typeName, Class typeClass) {
        super(typeName, typeClass);
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TypeCustomClass)) {
            return false;
        }
        return typeClass == ((TypeCustomClass) o).getTypeClass();
    }
}