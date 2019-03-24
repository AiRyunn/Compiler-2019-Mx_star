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
        String trace = PizzaIRVisitor.dom.getClassTrace();

        if (!rtype.equals("void") && !PizzaIRVisitor.typeList.hasType(rtype)) {
            assert false;
        }
        Logging.debug("enter function: " + name);

        PizzaIRVisitor.dom.enterFunc(trace, name, rtype);

        ParamListDefinitionListener lser = new ParamListDefinitionListener();
        ctx.paramListDefinition().enterRule(lser);

        if (PizzaIRVisitor.state == VisitState.MEMBER_DECLARATION) {
            String owner;
            if (!PizzaIRVisitor.dom.isGlobal()) {
                owner = trace;
            } else {
                owner = null;
            }

            Func func = new Func(owner, name, rtype);
            if (!PizzaIRVisitor.dom.isGlobal()) {
                func.addParam(trace);
            }
            lser.params.params.forEach(param -> func.addParam(param.second));

            if (PizzaIRVisitor.funcList.addFunc(func) == false) {
                assert false;
            }
        } else {
            PizzaIRVisitor.code.newSection(PizzaIRVisitor.dom.getAddr());

            for (Pair<String, String> param : lser.params.params) {
                PizzaIRVisitor.allocateVariable(param.first, param.second);
            }

            if (ctx.statements() != null) {
                StatementsListener stmtLser = new StatementsListener();
                ctx.statements().enterRule(stmtLser);
            }

            PizzaIRVisitor.code.packScope();
            PizzaIRVisitor.code.packSection();
        }

        Logging.debug("exit function: " + name);
        PizzaIRVisitor.dom.exitFunc();
    }
}