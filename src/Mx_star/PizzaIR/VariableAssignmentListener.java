package Mx_star.PizzaIR;

import Mx_star.AST.*;

class VariableAssignmentListener extends Mx_starBaseListener {
    @Override
    public void enterVariableAssignment(Mx_starParser.VariableAssignmentContext ctx) {
        var objLser = new ObjectListener();
        ctx.object().enterRule(objLser);

        var lvalueLser = new LvalueListener();
        ctx.lvalue().enterRule(lvalueLser);

        // String name = lvalueLser.name;
        String type = lvalueLser.type;

        if ((type.endsWith("[]") || !PizzaIR.typeList.getType(type).isBuiltin()) && objLser.type.equals("null")) {

        } else {
            if (!type.equals(objLser.type)) {
                assert false;
            }
        }
    }
}