package com.github.espylapiza.compiler_mxstar.pizza_ir;

import com.google.gson.annotations.Expose;

public abstract class Func extends Domain {
    private final FuncAddr addr;
    @Expose
    private final String name;
    @Expose
    private final Type rtype;
    @Expose
    protected ParamList params;

    public Func(FuncAddr addr, String name, Type rtype) {
        this.addr = addr;
        this.name = name;
        this.rtype = rtype;
    }

    public Func(FuncAddr addr, String name, Type rtype, ParamList params) {
        this.addr = addr;
        this.name = name;
        this.rtype = rtype;
        this.params = params;
    }

    public final FuncAddr getAddr() {
        return addr;
    }

    public final String getName() {
        return name;
    }

    public final Type getRtype() {
        return rtype;
    }

    public final ParamList getParams() {
        return params;
    }

    public final void setParams(ParamList params) {
        this.params = params;
    }

}
