package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.*;

import com.google.gson.annotations.Expose;

class Class implements Cloneable {
    @Expose
    private String name;
    @Expose
    private Map<String, Type> memVar;
    @Expose
    private Map<String, Func> memMtd;

    Class(String addr) {
        this.name = addr;
        memVar = new HashMap<String, Type>();
        memMtd = new HashMap<String, Func>();
    }

    String getName() {
        return name;
    }

    boolean hasVariable(String variableName) {
        return memVar.containsKey(variableName);
    }

    boolean hasMethod(String methodName) {
        return memMtd.containsKey(methodName);
    }

    void addVariable(String variableName, Type type) {
        if (hasVariable(variableName)) {
            assert false;
        }
        if (hasMethod(variableName)) {
            assert false;
        }
        memVar.put(variableName, type);
    }

    void addMethod(Func func) {
        if (hasVariable(func.name)) {
            assert false;
        }
        if (hasMethod(func.name)) {
            assert false;
        }
        memMtd.put(func.name, func);
    }

    Type getVarType(String member) {
        return memVar.get(member);
    }

    Func getMethod(String member) {
        return memMtd.get(member);
    }
}
