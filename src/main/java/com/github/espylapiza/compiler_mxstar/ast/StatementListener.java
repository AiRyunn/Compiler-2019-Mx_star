package com.github.espylapiza.compiler_mxstar.ast;

import com.github.espylapiza.compiler_mxstar.parser.*;

public class StatementListener extends Mx_starBaseListener {
    @Override
    public void enterStmtEmptyStatement(Mx_starParser.StmtEmptyStatementContext ctx) {
    }

    @Override
    public void enterStmtVariableDeclarationStatement(Mx_starParser.StmtVariableDeclarationStatementContext ctx) {
        VariableDeclarationStatementListener lser = new VariableDeclarationStatementListener();
        ctx.variableDeclarationStatement().enterRule(lser);
    }

    @Override
    public void enterStmtVariableDefinitionStatement(Mx_starParser.StmtVariableDefinitionStatementContext ctx) {
        VariableDefinitionStatementListener lser = new VariableDefinitionStatementListener();
        ctx.variableDefinitionStatement().enterRule(lser);
    }

    @Override
    public void enterStmtVariableAssignmentStatement(Mx_starParser.StmtVariableAssignmentStatementContext ctx) {
        VariableAssignmentStatementListener lser = new VariableAssignmentStatementListener();
        ctx.variableAssignmentStatement().enterRule(lser);
    }

    @Override
    public void enterStmtObjectStatement(Mx_starParser.StmtObjectStatementContext ctx) {
        ObjectStatementListener lser = new ObjectStatementListener();
        ctx.objectStatement().enterRule(lser);
    }

    @Override
    public void enterStmtLoopStatement(Mx_starParser.StmtLoopStatementContext ctx) {
        LoopStatementListener lser = new LoopStatementListener();
        ctx.loopStatement().enterRule(lser);
    }

    @Override
    public void enterStmtConditionStatement(Mx_starParser.StmtConditionStatementContext ctx) {
        ConditionStatementListener lser = new ConditionStatementListener();
        ctx.conditionStatement().enterRule(lser);
    }

    @Override
    public void enterStmtJumpStatement(Mx_starParser.StmtJumpStatementContext ctx) {
        JumpStatementListener lser = new JumpStatementListener();
        ctx.jumpStatement().enterRule(lser);
    }

    @Override
    public void enterStmtCompoundStatement(Mx_starParser.StmtCompoundStatementContext ctx) {
        CompoundStatementListener lser = new CompoundStatementListener();
        ctx.compoundStatement().enterRule(lser);
    }
}