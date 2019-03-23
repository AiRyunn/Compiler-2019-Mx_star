package Mx_star.PizzaIR;

import Mx_star.AST.*;

public class ForCondition3Listener extends Mx_starBaseListener {
    @Override
    public void enterForCdt3VariableAssignment(Mx_starParser.ForCdt3VariableAssignmentContext ctx) {
        VariableAssignmentListener lser = new VariableAssignmentListener();
        ctx.variableAssignment().enterRule(lser);
    }

    @Override
    public void enterForCdt3Object(Mx_starParser.ForCdt3ObjectContext ctx) {
        ObjectListener objLser = new ObjectListener();
        ctx.object().enterRule(objLser);
    }
}