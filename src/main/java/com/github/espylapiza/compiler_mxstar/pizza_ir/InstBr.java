package com.github.espylapiza.compiler_mxstar.pizza_ir;

public class InstBr extends InstBaseJump {
    Object obj;
    Scope scpIfTrue, scpIfFalse;

    InstBr() {
        super();
    }

    InstBr(ObjectID src, Scope scpIfTrue, Scope scpIfFalse) {
        super();
        this.scpIfTrue = scpIfTrue;
        this.scpIfFalse = scpIfFalse;
    }

    public InstBr(Object obj, Scope scpIfTrue, Scope scpIfFalse) {
        super();
        this.obj = obj;
        this.scpIfTrue = scpIfTrue;
        this.scpIfFalse = scpIfFalse;
    }

    @Override
    public String toString() {
        return "br " + obj + " " + scpIfTrue.getLabel() + " " + scpIfFalse.getLabel();
    }
}