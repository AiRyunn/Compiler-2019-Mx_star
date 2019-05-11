package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.Arrays;
import java.util.List;

public final class InstRet extends Inst implements InstBaseJump {
    public Object obj;

    public InstRet() {
        super();
    }

    public InstRet(Object obj) {
        super();
        this.obj = obj;
    }

    @Override
    public void accept(PizzaIRPartBaseVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        if (obj == null) {
            return "ret";
        } else {
            return "ret " + obj;
        }
    }

    @Override
    public List<Object> getObjects() {
        return Arrays.asList(obj);
    }
}
