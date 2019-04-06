package com.github.espylapiza.compiler_mxstar.pizza_ir;

class InstJump extends Inst {
    Scope to;

    InstJump() {
        super();
    }

    InstJump(Scope to) {
        super();
        this.to = to;
    }

    @Override
    public String toString() {
        return "jump " + to.getLabel();
    }
}