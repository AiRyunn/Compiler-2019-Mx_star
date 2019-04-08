package com.github.espylapiza.compiler_mxstar.pizza_ir;

abstract class TypeComposite extends Type {
    TypeComposite(String typeName, Class typeClass) {
        super(typeName, typeClass);
    }
}

class TypeArray extends TypeComposite implements NullComparable {
    private final Type subType;

    TypeArray(Type subType, Class arrayClass) {
        super(subType.getName() + "[]", arrayClass);
        this.subType = subType;
    }

    Type getSubType() {
        return subType;
    }

    @Override
    boolean equals(Type other) {
        if (other instanceof TypeArray) {
            return subType.equals(((TypeArray) other).getSubType());
        }
        return false;
    }
}