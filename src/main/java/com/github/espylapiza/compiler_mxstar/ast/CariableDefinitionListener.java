package com.github.espylapiza.compiler_mxstar.ast;

import com.github.espylapiza.compiler_mxstar.parser.*;

class VariableDeclarationListener extends Mx_starBaseListener {
    String name, type;

    @Override
    public void enterVariableDeclaration(Mx_starParser.VariableDeclarationContext ctx) {
        name = ctx.Identifier().getText();
        type = ctx.type().getText();

        if (!PizzaIR.typeList.hasType(type)) {
            assert false;
        }
    }
}