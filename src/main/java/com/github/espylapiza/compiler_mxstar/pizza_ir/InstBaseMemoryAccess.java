package com.github.espylapiza.compiler_mxstar.pizza_ir;

class InstBaseMemoryAccess extends Inst {
}

class InstLoad extends InstBaseMemoryAccess {
    Object dst, array, subscript;
    boolean value;

    InstLoad(Object dst, Object array, Object subscript) {
        super();
        this.dst = dst;
        this.array = array;
        this.subscript = subscript;
    }
}

class InstStore extends InstBaseMemoryAccess {
    Object dst, src;

    InstStore(Object dst, Object src) {
        super();
        this.dst = dst;
        this.src = src;
    }

    @Override
    public String toString() {
        return "[ addr " + dst + " ] = " + src;
    }
}

class InstMember extends InstBaseMemoryAccess {
    Object dst, src;
    String name;

    InstMember(Object dst, Object src, String name) {
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