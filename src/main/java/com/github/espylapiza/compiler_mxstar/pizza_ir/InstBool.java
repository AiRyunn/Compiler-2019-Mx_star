package com.github.espylapiza.compiler_mxstar.pizza_ir;

class InstBool extends Inst {
    ObjectID dst;
    boolean value;

    InstBool(ObjectID dst, boolean value) {
        super();
        this.dst = dst;
        this.value = value;
    }

    @Override
    public String toString() {
        return dst + " = " + value;
    }
}