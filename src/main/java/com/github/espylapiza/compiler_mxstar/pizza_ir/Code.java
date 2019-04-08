package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Logger;

class Code {
    private final static Logger LOGGER = Logger.getLogger(Code.class.getName());

    private List<Func> funcList = new ArrayList<Func>();
    private Stack<Func> funcStack = new Stack<Func>();

    void enterFunc(Func func) {
        LOGGER.fine("enterFunc: " + func.getAddr());
        funcStack.add(func);
    }

    void exitFunc() {
        LOGGER.fine("exitFunc");
        funcList.add(funcStack.pop());
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

    @Override
    public String toString() {
        String result = new String();
        boolean first = true;
        for (Func sec : funcList) {
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