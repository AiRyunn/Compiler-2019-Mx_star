package com.github.espylapiza.compiler_mxstar.ast;

import com.github.espylapiza.compiler_mxstar.parser.*;

public class VariableDefinitionStatementListener extends Mx_starBaseListener {
    @Override
    public void enterVariableDefinitionStatement(Mx_starParser.VariableDefinitionStatementContext ctx) {
        VariableDefinitionListener lser = new VariableDefinitionListener();
        ctx.variableDefinition().enterRule(lser);
        PizzaIRVisitor.allocateVariable(lser.name, lser.type);
    }
}