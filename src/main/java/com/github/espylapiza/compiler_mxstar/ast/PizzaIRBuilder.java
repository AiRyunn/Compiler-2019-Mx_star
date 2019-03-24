package com.github.espylapiza.compiler_mxstar.ast;

import java.util.logging.Logger;

import org.antlr.v4.runtime.tree.*;
import com.github.espylapiza.compiler_mxstar.nasm.*;
import com.google.gson.*;

public class PizzaIRBuilder {
    private final static Logger LOGGER = Logger.getLogger(PizzaIRBuilder.class.getName());

    public static PizzaIR fromParser(ParseTree parser) {
        PizzaIRVisitor visitor = new PizzaIRVisitor();

        LOGGER.info("register built-in types and functions");
        visitor.registerBuiltinType();
        visitor.registerBuiltinFunc();

        LOGGER.info("build IR");
        parser.accept(visitor);

        PizzaIR ir = new PizzaIR(visitor.data);

        return ir;
    }

}