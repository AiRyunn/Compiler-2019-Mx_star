package com.github.espylapiza.compiler_mxstar.pizza_ir;

public class InstOffset extends Inst {
    ObjectPointer dst;
    Object src;
    Object offset;

    public InstOffset(ObjectPointer dst, Object src, Object offset) {
        super();
        this.dst = dst;
        this.src = src;
        this.offset = offset;
    }

    @Override
    public String toString() {
        return dst.addr() + " = " + src + " offset " + offset;
    }
}
