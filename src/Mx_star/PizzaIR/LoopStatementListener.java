package Mx_star.PizzaIR;

import Mx_star.AST.*;

public class LoopStatementListener extends Mx_starBaseListener {
    @Override
    public void enterWhileLoop(Mx_starParser.WhileLoopContext ctx) {
        PizzaIR.dom.enterLoop(-1);

        var objLser = new ObjectListener();
        ctx.object().enterRule(objLser);

        if (!objLser.type.equals("bool")) {
            assert false;
        }

        var stmtLser = new StatementListener();
        ctx.statement().enterRule(stmtLser);

        PizzaIR.dom.exitLoop();
    }

    @Override
    public void enterForLoop(Mx_starParser.ForLoopContext ctx) {
        PizzaIR.dom.enterLoop(-1);

        if (ctx.forCondition().forCondition1() != null) {
            var cdt1Lser = new ForCondition1Listener();
            ctx.forCondition().forCondition1().enterRule(cdt1Lser);
        }
        if (ctx.forCondition().forCondition2() != null) {
            var objLser = new ObjectListener();
            ctx.forCondition().forCondition2().object().enterRule(objLser);
            if (objLser.type != "bool") {
                assert false;
            }
        }

        if (ctx.forCondition().forCondition3() != null) {
            var cdt3Lser = new ForCondition3Listener();
            ctx.forCondition().forCondition3().enterRule(cdt3Lser);
        }

        var stmtLser = new StatementListener();
        ctx.statement().enterRule(stmtLser);

        PizzaIR.dom.exitLoop();
    }
}