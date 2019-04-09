package com.github.espylapiza.compiler_mxstar.pizza_ir;

import com.google.gson.annotations.Expose;

public class Func extends Domain {
    private FuncAddr addr;
    @Expose
    public String name;
    @Expose
    private Type rtype;
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

    public final Type getRtype() {
        return rtype;
    }

    public final ParamList getParams() {
        return params;
    }

    public final void addParams(ParamList params) {
        this.params = params;
    }

}
