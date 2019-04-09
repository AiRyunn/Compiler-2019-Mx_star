package com.github.espylapiza.compiler_mxstar.pizza_ir;

public class InstMember extends InstBaseMemoryAccess {
    Object dst, src;
    String name;

    public InstMember(Object dst, Object src, String name) {
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