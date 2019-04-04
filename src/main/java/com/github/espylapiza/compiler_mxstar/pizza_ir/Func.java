package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

class Func extends ProgramFragment {
    Class owner;
    @Expose
    protected String name;
    @Expose
    private Type rtype;
    @Expose
    private ParamList params;

    private List<Scope> scps = new ArrayList<Scope>();
    private Scope scope;

    Func(Class owner, String name, Type rtype, ParamList params) {
        this.owner = owner;
        this.name = name;
        this.rtype = rtype;
        this.params = params;
    }

    String getName() {
        return name;
    }

    String getAddr() {
        if (owner != null) {
            return owner.getName() + "." + name;
        }
        return name;
    }

    Type getRtype() {
        return rtype;
    }

    ParamList getParams() {
        return params;
    }

    void addInstruction(Inst inst) {
        scope.addInstruction(inst);
    }

    void packScope() {
        if (scope != null) {
            scps.add(scope);
        }
        scope = new Scope(getAddr() + "_" + scps.size());
    }

    void enter() {
        scope = new Scope(getAddr() + "_0");
    }

    @Override
    public String toString() {
        String result = "func " + getAddr();
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

class ParamList extends ProgramFragment {
    @Expose
    private List<Type> params;

    ParamList() {
        params = new ArrayList<Type>();
    }

    ParamList(Type type) {
        params = new ArrayList<Type>();
        params.add(type);
    }

    ParamList(List<Type> types) {
        params = types;
    }

    void add(Type type) {
        params.add(type);
    }

    Type getType(int index) {
        return params.get(index);
    }

    List<Type> getParams() {
        return params;
    }

    boolean match(ParamList rhs) {
        if (params.size() != rhs.params.size()) {
            return false;
        }
        for (int i = 0; i < params.size(); i++) {
            if (!params.get(i).equals(rhs.params.get(i))) {
                return false;
            }
        }
        return true;
    }

    int count() {
        return params.size();
    }
}

class Scope {
    private String label;
    private List<Inst> insts = new ArrayList<Inst>();

    Scope(String label) {
        this.label = label;
    }

    void addInstruction(Inst inst) {
        insts.add(inst);
    }

    @Override
    public String toString() {
        String result = "#" + label + "\n";
        for (Inst inst : insts) {
            result += "\t" + inst.toString() + "\n";
        }
        return result;
    }
}

enum BlockType {
    CLASS, FUNC, SCOPE, LOOP
}

class Block {
    int position;
    BlockType type;
    String name;
    Type rtype;

    Block(int position, BlockType type) {
        this.position = position;
        this.type = type;
    }

    Block(int position, BlockType type, String name, Type rtype) {
        this.position = position;
        this.type = type;
        this.name = name;
        this.rtype = rtype;
    }
}
