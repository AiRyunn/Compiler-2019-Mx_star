package Mx_star.PizzaIR;

import Mx_star.AST.*;

public class VariableDefinitionStatementListener extends Mx_starBaseListener {
    @Override
    public void enterVariableDefinitionStatement(Mx_starParser.VariableDefinitionStatementContext ctx) {
        var lser = new VariableDefinitionListener();
        ctx.variableDefinition().enterRule(lser);
        PizzaIR.allocateVariable(lser.name, lser.type);
    }
}