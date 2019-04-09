package com.github.espylapiza.compiler_mxstar.pizza_ir;

public class InstLoad extends InstBaseMemoryAccess {
    Object dst, array, subscript;
    boolean value;

    public InstLoad(Object dst, Object array, Object subscript) {
        super();
        this.dst = dst;
        this.array = array;
        this.subscript = subscript;
    }
}