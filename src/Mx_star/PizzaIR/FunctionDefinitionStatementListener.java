package Mx_star.PizzaIR;

import Mx_star.AST.*;
import logging.*;

class FunctionDefinitionStatementListener extends Mx_starBaseListener {
    String name, rtype;

    @Override
    public void enterFunctionDefinitionStatement(Mx_starParser.FunctionDefinitionStatementContext ctx) {
        name = ctx.Identifier().getText();
        rtype = ctx.type().getText();
        String trace = PizzaIR.dom.getClassTrace();

        if (!rtype.equals("void") && !PizzaIR.typeList.hasType(rtype)) {
            assert false;
        }
        Logging.debug("enter function: " + name);

        PizzaIR.dom.enterFunc(trace, name, rtype);

        var lser = new ParamListDefinitionListener();
        ctx.paramListDefinition().enterRule(lser);

        if (PizzaIR.state == ListenState.MEMBER_DECLARATION) {
            String owner;
            if (!PizzaIR.dom.isGlobal()) {
                owner = trace;
            } else {
                owner = null;
            }

            Func func = new Func(owner, name, rtype);
            if (!PizzaIR.dom.isGlobal()) {
                func.addParam(trace);
            }
            lser.params.params.forEach(param -> func.addParam(param.second));

            if (PizzaIR.funcList.addFunc(func) == false) {
                assert false;
            }
        } else {
            for (var param : lser.params.params) {
                PizzaIR.allocateVariable(param.first, param.second);
            }

            if (ctx.statements() != null) {
                var stmtLser = new StatementsListener();
                ctx.statements().enterRule(stmtLser);
            }
        }

        Logging.debug("exit function: " + name);
        PizzaIR.dom.exitFunc();
    }
}