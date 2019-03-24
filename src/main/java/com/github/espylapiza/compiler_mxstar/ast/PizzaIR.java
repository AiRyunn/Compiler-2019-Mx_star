package com.github.espylapiza.compiler_mxstar.ast;

import com.github.espylapiza.compiler_mxstar.nasm.*;
import com.google.gson.*;

public class PizzaIR {
    JsonObject data;

    PizzaIR() {
    }

    PizzaIR(JsonObject data) {
        this.data = data;
    }

    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(data);
    }

    public String getCode() {
        return PizzaIRVisitor.code.toString();
    }

    public NASM toNASM() {
        // TODO:
        return new NASM();
    }

    public void optimize() {
        // TODO: Optimization
    }
}