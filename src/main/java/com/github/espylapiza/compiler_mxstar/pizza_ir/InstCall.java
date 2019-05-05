package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.List;

public final class InstCall extends InstBaseCall {
    public Object dst;
    private FuncAddr addr;
    public List<Object> params;

    /**
     * 
     * @param addr
     * @param params
     */
    public InstCall(FuncAddr addr, List<Object> params) {
        super();
        this.setAddr(addr);
        this.params = params;
    }

    /**
     * @return the addr
     */
    public FuncAddr getAddr() {
        return addr;
    }

    /**
     * @param addr the addr to set
     */
    public void setAddr(FuncAddr addr) {
        this.addr = addr;
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
        result += "call " + getAddr();
        for (Object param : params) {
            result += " " + param;
        }
        return result;
    }
}
