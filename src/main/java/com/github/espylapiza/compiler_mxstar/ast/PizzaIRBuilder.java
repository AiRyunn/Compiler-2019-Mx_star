package com.github.espylapiza.compiler_mxstar.ast;

import com.github.espylapiza.compiler_mxstar.parser.Mx_starBaseVisitor;
import com.github.espylapiza.compiler_mxstar.parser.Mx_starParser;
import com.github.espylapiza.compiler_mxstar.parser.Mx_starParser.*;
import com.google.gson.JsonObject;

enum VisitState {
    TYPE_DECLARATION, MEMBER_DECLARATION, TRANSLATION
}

public class PizzaIRBuilder extends Mx_starBaseVisitor<Void> {
    JsonObject data;
    VisitState state;

    @Override
    public Void visitProgram(Mx_starParser.ProgramContext ctx) {
        data = new JsonObject();

        PizzaIR.state = ListenState.TYPE_DECLARATION;
        ctx.programSection().forEach(ch -> ch.accept(this));

        PizzaIR.state = ListenState.MEMBER_DECLARATION;
        ctx.programSection().forEach(ch -> ch.accept(this));

        PizzaIR.state = ListenState.TRANSLATION;
        ctx.programSection().forEach(ch -> ch.accept(this));

        Func mainFunc = PizzaIR.funcList.getFunc("main");
        if (mainFunc == null || !mainFunc.rtype.equals("int") || mainFunc.params.count() != 0) {
            assert false;
        }

        data.add("Type", PizzaIR.typeList.toJson());
        data.add("Func", PizzaIR.funcList.toJson());
        data.add("Var", PizzaIR.varList.toJson());
        return null;
    }

    @Override
    public Void visitProgramVariableDeclarationStatement(Mx_starParser.ProgramVariableDeclarationStatementContext ctx) {
        if (PizzaIR.state != ListenState.TRANSLATION) {
            return null;
        }

        VariableDeclarationContext variableDeclaration = ctx.variableDeclarationStatement().variableDeclaration();
        VariableDeclarationListener lser = new VariableDeclarationListener();
        variableDeclaration.enterRule(lser);

        String name = lser.name, type = lser.type;
        PizzaIR.allocateVariable(name, type);
        return null;
    }

    @Override
    public Void visitProgramVariableDefinitionStatement(Mx_starParser.ProgramVariableDefinitionStatementContext ctx) {
        if (PizzaIR.state != ListenState.TRANSLATION) {
            return null;
        }

        VariableDefinitionContext variableDefinition = ctx.variableDefinitionStatement().variableDefinition();
        VariableDefinitionListener lser = new VariableDefinitionListener();
        variableDefinition.enterRule(lser);

        String name = lser.name, type = lser.type;
        PizzaIR.allocateVariable(name, type);
        return null;
    }

    @Override
    public Void visitProgramClassDefinitionStatement(Mx_starParser.ProgramClassDefinitionStatementContext ctx) {
        ClassDefinitionStatementListener lser = new ClassDefinitionStatementListener();
        ctx.classDefinitionStatement().enterRule(lser);
        return null;
    }

    @Override
    public Void visitProgramFunctionDefinitionStatement(Mx_starParser.ProgramFunctionDefinitionStatementContext ctx) {
        if (PizzaIR.state == ListenState.TYPE_DECLARATION) {
            return null;
        }

        FunctionDefinitionStatementListener lser = new FunctionDefinitionStatementListener();
        ctx.functionDefinitionStatement().enterRule(lser);
        return null;
    }
}