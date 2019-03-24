package com.github.espylapiza.compiler_mxstar.ast;

import com.github.espylapiza.compiler_mxstar.parser.Mx_starBaseVisitor;
import com.github.espylapiza.compiler_mxstar.parser.Mx_starParser;
import com.github.espylapiza.compiler_mxstar.parser.Mx_starParser.*;
import com.google.gson.JsonObject;

enum VisitState {
    TYPE_DECLARATION, MEMBER_DECLARATION, SEMANTIC_ANALYSIS, TRANSLATION_ENTRANCE, TRANSLATION
}

public class PizzaIRVisitor extends Mx_starBaseVisitor<Node> {
    JsonObject data;

    public static VisitState state;

    @Override
    public Node visitProgram(Mx_starParser.ProgramContext ctx) {
        data = new JsonObject();

        state = VisitState.TYPE_DECLARATION;
        ctx.programSection().forEach(ch -> ch.accept(this));

        state = VisitState.MEMBER_DECLARATION;
        ctx.programSection().forEach(ch -> ch.accept(this));

        Func mainFunc = PizzaIRBuilder.funcList.getFunc("main");
        if (mainFunc == null || !mainFunc.rtype.equals("int") || mainFunc.params.count() != 0) {
            assert false;
        }

        state = VisitState.SEMANTIC_ANALYSIS;
        ctx.programSection().forEach(ch -> ch.accept(this));

        data.add("Type", PizzaIRBuilder.typeList.toJson());
        data.add("Func", PizzaIRBuilder.funcList.toJson());
        data.add("Var", PizzaIRBuilder.varList.toJson());

        return null;
    }

    @Override
    public Node visitProgramVariableDeclarationStatement(Mx_starParser.ProgramVariableDeclarationStatementContext ctx) {
        if (state != VisitState.SEMANTIC_ANALYSIS) {
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
        if (state != VisitState.SEMANTIC_ANALYSIS) {
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
        String classname = ctx.classDefinitionStatement().Identifier().getText();

        PizzaIRBuilder.dom.enterClass(classname);

        switch (state) {
        case TYPE_DECLARATION:
            PizzaIRBuilder.typeList.addType(new Type(PizzaIRBuilder.dom.getClassTrace()));
            break;
        case MEMBER_DECLARATION:
        case SEMANTIC_ANALYSIS:
            ctx.classDefinitionStatement().classMember().forEach(ch -> ch.accept(this));
            break;
        }

        PizzaIRBuilder.dom.exitClass();

        return null;
    }

    @Override
    public Node visitProgramFunctionDefinitionStatement(Mx_starParser.ProgramFunctionDefinitionStatementContext ctx) {
        if (state == VisitState.TYPE_DECLARATION) {
            return null;
        }

        FunctionDefinitionStatementListener lser = new FunctionDefinitionStatementListener();
        ctx.functionDefinitionStatement().enterRule(lser);
        return null;
    }

    @Override
    public Node visitClassVariableDeclarationStatement(Mx_starParser.ClassVariableDeclarationStatementContext ctx) {
        VariableDeclarationListener lser = new VariableDeclarationListener();
        ctx.variableDeclarationStatement().variableDeclaration().enterRule(lser);
        String name = lser.name, type = lser.type;

        if (state == VisitState.MEMBER_DECLARATION) {
            Type t = PizzaIRBuilder.typeList.getType(PizzaIRBuilder.dom.getClassTrace());

            t.addMember(name, type);
        } else {
            PizzaIRBuilder.allocateVariable(name, type);
        }
        return null;
    }

    @Override
    public Node visitClassConstructionFunctionStatement(Mx_starParser.ClassConstructionFunctionStatementContext ctx) {
        ConstructionFunctionStatementListener lser = new ConstructionFunctionStatementListener();
        ctx.constructionFunctionStatement().enterRule(lser);

        if (state == VisitState.MEMBER_DECLARATION) {
            String name = lser.name;

            if (!PizzaIRBuilder.dom.getLastClass().equals(name)) {
                assert false;
            }

            Type t = PizzaIRBuilder.typeList.getType(PizzaIRBuilder.dom.getClassTrace());

            t.addMethod(name, PizzaIRBuilder.dom.getClassTrace() + "." + name);
        } else {

        }
        return null;
    }

    @Override
    public Node visitClassFunctionDefinitionStatement(Mx_starParser.ClassFunctionDefinitionStatementContext ctx) {
        FunctionDefinitionStatementListener lser = new FunctionDefinitionStatementListener();
        ctx.functionDefinitionStatement().enterRule(lser);

        if (state == VisitState.MEMBER_DECLARATION) {
            String name = lser.name;
            Type t = PizzaIRBuilder.typeList.getType(PizzaIRBuilder.dom.getClassTrace());

            t.addMethod(name, PizzaIRBuilder.dom.getClassTrace() + "." + name);
        } else {

        }
        return null;
    }
}