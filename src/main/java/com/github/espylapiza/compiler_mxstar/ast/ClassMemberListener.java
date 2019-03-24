package com.github.espylapiza.compiler_mxstar.ast;

import com.github.espylapiza.compiler_mxstar.parser.*;

public class ClassMemberListener extends Mx_starBaseListener {

    @Override
    public void enterClassVariableDeclarationStatement(Mx_starParser.ClassVariableDeclarationStatementContext ctx) {
        VariableDeclarationListener lser = new VariableDeclarationListener();
        ctx.variableDeclarationStatement().variableDeclaration().enterRule(lser);
        String name = lser.name, type = lser.type;

        if (PizzaIRBuilder.state == ListenState.MEMBER_DECLARATION) {
            Type t = PizzaIRBuilder.typeList.getType(PizzaIRBuilder.dom.getClassTrace());

            t.addMember(name, type);
        } else {
            PizzaIRBuilder.allocateVariable(name, type);
        }
    }

    @Override
    public void enterClassConstructionFunctionStatement(Mx_starParser.ClassConstructionFunctionStatementContext ctx) {
        ConstructionFunctionStatementListener lser = new ConstructionFunctionStatementListener();
        ctx.constructionFunctionStatement().enterRule(lser);

        if (PizzaIRBuilder.state == ListenState.MEMBER_DECLARATION) {
            String name = lser.name;

            if (!PizzaIRBuilder.dom.getLastClass().equals(name)) {
                assert false;
            }

            Type t = PizzaIRBuilder.typeList.getType(PizzaIRBuilder.dom.getClassTrace());

            t.addMethod(name, PizzaIRBuilder.dom.getClassTrace() + "." + name);
        } else {

        }
    }

    @Override
    public void enterClassFunctionDefinitionStatement(Mx_starParser.ClassFunctionDefinitionStatementContext ctx) {
        FunctionDefinitionStatementListener lser = new FunctionDefinitionStatementListener();
        ctx.functionDefinitionStatement().enterRule(lser);

        if (PizzaIRBuilder.state == ListenState.MEMBER_DECLARATION) {
            String name = lser.name;
            Type t = PizzaIRBuilder.typeList.getType(PizzaIRBuilder.dom.getClassTrace());

            t.addMethod(name, PizzaIRBuilder.dom.getClassTrace() + "." + name);
        } else {

        }
    }
}