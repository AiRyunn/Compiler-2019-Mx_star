package com.github.espylapiza.compiler_mxstar.pizza_ir;

public final class InstJump extends InstBaseJump {
    Scope to;

    InstJump() {
        super();
    }

    public InstJump(Scope to) {
        super();
        this.to = to;
    }

    @Override
    public String toString() {
        return "jump " + to.getLabel();
    }
}
