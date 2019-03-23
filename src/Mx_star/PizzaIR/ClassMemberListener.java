package Mx_star.PizzaIR;

import Mx_star.AST.*;

public class ClassMemberListener extends Mx_starBaseListener {

    @Override
    public void enterClassVariableDeclarationStatement(Mx_starParser.ClassVariableDeclarationStatementContext ctx) {
        VariableDeclarationListener lser = new VariableDeclarationListener();
        ctx.variableDeclarationStatement().variableDeclaration().enterRule(lser);
        String name = lser.name, type = lser.type;

        if (PizzaIR.state == ListenState.MEMBER_DECLARATION) {
            Type t = PizzaIR.typeList.getType(PizzaIR.dom.getClassTrace());

            t.addMember(name, type);
        } else {
            PizzaIR.allocateVariable(name, type);
        }
    }

    @Override
    public void enterClassConstructionFunctionStatement(Mx_starParser.ClassConstructionFunctionStatementContext ctx) {
        ConstructionFunctionStatementListener lser = new ConstructionFunctionStatementListener();
        ctx.constructionFunctionStatement().enterRule(lser);

        if (PizzaIR.state == ListenState.MEMBER_DECLARATION) {
            String name = lser.name;

            if (!PizzaIR.dom.getLastClass().equals(name)) {
                assert false;
            }

            Type t = PizzaIR.typeList.getType(PizzaIR.dom.getClassTrace());

            t.addMethod(name, PizzaIR.dom.getClassTrace() + "." + name);
        } else {

        }
    }

    @Override
    public void enterClassFunctionDefinitionStatement(Mx_starParser.ClassFunctionDefinitionStatementContext ctx) {
        FunctionDefinitionStatementListener lser = new FunctionDefinitionStatementListener();
        ctx.functionDefinitionStatement().enterRule(lser);

        if (PizzaIR.state == ListenState.MEMBER_DECLARATION) {
            String name = lser.name;
            Type t = PizzaIR.typeList.getType(PizzaIR.dom.getClassTrace());

            t.addMethod(name, PizzaIR.dom.getClassTrace() + "." + name);
        } else {

        }
    }
}