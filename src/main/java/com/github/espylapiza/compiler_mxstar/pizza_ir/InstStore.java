package com.github.espylapiza.compiler_mxstar.pizza_ir;

public final class InstStore extends InstBaseMemoryAccess {
    Object dst, src;

    public InstStore(Object dst, Object src) {
        super();
        this.dst = dst;
        this.src = src;
    }

    @Override
    public String toString() {
        return "[ addr " + dst + " ] = " + src;
    }
}