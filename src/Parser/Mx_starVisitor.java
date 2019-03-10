// Generated from Mx_star.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Mx_starParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface Mx_starVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link Mx_starParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(Mx_starParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link Mx_starParser#variableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaration(Mx_starParser.VariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Mx_starParser#classDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDefinition(Mx_starParser.ClassDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Mx_starParser#classMembers}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassMembers(Mx_starParser.ClassMembersContext ctx);
	/**
	 * Visit a parse tree produced by {@link Mx_starParser#memberVariable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberVariable(Mx_starParser.MemberVariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link Mx_starParser#constructionFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructionFunction(Mx_starParser.ConstructionFunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Mx_starParser#memberFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberFunction(Mx_starParser.MemberFunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Mx_starParser#functionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDefinition(Mx_starParser.FunctionDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Mx_starParser#paramListDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamListDefinition(Mx_starParser.ParamListDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Mx_starParser#paramList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamList(Mx_starParser.ParamListContext ctx);
	/**
	 * Visit a parse tree produced by {@link Mx_starParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(Mx_starParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Mx_starParser#statements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatements(Mx_starParser.StatementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link Mx_starParser#emptyStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyStatement(Mx_starParser.EmptyStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Mx_starParser#assignmentStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentStatement(Mx_starParser.AssignmentStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Mx_starParser#expressionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionStatement(Mx_starParser.ExpressionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Mx_starParser#declarationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationStatement(Mx_starParser.DeclarationStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Mx_starParser#loopStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoopStatement(Mx_starParser.LoopStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Mx_starParser#forCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForCondition(Mx_starParser.ForConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Mx_starParser#forCondition1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForCondition1(Mx_starParser.ForCondition1Context ctx);
	/**
	 * Visit a parse tree produced by {@link Mx_starParser#forCondition3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForCondition3(Mx_starParser.ForCondition3Context ctx);
	/**
	 * Visit a parse tree produced by {@link Mx_starParser#conditionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionStatement(Mx_starParser.ConditionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Mx_starParser#jumpStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJumpStatement(Mx_starParser.JumpStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Mx_starParser#compoundStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompoundStatement(Mx_starParser.CompoundStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Mx_starParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(Mx_starParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Mx_starParser#object}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObject(Mx_starParser.ObjectContext ctx);
	/**
	 * Visit a parse tree produced by {@link Mx_starParser#newObject}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewObject(Mx_starParser.NewObjectContext ctx);
	/**
	 * Visit a parse tree produced by {@link Mx_starParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(Mx_starParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Mx_starParser#simpleType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleType(Mx_starParser.SimpleTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Mx_starParser#compositeType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompositeType(Mx_starParser.CompositeTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Mx_starParser#fundamentalType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFundamentalType(Mx_starParser.FundamentalTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Mx_starParser#customType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCustomType(Mx_starParser.CustomTypeContext ctx);
}