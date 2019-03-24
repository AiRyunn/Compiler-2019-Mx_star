package com.github.espylapiza.compiler_mxstar.ast;

import com.github.espylapiza.compiler_mxstar.parser.*;

public class StatementsListener extends Mx_starBaseListener {
    @Override
    public void enterStatements(Mx_starParser.StatementsContext ctx) {
        ctx.statement().forEach(ch -> {
            StatementListener lser = new StatementListener();
            ch.enterRule(lser);
        });
    }
}