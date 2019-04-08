package com.github.espylapiza.compiler_mxstar.pizza_ir;

class InstAssignment extends Inst {
    Object dst, src;

    InstAssignment(Object dst, Object src) {
        super();
        this.dst = dst;
        this.src = src;
    }

    @Override
    public String toString() {
        return dst + " = " + src;
    }
}