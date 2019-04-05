package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.Arrays;
import java.util.ListIterator;
import java.util.Stack;
import java.util.logging.Logger;

import com.github.espylapiza.compiler_mxstar.utils.Pair;

import org.antlr.v4.runtime.tree.*;

public class PizzaIRBuilder {
    private final static Logger LOGGER = Logger.getLogger(PizzaIRBuilder.class.getName());

    private PizzaIR ir;
    Class classArray;

    public void fromParser(ParseTree parser) {
        ir = new PizzaIR();

        PizzaIRVisitor visitor = new PizzaIRVisitor(ir);

        LOGGER.info("register built-in types and functions");
        registerBuiltinClass(ir);
        registerBuiltinFunc(ir);

        LOGGER.info("build IR");
        parser.accept(visitor);

        LOGGER.info("save data");
        // JsonObject data = new JsonObject();
        // data.add("type", ir.typeTable.toJson());
        // data.add("func", visitor.funcList.toJson());
        // data.add("var", visitor.varList.toJson());

        System.out.println(ir.typeTable.toString());
        System.out.println(ir.classList.toString());
        // System.out.println(visitor.varList.toJson().toString());
        // System.out.println(visitor.code.toString());
    }

    public PizzaIR getIR() {
        return ir;
    }

    private void registerBuiltinClass(PizzaIR ir) {
        NullType t_null = new NullType("null");
        ir.typeTable.add(t_null);

        Class c_void = new Class("void");
        VoidType t_void = new VoidType("void", c_void);
        ir.classList.add(c_void);
        ir.typeTable.add(t_void);

        Class c_bool = new Class("bool");
        BoolType t_bool = new BoolType("bool", c_bool);
        Arrays.asList("__lgcnot__").forEach(method -> {
            Func func = new Func(c_bool, method, t_bool, new ParamList());
            c_bool.addMethod(func);
            ir.funcList.addFunc(func);
        });
        Arrays.asList("__lgcand__", "__lgcor__", "__eq__", "__ne__").forEach(method -> {
            Func func = new Func(c_bool, method, t_bool, new ParamList(Arrays.asList(t_bool)));
            c_bool.addMethod(func);
            ir.funcList.addFunc(func);
        });
        ir.classList.add(c_bool);
        ir.typeTable.add(t_bool);

        Class c_int = new Class("int");
        IntType t_int = new IntType("int", c_int);
        Arrays.asList("__pos__", "__neg__", "__bitinv__", "__preinc__", "__predec__", "__postinc__", "__postdec__")
                .forEach(method -> {
                    Func func = new Func(c_int, method, t_int, new ParamList());
                    c_int.addMethod(func);
                    ir.funcList.addFunc(func);
                });
        Arrays.asList("__add__", "__sub__", "__mul__", "__div__", "__mod__", "__shl__", "__shr__", "__bitand__",
                "__bitxor__", "__bitor__").forEach(method -> {
                    Func func = new Func(c_int, method, t_int, new ParamList(Arrays.asList(t_int)));
                    c_int.addMethod(func);
                    ir.funcList.addFunc(func);
                });
        Arrays.asList("__lt__", "__gt__", "__le__", "__ge__", "__eq__", "__ne__").forEach(method -> {
            Func func = new Func(c_int, method, t_bool, new ParamList(Arrays.asList(t_int)));
            c_int.addMethod(func);
            ir.funcList.addFunc(func);
        });
        ir.classList.add(c_int);
        ir.typeTable.add(t_int);

        Class c_string = new Class("string");
        StringType t_string = new StringType("string", c_string);
        Arrays.asList("__add__").forEach(method -> {
            Func func = new Func(c_string, method, t_string, new ParamList(Arrays.asList(t_string)));
            c_string.addMethod(func);
            ir.funcList.addFunc(func);
        });
        Arrays.asList("__lt__", "__gt__", "__le__", "__ge__", "__eq__", "__ne__").forEach(method -> {
            Func func = new Func(c_string, method, t_bool, new ParamList(Arrays.asList(t_string)));
            c_string.addMethod(func);
            ir.funcList.addFunc(func);
        });
        Arrays.asList("length", "parseInt").forEach(method -> {
            Func func = new Func(c_string, method, t_int, new ParamList());
            c_string.addMethod(func);
            ir.funcList.addFunc(func);
        });
        Arrays.asList("ord").forEach(method -> {
            Func func = new Func(c_string, method, t_int, new ParamList(Arrays.asList(t_int)));
            c_string.addMethod(func);
            ir.funcList.addFunc(func);
        });
        Arrays.asList("substring").forEach(method -> {
            Func func = new Func(c_string, method, t_string, new ParamList(Arrays.asList(t_int, t_int)));
            c_string.addMethod(func);
            ir.funcList.addFunc(func);
        });
        ir.classList.add(c_string);
        ir.typeTable.add(t_string);

        Class c_array = new Class("__array__");
        Arrays.asList("size").forEach(method -> {
            Func func = new Func(c_array, method, t_int, new ParamList());
            c_array.addMethod(func);
            ir.funcList.addFunc(func);
        });
        ir.classList.add(c_array);
        classArray = c_array;

        Class c_func = new Class("__func__");
        FuncType t_func = new FuncType("__func__", c_void);
        ir.classList.add(c_func);
        ir.typeTable.add(t_func);

        Class c_method = new Class("__method__");
        MethodType t_method = new MethodType("__method__", c_void);
        ir.classList.add(c_method);
        ir.typeTable.add(t_method);
    }

    void registerBuiltinFunc(PizzaIR ir) {
        Func __init__ = new Func(null, "__init__", ir.getType("void"), new ParamList());
        ir.funcList.addFunc(__init__);

        Func print = new Func(null, "print", ir.getType("int"), new ParamList(ir.getType("string")));
        ir.funcList.addFunc(print);

        Func println = new Func(null, "println", ir.getType("void"), new ParamList(ir.getType("string")));
        ir.funcList.addFunc(println);

        Func getInt = new Func(null, "getInt", ir.getType("int"), new ParamList());
        ir.funcList.addFunc(getInt);

        Func getString = new Func(null, "getString", ir.getType("string"), new ParamList());
        ir.funcList.addFunc(getString);

        Func toString = new Func(null, "toString", ir.getType("string"), new ParamList(ir.getType("int")));
        ir.funcList.addFunc(toString);
    }
}

class Trace {
    private final static Logger LOGGER = Logger.getLogger(Trace.class.getName());

    private Class currentClass;
    private Func currentFunc;

    private Integer depth = 0;
    private Stack<Pair<Object, Integer>> varStack = new Stack<Pair<Object, Integer>>();
    private Stack<Domain> doms = new Stack<Domain>();

    boolean isGlobal() {
        return currentClass == null;
    }

    void addVar(Object variable) {
        LOGGER.fine("allocate variable: " + variable.type + " " + variable.name);
        if (variable.name != null) {
            varStack.add(new Pair<Object, Integer>(variable, depth));
        }
    }

    boolean hasVar(String name) {
        for (ListIterator<Pair<Object, Integer>> it = varStack.listIterator(varStack.size()); it.hasPrevious();) {
            Pair<Object, Integer> pre = it.previous();
            if (pre.first.name.equals(name)) {
                return true;
            }
        }
        return false;
    }

    Object getVar(String name) {
        for (ListIterator<Pair<Object, Integer>> it = varStack.listIterator(varStack.size()); it.hasPrevious();) {
            Pair<Object, Integer> pre = it.previous();
            if (pre.first.name != null && pre.first.name.equals(name)) {
                return pre.first;
            }
        }
        return null;
    }

    Class getCurrentClass() {
        return currentClass;
    }

    Func getCurrentFunc() {
        return currentFunc;
    }

    String getClassAddr() {
        if (currentClass == null) {
            return "";
        }
        return currentClass.getName();
    }

    void enter(Domain dom) {
        depth++;
        if (dom instanceof Class) {
            currentClass = (Class) dom;
        } else if (dom instanceof Func) {
            currentFunc = (Func) dom;
        }
        doms.add(dom);
    }

    void exit() {
        while (!varStack.isEmpty() && varStack.lastElement().second.equals(depth)) {
            LOGGER.fine(
                    "remove variable: " + varStack.lastElement().first.type + " " + varStack.lastElement().first.name);
            varStack.pop();
        }
        depth--;

        Domain dom = doms.pop();
        if (dom instanceof Class) {
            currentClass = null;
        } else if (dom instanceof Func) {
            currentFunc = null;
        }
    }

    boolean inLoop() {
        for (Domain dom : doms) {
            if (dom instanceof LoopDomain)
                return true;
        }
        return false;
    }

    boolean inFunc() {
        return currentFunc != null;
    }

    Type getRtype() {
        for (Domain dom : doms) {
            if (dom instanceof Func)
                return ((Func) dom).getRtype();
        }
        assert false;
        return null;
    }

    boolean canAllocate(String name) {
        for (ListIterator<Pair<Object, Integer>> it = varStack.listIterator(varStack.size()); it.hasPrevious();) {
            Pair<Object, Integer> pre = it.previous();
            if (pre.second < depth) {
                break;
            }
            if (pre.first.name.equals(name)) {
                return false;
            }
        }
        return true;
    }
}
