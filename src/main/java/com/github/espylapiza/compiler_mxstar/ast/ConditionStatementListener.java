package com.github.espylapiza.compiler_mxstar.ast;

import com.github.espylapiza.compiler_mxstar.parser.*;

public class ConditionStatementListener extends Mx_starBaseListener {
    @Override
    public void enterConditionStatement(Mx_starParser.ConditionStatementContext ctx) {
        ObjectListener objLser = new ObjectListener();
        ctx.object().enterRule(objLser);

        if (!objLser.type.equals("bool")) {
            assert false;
        }

        ctx.statement().forEach(ch -> {
            PizzaIRVisitor.dom.enterCondition(-1);
            PizzaIRVisitor.code.packScope();

            StatementListener lser = new StatementListener();
            ch.enterRule(lser);

            PizzaIRVisitor.dom.exitCondition();
        });

        PizzaIRVisitor.code.packScope();
    }
}