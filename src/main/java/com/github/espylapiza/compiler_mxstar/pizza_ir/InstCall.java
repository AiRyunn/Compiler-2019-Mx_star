package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.List;

public class InstCall extends InstBaseCall {
    Object dst;
    FuncAddr addr;
    List<Object> params;

    InstCall(FuncAddr addr, List<Object> params) {
        super();
        this.addr = addr;
        this.params = params;
    }

    public InstCall(Object dst, FuncAddr addr, List<Object> params) {
        super();
        this.dst = dst;
        this.addr = addr;
        this.params = params;
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