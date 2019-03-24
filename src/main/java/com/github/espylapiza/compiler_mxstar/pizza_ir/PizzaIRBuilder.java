package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.logging.Logger;

import com.google.gson.JsonObject;

import org.antlr.v4.runtime.tree.*;

public class PizzaIRBuilder {
    private final static Logger LOGGER = Logger.getLogger(PizzaIRBuilder.class.getName());

    public static PizzaIR fromParser(ParseTree parser) {
        PizzaIRVisitor visitor = new PizzaIRVisitor();

        LOGGER.info("register built-in types and functions");
        visitor.registerBuiltinType();
        visitor.registerBuiltinFunc();

        LOGGER.info("build IR");
        parser.accept(visitor);

        LOGGER.info("save data");
        JsonObject data = new JsonObject();
        data.add("Type", visitor.typeList.toJson());
        data.add("Func", visitor.funcList.toJson());
        data.add("Var", visitor.varList.toJson());

        return new PizzaIR(data);
    }
}