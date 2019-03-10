// Generated from Mx_star.g4 by ANTLR 4.7.1

    package Mx_star.AST;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Mx_starParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, New=3, Bool=4, Int=5, Void=6, String=7, Class=8, This=9, 
		If=10, Else=11, For=12, While=13, Return=14, Break=15, Continue=16, Constant=17, 
		Null=18, True=19, False=20, LogicalConstant=21, IntegerConstant=22, StringLiteral=23, 
		Add=24, Sub=25, Mul=26, Div=27, Mod=28, Less=29, Greater=30, Equal=31, 
		Unequal=32, LessEqual=33, GreaterEqual=34, LogicalNot=35, LogicalAnd=36, 
		LogicalOr=37, ShiftLeft=38, ShiftRight=39, BitInversion=40, BitAnd=41, 
		BitOr=42, BitXor=43, Assign=44, Increment=45, Decrement=46, MemberAccess=47, 
		LeftRoundBracket=48, RightRoundBracket=49, LeftSquareBracket=50, RightSquareBracket=51, 
		LeftBrace=52, RightBrace=53, Identifier=54, Whitespace=55, Newline=56, 
		BlockComment=57, LineComment=58;
	public static final int
		RULE_program = 0, RULE_variableDeclaration = 1, RULE_classDefinition = 2, 
		RULE_classMembers = 3, RULE_memberVariable = 4, RULE_constructionFunction = 5, 
		RULE_memberFunction = 6, RULE_functionDefinition = 7, RULE_paramListDefinition = 8, 
		RULE_paramList = 9, RULE_statement = 10, RULE_statements = 11, RULE_emptyStatement = 12, 
		RULE_assignmentStatement = 13, RULE_expressionStatement = 14, RULE_declarationStatement = 15, 
		RULE_loopStatement = 16, RULE_forCondition = 17, RULE_forCondition1 = 18, 
		RULE_forCondition3 = 19, RULE_conditionStatement = 20, RULE_jumpStatement = 21, 
		RULE_compoundStatement = 22, RULE_expression = 23, RULE_object = 24, RULE_newObject = 25, 
		RULE_type = 26, RULE_simpleType = 27, RULE_compositeType = 28, RULE_fundamentalType = 29, 
		RULE_customType = 30;
	public static final String[] ruleNames = {
		"program", "variableDeclaration", "classDefinition", "classMembers", "memberVariable", 
		"constructionFunction", "memberFunction", "functionDefinition", "paramListDefinition", 
		"paramList", "statement", "statements", "emptyStatement", "assignmentStatement", 
		"expressionStatement", "declarationStatement", "loopStatement", "forCondition", 
		"forCondition1", "forCondition3", "conditionStatement", "jumpStatement", 
		"compoundStatement", "expression", "object", "newObject", "type", "simpleType", 
		"compositeType", "fundamentalType", "customType"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "','", "'new'", "'bool'", "'int'", "'void'", "'string'", 
		"'class'", "'this'", "'if'", "'else'", "'for'", "'while'", "'return'", 
		"'break'", "'continue'", null, "'null'", "'true'", "'false'", null, null, 
		null, "'+'", "'-'", "'*'", "'/'", "'%'", "'<'", "'>'", "'=='", "'!='", 
		"'<='", "'>='", "'!'", "'&&'", "'||'", "'<<'", "'>>'", "'~'", "'&'", "'|'", 
		"'^'", "'='", "'++'", "'--'", "'.'", "'('", "')'", "'['", "']'", "'{'", 
		"'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, "New", "Bool", "Int", "Void", "String", "Class", "This", 
		"If", "Else", "For", "While", "Return", "Break", "Continue", "Constant", 
		"Null", "True", "False", "LogicalConstant", "IntegerConstant", "StringLiteral", 
		"Add", "Sub", "Mul", "Div", "Mod", "Less", "Greater", "Equal", "Unequal", 
		"LessEqual", "GreaterEqual", "LogicalNot", "LogicalAnd", "LogicalOr", 
		"ShiftLeft", "ShiftRight", "BitInversion", "BitAnd", "BitOr", "BitXor", 
		"Assign", "Increment", "Decrement", "MemberAccess", "LeftRoundBracket", 
		"RightRoundBracket", "LeftSquareBracket", "RightSquareBracket", "LeftBrace", 
		"RightBrace", "Identifier", "Whitespace", "Newline", "BlockComment", "LineComment"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Mx_star.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public Mx_starParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(Mx_starParser.EOF, 0); }
		public List<VariableDeclarationContext> variableDeclaration() {
			return getRuleContexts(VariableDeclarationContext.class);
		}
		public VariableDeclarationContext variableDeclaration(int i) {
			return getRuleContext(VariableDeclarationContext.class,i);
		}
		public List<ClassDefinitionContext> classDefinition() {
			return getRuleContexts(ClassDefinitionContext.class);
		}
		public ClassDefinitionContext classDefinition(int i) {
			return getRuleContext(ClassDefinitionContext.class,i);
		}
		public List<FunctionDefinitionContext> functionDefinition() {
			return getRuleContexts(FunctionDefinitionContext.class);
		}
		public FunctionDefinitionContext functionDefinition(int i) {
			return getRuleContext(FunctionDefinitionContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(65);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(62);
					variableDeclaration();
					}
					break;
				case 2:
					{
					setState(63);
					classDefinition();
					}
					break;
				case 3:
					{
					setState(64);
					functionDefinition();
					}
					break;
				}
				}
				setState(67); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Bool) | (1L << Int) | (1L << Void) | (1L << String) | (1L << Class) | (1L << Identifier))) != 0) );
			setState(69);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclarationContext extends ParserRuleContext {
		public DeclarationStatementContext declarationStatement() {
			return getRuleContext(DeclarationStatementContext.class,0);
		}
		public VariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterVariableDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitVariableDeclaration(this);
		}
	}

	public final VariableDeclarationContext variableDeclaration() throws RecognitionException {
		VariableDeclarationContext _localctx = new VariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_variableDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			declarationStatement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDefinitionContext extends ParserRuleContext {
		public TerminalNode Class() { return getToken(Mx_starParser.Class, 0); }
		public TerminalNode Identifier() { return getToken(Mx_starParser.Identifier, 0); }
		public ClassMembersContext classMembers() {
			return getRuleContext(ClassMembersContext.class,0);
		}
		public ClassDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterClassDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitClassDefinition(this);
		}
	}

	public final ClassDefinitionContext classDefinition() throws RecognitionException {
		ClassDefinitionContext _localctx = new ClassDefinitionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_classDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			match(Class);
			setState(74);
			match(Identifier);
			setState(75);
			match(LeftBrace);
			setState(77);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Bool) | (1L << Int) | (1L << Void) | (1L << String) | (1L << Identifier))) != 0)) {
				{
				setState(76);
				classMembers();
				}
			}

			setState(79);
			match(RightBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassMembersContext extends ParserRuleContext {
		public List<MemberVariableContext> memberVariable() {
			return getRuleContexts(MemberVariableContext.class);
		}
		public MemberVariableContext memberVariable(int i) {
			return getRuleContext(MemberVariableContext.class,i);
		}
		public List<ConstructionFunctionContext> constructionFunction() {
			return getRuleContexts(ConstructionFunctionContext.class);
		}
		public ConstructionFunctionContext constructionFunction(int i) {
			return getRuleContext(ConstructionFunctionContext.class,i);
		}
		public List<MemberFunctionContext> memberFunction() {
			return getRuleContexts(MemberFunctionContext.class);
		}
		public MemberFunctionContext memberFunction(int i) {
			return getRuleContext(MemberFunctionContext.class,i);
		}
		public ClassMembersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classMembers; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterClassMembers(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitClassMembers(this);
		}
	}

	public final ClassMembersContext classMembers() throws RecognitionException {
		ClassMembersContext _localctx = new ClassMembersContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_classMembers);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(84);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(81);
					memberVariable();
					}
					break;
				case 2:
					{
					setState(82);
					constructionFunction();
					}
					break;
				case 3:
					{
					setState(83);
					memberFunction();
					}
					break;
				}
				}
				setState(86); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Bool) | (1L << Int) | (1L << Void) | (1L << String) | (1L << Identifier))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MemberVariableContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(Mx_starParser.Identifier, 0); }
		public MemberVariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_memberVariable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterMemberVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitMemberVariable(this);
		}
	}

	public final MemberVariableContext memberVariable() throws RecognitionException {
		MemberVariableContext _localctx = new MemberVariableContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_memberVariable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			type();
			setState(89);
			match(Identifier);
			setState(90);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstructionFunctionContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(Mx_starParser.Identifier, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public ConstructionFunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructionFunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterConstructionFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitConstructionFunction(this);
		}
	}

	public final ConstructionFunctionContext constructionFunction() throws RecognitionException {
		ConstructionFunctionContext _localctx = new ConstructionFunctionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_constructionFunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(Identifier);
			setState(93);
			match(LeftRoundBracket);
			setState(94);
			match(RightRoundBracket);
			setState(95);
			match(LeftBrace);
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << New) | (1L << Bool) | (1L << Int) | (1L << Void) | (1L << String) | (1L << This) | (1L << If) | (1L << For) | (1L << While) | (1L << Return) | (1L << Break) | (1L << Continue) | (1L << Constant) | (1L << Add) | (1L << Sub) | (1L << LogicalNot) | (1L << BitInversion) | (1L << Increment) | (1L << Decrement) | (1L << LeftRoundBracket) | (1L << LeftBrace) | (1L << Identifier))) != 0)) {
				{
				setState(96);
				statements();
				}
			}

			setState(99);
			match(RightBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MemberFunctionContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(Mx_starParser.Identifier, 0); }
		public ParamListDefinitionContext paramListDefinition() {
			return getRuleContext(ParamListDefinitionContext.class,0);
		}
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public MemberFunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_memberFunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterMemberFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitMemberFunction(this);
		}
	}

	public final MemberFunctionContext memberFunction() throws RecognitionException {
		MemberFunctionContext _localctx = new MemberFunctionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_memberFunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			type();
			setState(102);
			match(Identifier);
			setState(103);
			match(LeftRoundBracket);
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Bool) | (1L << Int) | (1L << Void) | (1L << String) | (1L << Identifier))) != 0)) {
				{
				setState(104);
				paramListDefinition();
				}
			}

			setState(107);
			match(RightRoundBracket);
			setState(108);
			match(LeftBrace);
			setState(110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << New) | (1L << Bool) | (1L << Int) | (1L << Void) | (1L << String) | (1L << This) | (1L << If) | (1L << For) | (1L << While) | (1L << Return) | (1L << Break) | (1L << Continue) | (1L << Constant) | (1L << Add) | (1L << Sub) | (1L << LogicalNot) | (1L << BitInversion) | (1L << Increment) | (1L << Decrement) | (1L << LeftRoundBracket) | (1L << LeftBrace) | (1L << Identifier))) != 0)) {
				{
				setState(109);
				statements();
				}
			}

			setState(112);
			match(RightBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionDefinitionContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(Mx_starParser.Identifier, 0); }
		public ParamListDefinitionContext paramListDefinition() {
			return getRuleContext(ParamListDefinitionContext.class,0);
		}
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public FunctionDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterFunctionDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitFunctionDefinition(this);
		}
	}

	public final FunctionDefinitionContext functionDefinition() throws RecognitionException {
		FunctionDefinitionContext _localctx = new FunctionDefinitionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_functionDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			type();
			setState(115);
			match(Identifier);
			setState(116);
			match(LeftRoundBracket);
			setState(118);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Bool) | (1L << Int) | (1L << Void) | (1L << String) | (1L << Identifier))) != 0)) {
				{
				setState(117);
				paramListDefinition();
				}
			}

			setState(120);
			match(RightRoundBracket);
			setState(121);
			match(LeftBrace);
			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << New) | (1L << Bool) | (1L << Int) | (1L << Void) | (1L << String) | (1L << This) | (1L << If) | (1L << For) | (1L << While) | (1L << Return) | (1L << Break) | (1L << Continue) | (1L << Constant) | (1L << Add) | (1L << Sub) | (1L << LogicalNot) | (1L << BitInversion) | (1L << Increment) | (1L << Decrement) | (1L << LeftRoundBracket) | (1L << LeftBrace) | (1L << Identifier))) != 0)) {
				{
				setState(122);
				statements();
				}
			}

			setState(125);
			match(RightBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamListDefinitionContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(Mx_starParser.Identifier, 0); }
		public ParamListDefinitionContext paramListDefinition() {
			return getRuleContext(ParamListDefinitionContext.class,0);
		}
		public ParamListDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramListDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterParamListDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitParamListDefinition(this);
		}
	}

	public final ParamListDefinitionContext paramListDefinition() throws RecognitionException {
		ParamListDefinitionContext _localctx = new ParamListDefinitionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_paramListDefinition);
		try {
			setState(135);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(127);
				type();
				setState(128);
				match(Identifier);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(130);
				type();
				setState(131);
				match(Identifier);
				setState(132);
				match(T__1);
				setState(133);
				paramListDefinition();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamListContext extends ParserRuleContext {
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class,0);
		}
		public ParamListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterParamList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitParamList(this);
		}
	}

	public final ParamListContext paramList() throws RecognitionException {
		ParamListContext _localctx = new ParamListContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_paramList);
		try {
			setState(142);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(137);
				object(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(138);
				object(0);
				setState(139);
				match(T__1);
				setState(140);
				paramList();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public EmptyStatementContext emptyStatement() {
			return getRuleContext(EmptyStatementContext.class,0);
		}
		public AssignmentStatementContext assignmentStatement() {
			return getRuleContext(AssignmentStatementContext.class,0);
		}
		public ExpressionStatementContext expressionStatement() {
			return getRuleContext(ExpressionStatementContext.class,0);
		}
		public DeclarationStatementContext declarationStatement() {
			return getRuleContext(DeclarationStatementContext.class,0);
		}
		public LoopStatementContext loopStatement() {
			return getRuleContext(LoopStatementContext.class,0);
		}
		public ConditionStatementContext conditionStatement() {
			return getRuleContext(ConditionStatementContext.class,0);
		}
		public JumpStatementContext jumpStatement() {
			return getRuleContext(JumpStatementContext.class,0);
		}
		public CompoundStatementContext compoundStatement() {
			return getRuleContext(CompoundStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_statement);
		try {
			setState(152);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(144);
				emptyStatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(145);
				assignmentStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(146);
				expressionStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(147);
				declarationStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(148);
				loopStatement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(149);
				conditionStatement();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(150);
				jumpStatement();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(151);
				compoundStatement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementsContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public StatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterStatements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitStatements(this);
		}
	}

	public final StatementsContext statements() throws RecognitionException {
		StatementsContext _localctx = new StatementsContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_statements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(154);
				statement();
				}
				}
				setState(157); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << New) | (1L << Bool) | (1L << Int) | (1L << Void) | (1L << String) | (1L << This) | (1L << If) | (1L << For) | (1L << While) | (1L << Return) | (1L << Break) | (1L << Continue) | (1L << Constant) | (1L << Add) | (1L << Sub) | (1L << LogicalNot) | (1L << BitInversion) | (1L << Increment) | (1L << Decrement) | (1L << LeftRoundBracket) | (1L << LeftBrace) | (1L << Identifier))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EmptyStatementContext extends ParserRuleContext {
		public EmptyStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_emptyStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterEmptyStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitEmptyStatement(this);
		}
	}

	public final EmptyStatementContext emptyStatement() throws RecognitionException {
		EmptyStatementContext _localctx = new EmptyStatementContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_emptyStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentStatementContext extends ParserRuleContext {
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignmentStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterAssignmentStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitAssignmentStatement(this);
		}
	}

	public final AssignmentStatementContext assignmentStatement() throws RecognitionException {
		AssignmentStatementContext _localctx = new AssignmentStatementContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_assignmentStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			object(0);
			setState(162);
			match(Assign);
			setState(163);
			expression();
			setState(164);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterExpressionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitExpressionStatement(this);
		}
	}

	public final ExpressionStatementContext expressionStatement() throws RecognitionException {
		ExpressionStatementContext _localctx = new ExpressionStatementContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_expressionStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			expression();
			setState(167);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationStatementContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(Mx_starParser.Identifier, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DeclarationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarationStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterDeclarationStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitDeclarationStatement(this);
		}
	}

	public final DeclarationStatementContext declarationStatement() throws RecognitionException {
		DeclarationStatementContext _localctx = new DeclarationStatementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_declarationStatement);
		try {
			setState(179);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(169);
				type();
				setState(170);
				match(Identifier);
				setState(171);
				match(T__0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(173);
				type();
				setState(174);
				match(Identifier);
				setState(175);
				match(Assign);
				setState(176);
				expression();
				setState(177);
				match(T__0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LoopStatementContext extends ParserRuleContext {
		public TerminalNode While() { return getToken(Mx_starParser.While, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode For() { return getToken(Mx_starParser.For, 0); }
		public ForConditionContext forCondition() {
			return getRuleContext(ForConditionContext.class,0);
		}
		public LoopStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loopStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterLoopStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitLoopStatement(this);
		}
	}

	public final LoopStatementContext loopStatement() throws RecognitionException {
		LoopStatementContext _localctx = new LoopStatementContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_loopStatement);
		try {
			setState(193);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case While:
				enterOuterAlt(_localctx, 1);
				{
				setState(181);
				match(While);
				setState(182);
				match(LeftRoundBracket);
				setState(183);
				expression();
				setState(184);
				match(RightRoundBracket);
				setState(185);
				statement();
				}
				break;
			case For:
				enterOuterAlt(_localctx, 2);
				{
				setState(187);
				match(For);
				setState(188);
				match(LeftRoundBracket);
				setState(189);
				forCondition();
				setState(190);
				match(RightRoundBracket);
				setState(191);
				statement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForConditionContext extends ParserRuleContext {
		public ForCondition1Context forCondition1() {
			return getRuleContext(ForCondition1Context.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ForCondition3Context forCondition3() {
			return getRuleContext(ForCondition3Context.class,0);
		}
		public ForConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterForCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitForCondition(this);
		}
	}

	public final ForConditionContext forCondition() throws RecognitionException {
		ForConditionContext _localctx = new ForConditionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_forCondition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << New) | (1L << Bool) | (1L << Int) | (1L << Void) | (1L << String) | (1L << This) | (1L << Constant) | (1L << Add) | (1L << Sub) | (1L << LogicalNot) | (1L << BitInversion) | (1L << Increment) | (1L << Decrement) | (1L << LeftRoundBracket) | (1L << Identifier))) != 0)) {
				{
				setState(195);
				forCondition1();
				}
			}

			setState(198);
			match(T__0);
			setState(200);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << New) | (1L << This) | (1L << Constant) | (1L << Add) | (1L << Sub) | (1L << LogicalNot) | (1L << BitInversion) | (1L << Increment) | (1L << Decrement) | (1L << LeftRoundBracket) | (1L << Identifier))) != 0)) {
				{
				setState(199);
				expression();
				}
			}

			setState(202);
			match(T__0);
			setState(204);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << New) | (1L << This) | (1L << Constant) | (1L << Add) | (1L << Sub) | (1L << LogicalNot) | (1L << BitInversion) | (1L << Increment) | (1L << Decrement) | (1L << LeftRoundBracket) | (1L << Identifier))) != 0)) {
				{
				setState(203);
				forCondition3();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForCondition1Context extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(Mx_starParser.Identifier, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ForCondition1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forCondition1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterForCondition1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitForCondition1(this);
		}
	}

	public final ForCondition1Context forCondition1() throws RecognitionException {
		ForCondition1Context _localctx = new ForCondition1Context(_ctx, getState());
		enterRule(_localctx, 36, RULE_forCondition1);
		try {
			setState(213);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(207);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
				case 1:
					{
					setState(206);
					type();
					}
					break;
				}
				setState(209);
				match(Identifier);
				setState(210);
				match(Assign);
				setState(211);
				expression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(212);
				expression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForCondition3Context extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(Mx_starParser.Identifier, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ForCondition3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forCondition3; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterForCondition3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitForCondition3(this);
		}
	}

	public final ForCondition3Context forCondition3() throws RecognitionException {
		ForCondition3Context _localctx = new ForCondition3Context(_ctx, getState());
		enterRule(_localctx, 38, RULE_forCondition3);
		try {
			setState(219);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(215);
				match(Identifier);
				setState(216);
				match(Assign);
				setState(217);
				expression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(218);
				expression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionStatementContext extends ParserRuleContext {
		public TerminalNode If() { return getToken(Mx_starParser.If, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode Else() { return getToken(Mx_starParser.Else, 0); }
		public ConditionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterConditionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitConditionStatement(this);
		}
	}

	public final ConditionStatementContext conditionStatement() throws RecognitionException {
		ConditionStatementContext _localctx = new ConditionStatementContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_conditionStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			match(If);
			setState(222);
			match(LeftRoundBracket);
			setState(223);
			expression();
			setState(224);
			match(RightRoundBracket);
			setState(225);
			statement();
			setState(228);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				setState(226);
				match(Else);
				setState(227);
				statement();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class JumpStatementContext extends ParserRuleContext {
		public TerminalNode Return() { return getToken(Mx_starParser.Return, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Break() { return getToken(Mx_starParser.Break, 0); }
		public TerminalNode Continue() { return getToken(Mx_starParser.Continue, 0); }
		public JumpStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jumpStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterJumpStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitJumpStatement(this);
		}
	}

	public final JumpStatementContext jumpStatement() throws RecognitionException {
		JumpStatementContext _localctx = new JumpStatementContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_jumpStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Return:
				{
				setState(230);
				match(Return);
				setState(231);
				expression();
				}
				break;
			case Break:
				{
				setState(232);
				match(Break);
				}
				break;
			case Continue:
				{
				setState(233);
				match(Continue);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(236);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CompoundStatementContext extends ParserRuleContext {
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public CompoundStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compoundStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterCompoundStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitCompoundStatement(this);
		}
	}

	public final CompoundStatementContext compoundStatement() throws RecognitionException {
		CompoundStatementContext _localctx = new CompoundStatementContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_compoundStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			match(LeftBrace);
			setState(239);
			statements();
			setState(240);
			match(RightBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			object(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjectContext extends ParserRuleContext {
		public Token op;
		public TerminalNode Identifier() { return getToken(Mx_starParser.Identifier, 0); }
		public TerminalNode Constant() { return getToken(Mx_starParser.Constant, 0); }
		public List<ObjectContext> object() {
			return getRuleContexts(ObjectContext.class);
		}
		public ObjectContext object(int i) {
			return getRuleContext(ObjectContext.class,i);
		}
		public TerminalNode This() { return getToken(Mx_starParser.This, 0); }
		public NewObjectContext newObject() {
			return getRuleContext(NewObjectContext.class,0);
		}
		public TerminalNode New() { return getToken(Mx_starParser.New, 0); }
		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class,0);
		}
		public ObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitObject(this);
		}
	}

	public final ObjectContext object() throws RecognitionException {
		return object(0);
	}

	private ObjectContext object(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ObjectContext _localctx = new ObjectContext(_ctx, _parentState);
		ObjectContext _prevctx = _localctx;
		int _startState = 48;
		enterRecursionRule(_localctx, 48, RULE_object, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(264);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Identifier:
				{
				setState(245);
				match(Identifier);
				}
				break;
			case Constant:
				{
				setState(246);
				match(Constant);
				}
				break;
			case LeftRoundBracket:
				{
				setState(247);
				match(LeftRoundBracket);
				setState(248);
				object(0);
				setState(249);
				match(RightRoundBracket);
				}
				break;
			case This:
				{
				setState(251);
				match(This);
				setState(252);
				match(MemberAccess);
				setState(253);
				object(19);
				}
				break;
			case Increment:
			case Decrement:
				{
				setState(254);
				((ObjectContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==Increment || _la==Decrement) ) {
					((ObjectContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(255);
				match(Identifier);
				}
				break;
			case BitInversion:
				{
				setState(256);
				((ObjectContext)_localctx).op = match(BitInversion);
				setState(257);
				object(14);
				}
				break;
			case LogicalNot:
				{
				setState(258);
				((ObjectContext)_localctx).op = match(LogicalNot);
				setState(259);
				object(13);
				}
				break;
			case Add:
			case Sub:
				{
				setState(260);
				((ObjectContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==Add || _la==Sub) ) {
					((ObjectContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(261);
				object(12);
				}
				break;
			case New:
				{
				setState(262);
				((ObjectContext)_localctx).op = match(New);
				setState(263);
				newObject();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(314);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(312);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
					case 1:
						{
						_localctx = new ObjectContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_object);
						setState(266);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(267);
						match(MemberAccess);
						setState(268);
						object(21);
						}
						break;
					case 2:
						{
						_localctx = new ObjectContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_object);
						setState(269);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(270);
						((ObjectContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Mul) | (1L << Div) | (1L << Mod))) != 0)) ) {
							((ObjectContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(271);
						object(11);
						}
						break;
					case 3:
						{
						_localctx = new ObjectContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_object);
						setState(272);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(273);
						((ObjectContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Add || _la==Sub) ) {
							((ObjectContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(274);
						object(10);
						}
						break;
					case 4:
						{
						_localctx = new ObjectContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_object);
						setState(275);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(276);
						((ObjectContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ShiftLeft || _la==ShiftRight) ) {
							((ObjectContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(277);
						object(9);
						}
						break;
					case 5:
						{
						_localctx = new ObjectContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_object);
						setState(278);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(279);
						((ObjectContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Less) | (1L << Greater) | (1L << LessEqual) | (1L << GreaterEqual))) != 0)) ) {
							((ObjectContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(280);
						object(8);
						}
						break;
					case 6:
						{
						_localctx = new ObjectContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_object);
						setState(281);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(282);
						((ObjectContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Equal || _la==Unequal) ) {
							((ObjectContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(283);
						object(7);
						}
						break;
					case 7:
						{
						_localctx = new ObjectContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_object);
						setState(284);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(285);
						((ObjectContext)_localctx).op = match(BitAnd);
						setState(286);
						object(6);
						}
						break;
					case 8:
						{
						_localctx = new ObjectContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_object);
						setState(287);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(288);
						((ObjectContext)_localctx).op = match(BitXor);
						setState(289);
						object(5);
						}
						break;
					case 9:
						{
						_localctx = new ObjectContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_object);
						setState(290);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(291);
						((ObjectContext)_localctx).op = match(BitOr);
						setState(292);
						object(4);
						}
						break;
					case 10:
						{
						_localctx = new ObjectContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_object);
						setState(293);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(294);
						((ObjectContext)_localctx).op = match(LogicalAnd);
						setState(295);
						object(3);
						}
						break;
					case 11:
						{
						_localctx = new ObjectContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_object);
						setState(296);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(297);
						((ObjectContext)_localctx).op = match(LogicalOr);
						setState(298);
						object(2);
						}
						break;
					case 12:
						{
						_localctx = new ObjectContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_object);
						setState(299);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(300);
						match(LeftRoundBracket);
						setState(302);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << New) | (1L << This) | (1L << Constant) | (1L << Add) | (1L << Sub) | (1L << LogicalNot) | (1L << BitInversion) | (1L << Increment) | (1L << Decrement) | (1L << LeftRoundBracket) | (1L << Identifier))) != 0)) {
							{
							setState(301);
							paramList();
							}
						}

						setState(304);
						match(RightRoundBracket);
						}
						break;
					case 13:
						{
						_localctx = new ObjectContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_object);
						setState(305);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(306);
						match(LeftSquareBracket);
						setState(307);
						object(0);
						setState(308);
						match(RightSquareBracket);
						}
						break;
					case 14:
						{
						_localctx = new ObjectContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_object);
						setState(310);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(311);
						((ObjectContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Increment || _la==Decrement) ) {
							((ObjectContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(316);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class NewObjectContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<ObjectContext> object() {
			return getRuleContexts(ObjectContext.class);
		}
		public ObjectContext object(int i) {
			return getRuleContext(ObjectContext.class,i);
		}
		public NewObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newObject; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterNewObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitNewObject(this);
		}
	}

	public final NewObjectContext newObject() throws RecognitionException {
		NewObjectContext _localctx = new NewObjectContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_newObject);
		try {
			int _alt;
			setState(338);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(317);
				type();
				setState(324);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(318);
						match(LeftSquareBracket);
						setState(319);
						object(0);
						setState(320);
						match(RightSquareBracket);
						}
						} 
					}
					setState(326);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
				}
				setState(331);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(327);
						match(LeftSquareBracket);
						setState(328);
						match(RightSquareBracket);
						}
						} 
					}
					setState(333);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(334);
				type();
				setState(335);
				match(LeftRoundBracket);
				setState(336);
				match(RightRoundBracket);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public SimpleTypeContext simpleType() {
			return getRuleContext(SimpleTypeContext.class,0);
		}
		public CompositeTypeContext compositeType() {
			return getRuleContext(CompositeTypeContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_type);
		try {
			setState(342);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(340);
				simpleType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(341);
				compositeType();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SimpleTypeContext extends ParserRuleContext {
		public FundamentalTypeContext fundamentalType() {
			return getRuleContext(FundamentalTypeContext.class,0);
		}
		public CustomTypeContext customType() {
			return getRuleContext(CustomTypeContext.class,0);
		}
		public SimpleTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterSimpleType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitSimpleType(this);
		}
	}

	public final SimpleTypeContext simpleType() throws RecognitionException {
		SimpleTypeContext _localctx = new SimpleTypeContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_simpleType);
		try {
			setState(346);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Bool:
			case Int:
			case Void:
			case String:
				enterOuterAlt(_localctx, 1);
				{
				setState(344);
				fundamentalType();
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(345);
				customType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CompositeTypeContext extends ParserRuleContext {
		public SimpleTypeContext simpleType() {
			return getRuleContext(SimpleTypeContext.class,0);
		}
		public CompositeTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compositeType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterCompositeType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitCompositeType(this);
		}
	}

	public final CompositeTypeContext compositeType() throws RecognitionException {
		CompositeTypeContext _localctx = new CompositeTypeContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_compositeType);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(348);
			simpleType();
			setState(351); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(349);
					match(LeftSquareBracket);
					setState(350);
					match(RightSquareBracket);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(353); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FundamentalTypeContext extends ParserRuleContext {
		public TerminalNode Int() { return getToken(Mx_starParser.Int, 0); }
		public TerminalNode Bool() { return getToken(Mx_starParser.Bool, 0); }
		public TerminalNode Void() { return getToken(Mx_starParser.Void, 0); }
		public TerminalNode String() { return getToken(Mx_starParser.String, 0); }
		public FundamentalTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fundamentalType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterFundamentalType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitFundamentalType(this);
		}
	}

	public final FundamentalTypeContext fundamentalType() throws RecognitionException {
		FundamentalTypeContext _localctx = new FundamentalTypeContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_fundamentalType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(355);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Bool) | (1L << Int) | (1L << Void) | (1L << String))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CustomTypeContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(Mx_starParser.Identifier, 0); }
		public CustomTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_customType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterCustomType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitCustomType(this);
		}
	}

	public final CustomTypeContext customType() throws RecognitionException {
		CustomTypeContext _localctx = new CustomTypeContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_customType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(357);
			match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 24:
			return object_sempred((ObjectContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean object_sempred(ObjectContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 20);
		case 1:
			return precpred(_ctx, 10);
		case 2:
			return precpred(_ctx, 9);
		case 3:
			return precpred(_ctx, 8);
		case 4:
			return precpred(_ctx, 7);
		case 5:
			return precpred(_ctx, 6);
		case 6:
			return precpred(_ctx, 5);
		case 7:
			return precpred(_ctx, 4);
		case 8:
			return precpred(_ctx, 3);
		case 9:
			return precpred(_ctx, 2);
		case 10:
			return precpred(_ctx, 1);
		case 11:
			return precpred(_ctx, 18);
		case 12:
			return precpred(_ctx, 17);
		case 13:
			return precpred(_ctx, 16);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3<\u016a\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \3\2"+
		"\3\2\3\2\6\2D\n\2\r\2\16\2E\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\5\4P\n\4\3"+
		"\4\3\4\3\5\3\5\3\5\6\5W\n\5\r\5\16\5X\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7"+
		"\3\7\5\7d\n\7\3\7\3\7\3\b\3\b\3\b\3\b\5\bl\n\b\3\b\3\b\3\b\5\bq\n\b\3"+
		"\b\3\b\3\t\3\t\3\t\3\t\5\ty\n\t\3\t\3\t\3\t\5\t~\n\t\3\t\3\t\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\5\n\u008a\n\n\3\13\3\13\3\13\3\13\3\13\5\13\u0091"+
		"\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u009b\n\f\3\r\6\r\u009e\n\r"+
		"\r\r\16\r\u009f\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00b6\n\21\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u00c4\n\22\3\23"+
		"\5\23\u00c7\n\23\3\23\3\23\5\23\u00cb\n\23\3\23\3\23\5\23\u00cf\n\23\3"+
		"\24\5\24\u00d2\n\24\3\24\3\24\3\24\3\24\5\24\u00d8\n\24\3\25\3\25\3\25"+
		"\3\25\5\25\u00de\n\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u00e7\n"+
		"\26\3\27\3\27\3\27\3\27\5\27\u00ed\n\27\3\27\3\27\3\30\3\30\3\30\3\30"+
		"\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\5\32\u010b\n\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\5\32\u0131\n\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\7\32\u013b\n\32\f\32\16\32\u013e\13\32\3\33\3\33\3\33\3\33"+
		"\3\33\7\33\u0145\n\33\f\33\16\33\u0148\13\33\3\33\3\33\7\33\u014c\n\33"+
		"\f\33\16\33\u014f\13\33\3\33\3\33\3\33\3\33\5\33\u0155\n\33\3\34\3\34"+
		"\5\34\u0159\n\34\3\35\3\35\5\35\u015d\n\35\3\36\3\36\3\36\6\36\u0162\n"+
		"\36\r\36\16\36\u0163\3\37\3\37\3 \3 \3 \2\3\62!\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>\2\t\3\2/\60\3\2\32\33\3\2\34"+
		"\36\3\2()\4\2\37 #$\3\2!\"\3\2\6\t\2\u0188\2C\3\2\2\2\4I\3\2\2\2\6K\3"+
		"\2\2\2\bV\3\2\2\2\nZ\3\2\2\2\f^\3\2\2\2\16g\3\2\2\2\20t\3\2\2\2\22\u0089"+
		"\3\2\2\2\24\u0090\3\2\2\2\26\u009a\3\2\2\2\30\u009d\3\2\2\2\32\u00a1\3"+
		"\2\2\2\34\u00a3\3\2\2\2\36\u00a8\3\2\2\2 \u00b5\3\2\2\2\"\u00c3\3\2\2"+
		"\2$\u00c6\3\2\2\2&\u00d7\3\2\2\2(\u00dd\3\2\2\2*\u00df\3\2\2\2,\u00ec"+
		"\3\2\2\2.\u00f0\3\2\2\2\60\u00f4\3\2\2\2\62\u010a\3\2\2\2\64\u0154\3\2"+
		"\2\2\66\u0158\3\2\2\28\u015c\3\2\2\2:\u015e\3\2\2\2<\u0165\3\2\2\2>\u0167"+
		"\3\2\2\2@D\5\4\3\2AD\5\6\4\2BD\5\20\t\2C@\3\2\2\2CA\3\2\2\2CB\3\2\2\2"+
		"DE\3\2\2\2EC\3\2\2\2EF\3\2\2\2FG\3\2\2\2GH\7\2\2\3H\3\3\2\2\2IJ\5 \21"+
		"\2J\5\3\2\2\2KL\7\n\2\2LM\78\2\2MO\7\66\2\2NP\5\b\5\2ON\3\2\2\2OP\3\2"+
		"\2\2PQ\3\2\2\2QR\7\67\2\2R\7\3\2\2\2SW\5\n\6\2TW\5\f\7\2UW\5\16\b\2VS"+
		"\3\2\2\2VT\3\2\2\2VU\3\2\2\2WX\3\2\2\2XV\3\2\2\2XY\3\2\2\2Y\t\3\2\2\2"+
		"Z[\5\66\34\2[\\\78\2\2\\]\7\3\2\2]\13\3\2\2\2^_\78\2\2_`\7\62\2\2`a\7"+
		"\63\2\2ac\7\66\2\2bd\5\30\r\2cb\3\2\2\2cd\3\2\2\2de\3\2\2\2ef\7\67\2\2"+
		"f\r\3\2\2\2gh\5\66\34\2hi\78\2\2ik\7\62\2\2jl\5\22\n\2kj\3\2\2\2kl\3\2"+
		"\2\2lm\3\2\2\2mn\7\63\2\2np\7\66\2\2oq\5\30\r\2po\3\2\2\2pq\3\2\2\2qr"+
		"\3\2\2\2rs\7\67\2\2s\17\3\2\2\2tu\5\66\34\2uv\78\2\2vx\7\62\2\2wy\5\22"+
		"\n\2xw\3\2\2\2xy\3\2\2\2yz\3\2\2\2z{\7\63\2\2{}\7\66\2\2|~\5\30\r\2}|"+
		"\3\2\2\2}~\3\2\2\2~\177\3\2\2\2\177\u0080\7\67\2\2\u0080\21\3\2\2\2\u0081"+
		"\u0082\5\66\34\2\u0082\u0083\78\2\2\u0083\u008a\3\2\2\2\u0084\u0085\5"+
		"\66\34\2\u0085\u0086\78\2\2\u0086\u0087\7\4\2\2\u0087\u0088\5\22\n\2\u0088"+
		"\u008a\3\2\2\2\u0089\u0081\3\2\2\2\u0089\u0084\3\2\2\2\u008a\23\3\2\2"+
		"\2\u008b\u0091\5\62\32\2\u008c\u008d\5\62\32\2\u008d\u008e\7\4\2\2\u008e"+
		"\u008f\5\24\13\2\u008f\u0091\3\2\2\2\u0090\u008b\3\2\2\2\u0090\u008c\3"+
		"\2\2\2\u0091\25\3\2\2\2\u0092\u009b\5\32\16\2\u0093\u009b\5\34\17\2\u0094"+
		"\u009b\5\36\20\2\u0095\u009b\5 \21\2\u0096\u009b\5\"\22\2\u0097\u009b"+
		"\5*\26\2\u0098\u009b\5,\27\2\u0099\u009b\5.\30\2\u009a\u0092\3\2\2\2\u009a"+
		"\u0093\3\2\2\2\u009a\u0094\3\2\2\2\u009a\u0095\3\2\2\2\u009a\u0096\3\2"+
		"\2\2\u009a\u0097\3\2\2\2\u009a\u0098\3\2\2\2\u009a\u0099\3\2\2\2\u009b"+
		"\27\3\2\2\2\u009c\u009e\5\26\f\2\u009d\u009c\3\2\2\2\u009e\u009f\3\2\2"+
		"\2\u009f\u009d\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\31\3\2\2\2\u00a1\u00a2"+
		"\7\3\2\2\u00a2\33\3\2\2\2\u00a3\u00a4\5\62\32\2\u00a4\u00a5\7.\2\2\u00a5"+
		"\u00a6\5\60\31\2\u00a6\u00a7\7\3\2\2\u00a7\35\3\2\2\2\u00a8\u00a9\5\60"+
		"\31\2\u00a9\u00aa\7\3\2\2\u00aa\37\3\2\2\2\u00ab\u00ac\5\66\34\2\u00ac"+
		"\u00ad\78\2\2\u00ad\u00ae\7\3\2\2\u00ae\u00b6\3\2\2\2\u00af\u00b0\5\66"+
		"\34\2\u00b0\u00b1\78\2\2\u00b1\u00b2\7.\2\2\u00b2\u00b3\5\60\31\2\u00b3"+
		"\u00b4\7\3\2\2\u00b4\u00b6\3\2\2\2\u00b5\u00ab\3\2\2\2\u00b5\u00af\3\2"+
		"\2\2\u00b6!\3\2\2\2\u00b7\u00b8\7\17\2\2\u00b8\u00b9\7\62\2\2\u00b9\u00ba"+
		"\5\60\31\2\u00ba\u00bb\7\63\2\2\u00bb\u00bc\5\26\f\2\u00bc\u00c4\3\2\2"+
		"\2\u00bd\u00be\7\16\2\2\u00be\u00bf\7\62\2\2\u00bf\u00c0\5$\23\2\u00c0"+
		"\u00c1\7\63\2\2\u00c1\u00c2\5\26\f\2\u00c2\u00c4\3\2\2\2\u00c3\u00b7\3"+
		"\2\2\2\u00c3\u00bd\3\2\2\2\u00c4#\3\2\2\2\u00c5\u00c7\5&\24\2\u00c6\u00c5"+
		"\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00ca\7\3\2\2\u00c9"+
		"\u00cb\5\60\31\2\u00ca\u00c9\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00cc\3"+
		"\2\2\2\u00cc\u00ce\7\3\2\2\u00cd\u00cf\5(\25\2\u00ce\u00cd\3\2\2\2\u00ce"+
		"\u00cf\3\2\2\2\u00cf%\3\2\2\2\u00d0\u00d2\5\66\34\2\u00d1\u00d0\3\2\2"+
		"\2\u00d1\u00d2\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d4\78\2\2\u00d4\u00d5"+
		"\7.\2\2\u00d5\u00d8\5\60\31\2\u00d6\u00d8\5\60\31\2\u00d7\u00d1\3\2\2"+
		"\2\u00d7\u00d6\3\2\2\2\u00d8\'\3\2\2\2\u00d9\u00da\78\2\2\u00da\u00db"+
		"\7.\2\2\u00db\u00de\5\60\31\2\u00dc\u00de\5\60\31\2\u00dd\u00d9\3\2\2"+
		"\2\u00dd\u00dc\3\2\2\2\u00de)\3\2\2\2\u00df\u00e0\7\f\2\2\u00e0\u00e1"+
		"\7\62\2\2\u00e1\u00e2\5\60\31\2\u00e2\u00e3\7\63\2\2\u00e3\u00e6\5\26"+
		"\f\2\u00e4\u00e5\7\r\2\2\u00e5\u00e7\5\26\f\2\u00e6\u00e4\3\2\2\2\u00e6"+
		"\u00e7\3\2\2\2\u00e7+\3\2\2\2\u00e8\u00e9\7\20\2\2\u00e9\u00ed\5\60\31"+
		"\2\u00ea\u00ed\7\21\2\2\u00eb\u00ed\7\22\2\2\u00ec\u00e8\3\2\2\2\u00ec"+
		"\u00ea\3\2\2\2\u00ec\u00eb\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00ef\7\3"+
		"\2\2\u00ef-\3\2\2\2\u00f0\u00f1\7\66\2\2\u00f1\u00f2\5\30\r\2\u00f2\u00f3"+
		"\7\67\2\2\u00f3/\3\2\2\2\u00f4\u00f5\5\62\32\2\u00f5\61\3\2\2\2\u00f6"+
		"\u00f7\b\32\1\2\u00f7\u010b\78\2\2\u00f8\u010b\7\23\2\2\u00f9\u00fa\7"+
		"\62\2\2\u00fa\u00fb\5\62\32\2\u00fb\u00fc\7\63\2\2\u00fc\u010b\3\2\2\2"+
		"\u00fd\u00fe\7\13\2\2\u00fe\u00ff\7\61\2\2\u00ff\u010b\5\62\32\25\u0100"+
		"\u0101\t\2\2\2\u0101\u010b\78\2\2\u0102\u0103\7*\2\2\u0103\u010b\5\62"+
		"\32\20\u0104\u0105\7%\2\2\u0105\u010b\5\62\32\17\u0106\u0107\t\3\2\2\u0107"+
		"\u010b\5\62\32\16\u0108\u0109\7\5\2\2\u0109\u010b\5\64\33\2\u010a\u00f6"+
		"\3\2\2\2\u010a\u00f8\3\2\2\2\u010a\u00f9\3\2\2\2\u010a\u00fd\3\2\2\2\u010a"+
		"\u0100\3\2\2\2\u010a\u0102\3\2\2\2\u010a\u0104\3\2\2\2\u010a\u0106\3\2"+
		"\2\2\u010a\u0108\3\2\2\2\u010b\u013c\3\2\2\2\u010c\u010d\f\26\2\2\u010d"+
		"\u010e\7\61\2\2\u010e\u013b\5\62\32\27\u010f\u0110\f\f\2\2\u0110\u0111"+
		"\t\4\2\2\u0111\u013b\5\62\32\r\u0112\u0113\f\13\2\2\u0113\u0114\t\3\2"+
		"\2\u0114\u013b\5\62\32\f\u0115\u0116\f\n\2\2\u0116\u0117\t\5\2\2\u0117"+
		"\u013b\5\62\32\13\u0118\u0119\f\t\2\2\u0119\u011a\t\6\2\2\u011a\u013b"+
		"\5\62\32\n\u011b\u011c\f\b\2\2\u011c\u011d\t\7\2\2\u011d\u013b\5\62\32"+
		"\t\u011e\u011f\f\7\2\2\u011f\u0120\7+\2\2\u0120\u013b\5\62\32\b\u0121"+
		"\u0122\f\6\2\2\u0122\u0123\7-\2\2\u0123\u013b\5\62\32\7\u0124\u0125\f"+
		"\5\2\2\u0125\u0126\7,\2\2\u0126\u013b\5\62\32\6\u0127\u0128\f\4\2\2\u0128"+
		"\u0129\7&\2\2\u0129\u013b\5\62\32\5\u012a\u012b\f\3\2\2\u012b\u012c\7"+
		"\'\2\2\u012c\u013b\5\62\32\4\u012d\u012e\f\24\2\2\u012e\u0130\7\62\2\2"+
		"\u012f\u0131\5\24\13\2\u0130\u012f\3\2\2\2\u0130\u0131\3\2\2\2\u0131\u0132"+
		"\3\2\2\2\u0132\u013b\7\63\2\2\u0133\u0134\f\23\2\2\u0134\u0135\7\64\2"+
		"\2\u0135\u0136\5\62\32\2\u0136\u0137\7\65\2\2\u0137\u013b\3\2\2\2\u0138"+
		"\u0139\f\22\2\2\u0139\u013b\t\2\2\2\u013a\u010c\3\2\2\2\u013a\u010f\3"+
		"\2\2\2\u013a\u0112\3\2\2\2\u013a\u0115\3\2\2\2\u013a\u0118\3\2\2\2\u013a"+
		"\u011b\3\2\2\2\u013a\u011e\3\2\2\2\u013a\u0121\3\2\2\2\u013a\u0124\3\2"+
		"\2\2\u013a\u0127\3\2\2\2\u013a\u012a\3\2\2\2\u013a\u012d\3\2\2\2\u013a"+
		"\u0133\3\2\2\2\u013a\u0138\3\2\2\2\u013b\u013e\3\2\2\2\u013c\u013a\3\2"+
		"\2\2\u013c\u013d\3\2\2\2\u013d\63\3\2\2\2\u013e\u013c\3\2\2\2\u013f\u0146"+
		"\5\66\34\2\u0140\u0141\7\64\2\2\u0141\u0142\5\62\32\2\u0142\u0143\7\65"+
		"\2\2\u0143\u0145\3\2\2\2\u0144\u0140\3\2\2\2\u0145\u0148\3\2\2\2\u0146"+
		"\u0144\3\2\2\2\u0146\u0147\3\2\2\2\u0147\u014d\3\2\2\2\u0148\u0146\3\2"+
		"\2\2\u0149\u014a\7\64\2\2\u014a\u014c\7\65\2\2\u014b\u0149\3\2\2\2\u014c"+
		"\u014f\3\2\2\2\u014d\u014b\3\2\2\2\u014d\u014e\3\2\2\2\u014e\u0155\3\2"+
		"\2\2\u014f\u014d\3\2\2\2\u0150\u0151\5\66\34\2\u0151\u0152\7\62\2\2\u0152"+
		"\u0153\7\63\2\2\u0153\u0155\3\2\2\2\u0154\u013f\3\2\2\2\u0154\u0150\3"+
		"\2\2\2\u0155\65\3\2\2\2\u0156\u0159\58\35\2\u0157\u0159\5:\36\2\u0158"+
		"\u0156\3\2\2\2\u0158\u0157\3\2\2\2\u0159\67\3\2\2\2\u015a\u015d\5<\37"+
		"\2\u015b\u015d\5> \2\u015c\u015a\3\2\2\2\u015c\u015b\3\2\2\2\u015d9\3"+
		"\2\2\2\u015e\u0161\58\35\2\u015f\u0160\7\64\2\2\u0160\u0162\7\65\2\2\u0161"+
		"\u015f\3\2\2\2\u0162\u0163\3\2\2\2\u0163\u0161\3\2\2\2\u0163\u0164\3\2"+
		"\2\2\u0164;\3\2\2\2\u0165\u0166\t\b\2\2\u0166=\3\2\2\2\u0167\u0168\78"+
		"\2\2\u0168?\3\2\2\2$CEOVXckpx}\u0089\u0090\u009a\u009f\u00b5\u00c3\u00c6"+
		"\u00ca\u00ce\u00d1\u00d7\u00dd\u00e6\u00ec\u010a\u0130\u013a\u013c\u0146"+
		"\u014d\u0154\u0158\u015c\u0163";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}