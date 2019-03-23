package Mx_star.PizzaIR;

import Mx_star.AST.*;
import logging.*;

public class StatementsListener extends Mx_starBaseListener {
    @Override
    public void enterStatements(Mx_starParser.StatementsContext ctx) {
        ctx.statement().forEach(ch -> {
            var lser = new StatementListener();
            ch.enterRule(lser);
        });
    }
}