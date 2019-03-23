package Mx_star.PizzaIR;

import Mx_star.AST.*;

public class VariableDeclarationStatementListener extends Mx_starBaseListener {
    @Override
    public void enterVariableDeclarationStatement(Mx_starParser.VariableDeclarationStatementContext ctx) {
        VariableDeclarationListener lser = new VariableDeclarationListener();
        ctx.variableDeclaration().enterRule(lser);
        PizzaIR.allocateVariable(lser.name, lser.type);
    }
}