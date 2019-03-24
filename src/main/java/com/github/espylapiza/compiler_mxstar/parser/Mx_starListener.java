// Generated from Mx_star.g4 by ANTLR 4.7.1

package com.github.espylapiza.compiler_mxstar.parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Mx_starParser}.
 */
public interface Mx_starListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Mx_starParser#program}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterProgram(Mx_starParser.ProgramContext ctx);

	/**
	 * Exit a parse tree produced by {@link Mx_starParser#program}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitProgram(Mx_starParser.ProgramContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ProgramEmptyStatement} labeled
	 * alternative in {@link Mx_starParser#programSection}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterProgramEmptyStatement(Mx_starParser.ProgramEmptyStatementContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ProgramEmptyStatement} labeled
	 * alternative in {@link Mx_starParser#programSection}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitProgramEmptyStatement(Mx_starParser.ProgramEmptyStatementContext ctx);

	/**
	 * Enter a parse tree produced by the
	 * {@code ProgramVariableDeclarationStatement} labeled alternative in
	 * {@link Mx_starParser#programSection}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterProgramVariableDeclarationStatement(Mx_starParser.ProgramVariableDeclarationStatementContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ProgramVariableDeclarationStatement}
	 * labeled alternative in {@link Mx_starParser#programSection}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitProgramVariableDeclarationStatement(Mx_starParser.ProgramVariableDeclarationStatementContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ProgramVariableDefinitionStatement}
	 * labeled alternative in {@link Mx_starParser#programSection}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterProgramVariableDefinitionStatement(Mx_starParser.ProgramVariableDefinitionStatementContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ProgramVariableDefinitionStatement}
	 * labeled alternative in {@link Mx_starParser#programSection}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitProgramVariableDefinitionStatement(Mx_starParser.ProgramVariableDefinitionStatementContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ProgramClassDefinitionStatement}
	 * labeled alternative in {@link Mx_starParser#programSection}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterProgramClassDefinitionStatement(Mx_starParser.ProgramClassDefinitionStatementContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ProgramClassDefinitionStatement}
	 * labeled alternative in {@link Mx_starParser#programSection}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitProgramClassDefinitionStatement(Mx_starParser.ProgramClassDefinitionStatementContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ProgramFunctionDefinitionStatement}
	 * labeled alternative in {@link Mx_starParser#programSection}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterProgramFunctionDefinitionStatement(Mx_starParser.ProgramFunctionDefinitionStatementContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ProgramFunctionDefinitionStatement}
	 * labeled alternative in {@link Mx_starParser#programSection}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitProgramFunctionDefinitionStatement(Mx_starParser.ProgramFunctionDefinitionStatementContext ctx);

	/**
	 * Enter a parse tree produced by
	 * {@link Mx_starParser#classDefinitionStatement}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterClassDefinitionStatement(Mx_starParser.ClassDefinitionStatementContext ctx);

	/**
	 * Exit a parse tree produced by {@link Mx_starParser#classDefinitionStatement}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitClassDefinitionStatement(Mx_starParser.ClassDefinitionStatementContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ClassVariableDeclarationStatement}
	 * labeled alternative in {@link Mx_starParser#classMember}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterClassVariableDeclarationStatement(Mx_starParser.ClassVariableDeclarationStatementContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ClassVariableDeclarationStatement}
	 * labeled alternative in {@link Mx_starParser#classMember}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitClassVariableDeclarationStatement(Mx_starParser.ClassVariableDeclarationStatementContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ClassConstructionFunctionStatement}
	 * labeled alternative in {@link Mx_starParser#classMember}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterClassConstructionFunctionStatement(Mx_starParser.ClassConstructionFunctionStatementContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ClassConstructionFunctionStatement}
	 * labeled alternative in {@link Mx_starParser#classMember}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitClassConstructionFunctionStatement(Mx_starParser.ClassConstructionFunctionStatementContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ClassFunctionDefinitionStatement}
	 * labeled alternative in {@link Mx_starParser#classMember}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterClassFunctionDefinitionStatement(Mx_starParser.ClassFunctionDefinitionStatementContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ClassFunctionDefinitionStatement}
	 * labeled alternative in {@link Mx_starParser#classMember}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitClassFunctionDefinitionStatement(Mx_starParser.ClassFunctionDefinitionStatementContext ctx);

	/**
	 * Enter a parse tree produced by
	 * {@link Mx_starParser#constructionFunctionStatement}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterConstructionFunctionStatement(Mx_starParser.ConstructionFunctionStatementContext ctx);

	/**
	 * Exit a parse tree produced by
	 * {@link Mx_starParser#constructionFunctionStatement}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitConstructionFunctionStatement(Mx_starParser.ConstructionFunctionStatementContext ctx);

	/**
	 * Enter a parse tree produced by
	 * {@link Mx_starParser#functionDefinitionStatement}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterFunctionDefinitionStatement(Mx_starParser.FunctionDefinitionStatementContext ctx);

	/**
	 * Exit a parse tree produced by
	 * {@link Mx_starParser#functionDefinitionStatement}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitFunctionDefinitionStatement(Mx_starParser.FunctionDefinitionStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link Mx_starParser#paramListDefinition}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterParamListDefinition(Mx_starParser.ParamListDefinitionContext ctx);

	/**
	 * Exit a parse tree produced by {@link Mx_starParser#paramListDefinition}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitParamListDefinition(Mx_starParser.ParamListDefinitionContext ctx);

	/**
	 * Enter a parse tree produced by {@link Mx_starParser#paramList}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterParamList(Mx_starParser.ParamListContext ctx);

	/**
	 * Exit a parse tree produced by {@link Mx_starParser#paramList}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitParamList(Mx_starParser.ParamListContext ctx);

	/**
	 * Enter a parse tree produced by the {@code StmtEmptyStatement} labeled
	 * alternative in {@link Mx_starParser#statement}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterStmtEmptyStatement(Mx_starParser.StmtEmptyStatementContext ctx);

	/**
	 * Exit a parse tree produced by the {@code StmtEmptyStatement} labeled
	 * alternative in {@link Mx_starParser#statement}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitStmtEmptyStatement(Mx_starParser.StmtEmptyStatementContext ctx);

	/**
	 * Enter a parse tree produced by the {@code StmtVariableDeclarationStatement}
	 * labeled alternative in {@link Mx_starParser#statement}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterStmtVariableDeclarationStatement(Mx_starParser.StmtVariableDeclarationStatementContext ctx);

	/**
	 * Exit a parse tree produced by the {@code StmtVariableDeclarationStatement}
	 * labeled alternative in {@link Mx_starParser#statement}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitStmtVariableDeclarationStatement(Mx_starParser.StmtVariableDeclarationStatementContext ctx);

	/**
	 * Enter a parse tree produced by the {@code StmtVariableDefinitionStatement}
	 * labeled alternative in {@link Mx_starParser#statement}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterStmtVariableDefinitionStatement(Mx_starParser.StmtVariableDefinitionStatementContext ctx);

	/**
	 * Exit a parse tree produced by the {@code StmtVariableDefinitionStatement}
	 * labeled alternative in {@link Mx_starParser#statement}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitStmtVariableDefinitionStatement(Mx_starParser.StmtVariableDefinitionStatementContext ctx);

	/**
	 * Enter a parse tree produced by the {@code StmtVariableAssignmentStatement}
	 * labeled alternative in {@link Mx_starParser#statement}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterStmtVariableAssignmentStatement(Mx_starParser.StmtVariableAssignmentStatementContext ctx);

	/**
	 * Exit a parse tree produced by the {@code StmtVariableAssignmentStatement}
	 * labeled alternative in {@link Mx_starParser#statement}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitStmtVariableAssignmentStatement(Mx_starParser.StmtVariableAssignmentStatementContext ctx);

	/**
	 * Enter a parse tree produced by the {@code StmtObjectStatement} labeled
	 * alternative in {@link Mx_starParser#statement}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterStmtObjectStatement(Mx_starParser.StmtObjectStatementContext ctx);

	/**
	 * Exit a parse tree produced by the {@code StmtObjectStatement} labeled
	 * alternative in {@link Mx_starParser#statement}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitStmtObjectStatement(Mx_starParser.StmtObjectStatementContext ctx);

	/**
	 * Enter a parse tree produced by the {@code StmtLoopStatement} labeled
	 * alternative in {@link Mx_starParser#statement}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterStmtLoopStatement(Mx_starParser.StmtLoopStatementContext ctx);

	/**
	 * Exit a parse tree produced by the {@code StmtLoopStatement} labeled
	 * alternative in {@link Mx_starParser#statement}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitStmtLoopStatement(Mx_starParser.StmtLoopStatementContext ctx);

	/**
	 * Enter a parse tree produced by the {@code StmtConditionStatement} labeled
	 * alternative in {@link Mx_starParser#statement}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterStmtConditionStatement(Mx_starParser.StmtConditionStatementContext ctx);

	/**
	 * Exit a parse tree produced by the {@code StmtConditionStatement} labeled
	 * alternative in {@link Mx_starParser#statement}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitStmtConditionStatement(Mx_starParser.StmtConditionStatementContext ctx);

	/**
	 * Enter a parse tree produced by the {@code StmtJumpStatement} labeled
	 * alternative in {@link Mx_starParser#statement}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterStmtJumpStatement(Mx_starParser.StmtJumpStatementContext ctx);

	/**
	 * Exit a parse tree produced by the {@code StmtJumpStatement} labeled
	 * alternative in {@link Mx_starParser#statement}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitStmtJumpStatement(Mx_starParser.StmtJumpStatementContext ctx);

	/**
	 * Enter a parse tree produced by the {@code StmtCompoundStatement} labeled
	 * alternative in {@link Mx_starParser#statement}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterStmtCompoundStatement(Mx_starParser.StmtCompoundStatementContext ctx);

	/**
	 * Exit a parse tree produced by the {@code StmtCompoundStatement} labeled
	 * alternative in {@link Mx_starParser#statement}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitStmtCompoundStatement(Mx_starParser.StmtCompoundStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link Mx_starParser#statements}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterStatements(Mx_starParser.StatementsContext ctx);

	/**
	 * Exit a parse tree produced by {@link Mx_starParser#statements}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitStatements(Mx_starParser.StatementsContext ctx);

	/**
	 * Enter a parse tree produced by {@link Mx_starParser#emptyStatement}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterEmptyStatement(Mx_starParser.EmptyStatementContext ctx);

	/**
	 * Exit a parse tree produced by {@link Mx_starParser#emptyStatement}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitEmptyStatement(Mx_starParser.EmptyStatementContext ctx);

	/**
	 * Enter a parse tree produced by
	 * {@link Mx_starParser#variableDeclarationStatement}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarationStatement(Mx_starParser.VariableDeclarationStatementContext ctx);

	/**
	 * Exit a parse tree produced by
	 * {@link Mx_starParser#variableDeclarationStatement}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarationStatement(Mx_starParser.VariableDeclarationStatementContext ctx);

	/**
	 * Enter a parse tree produced by
	 * {@link Mx_starParser#variableDefinitionStatement}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterVariableDefinitionStatement(Mx_starParser.VariableDefinitionStatementContext ctx);

	/**
	 * Exit a parse tree produced by
	 * {@link Mx_starParser#variableDefinitionStatement}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitVariableDefinitionStatement(Mx_starParser.VariableDefinitionStatementContext ctx);

	/**
	 * Enter a parse tree produced by
	 * {@link Mx_starParser#variableAssignmentStatement}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterVariableAssignmentStatement(Mx_starParser.VariableAssignmentStatementContext ctx);

	/**
	 * Exit a parse tree produced by
	 * {@link Mx_starParser#variableAssignmentStatement}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitVariableAssignmentStatement(Mx_starParser.VariableAssignmentStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link Mx_starParser#objectStatement}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterObjectStatement(Mx_starParser.ObjectStatementContext ctx);

	/**
	 * Exit a parse tree produced by {@link Mx_starParser#objectStatement}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitObjectStatement(Mx_starParser.ObjectStatementContext ctx);

	/**
	 * Enter a parse tree produced by the {@code WhileLoop} labeled alternative in
	 * {@link Mx_starParser#loopStatement}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterWhileLoop(Mx_starParser.WhileLoopContext ctx);

	/**
	 * Exit a parse tree produced by the {@code WhileLoop} labeled alternative in
	 * {@link Mx_starParser#loopStatement}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitWhileLoop(Mx_starParser.WhileLoopContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ForLoop} labeled alternative in
	 * {@link Mx_starParser#loopStatement}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterForLoop(Mx_starParser.ForLoopContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ForLoop} labeled alternative in
	 * {@link Mx_starParser#loopStatement}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitForLoop(Mx_starParser.ForLoopContext ctx);

	/**
	 * Enter a parse tree produced by {@link Mx_starParser#forCondition}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterForCondition(Mx_starParser.ForConditionContext ctx);

	/**
	 * Exit a parse tree produced by {@link Mx_starParser#forCondition}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitForCondition(Mx_starParser.ForConditionContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ForCdt1VariableDeclaration} labeled
	 * alternative in {@link Mx_starParser#forCondition1}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterForCdt1VariableDeclaration(Mx_starParser.ForCdt1VariableDeclarationContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ForCdt1VariableDeclaration} labeled
	 * alternative in {@link Mx_starParser#forCondition1}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitForCdt1VariableDeclaration(Mx_starParser.ForCdt1VariableDeclarationContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ForCdt1VariableDefinition} labeled
	 * alternative in {@link Mx_starParser#forCondition1}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterForCdt1VariableDefinition(Mx_starParser.ForCdt1VariableDefinitionContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ForCdt1VariableDefinition} labeled
	 * alternative in {@link Mx_starParser#forCondition1}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitForCdt1VariableDefinition(Mx_starParser.ForCdt1VariableDefinitionContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ForCdt1VariableAssignment} labeled
	 * alternative in {@link Mx_starParser#forCondition1}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterForCdt1VariableAssignment(Mx_starParser.ForCdt1VariableAssignmentContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ForCdt1VariableAssignment} labeled
	 * alternative in {@link Mx_starParser#forCondition1}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitForCdt1VariableAssignment(Mx_starParser.ForCdt1VariableAssignmentContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ForCdt1Object} labeled alternative
	 * in {@link Mx_starParser#forCondition1}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterForCdt1Object(Mx_starParser.ForCdt1ObjectContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ForCdt1Object} labeled alternative
	 * in {@link Mx_starParser#forCondition1}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitForCdt1Object(Mx_starParser.ForCdt1ObjectContext ctx);

	/**
	 * Enter a parse tree produced by {@link Mx_starParser#forCondition2}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterForCondition2(Mx_starParser.ForCondition2Context ctx);

	/**
	 * Exit a parse tree produced by {@link Mx_starParser#forCondition2}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitForCondition2(Mx_starParser.ForCondition2Context ctx);

	/**
	 * Enter a parse tree produced by the {@code ForCdt3VariableAssignment} labeled
	 * alternative in {@link Mx_starParser#forCondition3}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterForCdt3VariableAssignment(Mx_starParser.ForCdt3VariableAssignmentContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ForCdt3VariableAssignment} labeled
	 * alternative in {@link Mx_starParser#forCondition3}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitForCdt3VariableAssignment(Mx_starParser.ForCdt3VariableAssignmentContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ForCdt3Object} labeled alternative
	 * in {@link Mx_starParser#forCondition3}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterForCdt3Object(Mx_starParser.ForCdt3ObjectContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ForCdt3Object} labeled alternative
	 * in {@link Mx_starParser#forCondition3}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitForCdt3Object(Mx_starParser.ForCdt3ObjectContext ctx);

	/**
	 * Enter a parse tree produced by {@link Mx_starParser#conditionStatement}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterConditionStatement(Mx_starParser.ConditionStatementContext ctx);

	/**
	 * Exit a parse tree produced by {@link Mx_starParser#conditionStatement}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitConditionStatement(Mx_starParser.ConditionStatementContext ctx);

	/**
	 * Enter a parse tree produced by the {@code JumpReturn} labeled alternative in
	 * {@link Mx_starParser#jumpStatement}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterJumpReturn(Mx_starParser.JumpReturnContext ctx);

	/**
	 * Exit a parse tree produced by the {@code JumpReturn} labeled alternative in
	 * {@link Mx_starParser#jumpStatement}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitJumpReturn(Mx_starParser.JumpReturnContext ctx);

	/**
	 * Enter a parse tree produced by the {@code JumpBreak} labeled alternative in
	 * {@link Mx_starParser#jumpStatement}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterJumpBreak(Mx_starParser.JumpBreakContext ctx);

	/**
	 * Exit a parse tree produced by the {@code JumpBreak} labeled alternative in
	 * {@link Mx_starParser#jumpStatement}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitJumpBreak(Mx_starParser.JumpBreakContext ctx);

	/**
	 * Enter a parse tree produced by the {@code JumpContinue} labeled alternative
	 * in {@link Mx_starParser#jumpStatement}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterJumpContinue(Mx_starParser.JumpContinueContext ctx);

	/**
	 * Exit a parse tree produced by the {@code JumpContinue} labeled alternative in
	 * {@link Mx_starParser#jumpStatement}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitJumpContinue(Mx_starParser.JumpContinueContext ctx);

	/**
	 * Enter a parse tree produced by {@link Mx_starParser#compoundStatement}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterCompoundStatement(Mx_starParser.CompoundStatementContext ctx);

	/**
	 * Exit a parse tree produced by {@link Mx_starParser#compoundStatement}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitCompoundStatement(Mx_starParser.CompoundStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link Mx_starParser#variableDeclaration}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaration(Mx_starParser.VariableDeclarationContext ctx);

	/**
	 * Exit a parse tree produced by {@link Mx_starParser#variableDeclaration}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaration(Mx_starParser.VariableDeclarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link Mx_starParser#variableDefinition}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterVariableDefinition(Mx_starParser.VariableDefinitionContext ctx);

	/**
	 * Exit a parse tree produced by {@link Mx_starParser#variableDefinition}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitVariableDefinition(Mx_starParser.VariableDefinitionContext ctx);

	/**
	 * Enter a parse tree produced by {@link Mx_starParser#variableAssignment}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterVariableAssignment(Mx_starParser.VariableAssignmentContext ctx);

	/**
	 * Exit a parse tree produced by {@link Mx_starParser#variableAssignment}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitVariableAssignment(Mx_starParser.VariableAssignmentContext ctx);

	/**
	 * Enter a parse tree produced by the {@code SubscriptLvalue} labeled
	 * alternative in {@link Mx_starParser#lvalue}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterSubscriptLvalue(Mx_starParser.SubscriptLvalueContext ctx);

	/**
	 * Exit a parse tree produced by the {@code SubscriptLvalue} labeled alternative
	 * in {@link Mx_starParser#lvalue}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitSubscriptLvalue(Mx_starParser.SubscriptLvalueContext ctx);

	/**
	 * Enter a parse tree produced by the {@code MemberLvalue} labeled alternative
	 * in {@link Mx_starParser#lvalue}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterMemberLvalue(Mx_starParser.MemberLvalueContext ctx);

	/**
	 * Exit a parse tree produced by the {@code MemberLvalue} labeled alternative in
	 * {@link Mx_starParser#lvalue}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitMemberLvalue(Mx_starParser.MemberLvalueContext ctx);

	/**
	 * Enter a parse tree produced by the {@code IdentifierLvalue} labeled
	 * alternative in {@link Mx_starParser#lvalue}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterIdentifierLvalue(Mx_starParser.IdentifierLvalueContext ctx);

	/**
	 * Exit a parse tree produced by the {@code IdentifierLvalue} labeled
	 * alternative in {@link Mx_starParser#lvalue}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitIdentifierLvalue(Mx_starParser.IdentifierLvalueContext ctx);

	/**
	 * Enter a parse tree produced by the {@code FunctionReturnObject} labeled
	 * alternative in {@link Mx_starParser#object}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterFunctionReturnObject(Mx_starParser.FunctionReturnObjectContext ctx);

	/**
	 * Exit a parse tree produced by the {@code FunctionReturnObject} labeled
	 * alternative in {@link Mx_starParser#object}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitFunctionReturnObject(Mx_starParser.FunctionReturnObjectContext ctx);

	/**
	 * Enter a parse tree produced by the {@code SubscriptObject} labeled
	 * alternative in {@link Mx_starParser#object}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterSubscriptObject(Mx_starParser.SubscriptObjectContext ctx);

	/**
	 * Exit a parse tree produced by the {@code SubscriptObject} labeled alternative
	 * in {@link Mx_starParser#object}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitSubscriptObject(Mx_starParser.SubscriptObjectContext ctx);

	/**
	 * Enter a parse tree produced by the {@code NewObject} labeled alternative in
	 * {@link Mx_starParser#object}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterNewObject(Mx_starParser.NewObjectContext ctx);

	/**
	 * Exit a parse tree produced by the {@code NewObject} labeled alternative in
	 * {@link Mx_starParser#object}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitNewObject(Mx_starParser.NewObjectContext ctx);

	/**
	 * Enter a parse tree produced by the {@code IdentifierObject} labeled
	 * alternative in {@link Mx_starParser#object}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterIdentifierObject(Mx_starParser.IdentifierObjectContext ctx);

	/**
	 * Exit a parse tree produced by the {@code IdentifierObject} labeled
	 * alternative in {@link Mx_starParser#object}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitIdentifierObject(Mx_starParser.IdentifierObjectContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ConstantObject} labeled alternative
	 * in {@link Mx_starParser#object}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterConstantObject(Mx_starParser.ConstantObjectContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ConstantObject} labeled alternative
	 * in {@link Mx_starParser#object}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitConstantObject(Mx_starParser.ConstantObjectContext ctx);

	/**
	 * Enter a parse tree produced by the {@code LvalueObject} labeled alternative
	 * in {@link Mx_starParser#object}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterLvalueObject(Mx_starParser.LvalueObjectContext ctx);

	/**
	 * Exit a parse tree produced by the {@code LvalueObject} labeled alternative in
	 * {@link Mx_starParser#object}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitLvalueObject(Mx_starParser.LvalueObjectContext ctx);

	/**
	 * Enter a parse tree produced by the {@code MemberObject} labeled alternative
	 * in {@link Mx_starParser#object}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterMemberObject(Mx_starParser.MemberObjectContext ctx);

	/**
	 * Exit a parse tree produced by the {@code MemberObject} labeled alternative in
	 * {@link Mx_starParser#object}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitMemberObject(Mx_starParser.MemberObjectContext ctx);

	/**
	 * Enter a parse tree produced by the {@code BracketObject} labeled alternative
	 * in {@link Mx_starParser#object}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterBracketObject(Mx_starParser.BracketObjectContext ctx);

	/**
	 * Exit a parse tree produced by the {@code BracketObject} labeled alternative
	 * in {@link Mx_starParser#object}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitBracketObject(Mx_starParser.BracketObjectContext ctx);

	/**
	 * Enter a parse tree produced by the {@code BinaryOperatorObject} labeled
	 * alternative in {@link Mx_starParser#object}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterBinaryOperatorObject(Mx_starParser.BinaryOperatorObjectContext ctx);

	/**
	 * Exit a parse tree produced by the {@code BinaryOperatorObject} labeled
	 * alternative in {@link Mx_starParser#object}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitBinaryOperatorObject(Mx_starParser.BinaryOperatorObjectContext ctx);

	/**
	 * Enter a parse tree produced by the {@code UnaryOperatorObject} labeled
	 * alternative in {@link Mx_starParser#object}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterUnaryOperatorObject(Mx_starParser.UnaryOperatorObjectContext ctx);

	/**
	 * Exit a parse tree produced by the {@code UnaryOperatorObject} labeled
	 * alternative in {@link Mx_starParser#object}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitUnaryOperatorObject(Mx_starParser.UnaryOperatorObjectContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ThisObject} labeled alternative in
	 * {@link Mx_starParser#object}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterThisObject(Mx_starParser.ThisObjectContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ThisObject} labeled alternative in
	 * {@link Mx_starParser#object}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitThisObject(Mx_starParser.ThisObjectContext ctx);

	/**
	 * Enter a parse tree produced by {@link Mx_starParser#rtype}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterType(Mx_starParser.TypeContext ctx);

	/**
	 * Exit a parse tree produced by {@link Mx_starParser#rtype}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitType(Mx_starParser.TypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link Mx_starParser#simpleType}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterSimpleType(Mx_starParser.SimpleTypeContext ctx);

	/**
	 * Exit a parse tree produced by {@link Mx_starParser#simpleType}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitSimpleType(Mx_starParser.SimpleTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link Mx_starParser#compositeType}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterCompositeType(Mx_starParser.CompositeTypeContext ctx);

	/**
	 * Exit a parse tree produced by {@link Mx_starParser#compositeType}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitCompositeType(Mx_starParser.CompositeTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link Mx_starParser#fundamentalType}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterFundamentalType(Mx_starParser.FundamentalTypeContext ctx);

	/**
	 * Exit a parse tree produced by {@link Mx_starParser#fundamentalType}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitFundamentalType(Mx_starParser.FundamentalTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link Mx_starParser#customType}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterCustomType(Mx_starParser.CustomTypeContext ctx);

	/**
	 * Exit a parse tree produced by {@link Mx_starParser#customType}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitCustomType(Mx_starParser.CustomTypeContext ctx);

	/**
	 * Enter a parse tree produced by the {@code Null} labeled alternative in
	 * {@link Mx_starParser#constant}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterNull(Mx_starParser.NullContext ctx);

	/**
	 * Exit a parse tree produced by the {@code Null} labeled alternative in
	 * {@link Mx_starParser#constant}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitNull(Mx_starParser.NullContext ctx);

	/**
	 * Enter a parse tree produced by the {@code LogicalConstant} labeled
	 * alternative in {@link Mx_starParser#constant}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterLogicalConstant(Mx_starParser.LogicalConstantContext ctx);

	/**
	 * Exit a parse tree produced by the {@code LogicalConstant} labeled alternative
	 * in {@link Mx_starParser#constant}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitLogicalConstant(Mx_starParser.LogicalConstantContext ctx);

	/**
	 * Enter a parse tree produced by the {@code IntegerConstant} labeled
	 * alternative in {@link Mx_starParser#constant}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterIntegerConstant(Mx_starParser.IntegerConstantContext ctx);

	/**
	 * Exit a parse tree produced by the {@code IntegerConstant} labeled alternative
	 * in {@link Mx_starParser#constant}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitIntegerConstant(Mx_starParser.IntegerConstantContext ctx);

	/**
	 * Enter a parse tree produced by the {@code StringLiteral} labeled alternative
	 * in {@link Mx_starParser#constant}.
	 * 
	 * @param ctx the parse tree
	 */
	void enterStringLiteral(Mx_starParser.StringLiteralContext ctx);

	/**
	 * Exit a parse tree produced by the {@code StringLiteral} labeled alternative
	 * in {@link Mx_starParser#constant}.
	 * 
	 * @param ctx the parse tree
	 */
	void exitStringLiteral(Mx_starParser.StringLiteralContext ctx);
}