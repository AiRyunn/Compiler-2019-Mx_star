package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.List;

abstract class InstBaseCall extends Inst {
}

class InstCall extends InstBaseCall {
    Object dst;
    String addr;
    List<Object> params;

    InstCall(String addr, List<Object> params) {
        super();
        this.addr = addr;
        this.params = params;
    }

    InstCall(Object dst, String addr, List<Object> params) {
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