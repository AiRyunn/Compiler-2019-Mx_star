package com.github.espylapiza.compiler_mxstar.pizza_ir;

class InstCondition extends Inst {
    ObjectID src;
    Scope scp_if, scp_else;

    InstCondition() {
        super();
    }

    InstCondition(ObjectID src, Scope scp_if, Scope scp_else) {
        super();
        this.src = src;
        this.scp_if = scp_if;
        this.scp_else = scp_else;
    }

    @Override
    public String toString() {
        return "if " + src + " jump " + scp_if.getLabel() + " else jump " + scp_else.getLabel();
    }
}