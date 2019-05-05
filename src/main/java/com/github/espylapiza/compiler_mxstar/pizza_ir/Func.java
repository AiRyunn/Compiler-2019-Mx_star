package com.github.espylapiza.compiler_mxstar.pizza_ir;

public class Func extends Domain {
    protected final FuncAddr addr;
    protected final String name;
    protected final Type rtype;
    protected ParamList params;

    /**
     * Construct a func with params.
     * @param addr
     * @param name
     * @param rtype
     * @param params
     */
    public Func(FuncAddr addr, String name, Type rtype, ParamList params) {
        this.addr = addr;
        this.name = name;
        this.rtype = rtype;
        this.setParams(params);
    }


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

}
