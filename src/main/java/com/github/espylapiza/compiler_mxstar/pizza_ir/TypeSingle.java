package com.github.espylapiza.compiler_mxstar.pizza_ir;

public abstract class TypeSingle extends Type {
    TypeSingle(String typeName, Class typeClass) {
        super(typeName, typeClass);
    }
}


abstract class TypeFundamental extends TypeSingle {
    TypeFundamental(String typeName, Class typeClass) {
        super(typeName, typeClass);
    }

    @Override
    public final boolean equals(java.lang.Object o) {
        if (o == this) {
            return true;
        }
        return getClass() == o.getClass();
    }
}


abstract class TypeCustom extends TypeSingle {
    TypeCustom(String typeName, Class typeClass) {
        super(typeName, typeClass);
    }
}
