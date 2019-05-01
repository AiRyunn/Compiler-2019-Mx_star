package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.ArrayList;
import java.util.List;

public final class Func extends Domain {
    private final FuncAddr addr;
    private final String name;
    private final Type rtype;
    private final VarList varList = new VarList();
    private final List<Scope> scps = new ArrayList<Scope>();
    private ParamList params;

    /**
     * @return the addr
     */
    public FuncAddr getAddr() {
        return addr;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the rtype
     */
    public Type getRtype() {
        return rtype;
    }

    /**
     * @return the varList
     */
    public VarList getVarList() {
        return varList;
    }

    /**
     * @return the scps
     */
    public List<Scope> getScps() {
        return scps;
    }

    /**
     * @return the params
     */
    public ParamList getParams() {
        return params;
    }

    /**
     * @param params the params to set
     */
    public void setParams(ParamList params) {
        this.params = params;
    }

    /**
     * Construct a func without params.
     * @param addr
     * @param name
     * @param rtype
     */
    public Func(FuncAddr addr, String name, Type rtype) {
        this.addr = addr;
        this.name = name;
        this.rtype = rtype;
    }

    /**
     * Construct a func with params.
     * @param addr
     * @param name
     * @param rtype
     * @param params
     */
    public Func(FuncAddr addr, String name, Type rtype, ParamList params) {
        this(addr, name, rtype);
        this.setParams(params);
    }

    /**
     * Allocate a variable for the func.
     * @param obj
     */
    public void allocateVariable(Object obj) {
        obj.setID(new ObjectID(getVarList().count()));
        getVarList().add(obj);
    }
}
