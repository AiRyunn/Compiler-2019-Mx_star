package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.HashMap;
import java.util.Map;

public final class Class extends Domain implements Cloneable {
    private final String name;
    private Map<String, Type> memVar = new HashMap<String, Type>();
    private Map<String, Integer> memIndex = new HashMap<String, Integer>();
    private Map<String, FuncDefinition> memMtd = new HashMap<String, FuncDefinition>();

    /**
     * 
     * @param name
     */
    public Class(String name) {
        this.name = name;
    }

    /**
     * 
     * @return name
     */
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
        memIndex.put(name, memIndex.size());
    }

    public void addMethod(FuncDefinition func) {
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

    public int getVarIndex(String member) {
        return memIndex.get(member);
    }

    public FuncDefinition getMethod(String member) {
        return memMtd.get(member);
    }

    public int getSize() {
        return memVar.size() * 8;
    }
}
