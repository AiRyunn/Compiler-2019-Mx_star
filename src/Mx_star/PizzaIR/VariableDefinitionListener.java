package Mx_star.PizzaIR;

import Mx_star.AST.*;

class VariableDefinitionListener extends Mx_starBaseListener {
    String name, type;

    @Override
    public void enterVariableDefinition(Mx_starParser.VariableDefinitionContext ctx) {
        var lser = new ObjectListener();
        ctx.object().enterRule(lser);

        name = ctx.Identifier().getText();
        type = ctx.type().getText();

        if (!PizzaIR.typeList.hasType(type)) {
            assert false;
        }

        if ((type.endsWith("[]") || !PizzaIR.typeList.getType(type).isBuiltin()) && lser.type.equals("null")) {

        } else {
            if (!type.equals(lser.type)) {
                assert false;
            }
        }
    }
}