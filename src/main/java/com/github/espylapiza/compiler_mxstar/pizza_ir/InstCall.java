package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.ArrayList;
import java.util.List;

public final class InstCall extends Inst {
    // public Object dst;
    public Func func;
    public ParamList params;
    public Object objThis;

    /**
     * 
     * @param addr
     * @param params
     */
    public InstCall(Func func, ParamList params) {
        super();
        this.func = func;
        this.params = params;
    }

    /**
     * 
     * @param dst
     * @param addr
     * @param params
     */
    public InstCall(Object dst, Func func, ParamList params) {
        super(dst);
        this.func = func;
        this.params = params;
    }

    /**
     * 
     * @param dst
     * @param addr
     * @param params
     * @param objThis
     */
    public InstCall(Object dst, Func func, ParamList params, Object objThis) {
        super(dst);
        this.func = func;
        this.params = params;
        this.objThis = objThis;
    }

    @Override
    public void accept(PizzaIRPartBaseVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        String result = new String();
        if (dst != null) {
            result += dst + " = ";
        }
        result += "call " + func.getAddr();
        if (objThis != null) {
            result += " " + objThis;
        }
        for (Object param : params) {
            result += " " + param;
        }
        return result;
    }

    @Override
    public List<Object> getObjects() {
        List<Object> result = new ArrayList<Object>();
        for (Object obj : params) {
            result.add(obj);
        }
        result.add(objThis);
        return result;
    }
}
