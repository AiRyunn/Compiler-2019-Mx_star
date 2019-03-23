package Mx_star.PizzaIR;

import Mx_star.AST.*;

public class ConditionStatementListener extends Mx_starBaseListener {
    @Override
    public void enterConditionStatement(Mx_starParser.ConditionStatementContext ctx) {
        ObjectListener objLser = new ObjectListener();
        ctx.object().enterRule(objLser);

        if (!objLser.type.equals("bool")) {
            assert false;
        }

        ctx.statement().forEach(ch -> {
            PizzaIR.dom.enterCondition(-1);

            StatementListener lser = new StatementListener();
            ch.enterRule(lser);

            PizzaIR.dom.exitCondition();
        });
    }
}