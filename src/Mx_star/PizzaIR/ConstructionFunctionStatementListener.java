package Mx_star.PizzaIR;

import Mx_star.AST.*;
import logging.*;
import utils.Pair;

class ConstructionFunctionStatementListener extends Mx_starBaseListener {
    String name;

    @Override
    public void enterConstructionFunctionStatement(Mx_starParser.ConstructionFunctionStatementContext ctx) {
        name = ctx.Identifier().getText();
        String trace = PizzaIR.dom.getClassTrace();

        PizzaIR.dom.enterFunc(trace, name, "void");

        Logging.debug("enter construction function: " + name);

        ParamListDefinitionListener lser = new ParamListDefinitionListener();
        ctx.paramListDefinition().enterRule(lser);

        if (PizzaIR.state == ListenState.MEMBER_DECLARATION) {
            Func func = new Func(trace, name, "void");
            if (!PizzaIR.dom.isGlobal()) {
                func.addParam(trace);
            }
            lser.params.params.forEach(param -> func.addParam(param.second));

            if (PizzaIR.funcList.addFunc(func) == false) {
                assert false;
            }
        } else {
            for (Pair<String, String> param : lser.params.params) {
                PizzaIR.allocateVariable(param.first, param.second);
            }

            if (ctx.statements() != null) {
                StatementsListener stmtLser = new StatementsListener();
                ctx.statements().enterRule(stmtLser);
            }
        }

        Logging.debug("exit construction function: " + name);
        PizzaIR.dom.exitFunc();
    }
}