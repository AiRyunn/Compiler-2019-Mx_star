package com.github.espylapiza.compiler_mxstar.ast;

import com.github.espylapiza.compiler_mxstar.parser.*;

public class ObjectStatementListener extends Mx_starBaseListener {
    @Override
    public void enterObjectStatement(Mx_starParser.ObjectStatementContext ctx) {
        ObjectListener lser = new ObjectListener();
        ctx.object().enterRule(lser);
    }
}