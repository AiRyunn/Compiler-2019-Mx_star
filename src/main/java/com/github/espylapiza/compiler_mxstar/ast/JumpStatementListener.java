package com.github.espylapiza.compiler_mxstar.ast;

import com.github.espylapiza.compiler_mxstar.parser.*;

public class JumpStatementListener extends Mx_starBaseListener {
    @Override
    public void enterJumpReturn(Mx_starParser.JumpReturnContext ctx) {
        if (!PizzaIR.dom.inFunc()) {
            assert false;
        }

        if (ctx.object() != null) {
            ObjectListener lser = new ObjectListener();
            ctx.object().enterRule(lser);
            String objType = lser.type;

            String rtype = PizzaIR.dom.getRtype();
            if (objType.equals("null")) {
                if (rtype.endsWith("[]") || !PizzaIR.typeList.getType(rtype).isBuiltin()) {
                    // return null
                } else {
                    assert false;
                }
            } else if (!objType.equals(PizzaIR.dom.getRtype())) {
                assert false;
            } else {

            }

        } else {
            if (!PizzaIR.dom.getRtype().equals("void")) {
                assert false;
            }
        }
    }

    @Override
    public void enterJumpBreak(Mx_starParser.JumpBreakContext ctx) {
        if (!PizzaIR.dom.inLoop()) {
            assert false;
        }
    }

    @Override
    public void enterJumpContinue(Mx_starParser.JumpContinueContext ctx) {
        if (!PizzaIR.dom.inLoop()) {
            assert false;
        }
    }
}