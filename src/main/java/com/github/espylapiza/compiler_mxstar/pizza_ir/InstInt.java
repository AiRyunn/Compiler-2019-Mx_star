package com.github.espylapiza.compiler_mxstar.pizza_ir;

class InstInt extends Inst {
    ObjectID dst;
    int value;

    InstInt(ObjectID dst, int value) {
        super();
        this.dst = dst;
        this.value = value;
    }

    @Override
    public String toString() {
        return dst + " = " + value;
    }
}