package Mx_star.PizzaIR;

import Mx_star.AST.*;

public class StatementsListener extends Mx_starBaseListener {
    @Override
    public void enterStatements(Mx_starParser.StatementsContext ctx) {
        ctx.statement().forEach(ch -> {
            StatementListener lser = new StatementListener();
            ch.enterRule(lser);
        });
    }
}