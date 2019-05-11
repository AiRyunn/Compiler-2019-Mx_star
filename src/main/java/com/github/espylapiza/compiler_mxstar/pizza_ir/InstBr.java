package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.Arrays;
import java.util.List;

public final class InstBr extends Inst implements InstBaseJump {
    public Object obj;
    public Scope scpIfTrue;
    public Scope scpIfFalse;

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
    public void accept(PizzaIRPartBaseVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "br " + obj + " " + scpIfTrue.getLabel() + " " + scpIfFalse.getLabel();
    }

    @Override
    public List<Object> getObjects() {
        return Arrays.asList(obj);
    }
}
