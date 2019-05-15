package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.Arrays;
import java.util.List;

public final class InstBr extends Inst implements InstBaseJump {
    public Object obj;
    public BasicBlock scpIfTrue;
    public BasicBlock scpIfFalse;

    InstBr() {
        super();
    }

    InstBr(ObjectID src, BasicBlock scpIfTrue, BasicBlock scpIfFalse) {
        super();
        this.scpIfTrue = scpIfTrue;
        this.scpIfFalse = scpIfFalse;
    }

    public InstBr(Object obj, BasicBlock scpIfTrue, BasicBlock scpIfFalse) {
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
