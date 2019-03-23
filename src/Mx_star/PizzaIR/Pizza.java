package Mx_star.PizzaIR;

import java.util.*;
import com.google.gson.*;
import logging.*;

import utils.Pair;

class Type implements Cloneable {
    String name;
    boolean builtin = false;
    Map<String, String> memVar;
    Map<String, String> memMethod;

    // 对 params 求 hash 以实现重载

    Type(String name) {
        this.name = name;
        memVar = new HashMap<String, String>();
        memMethod = new HashMap<String, String>();
    }

    Type(String name, boolean builtin) {
        this.name = name;
        this.builtin = builtin;
        memVar = new HashMap<String, String>();
        memMethod = new HashMap<String, String>();
    }

    boolean isBuiltin() {
        return builtin;
    }

    boolean hasMember(String name) {
        return memVar.containsKey(name);
    }

    boolean hasMethod(String name) {
        return memMethod.containsKey(name);
    }

    void addMember(String name, String type) {
        if (hasMember(name)) {
            assert false;
        }
        if (hasMethod(name)) {
            assert false;
        }
        memVar.put(name, type);
    }

    void addMethod(String method, String func) {
        if (hasMember(method)) {
            assert false;
        }
        if (hasMethod(method)) {
            // 重载相关
            assert false;
        }
        memMethod.put(method, func);
    }

    String getVarType(String member) {
        return memVar.get(member);
    }

    String getMethod(String member) {
        return memMethod.get(member);
    }

    String getFunc(String method) {
        return memMethod.get(method);
    }

    @Override
    public Object clone() {
        Type obj = null;
        try {
            obj = (Type) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return obj;
    }
}

class TypeList {
    HashMap<String, Type> types;

    TypeList() {
        types = new HashMap<String, Type>();
    }

    void addType(Type t) {
        if (types.containsKey(t.name)) {
            assert false;
        }
        types.put(t.name, t);
    }

    boolean hasType(String name) {
        while (name.endsWith("[]")) {
            name = name.substring(0, name.length() - 2);
        }
        return types.containsKey(name);
    }

    Type getType(String name) {
        if (name.endsWith("[]")) {
            Type arraytype = (Type) types.get("__array__").clone();
            arraytype.name = name.substring(0, name.length() - 2);
            return arraytype;
        }
        return types.get(name);
    }

    JsonElement toJson() {
        return new Gson().toJsonTree(types, HashMap.class);
    }
}

class Instruction {
    String name;
    String type;
    String method;
    List<String> params;

    Instruction(String name, String type, String method, ArrayList<String> params) {
        this.name = name;
        this.type = type;
        this.method = method;
        this.params = params;
    }

    public static Instruction byValue(String name, String type, String value) {
        if (type.equals("void")) {
            assert false;
        }
        var list = new ArrayList<String>();
        list.add(value);
        return new Instruction(name, type, "%value", list);
    }

    public static Instruction byMethod(String name, String type, String method, ArrayList<String> params) {
        return new Instruction(name, type, "method", params);
    }
}

class Instructions {
    List<Instruction> insts;

    Instructions() {
        insts = new ArrayList<Instruction>();
    }

    public void concat(Instructions inst2) {
        if (inst2.insts.size() > 0) {
            insts.addAll(inst2.insts);
        }
    }

    public void add(Instruction obj) {
        insts.add(obj);
    }

    public JsonElement toJson() {
        return new Gson().toJsonTree(insts, ArrayList.class);
    }
}

class Variable {
    int id;
    String name;
    String type;
    String owner;

    Variable(int id, String name, String type, String owner) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.owner = owner;
    }
}

class VarList {
    Vector<Variable> list = new Vector<Variable>();
    Integer counter = 0;

    void add(Variable variable) {
        list.add(variable);
    }

    String getType(int id) {
        // int id = Integer.parseInt(name.substring(1, name.length()));
        return list.get(id).type;
    }

    JsonElement toJson() {
        return new Gson().toJsonTree(list, Vector.class);
    }
}

class ParamsInstance {
    Vector<Pair<String, String>> params = new Vector<Pair<String, String>>();

    void add(String name, String type) {
        params.add(new Pair<String, String>(name, type));
    }

    int count() {
        return params.size();
    }
}

class Params {
    Vector<String> params = new Vector<String>();

    void add(String type) {
        params.add(type);
    }

    boolean match(Params params2) {
        if (params.size() != params2.params.size()) {
            return false;
        }
        for (int i = 0; i < params.size(); i++) {
            if (!params.get(i).equals(params2.params.get(i))) {
                return false;
            }
        }
        return true;
    }

    int count() {
        return params.size();
    }
}

class Func {
    String owner;
    String name;
    String rtype;
    Params params;

    Func(String owner, String name, String rtype) {
        this.owner = owner;
        this.name = name;
        this.rtype = rtype;
        params = new Params();
    }

    void addParam(String type) {
        params.add(type);
    }
}

class FuncList {
    Map<String, Func> funcList = new HashMap<String, Func>();

    boolean addFunc(Func func) {
        String addr = func.name;
        if (func.owner != null) {
            addr = func.owner + "." + addr;
        }
        if (funcList.containsKey(addr)) {
            return false;
        }
        funcList.put(addr, func);
        return true;
    }

    Func getFunc(String addr) {
        return funcList.get(addr);
    }

    String getRtype(String addr) {
        return funcList.get(addr).rtype;
    }

    JsonElement toJson() {
        return new Gson().toJsonTree(funcList, HashMap.class);
    }
}

enum BlockType {
    CLASS, FUNC, SCOPE, LOOP
}

class Block {
    int position;
    BlockType type;
    String name, rtype;

    Block(int position, BlockType type) {
        this.position = position;
        this.type = type;

    }

    Block(int position, BlockType type, String name, String rtype) {
        this.position = position;
        this.type = type;
        this.name = name;
        this.rtype = rtype;
    }
}

class Domain {
    List<String> classTrace = new ArrayList<String>();
    List<Block> block = new ArrayList<Block>();

    Integer depth = 0;
    Stack<Pair<Variable, Integer>> varStack = new Stack<Pair<Variable, Integer>>();

    boolean isGlobal() {
        return classTrace.isEmpty();
    }

    void addVar(Variable variable) {
        Logging.debug("allocate variable: " + variable.type + " " + variable.name);
        varStack.add(new Pair<Variable, Integer>(variable, depth));
    }

    boolean hasVar(String name) {
        for (var it = varStack.listIterator(varStack.size()); it.hasPrevious();) {
            var pre = it.previous();
            if (pre.first.name.equals(name)) {
                return true;
            }
        }
        return false;
    }

    Variable getVar(String name) {
        for (var it = varStack.listIterator(varStack.size()); it.hasPrevious();) {
            var pre = it.previous();
            if (pre.first.name.equals(name)) {
                return pre.first;
            }
        }
        return null;
    }

    String getVarType(String name) {
        for (var it = varStack.listIterator(varStack.size()); it.hasPrevious();) {
            var pre = it.previous();
            if (pre.first.name.equals(name)) {
                return pre.first.type;
            }
        }
        assert false;
        return null;
    }

    String getLastClass() {
        if (classTrace.isEmpty()) {
            return null;
        }
        return classTrace.get(classTrace.size() - 1);
    }

    String getClassTrace() {
        if (classTrace.isEmpty()) {
            return null;
        }

        String result = "";
        for (var it = classTrace.iterator(); it.hasNext();) {
            result += it.next();
            if (it.hasNext()) {
                result += ".";
            }
        }
        return result;
    }

    String getAddr() {
        String trace = getClassTrace();
        String currentFunc = getCurrentFunc();
        if (trace == null) {
            return currentFunc;
        } else if (currentFunc == null) {
            return trace;
        } else {
            return trace + "." + currentFunc;
        }
    }

    void enterClass(String className) {
        depth++;
        classTrace.add(className);
        block.add(new Block(-1, BlockType.CLASS));
    }

    void exitClass() {
        while (!varStack.isEmpty() && varStack.lastElement().second.equals(depth)) {
            varStack.pop();
        }
        depth--;
        classTrace.remove(classTrace.size() - 1);
        block.remove(block.size() - 1);
    }

    void enterFunc(String owner, String name, String rtype) {
        depth++;
        block.add(new Block(-1, BlockType.FUNC, name, rtype));
    }

    void exitFunc() {
        while (!varStack.isEmpty() && varStack.lastElement().second.equals(depth)) {
            Logging.debug(
                    "remove variable: " + varStack.lastElement().first.type + " " + varStack.lastElement().first.name);
            varStack.pop();
        }
        depth--;
        block.remove(block.size() - 1);
    }

    void enterScope(int position) {
        Logging.debug("enterCondition");
        depth++;
        block.add(new Block(position, BlockType.SCOPE));
    }

    void exitScope() {
        Logging.debug("exitCondition");
        while (!varStack.isEmpty() && varStack.lastElement().second.equals(depth)) {
            varStack.pop();
        }
        depth--;
        block.remove(block.size() - 1);
    }

    void enterLoop(int position) {
        Logging.debug("enterLoop");
        depth++;
        block.add(new Block(position, BlockType.LOOP));
    }

    void exitLoop() {
        Logging.debug("exitLoop");
        while (!varStack.isEmpty() && varStack.lastElement().second.equals(depth)) {
            varStack.pop();
        }
        depth--;
        block.remove(block.size() - 1);
    }

    boolean inLoop() {
        for (var b : block) {
            if (b.type == BlockType.LOOP)
                return true;
        }
        return false;
    }

    boolean inFunc() {
        for (var b : block) {
            if (b.type == BlockType.FUNC)
                return true;
        }
        return false;
    }

    String getRtype() {
        for (var b : block) {
            if (b.type == BlockType.FUNC)
                return b.rtype;
        }
        assert false;
        return null;
    }

    String getCurrentFunc() {
        for (var b : block) {
            if (b.type == BlockType.FUNC)
                return b.name;
        }
        return null;
    }

    boolean canAllocate(String name) {
        for (var it = varStack.listIterator(varStack.size()); it.hasPrevious();) {
            var pre = it.previous();
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