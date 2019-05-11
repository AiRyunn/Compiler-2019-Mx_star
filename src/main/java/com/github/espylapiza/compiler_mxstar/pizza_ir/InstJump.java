package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.Arrays;
import java.util.List;

public final class InstJump extends Inst implements InstBaseJump {
    public Scope scp;

    InstJump() {
        super();
    }

    public InstJump(Scope to) {
        super();
        this.scp = to;
    }

    @Override
    public void accept(PizzaIRPartBaseVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "jump " + scp.getLabel();
    }

    @Override
    public List<Object> getObjects() {
        return Arrays.asList();
    }
}
