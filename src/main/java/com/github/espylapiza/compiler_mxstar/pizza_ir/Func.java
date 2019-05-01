package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.ArrayList;
import java.util.List;

public class Func extends Domain {
    private FuncAddr addr;
    private String name;
    private Type rtype;
    protected ParamList params;
    public VarList varList = new VarList();
    public List<Scope> scps = new ArrayList<Scope>();

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

    public Object allocate(Object obj) {
        obj.setID(new ObjectID(varList.count()));
        varList.add(obj);
        return obj;
    }
}
