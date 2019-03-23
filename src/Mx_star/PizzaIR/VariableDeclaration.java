package Mx_star.PizzaIR;

import Mx_star.AST.*;

public class VariableDeclaration extends Mx_starBaseListener {
    String name, type;

    @Override
    public void enterVariableDeclaration(Mx_starParser.VariableDeclarationContext ctx) {
        name = ctx.Identifier().getText();
        type = ctx.type().getText();

        if (!PizzaIR.typeList.hasType(type)) {
            assert false;
        }
    }
}