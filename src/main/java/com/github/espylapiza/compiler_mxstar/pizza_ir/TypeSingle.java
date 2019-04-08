package com.github.espylapiza.compiler_mxstar.pizza_ir;

abstract class TypeSingle extends Type {
    TypeSingle(String typeName, Class typeClass) {
        super(typeName, typeClass);
    }
}

class TypeNull extends TypeSingle implements NullComparable {
    TypeNull(String typeName) {
        super(typeName, null);
    }

    @Override
    boolean equals(Type other) {
        return other instanceof TypeNull;
    }
}

class TypeFunc extends TypeSingle {
    TypeFunc(String typeName, Class typeClass) {
        super(typeName, typeClass);
    }

    @Override
    boolean equals(Type other) {
        return false;
    }
}

class TypeMethod extends TypeSingle {
    TypeMethod(String typeName, Class typeClass) {
        super(typeName, typeClass);
    }

    @Override
    boolean equals(Type other) {
        return false;
    }
}

abstract class TypeFundamental extends TypeSingle {
    TypeFundamental(String typeName, Class typeClass) {
        super(typeName, typeClass);
    }

    @Override
    final boolean equals(Type other) {
        return typeClass == other.getTypeClass();
    }
}

abstract class TypeCustom extends TypeSingle {
    TypeCustom(String typeName, Class typeClass) {
        super(typeName, typeClass);
    }
}

class TypeVoid extends TypeFundamental {
    TypeVoid(String typeName) {
        super(typeName, null);
    }
}

class TypeBool extends TypeFundamental {
    TypeBool(String typeName, Class typeClass) {
        super(typeName, typeClass);
    }
}

class TypeInt extends TypeFundamental {
    TypeInt(String typeName, Class typeClass) {
        super(typeName, typeClass);
    }
}

class TypeString extends TypeFundamental {
    TypeString(String typeName, Class typeClass) {
        super(typeName, typeClass);
    }
}

class TypeCustomClass extends TypeCustom implements NullComparable {
    TypeCustomClass(String typeName, Class typeClass) {
        super(typeName, typeClass);
    }

    @Override
    boolean equals(Type other) {
        return typeClass == other.getTypeClass();
    }
}