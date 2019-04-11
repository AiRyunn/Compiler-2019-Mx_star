package com.github.espylapiza.compiler_mxstar.pizza_ir;

public class InstAlloc extends Inst {
    Object dst;
    Integer size;

    public InstAlloc(Object dst, Integer size) {
        this.dst = dst;
        this.size = size;
    }

    @Override
    public String toString() {
        return dst + " = alloc " + size;
    }
}