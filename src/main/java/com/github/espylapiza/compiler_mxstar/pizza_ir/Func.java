package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

import com.github.espylapiza.compiler_mxstar.utils.Pair;
import com.google.gson.annotations.Expose;

class Func extends Domain {
    Class owner;
    @Expose
    protected String name;
    @Expose
    private Type rtype;
    @Expose
    private ParamList params;
    private VarList varList = new VarList();

    private List<Scope> scps = new ArrayList<Scope>();
    private Stack<Pair<Scope, Boolean>> scpStack = new Stack<Pair<Scope, Boolean>>();
    // private Scope scope;

    private int counter = 0;

    Func(Class owner, String name, Type rtype) {
        this.owner = owner;
        this.name = name;
        this.rtype = rtype;
    }

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

    void addParams(ParamList params) {
        this.params = params;
    }

    void addInstruction(Inst inst) {
        if (scpStack.lastElement().second) {
            return;
        }
        scpStack.lastElement().first.addInstruction(inst);
        if (inst instanceof InstBaseJump) {
            scpStack.lastElement().second = true;
        }
    }

    void jumpBreak() {
        if (scpStack.lastElement().second) {
            return;
        }
        for (ListIterator<Pair<Scope, Boolean>> it = scpStack.listIterator(scpStack.size()); it.hasPrevious();) {
            Pair<Scope, Boolean> pre = it.previous();
            if (pre.first.getType() == ScopeType.ENDLOOP) {
                scpStack.lastElement().first.addInstruction(new InstJump(pre.first));
                scpStack.lastElement().second = true;
            }
        }
    }

    void jumpContinue() {
        if (scpStack.lastElement().second) {
            return;
        }
        scpStack.lastElement().first.addInstruction(new InstJump(scpStack.lastElement().first));
        scpStack.lastElement().second = true;
    }

    Scope newScope(ScopeType type) {
        return new Scope(type, getAddr() + "." + type.toString() + "_" + (counter++));
    }

    // Scope newScope(String info) {
    //     return new Scope(getAddr() + "." + info + "_" + (counter++));
    // }

    // void enterScope(Scope scp) {
    //     scope = scp;
    // }

    void pushScope(Scope scp) {
        boolean dead = false;
        if (!scpStack.empty()) {
            dead = scpStack.lastElement().second;
        }
        scpStack.add(new Pair<Scope, Boolean>(scp, dead));
    }

    void popScope() {
        Pair<Scope, Boolean> top = scpStack.pop();
        if (!top.second && !scpStack.empty()) {
            top.first.addInstruction(new InstJump(scpStack.lastElement().first));
        }
        scps.add(top.first);
    }

    Object allocate(Object obj) {
        obj.setID(new ObjectID(varList.count()));
        varList.add(obj);
        return obj;
    }

    @Override
    public String toString() {
        String result = "func " + getAddr() + " (\n";
        for (int i = 0; i < params.count(); i++) {
            result += "\t" + varList.get(i) + ": " + varList.get(i).type.getName() + "\n";
        }
        result += ") {\n";
        result += "\tvar: (\n";
        for (int i = params.count(); i < varList.count(); i++) {
            if (varList.get(i).name != null) {
                result += "\t\t" + varList.get(i) + ": " + varList.get(i).type.getName() + ", " + varList.get(i).name
                        + "\n";
            }
        }
        result += "\t), (\n";
        for (int i = params.count(); i < varList.count(); i++) {
            if (varList.get(i).name == null) {
                result += "\t\t" + varList.get(i) + ": " + varList.get(i).type.getName() + "\n";
            }
        }
        result += "\t)\n";
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

enum ScopeType {
    FUNC, ENDFUNC, IF, ELSE, ENDIF, LOOP, ENDLOOP
}

class Scope {
    private ScopeType type;
    private String label;
    private List<Inst> insts = new ArrayList<Inst>();

    Scope(ScopeType type, String label) {
        this.type = type;
        this.label = label;
    }

    ScopeType getType() {
        return type;
    }

    String getLabel() {
        return "#" + label;
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
