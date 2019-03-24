package com.github.espylapiza.compiler_mxstar.ast;

import com.github.espylapiza.compiler_mxstar.parser.*;

public class LoopStatementListener extends Mx_starBaseListener {
    @Override
    public void enterWhileLoop(Mx_starParser.WhileLoopContext ctx) {
        PizzaIRVisitor.dom.enterLoop(-1);

        ObjectListener objLser = new ObjectListener();
        ctx.object().enterRule(objLser);

        if (!objLser.type.equals("bool")) {
            assert false;
        }

        StatementListener stmtLser = new StatementListener();
        ctx.statement().enterRule(stmtLser);

        PizzaIRVisitor.dom.exitLoop();
    }

    @Override
    public void enterForLoop(Mx_starParser.ForLoopContext ctx) {
        PizzaIRVisitor.dom.enterLoop(-1);

        if (ctx.forCondition().forCondition1() != null) {
            ForCondition1Listener cdt1Lser = new ForCondition1Listener();
            ctx.forCondition().forCondition1().enterRule(cdt1Lser);
        }
        if (ctx.forCondition().forCondition2() != null) {
            ObjectListener objLser = new ObjectListener();
            ctx.forCondition().forCondition2().object().enterRule(objLser);
            if (!objLser.type.equals("bool")) {
                assert false;
            }
        }

        if (ctx.forCondition().forCondition3() != null) {
            ForCondition3Listener cdt3Lser = new ForCondition3Listener();
            ctx.forCondition().forCondition3().enterRule(cdt3Lser);
        }

        StatementListener stmtLser = new StatementListener();
        ctx.statement().enterRule(stmtLser);

        PizzaIRVisitor.dom.exitLoop();
    }
}