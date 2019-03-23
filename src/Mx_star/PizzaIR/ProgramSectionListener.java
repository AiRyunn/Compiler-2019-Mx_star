package Mx_star.PizzaIR;

import Mx_star.AST.*;
import Mx_star.AST.Mx_starParser.VariableDeclarationContext;
import Mx_star.AST.Mx_starParser.VariableDefinitionContext;

public class ProgramSectionListener extends Mx_starBaseListener {
    @Override
    public void enterProgramVariableDeclarationStatement(Mx_starParser.ProgramVariableDeclarationStatementContext ctx) {
        if (PizzaIR.state != ListenState.TRANSLATION) {
            return;
        }

        VariableDeclarationContext variableDeclaration = ctx.variableDeclarationStatement().variableDeclaration();
        VariableDeclarationListener lser = new VariableDeclarationListener();
        variableDeclaration.enterRule(lser);

        String name = lser.name, type = lser.type;
        PizzaIR.allocateVariable(name, type);
    }

    @Override
    public void enterProgramVariableDefinitionStatement(Mx_starParser.ProgramVariableDefinitionStatementContext ctx) {
        if (PizzaIR.state != ListenState.TRANSLATION) {
            return;
        }

        VariableDefinitionContext variableDefinition = ctx.variableDefinitionStatement().variableDefinition();
        VariableDefinitionListener lser = new VariableDefinitionListener();
        variableDefinition.enterRule(lser);

        String name = lser.name, type = lser.type;
        PizzaIR.allocateVariable(name, type);
    }

    @Override
    public void enterProgramClassDefinitionStatement(Mx_starParser.ProgramClassDefinitionStatementContext ctx) {
        ClassDefinitionStatementListener lser = new ClassDefinitionStatementListener();
        ctx.classDefinitionStatement().enterRule(lser);
    }

    @Override
    public void enterProgramFunctionDefinitionStatement(Mx_starParser.ProgramFunctionDefinitionStatementContext ctx) {
        if (PizzaIR.state == ListenState.TYPE_DECLARATION) {
            return;
        }

        FunctionDefinitionStatementListener lser = new FunctionDefinitionStatementListener();
        ctx.functionDefinitionStatement().enterRule(lser);
    }
}