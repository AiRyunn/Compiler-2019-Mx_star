package com.github.espylapiza.compiler_mxstar.ast;

import com.github.espylapiza.compiler_mxstar.parser.*;

class VariableAssignmentListener extends Mx_starBaseListener {
    @Override
    public void enterVariableAssignment(Mx_starParser.VariableAssignmentContext ctx) {
        ObjectListener objLser = new ObjectListener();
        ctx.object().enterRule(objLser);

        LvalueListener lvalueLser = new LvalueListener();
        ctx.lvalue().enterRule(lvalueLser);

        // String name = lvalueLser.name;
        String type = lvalueLser.type;

        if ((type.endsWith("[]") || !PizzaIRVisitor.typeList.getType(type).isBuiltin())
                && objLser.type.equals("null")) {

        } else {
            if (!type.equals(objLser.type)) {
                assert false;
            }
        }
    }
}