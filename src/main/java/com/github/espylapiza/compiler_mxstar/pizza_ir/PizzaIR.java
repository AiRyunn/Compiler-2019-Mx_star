package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Stack;
import java.util.logging.Logger;

import com.github.espylapiza.compiler_mxstar.nasm.*;
import com.github.espylapiza.compiler_mxstar.utils.Pair;
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
    private transient HashMap<String, TypeSingle> typeTable;

    TypeTable() {
        typeTable = new HashMap<String, TypeSingle>();
    }

    void add(TypeSingle type) {
        if (typeTable.containsKey(type.getName())) {
            assert false;
        }
        typeTable.put(type.getName(), type);
    }

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
