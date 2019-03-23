package Mx_star.PizzaIR;

import Mx_star.AST.*;

public class CompoundStatementListener extends Mx_starBaseListener {
    @Override
    public void enterCompoundStatement(Mx_starParser.CompoundStatementContext ctx) {
        if (ctx.statements() != null) {
            PizzaIR.dom.enterScope(-1);

            var lser = new StatementsListener();
            ctx.statements().enterRule(lser);

            PizzaIR.dom.exitScope();
        }
    }
}