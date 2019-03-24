package com.github.espylapiza.compiler_mxstar.ast;

import java.util.*;
import java.util.logging.Logger;

import com.google.gson.*;

import com.github.espylapiza.compiler_mxstar.utils.Pair;

class Type implements Cloneable {
    String name;
    boolean builtin = false;
    Map<String, String> memVar;
    Map<String, String> memMethod;

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
    private final static Logger LOGGER = Logger.getLogger(Domain.class.getName());

    List<String> classTrace = new ArrayList<String>();
    List<Block> block = new ArrayList<Block>();

    Integer depth = 0;
    Stack<Pair<Variable, Integer>> varStack = new Stack<Pair<Variable, Integer>>();

    boolean isGlobal() {
        return classTrace.isEmpty();
    }

    void addVar(Variable variable) {
        LOGGER.fine("allocate variable: " + variable.type + " " + variable.name);
        varStack.add(new Pair<Variable, Integer>(variable, depth));
    }

    boolean hasVar(String name) {
        for (ListIterator<Pair<Variable, Integer>> it = varStack.listIterator(varStack.size()); it.hasPrevious();) {
            Pair<Variable, Integer> pre = it.previous();
            if (pre.first.name.equals(name)) {
                return true;
            }
        }
        return false;
    }

    Variable getVar(String name) {
        for (ListIterator<Pair<Variable, Integer>> it = varStack.listIterator(varStack.size()); it.hasPrevious();) {
            Pair<Variable, Integer> pre = it.previous();
            if (pre.first.name.equals(name)) {
                return pre.first;
            }
        }
        return null;
    }

    String getVarType(String name) {
        for (ListIterator<Pair<Variable, Integer>> it = varStack.listIterator(varStack.size()); it.hasPrevious();) {
            Pair<Variable, Integer> pre = it.previous();
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
        for (Iterator<String> it = classTrace.iterator(); it.hasNext();) {
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
            LOGGER.fine(
                    "remove variable: " + varStack.lastElement().first.type + " " + varStack.lastElement().first.name);
            varStack.pop();
        }
        depth--;
        block.remove(block.size() - 1);
    }

    void enterCondition(int position) {
        LOGGER.fine("enterCondition");
        depth++;
        block.add(new Block(position, BlockType.SCOPE));
    }

    void exitCondition() {
        LOGGER.fine("exitCondition");
        while (!varStack.isEmpty() && varStack.lastElement().second.equals(depth)) {
            varStack.pop();
        }
        depth--;
        block.remove(block.size() - 1);
    }

    void enterLoop(int position) {
        LOGGER.fine("enterLoop");
        depth++;
        block.add(new Block(position, BlockType.LOOP));
    }

    void exitLoop() {
        LOGGER.fine("exitLoop");
        while (!varStack.isEmpty() && varStack.lastElement().second.equals(depth)) {
            varStack.pop();
        }
        depth--;
        block.remove(block.size() - 1);
    }

    boolean inLoop() {
        for (Block b : block) {
            if (b.type == BlockType.LOOP)
                return true;
        }
        return false;
    }

    boolean inFunc() {
        for (Block b : block) {
            if (b.type == BlockType.FUNC)
                return true;
        }
        return false;
    }

    String getRtype() {
        for (Block b : block) {
            if (b.type == BlockType.FUNC)
                return b.rtype;
        }
        assert false;
        return null;
    }

    String getCurrentFunc() {
        for (Block b : block) {
            if (b.type == BlockType.FUNC)
                return b.name;
        }
        return null;
    }

    boolean canAllocate(String name) {
        for (ListIterator<Pair<Variable, Integer>> it = varStack.listIterator(varStack.size()); it.hasPrevious();) {
            Pair<Variable, Integer> pre = it.previous();
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

enum InstructionType {
    ASSIGNMENT, CALL, RETURN, CONDITIONJUMP,
}

class Instruction {
    InstructionType type;
    Integer dst, src;
    Vector<Integer> params;
    String label1, label2;

    Instruction(InstructionType type) {
        this.type = type;
    }

    static Instruction newAssignment(int dst, int src) {
        Instruction inst = new Instruction(InstructionType.ASSIGNMENT);
        inst.dst = dst;
        inst.src = src;
        return inst;
    }

    static Instruction newCall(int dst, Vector<Integer> params) {
        Instruction inst = new Instruction(InstructionType.CALL);
        inst.dst = dst;
        inst.params = params;
        return inst;
    }

    static Instruction newReturn() {
        return new Instruction(InstructionType.RETURN);
    }

    static Instruction newReturn(int src) {
        Instruction inst = new Instruction(InstructionType.RETURN);
        inst.src = src;
        return inst;
    }

    static Instruction newConditionJump(int src, String label1, String label2) {
        Instruction inst = new Instruction(InstructionType.CONDITIONJUMP);
        inst.src = src;
        inst.label1 = label1;
        inst.label2 = label2;
        return inst;
    }

    @Override
    public String toString() {
        String result = "";
        switch (type) {
        case ASSIGNMENT:
            result = "$" + dst + " = " + "$" + src;
            break;
        case CALL:
            result = "$" + dst + " =";
            for (Integer param : params) {
                result += " $" + param;
            }
            break;
        case RETURN:
            result = "return";
            if (src != null) {
                result += " $" + src;
            }
            break;
        case CONDITIONJUMP:
            result = "if $" + src + " jump #" + label1 + " else jump #" + label2;
            break;
        }
        return result;
    }
}

class Scope {
    String label;
    Vector<Instruction> insts = new Vector<Instruction>();

    Scope(String label) {
        this.label = label;
    }

    void addInstruction(Instruction inst) {
        insts.add(inst);
    }

    @Override
    public String toString() {
        String result = "#" + label + ":\n";
        for (Instruction inst : insts) {
            result += "\t" + inst.toString() + "\n";
        }
        return result;
    }
}

class Section {
    Vector<Scope> scps = new Vector<Scope>();
    Scope scope;
    String addr;

    Section(String addr) {
        scope = new Scope(addr + "_0");
        this.addr = addr;
    }

    void addInstruction(Instruction inst) {
        scope.addInstruction(inst);
    }

    void packScope() {
        if (scope != null) {
            scps.add(scope);
        }
        scope = new Scope(addr + "_" + scps.size());
    }

    @Override
    public String toString() {
        String result = "func " + addr;
        result += " {\n";
        boolean first = true;
        for (Scope scp : scps) {
            if (first) {
                first = false;
            } else {
                result += "\n";
            }
            result += scp.toString();
        }
        result += "}";
        return result;
    }
}

class Code {
    private final static Logger LOGGER = Logger.getLogger(Code.class.getName());

    Vector<Section> secs = new Vector<Section>();
    Section section;

    void addInstruction(Instruction inst) {
        section.addInstruction(inst);
    }

    void newSection(String addr) {
        LOGGER.fine("newSection: " + addr);
        section = new Section(addr);
    }

    void packSection() {
        LOGGER.fine("packSection");
        secs.add(section);
        section = null;
    }

    void packScope() {
        LOGGER.fine("packScope");
        section.packScope();
    }

    @Override
    public String toString() {
        String result = "";
        boolean first = true;
        for (Section sec : secs) {
            if (first) {
                first = false;
            } else {
                result += "\n\n";
            }
            result += sec.toString();
        }
        return result;
    }
}