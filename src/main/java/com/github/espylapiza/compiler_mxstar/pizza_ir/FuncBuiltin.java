package com.github.espylapiza.compiler_mxstar.pizza_ir;

public class FuncBuiltin extends FuncDefinition {
    /**
     * Construct a func with params.
     * @param addr
     * @param name
     * @param rtype
     * @param params
     */
    public FuncBuiltin(FuncAddr addr, String name, Type rtype, ParamList params) {
        super(addr, name, rtype, params);
    }

    @Override
    public String toString() {
        return "";
    }
}
