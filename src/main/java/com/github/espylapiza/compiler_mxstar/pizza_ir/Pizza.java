package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Logger;

class Object extends ProgramFragment {
    Class owner;
    String name;
    Type type;
    ObjectID id;

    Object(Class owner, String name, Type type, ObjectID id) {
        this.owner = owner;
        this.name = name;
        this.type = type;
        this.id = id;
    }
}

class ObjectID {
    private Integer id;

    ObjectID(int id) {
        this.id = id;
    }

    Integer getInt() {
        return id;
    }

    boolean equals(ObjectID other) {
        return id == other.getInt();
    }

    @Override
    public String toString() {
        return "$" + id;
    }
}

class Code {
    private final static Logger LOGGER = Logger.getLogger(Code.class.getName());

    private List<Func> funcs = new ArrayList<Func>();
    private Stack<Func> funcStack = new Stack<Func>();

    void addInstruction(Inst inst) {
        LOGGER.fine("addInstruction: " + inst.toString());
        funcStack.lastElement().addInstruction(inst);
    }

    void enterFunc(Func func) {
        LOGGER.fine("enterFunc: " + func.getAddr());
        funcStack.add(func);
        func.enter();
    }

    void exitFunc() {
        LOGGER.fine("exitFunc");
        funcs.add(funcStack.pop());
    }

    void packScope() {
        LOGGER.fine("packScope");
        funcStack.lastElement().packScope();
    }

    void newlabel() {
    }

    @Override
    public String toString() {
        String result = "";
        boolean first = true;
        for (Func sec : funcs) {
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