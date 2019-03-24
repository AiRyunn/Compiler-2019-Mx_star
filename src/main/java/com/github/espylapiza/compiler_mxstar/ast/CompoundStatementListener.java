package com.github.espylapiza.compiler_mxstar.ast;

import com.github.espylapiza.compiler_mxstar.parser.*;

public class CompoundStatementListener extends Mx_starBaseListener {
    @Override
    public void enterCompoundStatement(Mx_starParser.CompoundStatementContext ctx) {
        if (ctx.statements() != null) {
            PizzaIRBuilder.dom.enterCondition(-1);

            StatementsListener lser = new StatementsListener();
            ctx.statements().enterRule(lser);

            PizzaIRBuilder.dom.exitCondition();
        }
    }
}