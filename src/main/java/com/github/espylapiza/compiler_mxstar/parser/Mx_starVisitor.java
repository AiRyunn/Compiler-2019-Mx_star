// Generated from Mx_star.g4 by ANTLR 4.7.1

package com.github.espylapiza.compiler_mxstar.parser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Mx_starParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 *            operations with no return type.
 */
public interface Mx_starVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link Mx_starParser#program}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(Mx_starParser.ProgramContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ProgramEmptyStatement} labeled
	 * alternative in {@link Mx_starParser#programSection}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgramEmptyStatement(Mx_starParser.ProgramEmptyStatementContext ctx);

	/**
	 * Visit a parse tree produced by the
	 * {@code ProgramVariableDeclarationStatement} labeled alternative in
	 * {@link Mx_starParser#programSection}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgramVariableDeclarationStatement(Mx_starParser.ProgramVariableDeclarationStatementContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ProgramVariableDefinitionStatement}
	 * labeled alternative in {@link Mx_starParser#programSection}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgramVariableDefinitionStatement(Mx_starParser.ProgramVariableDefinitionStatementContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ProgramClassDefinitionStatement}
	 * labeled alternative in {@link Mx_starParser#programSection}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgramClassDefinitionStatement(Mx_starParser.ProgramClassDefinitionStatementContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ProgramFunctionDefinitionStatement}
	 * labeled alternative in {@link Mx_starParser#programSection}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgramFunctionDefinitionStatement(Mx_starParser.ProgramFunctionDefinitionStatementContext ctx);

	/**
	 * Visit a parse tree produced by
	 * {@link Mx_starParser#classDefinitionStatement}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDefinitionStatement(Mx_starParser.ClassDefinitionStatementContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ClassVariableDeclarationStatement}
	 * labeled alternative in {@link Mx_starParser#classMember}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassVariableDeclarationStatement(Mx_starParser.ClassVariableDeclarationStatementContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ClassConstructionFunctionStatement}
	 * labeled alternative in {@link Mx_starParser#classMember}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassConstructionFunctionStatement(Mx_starParser.ClassConstructionFunctionStatementContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ClassFunctionDefinitionStatement}
	 * labeled alternative in {@link Mx_starParser#classMember}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassFunctionDefinitionStatement(Mx_starParser.ClassFunctionDefinitionStatementContext ctx);

	/**
	 * Visit a parse tree produced by
	 * {@link Mx_starParser#constructionFunctionStatement}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructionFunctionStatement(Mx_starParser.ConstructionFunctionStatementContext ctx);

	/**
	 * Visit a parse tree produced by
	 * {@link Mx_starParser#functionDefinitionStatement}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDefinitionStatement(Mx_starParser.FunctionDefinitionStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link Mx_starParser#paramListDefinition}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamListDefinition(Mx_starParser.ParamListDefinitionContext ctx);

	/**
	 * Visit a parse tree produced by {@link Mx_starParser#paramList}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamList(Mx_starParser.ParamListContext ctx);

	/**
	 * Visit a parse tree produced by the {@code StmtEmptyStatement} labeled
	 * alternative in {@link Mx_starParser#statement}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtEmptyStatement(Mx_starParser.StmtEmptyStatementContext ctx);

	/**
	 * Visit a parse tree produced by the {@code StmtVariableDeclarationStatement}
	 * labeled alternative in {@link Mx_starParser#statement}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtVariableDeclarationStatement(Mx_starParser.StmtVariableDeclarationStatementContext ctx);

	/**
	 * Visit a parse tree produced by the {@code StmtVariableDefinitionStatement}
	 * labeled alternative in {@link Mx_starParser#statement}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtVariableDefinitionStatement(Mx_starParser.StmtVariableDefinitionStatementContext ctx);

	/**
	 * Visit a parse tree produced by the {@code StmtVariableAssignmentStatement}
	 * labeled alternative in {@link Mx_starParser#statement}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtVariableAssignmentStatement(Mx_starParser.StmtVariableAssignmentStatementContext ctx);

	/**
	 * Visit a parse tree produced by the {@code StmtObjectStatement} labeled
	 * alternative in {@link Mx_starParser#statement}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtObjectStatement(Mx_starParser.StmtObjectStatementContext ctx);

	/**
	 * Visit a parse tree produced by the {@code StmtLoopStatement} labeled
	 * alternative in {@link Mx_starParser#statement}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtLoopStatement(Mx_starParser.StmtLoopStatementContext ctx);

	/**
	 * Visit a parse tree produced by the {@code StmtConditionStatement} labeled
	 * alternative in {@link Mx_starParser#statement}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtConditionStatement(Mx_starParser.StmtConditionStatementContext ctx);

	/**
	 * Visit a parse tree produced by the {@code StmtJumpStatement} labeled
	 * alternative in {@link Mx_starParser#statement}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtJumpStatement(Mx_starParser.StmtJumpStatementContext ctx);

	/**
	 * Visit a parse tree produced by the {@code StmtCompoundStatement} labeled
	 * alternative in {@link Mx_starParser#statement}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtCompoundStatement(Mx_starParser.StmtCompoundStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link Mx_starParser#statements}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatements(Mx_starParser.StatementsContext ctx);

	/**
	 * Visit a parse tree produced by {@link Mx_starParser#emptyStatement}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyStatement(Mx_starParser.EmptyStatementContext ctx);

	/**
	 * Visit a parse tree produced by
	 * {@link Mx_starParser#variableDeclarationStatement}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclarationStatement(Mx_starParser.VariableDeclarationStatementContext ctx);

	/**
	 * Visit a parse tree produced by
	 * {@link Mx_starParser#variableDefinitionStatement}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDefinitionStatement(Mx_starParser.VariableDefinitionStatementContext ctx);

	/**
	 * Visit a parse tree produced by
	 * {@link Mx_starParser#variableAssignmentStatement}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableAssignmentStatement(Mx_starParser.VariableAssignmentStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link Mx_starParser#objectStatement}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectStatement(Mx_starParser.ObjectStatementContext ctx);

	/**
	 * Visit a parse tree produced by the {@code WhileLoop} labeled alternative in
	 * {@link Mx_starParser#loopStatement}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileLoop(Mx_starParser.WhileLoopContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ForLoop} labeled alternative in
	 * {@link Mx_starParser#loopStatement}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForLoop(Mx_starParser.ForLoopContext ctx);

	/**
	 * Visit a parse tree produced by {@link Mx_starParser#forCondition}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForCondition(Mx_starParser.ForConditionContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ForCdt1VariableDeclaration} labeled
	 * alternative in {@link Mx_starParser#forCondition1}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForCdt1VariableDeclaration(Mx_starParser.ForCdt1VariableDeclarationContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ForCdt1VariableDefinition} labeled
	 * alternative in {@link Mx_starParser#forCondition1}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForCdt1VariableDefinition(Mx_starParser.ForCdt1VariableDefinitionContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ForCdt1VariableAssignment} labeled
	 * alternative in {@link Mx_starParser#forCondition1}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForCdt1VariableAssignment(Mx_starParser.ForCdt1VariableAssignmentContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ForCdt1Object} labeled alternative
	 * in {@link Mx_starParser#forCondition1}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForCdt1Object(Mx_starParser.ForCdt1ObjectContext ctx);

	/**
	 * Visit a parse tree produced by {@link Mx_starParser#forCondition2}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForCondition2(Mx_starParser.ForCondition2Context ctx);

	/**
	 * Visit a parse tree produced by the {@code ForCdt3VariableAssignment} labeled
	 * alternative in {@link Mx_starParser#forCondition3}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForCdt3VariableAssignment(Mx_starParser.ForCdt3VariableAssignmentContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ForCdt3Object} labeled alternative
	 * in {@link Mx_starParser#forCondition3}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForCdt3Object(Mx_starParser.ForCdt3ObjectContext ctx);

	/**
	 * Visit a parse tree produced by {@link Mx_starParser#conditionStatement}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionStatement(Mx_starParser.ConditionStatementContext ctx);

	/**
	 * Visit a parse tree produced by the {@code JumpReturn} labeled alternative in
	 * {@link Mx_starParser#jumpStatement}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJumpReturn(Mx_starParser.JumpReturnContext ctx);

	/**
	 * Visit a parse tree produced by the {@code JumpBreak} labeled alternative in
	 * {@link Mx_starParser#jumpStatement}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJumpBreak(Mx_starParser.JumpBreakContext ctx);

	/**
	 * Visit a parse tree produced by the {@code JumpContinue} labeled alternative
	 * in {@link Mx_starParser#jumpStatement}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJumpContinue(Mx_starParser.JumpContinueContext ctx);

	/**
	 * Visit a parse tree produced by {@link Mx_starParser#compoundStatement}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompoundStatement(Mx_starParser.CompoundStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link Mx_starParser#variableDeclaration}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaration(Mx_starParser.VariableDeclarationContext ctx);

	/**
	 * Visit a parse tree produced by {@link Mx_starParser#variableDefinition}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDefinition(Mx_starParser.VariableDefinitionContext ctx);

	/**
	 * Visit a parse tree produced by {@link Mx_starParser#variableAssignment}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableAssignment(Mx_starParser.VariableAssignmentContext ctx);

	/**
	 * Visit a parse tree produced by the {@code SubscriptLvalue} labeled
	 * alternative in {@link Mx_starParser#lvalue}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubscriptLvalue(Mx_starParser.SubscriptLvalueContext ctx);

	/**
	 * Visit a parse tree produced by the {@code MemberLvalue} labeled alternative
	 * in {@link Mx_starParser#lvalue}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberLvalue(Mx_starParser.MemberLvalueContext ctx);

	/**
	 * Visit a parse tree produced by the {@code IdentifierLvalue} labeled
	 * alternative in {@link Mx_starParser#lvalue}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierLvalue(Mx_starParser.IdentifierLvalueContext ctx);

	/**
	 * Visit a parse tree produced by the {@code FunctionReturnObject} labeled
	 * alternative in {@link Mx_starParser#object}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionReturnObject(Mx_starParser.FunctionReturnObjectContext ctx);

	/**
	 * Visit a parse tree produced by the {@code SubscriptObject} labeled
	 * alternative in {@link Mx_starParser#object}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubscriptObject(Mx_starParser.SubscriptObjectContext ctx);

	/**
	 * Visit a parse tree produced by the {@code NewObject} labeled alternative in
	 * {@link Mx_starParser#object}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewObject(Mx_starParser.NewObjectContext ctx);

	/**
	 * Visit a parse tree produced by the {@code IdentifierObject} labeled
	 * alternative in {@link Mx_starParser#object}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierObject(Mx_starParser.IdentifierObjectContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ConstantObject} labeled alternative
	 * in {@link Mx_starParser#object}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantObject(Mx_starParser.ConstantObjectContext ctx);

	/**
	 * Visit a parse tree produced by the {@code LvalueObject} labeled alternative
	 * in {@link Mx_starParser#object}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLvalueObject(Mx_starParser.LvalueObjectContext ctx);

	/**
	 * Visit a parse tree produced by the {@code MemberObject} labeled alternative
	 * in {@link Mx_starParser#object}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberObject(Mx_starParser.MemberObjectContext ctx);

	/**
	 * Visit a parse tree produced by the {@code BracketObject} labeled alternative
	 * in {@link Mx_starParser#object}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBracketObject(Mx_starParser.BracketObjectContext ctx);

	/**
	 * Visit a parse tree produced by the {@code BinaryOperatorObject} labeled
	 * alternative in {@link Mx_starParser#object}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryOperatorObject(Mx_starParser.BinaryOperatorObjectContext ctx);

	/**
	 * Visit a parse tree produced by the {@code UnaryOperatorObject} labeled
	 * alternative in {@link Mx_starParser#object}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOperatorObject(Mx_starParser.UnaryOperatorObjectContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ThisObject} labeled alternative in
	 * {@link Mx_starParser#object}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThisObject(Mx_starParser.ThisObjectContext ctx);

	/**
	 * Visit a parse tree produced by {@link Mx_starParser#rtype}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(Mx_starParser.TypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link Mx_starParser#simpleType}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleType(Mx_starParser.SimpleTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link Mx_starParser#compositeType}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompositeType(Mx_starParser.CompositeTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link Mx_starParser#fundamentalType}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFundamentalType(Mx_starParser.FundamentalTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link Mx_starParser#customType}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCustomType(Mx_starParser.CustomTypeContext ctx);

	/**
	 * Visit a parse tree produced by the {@code Null} labeled alternative in
	 * {@link Mx_starParser#constant}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNull(Mx_starParser.NullContext ctx);

	/**
	 * Visit a parse tree produced by the {@code LogicalConstant} labeled
	 * alternative in {@link Mx_starParser#constant}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalConstant(Mx_starParser.LogicalConstantContext ctx);

	/**
	 * Visit a parse tree produced by the {@code IntegerConstant} labeled
	 * alternative in {@link Mx_starParser#constant}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerConstant(Mx_starParser.IntegerConstantContext ctx);

	/**
	 * Visit a parse tree produced by the {@code StringLiteral} labeled alternative
	 * in {@link Mx_starParser#constant}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteral(Mx_starParser.StringLiteralContext ctx);
}