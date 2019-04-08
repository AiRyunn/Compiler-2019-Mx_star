package com.github.espylapiza.compiler_mxstar.pizza_ir;

abstract class InstBaseJump extends Inst {
}

class InstJump extends InstBaseJump {
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

class InstBr extends InstBaseJump {
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

    InstBr(Object obj, Scope scpIfTrue, Scope scpIfFalse) {
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

class InstRet extends InstBaseJump {
    private Object obj;

    InstRet() {
        super();
    }

    InstRet(Object obj) {
        super();
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "ret " + obj;
    }
}