package com.github.espylapiza.compiler_mxstar.ast;

import com.github.espylapiza.compiler_mxstar.parser.*;

public class VariableAssignmentStatementListener extends Mx_starBaseListener {
    @Override
    public void enterVariableAssignmentStatement(Mx_starParser.VariableAssignmentStatementContext ctx) {
        VariableAssignmentListener lser = new VariableAssignmentListener();
        ctx.variableAssignment().enterRule(lser);
    }
}