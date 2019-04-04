package com.github.espylapiza.compiler_mxstar.pizza_ir;

abstract class CompositeType extends Type {
    CompositeType(String typeName, Class typeClass) {
        super(typeName, typeClass);
    }
}

class ArrayType extends CompositeType implements NullComparable {
    private final Type subType;

    ArrayType(Type subType, Class arrayClass) {
        super(subType.getName() + "[]", arrayClass);
        this.subType = subType;
    }

    Type getSubType() {
        return subType;
    }

    @Override
    boolean equals(Type other) {
        if (other instanceof ArrayType) {
            return subType.equals(((ArrayType) other).getSubType());
        }
        return false;
    }
}