package com.github.espylapiza.compiler_mxstar.pizza_ir;

public class FuncBuiltin extends Func {
    public FuncBuiltin(FuncAddr addr, String name, Type rtype) {
        super(addr, name, rtype);
    }

    public FuncBuiltin(FuncAddr addr, String name, Type rtype, ParamList params) {
        super(addr, name, rtype, params);
    }
}