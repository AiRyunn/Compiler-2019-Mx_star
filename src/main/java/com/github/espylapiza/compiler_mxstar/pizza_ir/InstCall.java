package com.github.espylapiza.compiler_mxstar.pizza_ir;

public final class InstCall extends InstBaseCall {
    public Object dst;
    public Func func;
    public ParamList params;

    /**
     * 
     * @param dst
     * @param addr
     * @param params
     */
    public InstCall(Object dst, Func func, ParamList params) {
        this(func, params);
        this.dst = dst;
    }

    @Override
    public void accept(PizzaIRPartBaseVisitor visitor) {
        visitor.visit(this);
    }

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

    @Override
    public String toString() {
        String result = new String();
        if (dst != null) {
            result += dst + " = ";
        }
        result += "call " + func.getAddr();
        for (Object param : params) {
            result += " " + param;
        }
        return result;
    }
}
