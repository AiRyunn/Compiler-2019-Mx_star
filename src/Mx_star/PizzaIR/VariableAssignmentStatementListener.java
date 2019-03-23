package Mx_star.PizzaIR;

import Mx_star.AST.*;

public class VariableAssignmentStatementListener extends Mx_starBaseListener {
    @Override
    public void enterVariableAssignmentStatement(Mx_starParser.VariableAssignmentStatementContext ctx) {
        var lser = new VariableAssignmentListener();
        ctx.variableAssignment().enterRule(lser);
    }
}