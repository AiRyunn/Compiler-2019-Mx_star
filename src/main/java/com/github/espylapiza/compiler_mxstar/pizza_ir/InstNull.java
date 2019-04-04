package com.github.espylapiza.compiler_mxstar.pizza_ir;

class InstNull extends Inst {
    ObjectID dst;

    InstNull(ObjectID dst) {
        super();
        this.dst = dst;
    }

    @Override
    public String toString() {
        return dst + " = " + "null";
    }
}