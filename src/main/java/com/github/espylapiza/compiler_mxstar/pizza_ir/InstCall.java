package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.Vector;

class InstCall extends Inst {
    ObjectID dst;
    String addr;
    Vector<ObjectID> params;

    InstCall(ObjectID dst, String addr, Vector<ObjectID> params) {
        super();
        this.dst = dst;
        this.addr = addr;
        this.params = params;
    }

    @Override
    public String toString() {
        String result = "";
        result = dst + " = call " + addr;
        for (ObjectID param : params) {
            result += " " + param;
        }
        return result;
    }
}