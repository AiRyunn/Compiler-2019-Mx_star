package com.github.espylapiza.compiler_mxstar.pizza_ir;

public class FuncAddr {
    String addr = new String();

    public FuncAddr addClass(Class class1) {
        if (class1 != null) {
            addr += "." + class1.getName();
        }
        return this;
    }

    public FuncAddr addString(String name) {
        addr += "." + name;
        return this;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return addr.equals(((FuncAddr) o).addr);
    }

    @Override
    public int hashCode() {
        return addr != null ? addr.hashCode() : 0;
    }

    @Override
    public String toString() {
        return addr;
    }
}