package com.github.espylapiza.compiler_mxstar.pizza_ir;

public abstract class PizzaIRPartBaseVisitor {
    public final void beAccept(PizzaIRPart part) {
        part.accept(this);
    }

    public abstract void visit(PizzaIR ir);

    public abstract void visit(FuncExtra func);

    public abstract void visit(BasicBlock scope);

    public abstract void visit(Inst inst);

    public abstract void visit(InstCall inst);

    public abstract void visit(InstRet inst);

    public abstract void visit(InstMov inst);

    public abstract void visit(InstStore inst);

    public abstract void visit(InstBr inst);

    public abstract void visit(InstJump inst);

    public abstract void visit(InstAlloc inst);

    public abstract void visit(InstOffset inst);

    public abstract void visit(InstLoad inst);
}
