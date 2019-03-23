package Mx_star.PizzaIR;

import Mx_star.AST.*;

public class ObjectStatementListener extends Mx_starBaseListener {
    @Override
    public void enterObjectStatement(Mx_starParser.ObjectStatementContext ctx) {
        var lser = new ObjectListener();
        ctx.object().enterRule(lser);
    }
}