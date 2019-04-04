package com.github.espylapiza.compiler_mxstar.pizza_ir;

class InstAssignment extends Inst {
    ObjectID dst, src;

    InstAssignment(ObjectID dst, ObjectID src) {
        super();
        this.dst = dst;
        this.src = src;
    }

    @Override
    public String toString() {
        return dst + " = " + src;
    }
}