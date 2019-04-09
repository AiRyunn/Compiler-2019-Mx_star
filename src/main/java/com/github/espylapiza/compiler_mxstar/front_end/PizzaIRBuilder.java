package com.github.espylapiza.compiler_mxstar.front_end;

import java.util.Arrays;
import java.util.ListIterator;
import java.util.Stack;
import java.util.logging.Logger;

import com.github.espylapiza.compiler_mxstar.pizza_ir.Class;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Domain;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Func;
import com.github.espylapiza.compiler_mxstar.pizza_ir.FuncAddr;
import com.github.espylapiza.compiler_mxstar.pizza_ir.FuncBuiltin;
import com.github.espylapiza.compiler_mxstar.pizza_ir.FuncExtra;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Inst;
import com.github.espylapiza.compiler_mxstar.pizza_ir.DomainLoop;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ParamList;
import com.github.espylapiza.compiler_mxstar.pizza_ir.PizzaIR;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Scope;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ScopeType;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Type;
import com.github.espylapiza.compiler_mxstar.pizza_ir.TypeBool;
import com.github.espylapiza.compiler_mxstar.pizza_ir.TypeFunc;
import com.github.espylapiza.compiler_mxstar.pizza_ir.TypeInt;
import com.github.espylapiza.compiler_mxstar.pizza_ir.TypeMethod;
import com.github.espylapiza.compiler_mxstar.pizza_ir.TypeNull;
import com.github.espylapiza.compiler_mxstar.pizza_ir.TypeString;
import com.github.espylapiza.compiler_mxstar.pizza_ir.TypeVoid;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Object;
import com.github.espylapiza.compiler_mxstar.utils.Pair;

import org.antlr.v4.runtime.tree.ParseTree;

public class PizzaIRBuilder {
    private final static Logger LOGGER = Logger.getLogger(PizzaIRBuilder.class.getName());

    private PizzaIR ir = new PizzaIR();

    public void fromParser(ParseTree parser) {
        LOGGER.info("build PizzaIR from parser");

        LOGGER.info("register built-in classes and functions");
        registerBuiltinClass();
        registerBuiltinFunc();

        LOGGER.info("build IR");
        Mx_starParseTreeVisitor visitor = new Mx_starParseTreeVisitor(ir);
        parser.accept(visitor);

        // LOGGER.info("save data");
        // JsonObject data = new JsonObject();
        // data.add("type", ir.typeTable.toJson());
        // data.add("func", visitor.funcList.toJson());
        // data.add("var", visitor.varList.toJson());

        // System.out.println(ir.typeTable.toString());
        // System.out.println(ir.classList.toString());
        System.out.println(ir.funcList.toString());
    }

    public PizzaIR getIR() {
        return ir;
    }

    private void registerBuiltinClass() {
        TypeNull t_null = new TypeNull("null");
        ir.typeTable.add(t_null);

        Class c_void = new Class("void");
        TypeVoid t_void = new TypeVoid("void");
        ir.classList.add(c_void);
        ir.typeTable.add(t_void);

        Class c_bool = new Class("bool");
        TypeBool t_bool = new TypeBool("bool", c_bool);
        Arrays.asList("__lgcnot__").forEach(method -> {
            Func func = new Func(new FuncAddr().addClass(c_bool).addString(method), method, t_bool, new ParamList());
            c_bool.addMethod(func);
            ir.funcList.addFunc(func);
        });
        Arrays.asList("__lgcand__", "__lgcor__", "__eq__", "__ne__").forEach(method -> {
            Func func = new Func(new FuncAddr().addClass(c_bool).addString(method), method, t_bool,
                    new ParamList(Arrays.asList(t_bool)));
            c_bool.addMethod(func);
            ir.funcList.addFunc(func);
        });
        ir.classList.add(c_bool);
        ir.typeTable.add(t_bool);

        Class c_int = new Class("int");
        TypeInt t_int = new TypeInt("int", c_int);
        Arrays.asList("__pos__", "__neg__", "__bitinv__", "__preinc__", "__predec__", "__postinc__", "__postdec__")
                .forEach(method -> {
                    Func func = new Func(new FuncAddr().addClass(c_int).addString(method), method, t_int,
                            new ParamList());
                    c_int.addMethod(func);
                    ir.funcList.addFunc(func);
                });
        Arrays.asList("__add__", "__sub__", "__mul__", "__div__", "__mod__", "__shl__", "__shr__", "__bitand__",
                "__bitxor__", "__bitor__").forEach(method -> {
                    Func func = new Func(new FuncAddr().addClass(c_int).addString(method), method, t_int,
                            new ParamList(Arrays.asList(t_int)));
                    c_int.addMethod(func);
                    ir.funcList.addFunc(func);
                });
        Arrays.asList("__lt__", "__gt__", "__le__", "__ge__", "__eq__", "__ne__").forEach(method -> {
            Func func = new Func(new FuncAddr().addClass(c_int).addString(method), method, t_bool,
                    new ParamList(Arrays.asList(t_int)));
            c_int.addMethod(func);
            ir.funcList.addFunc(func);
        });
        ir.classList.add(c_int);
        ir.typeTable.add(t_int);

        Class c_string = new Class("string");
        TypeString t_string = new TypeString("string", c_string);
        Arrays.asList("__add__").forEach(method -> {
            Func func = new Func(new FuncAddr().addClass(c_string).addString(method), method, t_string,
                    new ParamList(Arrays.asList(t_string)));
            c_string.addMethod(func);
            ir.funcList.addFunc(func);
        });
        Arrays.asList("__lt__", "__gt__", "__le__", "__ge__", "__eq__", "__ne__").forEach(method -> {
            Func func = new Func(new FuncAddr().addClass(c_string).addString(method), method, t_bool,
                    new ParamList(Arrays.asList(t_string)));
            c_string.addMethod(func);
            ir.funcList.addFunc(func);
        });
        Arrays.asList("length", "parseInt").forEach(method -> {
            Func func = new Func(new FuncAddr().addClass(c_string).addString(method), method, t_int, new ParamList());
            c_string.addMethod(func);
            ir.funcList.addFunc(func);
        });
        Arrays.asList("ord").forEach(method -> {
            Func func = new Func(new FuncAddr().addClass(c_string).addString(method), method, t_int,
                    new ParamList(Arrays.asList(t_int)));
            c_string.addMethod(func);
            ir.funcList.addFunc(func);
        });
        Arrays.asList("substring").forEach(method -> {
            Func func = new Func(new FuncAddr().addClass(c_string).addString(method), method, t_string,
                    new ParamList(Arrays.asList(t_int, t_int)));
            c_string.addMethod(func);
            ir.funcList.addFunc(func);
        });
        ir.classList.add(c_string);
        ir.typeTable.add(t_string);

        Class c_array = new Class("__array__");
        Arrays.asList("size").forEach(method -> {
            Func func = new Func(new FuncAddr().addClass(c_array).addString(method), method, t_int, new ParamList());
            c_array.addMethod(func);
            ir.funcList.addFunc(func);
        });
        ir.classList.add(c_array);

        Class c_func = new Class("__func__");
        TypeFunc t_func = new TypeFunc("__func__", c_void);
        ir.classList.add(c_func);
        ir.typeTable.add(t_func);

        Class c_method = new Class("__method__");
        TypeMethod t_method = new TypeMethod("__method__", c_void);
        ir.classList.add(c_method);
        ir.typeTable.add(t_method);
    }

    private void registerBuiltinFunc() {
        FuncExtra __init__ = new FuncExtra(new FuncAddr().addString("__init__"), "__init__", ir.getType("void"),
                new ParamList());
        ir.funcList.addFunc(__init__);

        FuncBuiltin print = new FuncBuiltin(new FuncAddr().addString("print"), "print", ir.getType("int"),
                new ParamList(ir.getType("string")));
        ir.funcList.addFunc(print);

        FuncBuiltin println = new FuncBuiltin(new FuncAddr().addString("println"), "println", ir.getType("void"),
                new ParamList(ir.getType("string")));
        ir.funcList.addFunc(println);

        FuncBuiltin getInt = new FuncBuiltin(new FuncAddr().addString("getInt"), "getInt", ir.getType("int"),
                new ParamList());
        ir.funcList.addFunc(getInt);

        FuncBuiltin getString = new FuncBuiltin(new FuncAddr().addString("getString"), "getString",
                ir.getType("string"), new ParamList());
        ir.funcList.addFunc(getString);

        FuncBuiltin toString = new FuncBuiltin(new FuncAddr().addString("toString"), "toString", ir.getType("string"),
                new ParamList(ir.getType("int")));
        ir.funcList.addFunc(toString);
    }
}

class DomainTrace {
    private final static Logger LOGGER = Logger.getLogger(DomainTrace.class.getName());

    private Class currentClass;
    private FuncExtra currentFunc;

    private Integer depth = 0;
    private Stack<Pair<Object, Integer>> varStack = new Stack<Pair<Object, Integer>>();
    private Stack<Domain> doms = new Stack<Domain>();

    boolean isGlobal() {
        return currentClass == null;
    }

    void addVar(Object variable) {
        LOGGER.fine("allocate variable: " + variable.type + " " + variable.name);
        varStack.add(new Pair<Object, Integer>(variable, depth));
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

    FuncExtra getCurrentFunc() {
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
        } else if (dom instanceof FuncExtra) {
            currentFunc = (FuncExtra) dom;
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
            if (dom instanceof DomainLoop)
                return true;
        }
        return false;
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

class ScopeManager {
    private final static Logger LOGGER = Logger.getLogger(ScopeManager.class.getName());

    private Stack<FuncExtra> funcStack = new Stack<FuncExtra>();

    void enter(FuncExtra func) {
        LOGGER.fine("enterFunc: " + func.getAddr());
        funcStack.add(func);
    }

    void exit() {
        LOGGER.fine("exitFunc");
        funcStack.pop();
    }

    void addInstruction(Inst inst) {
        LOGGER.fine("addInstruction: " + inst.toString());
        funcStack.lastElement().addInstruction(inst);
    }

    void jumpBreak() {
        funcStack.lastElement().jumpBreak();
    }

    void jumpContinue() {
        funcStack.lastElement().jumpContinue();
    }

    Scope newScope(ScopeType type) {
        return funcStack.lastElement().newScope(type);
    }

    void pushScope(Scope scp) {
        funcStack.lastElement().pushScope(scp);
    }

    void popScope() {
        funcStack.lastElement().popScope();
    }
}