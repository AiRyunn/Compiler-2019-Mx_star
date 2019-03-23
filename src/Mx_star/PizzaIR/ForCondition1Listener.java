package Mx_star.PizzaIR;

import Mx_star.AST.*;

public class ForCondition1Listener extends Mx_starBaseListener {
    @Override
    public void enterForCdt1VariableDeclaration(Mx_starParser.ForCdt1VariableDeclarationContext ctx) {
        var lser = new VariableDeclarationListener();
        ctx.variableDeclaration().enterRule(lser);
        PizzaIR.allocateVariable(lser.name, lser.type);
    }

    @Override
    public void enterForCdt1VariableDefinition(Mx_starParser.ForCdt1VariableDefinitionContext ctx) {
        var lser = new VariableDefinitionListener();
        ctx.variableDefinition().enterRule(lser);
        PizzaIR.allocateVariable(lser.name, lser.type);
    }

    @Override
    public void enterForCdt1VariableAssignment(Mx_starParser.ForCdt1VariableAssignmentContext ctx) {
        var lser = new VariableAssignmentListener();
        ctx.variableAssignment().enterRule(lser);
    }

    @Override
    public void enterForCdt1Object(Mx_starParser.ForCdt1ObjectContext ctx) {
        var objLser = new ObjectListener();
        ctx.object().enterRule(objLser);
    }
}