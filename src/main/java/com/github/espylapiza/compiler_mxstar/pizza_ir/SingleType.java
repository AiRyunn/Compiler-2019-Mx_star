package com.github.espylapiza.compiler_mxstar.pizza_ir;

abstract class SingleType extends Type {
    SingleType(String typeName, Class typeClass) {
        super(typeName, typeClass);
    }
}

class NullType extends SingleType implements NullComparable {
    NullType(String typeName) {
        super(typeName, null);
    }

    @Override
    boolean equals(Type other) {
        return other instanceof NullType;
    }
}

class FuncType extends SingleType {
    FuncType(String typeName, Class typeClass) {
        super(typeName, typeClass);
    }

    @Override
    boolean equals(Type other) {
        return false;
    }
}

class MethodType extends SingleType {
    MethodType(String typeName, Class typeClass) {
        super(typeName, typeClass);
    }

    @Override
    boolean equals(Type other) {
        return false;
    }
}

abstract class FundamentalType extends SingleType {
    FundamentalType(String typeName, Class typeClass) {
        super(typeName, typeClass);
    }

    @Override
    final boolean equals(Type other) {
        return typeClass == other.getTypeClass();
    }
}

abstract class CustomType extends SingleType {
    CustomType(String typeName, Class typeClass) {
        super(typeName, typeClass);
    }
}

class VoidType extends FundamentalType {
    VoidType(String typeName) {
        super(typeName, null);
    }
}

class BoolType extends FundamentalType {
    BoolType(String typeName, Class typeClass) {
        super(typeName, typeClass);
    }
}

class IntType extends FundamentalType {
    IntType(String typeName, Class typeClass) {
        super(typeName, typeClass);
    }
}

class StringType extends FundamentalType {
    StringType(String typeName, Class typeClass) {
        super(typeName, typeClass);
    }
}

class CustomClassType extends CustomType implements NullComparable {
    CustomClassType(String typeName, Class typeClass) {
        super(typeName, typeClass);
    }

    @Override
    boolean equals(Type other) {
        return typeClass == other.getTypeClass();
    }
}