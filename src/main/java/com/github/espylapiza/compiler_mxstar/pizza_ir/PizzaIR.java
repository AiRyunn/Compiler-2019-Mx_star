package com.github.espylapiza.compiler_mxstar.pizza_ir;

// import com.google.gson.GsonBuilder;
// import com.google.gson.JsonObject;

public class PizzaIR {
    // JsonObject data;

    public TypeTable typeTable = new TypeTable();
    public ClassList classList = new ClassList();
    public FuncList funcList = new FuncList();

    public PizzaIR() {
    }

    // @Override
    // public String toString() {
    //     return new GsonBuilder().setPrettyPrinting().create().toJson(data);
    // }
}
