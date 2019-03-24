package com.github.espylapiza.compiler_mxstar.ast;

import com.github.espylapiza.compiler_mxstar.parser.Mx_starBaseVisitor;
import com.github.espylapiza.compiler_mxstar.parser.Mx_starParser;
import com.github.espylapiza.compiler_mxstar.parser.Mx_starParser.*;
import com.google.gson.JsonObject;

enum VisitState {
    TYPE_DECLARATION, MEMBER_DECLARATION, TRANSLATION
}

public class PizzaIRVisitor extends Mx_starBaseVisitor<Node> {
    JsonObject data;
    VisitState state;

    @Override
    public Node visitProgram(Mx_starParser.ProgramContext ctx) {
        data = new JsonObject();

        PizzaIRBuilder.state = ListenState.TYPE_DECLARATION;
        ctx.programSection().forEach(ch -> ch.accept(this));

        PizzaIRBuilder.state = ListenState.MEMBER_DECLARATION;
        ctx.programSection().forEach(ch -> ch.accept(this));

        PizzaIRBuilder.state = ListenState.TRANSLATION;
        ctx.programSection().forEach(ch -> ch.accept(this));

        Func mainFunc = PizzaIRBuilder.funcList.getFunc("main");
        if (mainFunc == null || !mainFunc.rtype.equals("int") || mainFunc.params.count() != 0) {
            assert false;
        }

        data.add("Type", PizzaIRBuilder.typeList.toJson());
        data.add("Func", PizzaIRBuilder.funcList.toJson());
        data.add("Var", PizzaIRBuilder.varList.toJson());
        return null;
    }

    @Override
    public Node visitProgramVariableDeclarationStatement(Mx_starParser.ProgramVariableDeclarationStatementContext ctx) {
        if (PizzaIRBuilder.state != ListenState.TRANSLATION) {
            return null;
        }

        VariableDeclarationContext variableDeclaration = ctx.variableDeclarationStatement().variableDeclaration();
        VariableDeclarationListener lser = new VariableDeclarationListener();
        variableDeclaration.enterRule(lser);

        String name = lser.name, type = lser.type;
        PizzaIRBuilder.allocateVariable(name, type);
        return null;
    }

    @Override
    public Node visitProgramVariableDefinitionStatement(Mx_starParser.ProgramVariableDefinitionStatementContext ctx) {
        if (PizzaIRBuilder.state != ListenState.TRANSLATION) {
            return null;
        }

        VariableDefinitionContext variableDefinition = ctx.variableDefinitionStatement().variableDefinition();
        VariableDefinitionListener lser = new VariableDefinitionListener();
        variableDefinition.enterRule(lser);

        String name = lser.name, type = lser.type;
        PizzaIRBuilder.allocateVariable(name, type);
        return null;
    }

    @Override
    public Node visitProgramClassDefinitionStatement(Mx_starParser.ProgramClassDefinitionStatementContext ctx) {
        ClassDefinitionStatementListener lser = new ClassDefinitionStatementListener();
        ctx.classDefinitionStatement().enterRule(lser);
        return null;
    }

    @Override
    public Node visitProgramFunctionDefinitionStatement(Mx_starParser.ProgramFunctionDefinitionStatementContext ctx) {
        if (PizzaIRBuilder.state == ListenState.TYPE_DECLARATION) {
            return null;
        }

        FunctionDefinitionStatementListener lser = new FunctionDefinitionStatementListener();
        ctx.functionDefinitionStatement().enterRule(lser);
        return null;
    }
}