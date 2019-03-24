package com.github.espylapiza.compiler_mxstar.ast;

import com.github.espylapiza.compiler_mxstar.parser.*;
import com.github.espylapiza.compiler_mxstar.logging.*;
import com.github.espylapiza.compiler_mxstar.utils.Pair;

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
            PizzaIR.code.newSection(PizzaIR.dom.getAddr());

            for (Pair<String, String> param : lser.params.params) {
                PizzaIR.allocateVariable(param.first, param.second);
            }

            if (ctx.statements() != null) {
                StatementsListener stmtLser = new StatementsListener();
                ctx.statements().enterRule(stmtLser);
            }

            PizzaIR.code.packScope();
            PizzaIR.code.packSection();
        }

        Logging.debug("exit construction function: " + name);
        PizzaIR.dom.exitFunc();
    }
}