package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.annotations.Expose;

public class Class extends Domain implements Cloneable {
    @Expose
    private String name;
    @Expose
    private Map<String, Type> memVar;
    @Expose
    private Map<String, Func> memMtd;

    public Class(String addr) {
        this.name = addr;
        memVar = new HashMap<String, Type>();
        memMtd = new HashMap<String, Func>();
    }

    public String getName() {
        return name;
    }

    public boolean hasVariable(String variableName) {
        return memVar.containsKey(variableName);
    }

    public boolean hasMethod(String methodName) {
        return memMtd.containsKey(methodName);
    }

    public void addVariable(String variableName, Type type) {
        if (hasVariable(variableName)) {
            assert false;
        }
        if (hasMethod(variableName)) {
            assert false;
        }
        memVar.put(variableName, type);
    }

    public void addMethod(Func func) {
        if (hasVariable(func.name)) {
            assert false;
        }
        if (hasMethod(func.name)) {
            assert false;
        }
        memMtd.put(func.name, func);
    }

    public Type getVarType(String member) {
        return memVar.get(member);
    }

    public Func getMethod(String member) {
        return memMtd.get(member);
    }
}
