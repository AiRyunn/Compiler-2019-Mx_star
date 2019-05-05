package com.github.espylapiza.compiler_mxstar.pizza_ir;

public abstract class FuncPrototype extends Func {
    public FuncPrototype(FuncAddr addr, String name, Type rtype, ParamList params) {
        super(addr, name, rtype, params);
    }

    @Override
    public String toString() {
        return "";
    }
}
