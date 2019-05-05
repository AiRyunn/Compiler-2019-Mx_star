package com.github.espylapiza.compiler_mxstar.pizza_ir;

public class FuncAddr {
    private final String addr;

    private FuncAddr(String strFunc) {
        this.addr = strFunc;
    }

    /**
     * Create a func addr by func's name..
     * @param strFunc
     * @return FuncAddr Object
     */
    public static FuncAddr createGlobalFuncAddr(String strFunc) {
        return new FuncAddr(strFunc);
    }

    /**
     * Create a func addr by func's name..
     * @param strFunc
     * @return FuncAddr Object
     */
    public static FuncAddr createFuncAddr(String strFunc) {
        return new FuncAddr("_MS_" + strFunc);
    }

    /**
     * Create a func addr by a method of class.
     * @param class1
     * @param strFunc
     * @return FuncAddr Object
     */
    public static FuncAddr createMethodAddr(Class class1, String strFunc) {
        if (class1 == null) {
            return createFuncAddr(strFunc);
        } else {
            return createFuncAddr(class1.getName() + "." + strFunc);
        }
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
