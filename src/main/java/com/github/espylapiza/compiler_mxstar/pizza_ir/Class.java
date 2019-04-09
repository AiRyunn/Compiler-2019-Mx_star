package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.annotations.Expose;

public final class Class extends Domain implements Cloneable {
    @Expose
    private final String name;
    @Expose
    private Map<String, Type> memVar = new HashMap<String, Type>();
    @Expose
    private Map<String, Func> memMtd = new HashMap<String, Func>();

    public Class(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean hasVariable(String name) {
        return memVar.containsKey(name);
    }

    public boolean hasMethod(String name) {
        return memMtd.containsKey(name);
    }

    public void addVariable(String name, Type type) {
        if (hasVariable(name)) {
            assert false;
        }
        if (hasMethod(name)) {
            assert false;
        }
        memVar.put(name, type);
    }

    public void addMethod(Func func) {
        if (hasVariable(func.getName())) {
            assert false;
        }
        if (hasMethod(func.getName())) {
            assert false;
        }
        memMtd.put(func.getName(), func);
    }

    public Type getVarType(String member) {
        return memVar.get(member);
    }

    public Func getMethod(String member) {
        return memMtd.get(member);
    }
}
