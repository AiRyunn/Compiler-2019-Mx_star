// Generated from Mx_star.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Mx_starParser}.
 */
public interface Mx_starListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Mx_starParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(Mx_starParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_starParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(Mx_starParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_starParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaration(Mx_starParser.VariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_starParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaration(Mx_starParser.VariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_starParser#classDefinition}.
	 * @param ctx the parse tree
	 */
	void enterClassDefinition(Mx_starParser.ClassDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_starParser#classDefinition}.
	 * @param ctx the parse tree
	 */
	void exitClassDefinition(Mx_starParser.ClassDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_starParser#classMembers}.
	 * @param ctx the parse tree
	 */
	void enterClassMembers(Mx_starParser.ClassMembersContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_starParser#classMembers}.
	 * @param ctx the parse tree
	 */
	void exitClassMembers(Mx_starParser.ClassMembersContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_starParser#memberVariable}.
	 * @param ctx the parse tree
	 */
	void enterMemberVariable(Mx_starParser.MemberVariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_starParser#memberVariable}.
	 * @param ctx the parse tree
	 */
	void exitMemberVariable(Mx_starParser.MemberVariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_starParser#constructionFunction}.
	 * @param ctx the parse tree
	 */
	void enterConstructionFunction(Mx_starParser.ConstructionFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_starParser#constructionFunction}.
	 * @param ctx the parse tree
	 */
	void exitConstructionFunction(Mx_starParser.ConstructionFunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_starParser#memberFunction}.
	 * @param ctx the parse tree
	 */
	void enterMemberFunction(Mx_starParser.MemberFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_starParser#memberFunction}.
	 * @param ctx the parse tree
	 */
	void exitMemberFunction(Mx_starParser.MemberFunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_starParser#functionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDefinition(Mx_starParser.FunctionDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_starParser#functionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDefinition(Mx_starParser.FunctionDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_starParser#paramListDefinition}.
	 * @param ctx the parse tree
	 */
	void enterParamListDefinition(Mx_starParser.ParamListDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_starParser#paramListDefinition}.
	 * @param ctx the parse tree
	 */
	void exitParamListDefinition(Mx_starParser.ParamListDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_starParser#paramList}.
	 * @param ctx the parse tree
	 */
	void enterParamList(Mx_starParser.ParamListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_starParser#paramList}.
	 * @param ctx the parse tree
	 */
	void exitParamList(Mx_starParser.ParamListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_starParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(Mx_starParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_starParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(Mx_starParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_starParser#statements}.
	 * @param ctx the parse tree
	 */
	void enterStatements(Mx_starParser.StatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_starParser#statements}.
	 * @param ctx the parse tree
	 */
	void exitStatements(Mx_starParser.StatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_starParser#emptyStatement}.
	 * @param ctx the parse tree
	 */
	void enterEmptyStatement(Mx_starParser.EmptyStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_starParser#emptyStatement}.
	 * @param ctx the parse tree
	 */
	void exitEmptyStatement(Mx_starParser.EmptyStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_starParser#assignmentStatement}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentStatement(Mx_starParser.AssignmentStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_starParser#assignmentStatement}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentStatement(Mx_starParser.AssignmentStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_starParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStatement(Mx_starParser.ExpressionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_starParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStatement(Mx_starParser.ExpressionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_starParser#declarationStatement}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationStatement(Mx_starParser.DeclarationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_starParser#declarationStatement}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationStatement(Mx_starParser.DeclarationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_starParser#loopStatement}.
	 * @param ctx the parse tree
	 */
	void enterLoopStatement(Mx_starParser.LoopStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_starParser#loopStatement}.
	 * @param ctx the parse tree
	 */
	void exitLoopStatement(Mx_starParser.LoopStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_starParser#forCondition}.
	 * @param ctx the parse tree
	 */
	void enterForCondition(Mx_starParser.ForConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_starParser#forCondition}.
	 * @param ctx the parse tree
	 */
	void exitForCondition(Mx_starParser.ForConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_starParser#forCondition1}.
	 * @param ctx the parse tree
	 */
	void enterForCondition1(Mx_starParser.ForCondition1Context ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_starParser#forCondition1}.
	 * @param ctx the parse tree
	 */
	void exitForCondition1(Mx_starParser.ForCondition1Context ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_starParser#forCondition3}.
	 * @param ctx the parse tree
	 */
	void enterForCondition3(Mx_starParser.ForCondition3Context ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_starParser#forCondition3}.
	 * @param ctx the parse tree
	 */
	void exitForCondition3(Mx_starParser.ForCondition3Context ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_starParser#conditionStatement}.
	 * @param ctx the parse tree
	 */
	void enterConditionStatement(Mx_starParser.ConditionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_starParser#conditionStatement}.
	 * @param ctx the parse tree
	 */
	void exitConditionStatement(Mx_starParser.ConditionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_starParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterJumpStatement(Mx_starParser.JumpStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_starParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitJumpStatement(Mx_starParser.JumpStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_starParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void enterCompoundStatement(Mx_starParser.CompoundStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_starParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void exitCompoundStatement(Mx_starParser.CompoundStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_starParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(Mx_starParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_starParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(Mx_starParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_starParser#object}.
	 * @param ctx the parse tree
	 */
	void enterObject(Mx_starParser.ObjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_starParser#object}.
	 * @param ctx the parse tree
	 */
	void exitObject(Mx_starParser.ObjectContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_starParser#newObject}.
	 * @param ctx the parse tree
	 */
	void enterNewObject(Mx_starParser.NewObjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_starParser#newObject}.
	 * @param ctx the parse tree
	 */
	void exitNewObject(Mx_starParser.NewObjectContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_starParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(Mx_starParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_starParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(Mx_starParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_starParser#simpleType}.
	 * @param ctx the parse tree
	 */
	void enterSimpleType(Mx_starParser.SimpleTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_starParser#simpleType}.
	 * @param ctx the parse tree
	 */
	void exitSimpleType(Mx_starParser.SimpleTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_starParser#compositeType}.
	 * @param ctx the parse tree
	 */
	void enterCompositeType(Mx_starParser.CompositeTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_starParser#compositeType}.
	 * @param ctx the parse tree
	 */
	void exitCompositeType(Mx_starParser.CompositeTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_starParser#fundamentalType}.
	 * @param ctx the parse tree
	 */
	void enterFundamentalType(Mx_starParser.FundamentalTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_starParser#fundamentalType}.
	 * @param ctx the parse tree
	 */
	void exitFundamentalType(Mx_starParser.FundamentalTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_starParser#customType}.
	 * @param ctx the parse tree
	 */
	void enterCustomType(Mx_starParser.CustomTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_starParser#customType}.
	 * @param ctx the parse tree
	 */
	void exitCustomType(Mx_starParser.CustomTypeContext ctx);
}