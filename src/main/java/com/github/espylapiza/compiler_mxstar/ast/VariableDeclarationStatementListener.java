package com.github.espylapiza.compiler_mxstar.ast;

import com.github.espylapiza.compiler_mxstar.parser.*;

public class VariableDeclarationStatementListener extends Mx_starBaseListener {
    @Override
    public void enterVariableDeclarationStatement(Mx_starParser.VariableDeclarationStatementContext ctx) {
        VariableDeclarationListener lser = new VariableDeclarationListener();
        ctx.variableDeclaration().enterRule(lser);
        PizzaIR.allocateVariable(lser.name, lser.type);
    }
}