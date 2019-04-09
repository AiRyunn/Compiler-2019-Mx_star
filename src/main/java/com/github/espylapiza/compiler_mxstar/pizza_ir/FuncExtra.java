package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

import com.github.espylapiza.compiler_mxstar.utils.Pair;

public class FuncExtra extends Func {
    private VarList varList = new VarList();
    private List<Scope> scps = new ArrayList<Scope>();
    private Stack<Pair<Scope, Boolean>> scpStack = new Stack<Pair<Scope, Boolean>>();
    private int counter = 0;

    public FuncExtra(FuncAddr addr, String name, Type rtype) {
        super(addr, name, rtype);
    }

    public FuncExtra(FuncAddr addr, String name, Type rtype, ParamList params) {
        super(addr, name, rtype, params);
    }

    public void addInstruction(Inst inst) {
        if (scpStack.lastElement().second) {
            return;
        }
        scpStack.lastElement().first.addInstruction(inst);
        if (inst instanceof InstBaseJump) {
            scpStack.lastElement().second = true;
        }
    }

    public void jumpBreak() {
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

    public void jumpContinue() {
        if (scpStack.lastElement().second) {
            return;
        }
        scpStack.lastElement().first.addInstruction(new InstJump(scpStack.lastElement().first));
        scpStack.lastElement().second = true;
    }

    public Scope newScope(ScopeType type) {
        return new Scope(type, getAddr() + "." + type.toString() + "_" + (counter++));
    }

    public void pushScope(Scope scp) {
        boolean dead = false;
        if (!scpStack.empty()) {
            dead = scpStack.lastElement().second;
        }
        scpStack.add(new Pair<Scope, Boolean>(scp, dead));
    }

    public void popScope() {
        Pair<Scope, Boolean> top = scpStack.pop();
        if (!top.second && !scpStack.empty()) {
            top.first.addInstruction(new InstJump(scpStack.lastElement().first));
        }
        scps.add(top.first);
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