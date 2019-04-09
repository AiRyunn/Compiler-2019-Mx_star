package com.github.espylapiza.compiler_mxstar.pizza_ir;

public final class InstRet extends InstBaseJump {
    private Object obj;

    public InstRet() {
        super();
    }

    public InstRet(Object obj) {
        super();
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "ret " + obj;
    }
}