package com.github.espylapiza.compiler_mxstar.pizza_ir;

public final class InstMember extends InstBaseMemoryAccess {
    ObjectReference dst;
    Object src;
    String name;

    public InstMember(ObjectReference dst, Object src, String name) {
        super();
        this.dst = dst;
        this.src = src;
        this.name = name;
    }

    @Override
    public String toString() {
        return dst + " = " + src + "." + name;
    }
}
