package com.github.espylapiza.compiler_mxstar.ast;

import com.github.espylapiza.compiler_mxstar.parser.*;
import com.github.espylapiza.compiler_mxstar.logging.*;
import com.github.espylapiza.compiler_mxstar.utils.Pair;

class FunctionDefinitionStatementListener extends Mx_starBaseListener {
    String name, rtype;

    @Override
    public void enterFunctionDefinitionStatement(Mx_starParser.FunctionDefinitionStatementContext ctx) {
        name = ctx.Identifier().getText();
        rtype = ctx.type().getText();
        String trace = PizzaIRBuilder.dom.getClassTrace();

        if (!rtype.equals("void") && !PizzaIRBuilder.typeList.hasType(rtype)) {
            assert false;
        }
        Logging.debug("enter function: " + name);

        PizzaIRBuilder.dom.enterFunc(trace, name, rtype);

        ParamListDefinitionListener lser = new ParamListDefinitionListener();
        ctx.paramListDefinition().enterRule(lser);

        if (PizzaIRBuilder.state == ListenState.MEMBER_DECLARATION) {
            String owner;
            if (!PizzaIRBuilder.dom.isGlobal()) {
                owner = trace;
            } else {
                owner = null;
            }

            Func func = new Func(owner, name, rtype);
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

        Logging.debug("exit function: " + name);
        PizzaIRBuilder.dom.exitFunc();
    }
}