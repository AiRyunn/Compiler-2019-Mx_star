package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.ArrayList;
import java.util.List;

import com.github.espylapiza.compiler_mxstar.nasm.NASM;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class PizzaIR {
    JsonObject data;

    public TypeTable typeTable = new TypeTable();
    public ClassList classList = new ClassList();
    VarList varList = new VarList();
    public FuncList funcList = new FuncList();

    public PizzaIR() {
    }

    PizzaIR(JsonObject data) {
        this.data = data;
    }

    Type getTypeByName(String typeName) {
        return null;
    }

    public NASM toNASM() {
        // TODO:
        return new NASM();
    }

    public void optimize() {
        // TODO: Optimization
    }

    public Type getType(String name) {
        return typeTable.get(name);
    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(data);
    }
}

class VarList {
    private List<Object> varList = new ArrayList<Object>();

    void add(Object variable) {
        varList.add(variable);
    }

    int count() {
        return varList.size();
    }

    Object get(int index) {
        return varList.get(index);
    }

    JsonElement toJson() {
        return new Gson().toJsonTree(varList, List.class);
    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create().toJson(varList);
    }
}
