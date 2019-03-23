package Mx_star.PizzaIR;

import Mx_star.AST.*;

public class ProgramSectionListener extends Mx_starBaseListener {
    @Override
    public void enterProgramVariableDeclarationStatement(Mx_starParser.ProgramVariableDeclarationStatementContext ctx) {
        if (PizzaIR.state != ListenState.TRANSLATION) {
            return;
        }

        var variableDeclaration = ctx.variableDeclarationStatement().variableDeclaration();
        var lser = new VariableDeclarationListener();
        variableDeclaration.enterRule(lser);

        String name = lser.name, type = lser.type;
        PizzaIR.allocateVariable(name, type);
    }

    @Override
    public void enterProgramVariableDefinitionStatement(Mx_starParser.ProgramVariableDefinitionStatementContext ctx) {
        if (PizzaIR.state != ListenState.TRANSLATION) {
            return;
        }

        var variableDefinition = ctx.variableDefinitionStatement().variableDefinition();
        var lser = new VariableDefinitionListener();
        variableDefinition.enterRule(lser);

        String name = lser.name, type = lser.type;
        PizzaIR.allocateVariable(name, type);
    }

    @Override
    public void enterProgramClassDefinitionStatement(Mx_starParser.ProgramClassDefinitionStatementContext ctx) {
        var lser = new ClassDefinitionStatementListener();
        ctx.classDefinitionStatement().enterRule(lser);
    }

    @Override
    public void enterProgramFunctionDefinitionStatement(Mx_starParser.ProgramFunctionDefinitionStatementContext ctx) {
        if (PizzaIR.state == ListenState.TYPE_DECLARATION) {
            return;
        }

        var lser = new FunctionDefinitionStatementListener();
        ctx.functionDefinitionStatement().enterRule(lser);
    }
}