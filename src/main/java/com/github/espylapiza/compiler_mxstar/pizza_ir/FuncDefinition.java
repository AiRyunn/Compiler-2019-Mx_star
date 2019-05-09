package com.github.espylapiza.compiler_mxstar.pizza_ir;

public abstract class FuncDefinition extends Func {
    public FuncDefinition(FuncAddr addr, String name, Type rtype, ParamList params) {
        super(addr, name, rtype, params);
    }

    public FuncDefinition(FuncAddr addr, String name, Type rtype, ParamList params, Class ownerClass) {
        super(addr, name, rtype, params, ownerClass);
    }
}
