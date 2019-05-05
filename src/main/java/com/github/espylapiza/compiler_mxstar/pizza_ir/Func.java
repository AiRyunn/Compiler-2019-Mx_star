package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.ArrayList;
import java.util.List;

public class Func extends Domain {
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
     * Allocate a variable for the func.
     * @param obj
     */
    public void allocateVariable(Object obj) {
        obj.setID(new ObjectID(varList.count()));
        varList.add(obj);
    }

    @Override
    public String toString() {
        if (scps.isEmpty()) {
            return "";
        }

        String result = "func " + getAddr() + " (\n";
        for (int i = 0; i < params.count(); i++) {
            result += "\t" + params.get(i) + ": " + params.get(i).type.getName() + "\n";
        }
        result += ") {\n";
        result += "\tvar: (\n";
        for (int i = params.count(); i < varList.count(); i++) {
            if (varList.get(i).name != null) {
                result += "\t\t" + varList.get(i) + ": " + varList.get(i).type.getName() + ", "
                        + varList.get(i).name + "\n";
            }
        }
        result += "\t), (\n";
        for (int i = params.count(); i < varList.count(); i++) {
            if (varList.get(i).name == null) {
                result += "\t\t" + varList.get(i) + ": " + varList.get(i).type.getName() + "\n";
            }
        }
        result += "\t)\n";
        boolean first = true;
        for (Scope scp : scps) {
            if (first) {
                first = false;
            } else {
                result += "\n";
            }
            result += scp.toString();
        }
        result += "}";
        return result;
    }
}
