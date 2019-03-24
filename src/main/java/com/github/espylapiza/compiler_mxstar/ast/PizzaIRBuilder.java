package com.github.espylapiza.compiler_mxstar.ast;

import java.util.*;
import java.util.logging.Logger;

import org.antlr.v4.runtime.tree.*;
import com.github.espylapiza.compiler_mxstar.nasm.*;
import com.google.gson.*;

public class PizzaIRBuilder {
    private final static Logger LOGGER = Logger.getLogger(PizzaIRBuilder.class.getName());

    JsonObject data;

    public static PizzaIRBuilder fromParser(ParseTree parser) {
        PizzaIRVisitor builder = new PizzaIRVisitor();

        PizzaIRBuilder ir = new PizzaIRBuilder();

        LOGGER.info("register built-in types and functions");
        ir.registerBuiltinType();
        ir.registerBuiltinFunc();

        LOGGER.info("build IR");
        parser.accept(builder);

        return ir;
    }

    PizzaIRBuilder() {
    }

    public PizzaIRBuilder(JsonObject data) {
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

    void registerBuiltinType() {
        Type t_bool = new Type("bool", true);
        Arrays.asList("__lgcand__", "__lgcor__", "__eq__", "__ne__").forEach(method -> {
            String addr = t_bool.name + "." + method;
            t_bool.addMethod(method, addr);
            Func func = new Func(t_bool.name, method, t_bool.name);
            func.addParam(t_bool.name);
            func.addParam(t_bool.name);
            PizzaIRVisitor.funcList.addFunc(func);
        });
        Arrays.asList("__lgcnot__").forEach(method -> {
            String addr = t_bool.name + "." + method;
            t_bool.addMethod(method, addr);
            Func func = new Func(t_bool.name, method, t_bool.name);
            func.addParam(t_bool.name);
            PizzaIRVisitor.funcList.addFunc(func);
        });
        PizzaIRVisitor.typeList.addType(t_bool);

        Type t_int = new Type("int", true);
        Arrays.asList("__add__", "__sub__", "__mul__", "__div__", "__mod__", "__shl__", "__shr__", "__bitand__",
                "__bitxor__", "__bitor__").forEach(method -> {
                    String addr = t_int.name + "." + method;
                    t_int.addMethod(method, addr);
                    Func func = new Func(t_int.name, method, t_int.name);
                    func.addParam(t_int.name);
                    func.addParam(t_int.name);
                    PizzaIRVisitor.funcList.addFunc(func);
                });
        Arrays.asList("__lt__", "__gt__", "__le__", "__ge__", "__eq__", "__ne__").forEach(method -> {
            String addr = t_int.name + "." + method;
            t_int.addMethod(method, addr);
            Func func = new Func(t_int.name, method, "bool");
            func.addParam(t_int.name);
            func.addParam(t_int.name);
            PizzaIRVisitor.funcList.addFunc(func);
        });
        Arrays.asList("__pos__", "__neg__", "__bitinv__", "__preinc__", "__predec__", "__postinc__", "__postdec__")
                .forEach(method -> {
                    String addr = t_int.name + "." + method;
                    t_int.addMethod(method, addr);
                    Func func = new Func(t_int.name, method, t_int.name);
                    func.addParam(t_int.name);
                    PizzaIRVisitor.funcList.addFunc(func);
                });
        PizzaIRVisitor.typeList.addType(t_int);

        Type t_string = new Type("string", true);
        Arrays.asList("__add__").forEach(method -> {
            String addr = t_string.name + "." + method;
            t_string.addMethod(method, addr);
            Func func = new Func(t_string.name, method, t_string.name);
            func.addParam(t_string.name);
            func.addParam(t_string.name);
            PizzaIRVisitor.funcList.addFunc(func);
        });
        Arrays.asList("__lt__", "__gt__", "__le__", "__ge__", "__eq__", "__ne__").forEach(method -> {
            String addr = t_string.name + "." + method;
            t_string.addMethod(method, addr);
            Func func = new Func(t_string.name, method, "bool");
            func.addParam(t_string.name);
            func.addParam(t_string.name);
            PizzaIRVisitor.funcList.addFunc(func);
        });
        Arrays.asList("length", "parseInt").forEach(method -> {
            String addr = t_string.name + "." + method;
            t_string.addMethod(method, addr);
            Func func = new Func(t_string.name, method, "int");
            func.addParam("string");
            PizzaIRVisitor.funcList.addFunc(func);
        });
        Arrays.asList("ord").forEach(method -> {
            String addr = t_string.name + "." + method;
            t_string.addMethod(method, addr);
            Func func = new Func(t_string.name, method, "int");
            func.addParam("string");
            func.addParam("int");
            PizzaIRVisitor.funcList.addFunc(func);
        });
        Arrays.asList("substring").forEach(method -> {
            String addr = t_string.name + "." + method;
            t_string.addMethod(method, addr);
            Func func = new Func(t_string.name, method, "string");
            func.addParam("string");
            func.addParam("int");
            func.addParam("int");
            PizzaIRVisitor.funcList.addFunc(func);
        });
        PizzaIRVisitor.typeList.addType(t_string);

        Type t_array = new Type("__array__", true);
        Arrays.asList("size").forEach(method -> {
            String addr = t_array.name + "." + method;
            t_array.addMethod(method, addr);
            Func func = new Func(t_array.name, method, "int");
            func.addParam("__array__");
            PizzaIRVisitor.funcList.addFunc(func);
        });
        PizzaIRVisitor.typeList.addType(t_array);
    }

    void registerBuiltinFunc() {
        Func print = new Func(null, "print", "void");
        print.addParam("string");
        PizzaIRVisitor.funcList.addFunc(print);

        Func println = new Func(null, "println", "void");
        println.addParam("string");
        PizzaIRVisitor.funcList.addFunc(println);

        Func getInt = new Func(null, "getInt", "int");
        PizzaIRVisitor.funcList.addFunc(getInt);

        Func getString = new Func(null, "getString", "string");
        PizzaIRVisitor.funcList.addFunc(getString);

        Func toString = new Func(null, "toString", "string");
        toString.addParam("int");
        PizzaIRVisitor.funcList.addFunc(toString);
    }

}