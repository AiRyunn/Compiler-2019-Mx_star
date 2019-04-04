package com.github.espylapiza.compiler_mxstar.pizza_ir;

class InstString extends Inst {
    ObjectID dst;
    String value;

    InstString(ObjectID dst, String value) {
        super();
        this.dst = dst;
        this.value = value;
    }

    @Override
    public String toString() {
        return dst + " = " + value;
    }
}