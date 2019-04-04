package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.espylapiza.compiler_mxstar.nasm.*;
import com.google.gson.*;
import com.google.gson.annotations.Expose;

public class PizzaIR {
    JsonObject data;

    TypeTable typeTable = new TypeTable();
    ClassList classList = new ClassList();
    VarList varList = new VarList();
    FuncList funcList = new FuncList();
    Code code = new Code();

    PizzaIR() {
    }

    PizzaIR(JsonObject data) {
        this.data = data;
    }

    Type getTypeByName(String typeName) {
        return null;
    }

    public String getCode() {
        return null;
    }

    public NASM toNASM() {
        // TODO:
        return new NASM();
    }

    public void optimize() {
        // TODO: Optimization
    }

    Type getType(String name) {
        return typeTable.get(name);
    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(data);
    }
}

class TypeTable {
    private transient HashMap<String, SingleType> typeTable;

    TypeTable() {
        typeTable = new HashMap<String, SingleType>();
    }

    void add(SingleType type) {
        if (typeTable.containsKey(type.getName())) {
            assert false;
        }
        typeTable.put(type.getName(), type);
    }

    // TODO: to be deprecated
    Type get(String name) {
        return typeTable.get(name);
    }

    JsonElement toJson() {
        return new GsonBuilder().create().toJsonTree(typeTable, HashMap.class);
    }

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(typeTable);
    }
}

class ClassList {
    @Expose
    private Map<String, Class> classList;

    ClassList() {
        classList = new HashMap<String, Class>();
    }

    void add(Class class1) {
        classList.put(class1.getName(), class1);
    }

    Class get(String name) {
        return classList.get(name);
    }

    JsonElement toJson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJsonTree(classList, HashMap.class);
    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create().toJson(classList);
    }
}

class VarList {
    private List<Object> varList = new ArrayList<Object>();

    void add(Object variable) {
        varList.add(variable);
    }

    JsonElement toJson() {
        return new Gson().toJsonTree(varList, List.class);
    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create().toJson(varList);
    }
}

class FuncList {
    private Map<String, Func> funcList = new HashMap<String, Func>();

    void addFunc(Func func) {
        String addr = func.getAddr();
        if (funcList.containsKey(addr)) {
            assert false;
        }
        funcList.put(addr, func);
    }

    Func get(String addr) {
        return funcList.get(addr);
    }

    JsonElement toJson() {
        return new Gson().toJsonTree(funcList, HashMap.class);
    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create().toJson(funcList);
    }
}