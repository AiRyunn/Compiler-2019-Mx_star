package com.github.espylapiza.compiler_mxstar.pizza_ir;

public class FuncExtern extends Func {
    public FuncExtern(FuncAddr addr, String name, Type rtype, ParamList params) {
        super(addr, name, rtype, params);
    }

    @Override
    public String toString() {
        return "";
    }
}
