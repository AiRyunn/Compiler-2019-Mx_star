package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.List;

public final class InstCall extends InstBaseCall {
    Object dst;
    FuncAddr addr;
    public List<Object> params;

    /**
     * 
     * @param addr
     * @param params
     */
    public InstCall(FuncAddr addr, List<Object> params) {
        super();
        this.addr = addr;
        this.params = params;
    }

    /**
     * 
     * @param dst
     * @param addr
     * @param params
     */
    public InstCall(Object dst, FuncAddr addr, List<Object> params) {
        this(addr, params);
        this.dst = dst;
    }

    @Override
    public String toString() {
        String result = new String();
        if (dst != null) {
            result += dst + " = ";
        }
        result += "call " + addr;
        for (Object param : params) {
            result += " " + param;
        }
        return result;
    }
}
