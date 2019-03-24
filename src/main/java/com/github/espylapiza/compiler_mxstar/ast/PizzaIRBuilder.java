package com.github.espylapiza.compiler_mxstar.ast;

import java.util.*;
import java.util.logging.Logger;

import org.antlr.v4.runtime.tree.*;
import com.github.espylapiza.compiler_mxstar.nasm.*;
import com.google.gson.*;

enum ListenState {
    TYPE_DECLARATION, MEMBER_DECLARATION, SEMANTIC_ANALYSIS
}

public class PizzaIRBuilder {
    private final static Logger LOGGER = Logger.getLogger(PizzaIRBuilder.class.getName());

    JsonObject data;

    public static TypeList typeList = new TypeList();
    public static FuncList funcList = new FuncList();
    public static VarList varList = new VarList();

    public static Domain dom = new Domain();
    public static int counter = 0;

    public static Code code = new Code();

    public static PizzaIRBuilder fromParser(ParseTree parser) {
        PizzaIRVisitor builder = new PizzaIRVisitor();

        PizzaIRBuilder ir = new PizzaIRBuilder();

        LOGGER.info("register built-in types and functions");
        ir.registerBuiltinType();
        ir.registerBuiltinFunc();

        LOGGER.info("build IR");
        parser.accept(builder);

        return ir;
        // ParseTreeWalker walker = new ParseTreeWalker();
        // ProgramListener listener = new ProgramListener();

        // PizzaIRBuilder ir = new PizzaIRBuilder();

        // LOGGER.info("register built-in types and functions");
        // ir.registerBuiltinType();
        // ir.registerBuiltinFunc();

        // LOGGER.info("TYPE_DECLARATION");
        // state = ListenState.TYPE_DECLARATION;
        // walker.walk(listener, parser);

        // LOGGER.info("MEMBER_DECLARATION");
        // state = ListenState.MEMBER_DECLARATION;
        // walker.walk(listener, parser);

        // LOGGER.info("TRANSLATION");
        // state = ListenState.TRANSLATION;
        // walker.walk(listener, parser);

        // LOGGER.info("check main function");
        // Func mainFunc = funcList.getFunc("main");
        // if (mainFunc == null || !mainFunc.rtype.equals("int") ||
        // mainFunc.params.count() != 0) {
        // assert false;
        // }

        // LOGGER.info("save data");
        // ir.data = listener.data;

        // return ir;
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
        return code.toString();
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
            PizzaIRBuilder.funcList.addFunc(func);
        });
        Arrays.asList("__lgcnot__").forEach(method -> {
            String addr = t_bool.name + "." + method;
            t_bool.addMethod(method, addr);
            Func func = new Func(t_bool.name, method, t_bool.name);
            func.addParam(t_bool.name);
            PizzaIRBuilder.funcList.addFunc(func);
        });
        PizzaIRBuilder.typeList.addType(t_bool);

        Type t_int = new Type("int", true);
        Arrays.asList("__add__", "__sub__", "__mul__", "__div__", "__mod__", "__shl__", "__shr__", "__bitand__",
                "__bitxor__", "__bitor__").forEach(method -> {
                    String addr = t_int.name + "." + method;
                    t_int.addMethod(method, addr);
                    Func func = new Func(t_int.name, method, t_int.name);
                    func.addParam(t_int.name);
                    func.addParam(t_int.name);
                    PizzaIRBuilder.funcList.addFunc(func);
                });
        Arrays.asList("__lt__", "__gt__", "__le__", "__ge__", "__eq__", "__ne__").forEach(method -> {
            String addr = t_int.name + "." + method;
            t_int.addMethod(method, addr);
            Func func = new Func(t_int.name, method, "bool");
            func.addParam(t_int.name);
            func.addParam(t_int.name);
            PizzaIRBuilder.funcList.addFunc(func);
        });
        Arrays.asList("__pos__", "__neg__", "__bitinv__", "__preinc__", "__predec__", "__postinc__", "__postdec__")
                .forEach(method -> {
                    String addr = t_int.name + "." + method;
                    t_int.addMethod(method, addr);
                    Func func = new Func(t_int.name, method, t_int.name);
                    func.addParam(t_int.name);
                    PizzaIRBuilder.funcList.addFunc(func);
                });
        PizzaIRBuilder.typeList.addType(t_int);

        Type t_string = new Type("string", true);
        Arrays.asList("__add__").forEach(method -> {
            String addr = t_string.name + "." + method;
            t_string.addMethod(method, addr);
            Func func = new Func(t_string.name, method, t_string.name);
            func.addParam(t_string.name);
            func.addParam(t_string.name);
            PizzaIRBuilder.funcList.addFunc(func);
        });
        Arrays.asList("__lt__", "__gt__", "__le__", "__ge__", "__eq__", "__ne__").forEach(method -> {
            String addr = t_string.name + "." + method;
            t_string.addMethod(method, addr);
            Func func = new Func(t_string.name, method, "bool");
            func.addParam(t_string.name);
            func.addParam(t_string.name);
            PizzaIRBuilder.funcList.addFunc(func);
        });
        Arrays.asList("length", "parseInt").forEach(method -> {
            String addr = t_string.name + "." + method;
            t_string.addMethod(method, addr);
            Func func = new Func(t_string.name, method, "int");
            func.addParam("string");
            PizzaIRBuilder.funcList.addFunc(func);
        });
        Arrays.asList("ord").forEach(method -> {
            String addr = t_string.name + "." + method;
            t_string.addMethod(method, addr);
            Func func = new Func(t_string.name, method, "int");
            func.addParam("string");
            func.addParam("int");
            PizzaIRBuilder.funcList.addFunc(func);
        });
        Arrays.asList("substring").forEach(method -> {
            String addr = t_string.name + "." + method;
            t_string.addMethod(method, addr);
            Func func = new Func(t_string.name, method, "string");
            func.addParam("string");
            func.addParam("int");
            func.addParam("int");
            PizzaIRBuilder.funcList.addFunc(func);
        });
        PizzaIRBuilder.typeList.addType(t_string);

        Type t_array = new Type("__array__", true);
        Arrays.asList("size").forEach(method -> {
            String addr = t_array.name + "." + method;
            t_array.addMethod(method, addr);
            Func func = new Func(t_array.name, method, "int");
            func.addParam("__array__");
            PizzaIRBuilder.funcList.addFunc(func);
        });
        PizzaIRBuilder.typeList.addType(t_array);
    }

    void registerBuiltinFunc() {
        Func print = new Func(null, "print", "void");
        print.addParam("string");
        PizzaIRBuilder.funcList.addFunc(print);

        Func println = new Func(null, "println", "void");
        println.addParam("string");
        PizzaIRBuilder.funcList.addFunc(println);

        Func getInt = new Func(null, "getInt", "int");
        PizzaIRBuilder.funcList.addFunc(getInt);

        Func getString = new Func(null, "getString", "string");
        PizzaIRBuilder.funcList.addFunc(getString);

        Func toString = new Func(null, "toString", "string");
        toString.addParam("int");
        PizzaIRBuilder.funcList.addFunc(toString);
    }

    static void allocateVariable(String name, String type) {
        if (!dom.canAllocate(name)) {
            assert false;
        }
        counter++;
        Variable variable = new Variable(counter, name, type, dom.getAddr());
        varList.add(variable);
        dom.addVar(variable);
    }
}