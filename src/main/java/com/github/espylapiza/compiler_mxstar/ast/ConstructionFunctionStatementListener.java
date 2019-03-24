package com.github.espylapiza.compiler_mxstar.ast;

import com.github.espylapiza.compiler_mxstar.parser.*;
import com.github.espylapiza.compiler_mxstar.logging.*;
import com.github.espylapiza.compiler_mxstar.utils.Pair;

class ConstructionFunctionStatementListener extends Mx_starBaseListener {
    String name;

    @Override
    public void enterConstructionFunctionStatement(Mx_starParser.ConstructionFunctionStatementContext ctx) {
        name = ctx.Identifier().getText();
        String trace = PizzaIRBuilder.dom.getClassTrace();

        PizzaIRBuilder.dom.enterFunc(trace, name, "void");

        Logging.debug("enter construction function: " + name);

        ParamListDefinitionListener lser = new ParamListDefinitionListener();
        ctx.paramListDefinition().enterRule(lser);

        if (PizzaIRVisitor.state == ListenState.MEMBER_DECLARATION) {
            Func func = new Func(trace, name, "void");
            if (!PizzaIRBuilder.dom.isGlobal()) {
                func.addParam(trace);
            }
            lser.params.params.forEach(param -> func.addParam(param.second));

            if (PizzaIRBuilder.funcList.addFunc(func) == false) {
                assert false;
            }
        } else {
            PizzaIRBuilder.code.newSection(PizzaIRBuilder.dom.getAddr());

            for (Pair<String, String> param : lser.params.params) {
                PizzaIRBuilder.allocateVariable(param.first, param.second);
            }

            if (ctx.statements() != null) {
                StatementsListener stmtLser = new StatementsListener();
                ctx.statements().enterRule(stmtLser);
            }

            PizzaIRBuilder.code.packScope();
            PizzaIRBuilder.code.packSection();
        }

        Logging.debug("exit construction function: " + name);
        PizzaIRBuilder.dom.exitFunc();
    }
}