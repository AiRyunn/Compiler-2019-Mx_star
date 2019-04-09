package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

public final class FuncExtra extends Func {
    private class ScopeWithStatus {
        final Scope scope;
        boolean dead;

        ScopeWithStatus(Scope scope, boolean dead) {
            this.scope = scope;
            this.dead = dead;
        }
    }

    private VarList varList = new VarList();
    private List<Scope> scps = new ArrayList<Scope>();
    private Stack<ScopeWithStatus> scpStack = new Stack<ScopeWithStatus>();
    private int counter = 0;

    public FuncExtra(FuncAddr addr, String name, Type rtype) {
        super(addr, name, rtype);
    }

    public FuncExtra(FuncAddr addr, String name, Type rtype, ParamList params) {
        super(addr, name, rtype, params);
    }

    public void addInstruction(Inst inst) {
        if (scpStack.lastElement().dead) {
            return;
        }
        scpStack.lastElement().scope.addInstruction(inst);
        if (inst instanceof InstBaseJump) {
            scpStack.lastElement().dead = true;
        }
    }

    public void jumpBreak() {
        if (scpStack.lastElement().dead) {
            return;
        }
        for (ListIterator<ScopeWithStatus> it = scpStack.listIterator(scpStack.size()); it.hasPrevious();) {
            ScopeWithStatus pre = it.previous();
            if (pre.scope.getType() == ScopeType.ENDLOOP) {
                scpStack.lastElement().scope.addInstruction(new InstJump(pre.scope));
                scpStack.lastElement().dead = true;
            }
        }
    }

    public void jumpContinue() {
        if (scpStack.lastElement().dead) {
            return;
        }
        scpStack.lastElement().scope.addInstruction(new InstJump(scpStack.lastElement().scope));
        scpStack.lastElement().dead = true;
    }

    public Scope newScope(ScopeType type) {
        return new Scope(type, getAddr() + "." + type.toString() + "_" + (counter++));
    }

    public void pushScope(Scope scp) {
        boolean dead = false;
        if (!scpStack.empty()) {
            dead = scpStack.lastElement().dead;
        }
        scpStack.add(new ScopeWithStatus(scp, dead));
    }

    public void popScope() {
        ScopeWithStatus top = scpStack.pop();
        if (!top.dead && !scpStack.empty()) {
            top.scope.addInstruction(new InstJump(scpStack.lastElement().scope));
        }
        scps.add(top.scope);
    }

    public Object allocate(Object obj) {
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
