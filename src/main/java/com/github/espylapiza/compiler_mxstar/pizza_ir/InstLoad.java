package com.github.espylapiza.compiler_mxstar.pizza_ir;

class InstLoad extends Inst {
    ObjectID dst, array, subscript;
    boolean value;

    InstLoad(ObjectID dst, ObjectID array, ObjectID subscript) {
        super();
        this.dst = dst;
        this.array = array;
        this.subscript = subscript;
    }
}