package com.github.espylapiza.compiler_mxstar.pizza_ir;

public class TypeArray extends TypeComposite implements NullComparable {
    private final Type subType;

    public TypeArray(Type subType, Class arrayClass) {
        super(subType.getName() + "[]", arrayClass);
        this.subType = subType;
    }

    public Type getSubType() {
        return subType;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TypeArray)) {
            return false;
        }
        return subType.equals(((TypeArray) o).getSubType());
    }
}