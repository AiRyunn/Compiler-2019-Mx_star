package Mx_star.PizzaIR;

import Mx_star.AST.*;

public class StatementListener extends Mx_starBaseListener {
    @Override
    public void enterStmtEmptyStatement(Mx_starParser.StmtEmptyStatementContext ctx) {
    }

    @Override
    public void enterStmtVariableDeclarationStatement(Mx_starParser.StmtVariableDeclarationStatementContext ctx) {
        var lser = new VariableDeclarationStatementListener();
        ctx.variableDeclarationStatement().enterRule(lser);
    }

    @Override
    public void enterStmtVariableDefinitionStatement(Mx_starParser.StmtVariableDefinitionStatementContext ctx) {
        var lser = new VariableDefinitionStatementListener();
        ctx.variableDefinitionStatement().enterRule(lser);
    }

    @Override
    public void enterStmtVariableAssignmentStatement(Mx_starParser.StmtVariableAssignmentStatementContext ctx) {
        var lser = new VariableAssignmentStatementListener();
        ctx.variableAssignmentStatement().enterRule(lser);
    }

    @Override
    public void enterStmtObjectStatement(Mx_starParser.StmtObjectStatementContext ctx) {
        var lser = new ObjectStatementListener();
        ctx.objectStatement().enterRule(lser);
    }

    @Override
    public void enterStmtLoopStatement(Mx_starParser.StmtLoopStatementContext ctx) {
        var lser = new LoopStatementListener();
        ctx.loopStatement().enterRule(lser);
    }

    @Override
    public void enterStmtConditionStatement(Mx_starParser.StmtConditionStatementContext ctx) {
        var lser = new ConditionStatementListener();
        ctx.conditionStatement().enterRule(lser);
    }

    @Override
    public void enterStmtJumpStatement(Mx_starParser.StmtJumpStatementContext ctx) {
        var lser = new JumpStatementListener();
        ctx.jumpStatement().enterRule(lser);
    }

    @Override
    public void enterStmtCompoundStatement(Mx_starParser.StmtCompoundStatementContext ctx) {
        var lser = new CompoundStatementListener();
        ctx.compoundStatement().enterRule(lser);
    }
}