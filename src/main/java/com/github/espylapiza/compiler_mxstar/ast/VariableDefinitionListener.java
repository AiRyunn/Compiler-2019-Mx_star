package com.github.espylapiza.compiler_mxstar.ast;

import com.github.espylapiza.compiler_mxstar.parser.*;

class VariableDefinitionListener extends Mx_starBaseListener {
    String name, type;

    @Override
    public void enterVariableDefinition(Mx_starParser.VariableDefinitionContext ctx) {
        ObjectListener lser = new ObjectListener();
        ctx.object().enterRule(lser);

        name = ctx.Identifier().getText();
        type = ctx.type().getText();

        if (!PizzaIRBuilder.typeList.hasType(type)) {
            assert false;
        }

        if ((type.endsWith("[]") || !PizzaIRBuilder.typeList.getType(type).isBuiltin()) && lser.type.equals("null")) {

        } else {
            if (!type.equals(lser.type)) {
                assert false;
            }
        }
    }
}