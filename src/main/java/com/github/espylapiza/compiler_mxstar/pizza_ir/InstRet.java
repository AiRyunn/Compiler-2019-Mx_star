package com.github.espylapiza.compiler_mxstar.pizza_ir;

class InstRet extends Inst {
    ObjectID ret;

    InstRet() {
        super();
    }

    InstRet(ObjectID ret) {
        super();
        this.ret = ret;
    }

    @Override
    public String toString() {
        return "ret " + ret;
    }
}