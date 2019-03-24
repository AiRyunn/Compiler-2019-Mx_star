// Generated from Mx_star.g4 by ANTLR 4.7.1

    package com.github.espylapiza.compiler_mxstar.parser;

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
		If=10, Else=11, For=12, While=13, Return=14, Break=15, Continue=16, Null=17, 
		LogicalConstant=18, True=19, False=20, IntegerConstant=21, StringLiteral=22, 
		Add=23, Sub=24, Mul=25, Div=26, Mod=27, Less=28, Greater=29, Equal=30, 
		Unequal=31, LessEqual=32, GreaterEqual=33, LogicalNot=34, LogicalAnd=35, 
		LogicalOr=36, SHL=37, SHR=38, BitInversion=39, BitAnd=40, BitOr=41, BitXor=42, 
		Assign=43, Increment=44, Decrement=45, MemberAccess=46, LeftRoundBracket=47, 
		RightRoundBracket=48, LeftSquareBracket=49, RightSquareBracket=50, LeftBrace=51, 
		RightBrace=52, Identifier=53, Whitespace=54, Newline=55, BlockComment=56, 
		LineComment=57;
	public static final int
		RULE_program = 0, RULE_programSection = 1, RULE_classDefinitionStatement = 2, 
		RULE_classMember = 3, RULE_constructionFunctionStatement = 4, RULE_functionDefinitionStatement = 5, 
		RULE_paramListDefinition = 6, RULE_paramList = 7, RULE_statement = 8, 
		RULE_statements = 9, RULE_emptyStatement = 10, RULE_variableDeclarationStatement = 11, 
		RULE_variableDefinitionStatement = 12, RULE_variableAssignmentStatement = 13, 
		RULE_objectStatement = 14, RULE_loopStatement = 15, RULE_forCondition = 16, 
		RULE_forCondition1 = 17, RULE_forCondition2 = 18, RULE_forCondition3 = 19, 
		RULE_conditionStatement = 20, RULE_jumpStatement = 21, RULE_compoundStatement = 22, 
		RULE_variableDeclaration = 23, RULE_variableDefinition = 24, RULE_variableAssignment = 25, 
		RULE_lvalue = 26, RULE_object = 27, RULE_type = 28, RULE_simpleType = 29, 
		RULE_compositeType = 30, RULE_fundamentalType = 31, RULE_customType = 32, 
		RULE_constant = 33;
	public static final String[] ruleNames = {
		"program", "programSection", "classDefinitionStatement", "classMember", 
		"constructionFunctionStatement", "functionDefinitionStatement", "paramListDefinition", 
		"paramList", "statement", "statements", "emptyStatement", "variableDeclarationStatement", 
		"variableDefinitionStatement", "variableAssignmentStatement", "objectStatement", 
		"loopStatement", "forCondition", "forCondition1", "forCondition2", "forCondition3", 
		"conditionStatement", "jumpStatement", "compoundStatement", "variableDeclaration", 
		"variableDefinition", "variableAssignment", "lvalue", "object", "type", 
		"simpleType", "compositeType", "fundamentalType", "customType", "constant"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "','", "';'", "'new'", "'bool'", "'int'", "'void'", "'string'", 
		"'class'", "'this'", "'if'", "'else'", "'for'", "'while'", "'return'", 
		"'break'", "'continue'", "'null'", null, "'true'", "'false'", null, null, 
		"'+'", "'-'", "'*'", "'/'", "'%'", "'<'", "'>'", "'=='", "'!='", "'<='", 
		"'>='", "'!'", "'&&'", "'||'", "'<<'", "'>>'", "'~'", "'&'", "'|'", "'^'", 
		"'='", "'++'", "'--'", "'.'", "'('", "')'", "'['", "']'", "'{'", "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, "New", "Bool", "Int", "Void", "String", "Class", "This", 
		"If", "Else", "For", "While", "Return", "Break", "Continue", "Null", "LogicalConstant", 
		"True", "False", "IntegerConstant", "StringLiteral", "Add", "Sub", "Mul", 
		"Div", "Mod", "Less", "Greater", "Equal", "Unequal", "LessEqual", "GreaterEqual", 
		"LogicalNot", "LogicalAnd", "LogicalOr", "SHL", "SHR", "BitInversion", 
		"BitAnd", "BitOr", "BitXor", "Assign", "Increment", "Decrement", "MemberAccess", 
		"LeftRoundBracket", "RightRoundBracket", "LeftSquareBracket", "RightSquareBracket", 
		"LeftBrace", "RightBrace", "Identifier", "Whitespace", "Newline", "BlockComment", 
		"LineComment"
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
		public List<ProgramSectionContext> programSection() {
			return getRuleContexts(ProgramSectionContext.class);
		}
		public ProgramSectionContext programSection(int i) {
			return getRuleContext(ProgramSectionContext.class,i);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(68);
				programSection();
				}
				}
				setState(71); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << Bool) | (1L << Int) | (1L << Void) | (1L << String) | (1L << Class) | (1L << Identifier))) != 0) );
			setState(73);
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

	public static class ProgramSectionContext extends ParserRuleContext {
		public ProgramSectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programSection; }
	 
		public ProgramSectionContext() { }
		public void copyFrom(ProgramSectionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ProgramClassDefinitionStatementContext extends ProgramSectionContext {
		public ClassDefinitionStatementContext classDefinitionStatement() {
			return getRuleContext(ClassDefinitionStatementContext.class,0);
		}
		public ProgramClassDefinitionStatementContext(ProgramSectionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterProgramClassDefinitionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitProgramClassDefinitionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitProgramClassDefinitionStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ProgramFunctionDefinitionStatementContext extends ProgramSectionContext {
		public FunctionDefinitionStatementContext functionDefinitionStatement() {
			return getRuleContext(FunctionDefinitionStatementContext.class,0);
		}
		public ProgramFunctionDefinitionStatementContext(ProgramSectionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterProgramFunctionDefinitionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitProgramFunctionDefinitionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitProgramFunctionDefinitionStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ProgramEmptyStatementContext extends ProgramSectionContext {
		public EmptyStatementContext emptyStatement() {
			return getRuleContext(EmptyStatementContext.class,0);
		}
		public ProgramEmptyStatementContext(ProgramSectionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterProgramEmptyStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitProgramEmptyStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitProgramEmptyStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ProgramVariableDefinitionStatementContext extends ProgramSectionContext {
		public VariableDefinitionStatementContext variableDefinitionStatement() {
			return getRuleContext(VariableDefinitionStatementContext.class,0);
		}
		public ProgramVariableDefinitionStatementContext(ProgramSectionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterProgramVariableDefinitionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitProgramVariableDefinitionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitProgramVariableDefinitionStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ProgramVariableDeclarationStatementContext extends ProgramSectionContext {
		public VariableDeclarationStatementContext variableDeclarationStatement() {
			return getRuleContext(VariableDeclarationStatementContext.class,0);
		}
		public ProgramVariableDeclarationStatementContext(ProgramSectionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterProgramVariableDeclarationStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitProgramVariableDeclarationStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitProgramVariableDeclarationStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramSectionContext programSection() throws RecognitionException {
		ProgramSectionContext _localctx = new ProgramSectionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_programSection);
		try {
			setState(80);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				_localctx = new ProgramEmptyStatementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(75);
				emptyStatement();
				}
				break;
			case 2:
				_localctx = new ProgramVariableDeclarationStatementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(76);
				variableDeclarationStatement();
				}
				break;
			case 3:
				_localctx = new ProgramVariableDefinitionStatementContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(77);
				variableDefinitionStatement();
				}
				break;
			case 4:
				_localctx = new ProgramClassDefinitionStatementContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(78);
				classDefinitionStatement();
				}
				break;
			case 5:
				_localctx = new ProgramFunctionDefinitionStatementContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(79);
				functionDefinitionStatement();
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

	public static class ClassDefinitionStatementContext extends ParserRuleContext {
		public TerminalNode Class() { return getToken(Mx_starParser.Class, 0); }
		public TerminalNode Identifier() { return getToken(Mx_starParser.Identifier, 0); }
		public List<ClassMemberContext> classMember() {
			return getRuleContexts(ClassMemberContext.class);
		}
		public ClassMemberContext classMember(int i) {
			return getRuleContext(ClassMemberContext.class,i);
		}
		public ClassDefinitionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDefinitionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterClassDefinitionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitClassDefinitionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitClassDefinitionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDefinitionStatementContext classDefinitionStatement() throws RecognitionException {
		ClassDefinitionStatementContext _localctx = new ClassDefinitionStatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_classDefinitionStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(Class);
			setState(83);
			match(Identifier);
			setState(84);
			match(LeftBrace);
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Bool) | (1L << Int) | (1L << Void) | (1L << String) | (1L << Identifier))) != 0)) {
				{
				{
				setState(85);
				classMember();
				}
				}
				setState(90);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(91);
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

	public static class ClassMemberContext extends ParserRuleContext {
		public ClassMemberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classMember; }
	 
		public ClassMemberContext() { }
		public void copyFrom(ClassMemberContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ClassConstructionFunctionStatementContext extends ClassMemberContext {
		public ConstructionFunctionStatementContext constructionFunctionStatement() {
			return getRuleContext(ConstructionFunctionStatementContext.class,0);
		}
		public ClassConstructionFunctionStatementContext(ClassMemberContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterClassConstructionFunctionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitClassConstructionFunctionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitClassConstructionFunctionStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ClassVariableDeclarationStatementContext extends ClassMemberContext {
		public VariableDeclarationStatementContext variableDeclarationStatement() {
			return getRuleContext(VariableDeclarationStatementContext.class,0);
		}
		public ClassVariableDeclarationStatementContext(ClassMemberContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterClassVariableDeclarationStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitClassVariableDeclarationStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitClassVariableDeclarationStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ClassFunctionDefinitionStatementContext extends ClassMemberContext {
		public FunctionDefinitionStatementContext functionDefinitionStatement() {
			return getRuleContext(FunctionDefinitionStatementContext.class,0);
		}
		public ClassFunctionDefinitionStatementContext(ClassMemberContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterClassFunctionDefinitionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitClassFunctionDefinitionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitClassFunctionDefinitionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassMemberContext classMember() throws RecognitionException {
		ClassMemberContext _localctx = new ClassMemberContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_classMember);
		try {
			setState(96);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				_localctx = new ClassVariableDeclarationStatementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(93);
				variableDeclarationStatement();
				}
				break;
			case 2:
				_localctx = new ClassConstructionFunctionStatementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(94);
				constructionFunctionStatement();
				}
				break;
			case 3:
				_localctx = new ClassFunctionDefinitionStatementContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(95);
				functionDefinitionStatement();
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

	public static class ConstructionFunctionStatementContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(Mx_starParser.Identifier, 0); }
		public ParamListDefinitionContext paramListDefinition() {
			return getRuleContext(ParamListDefinitionContext.class,0);
		}
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public ConstructionFunctionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructionFunctionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterConstructionFunctionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitConstructionFunctionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitConstructionFunctionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstructionFunctionStatementContext constructionFunctionStatement() throws RecognitionException {
		ConstructionFunctionStatementContext _localctx = new ConstructionFunctionStatementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_constructionFunctionStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(Identifier);
			setState(99);
			match(LeftRoundBracket);
			setState(100);
			paramListDefinition();
			setState(101);
			match(RightRoundBracket);
			setState(102);
			match(LeftBrace);
			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << New) | (1L << Bool) | (1L << Int) | (1L << Void) | (1L << String) | (1L << This) | (1L << If) | (1L << For) | (1L << While) | (1L << Return) | (1L << Break) | (1L << Continue) | (1L << Null) | (1L << LogicalConstant) | (1L << IntegerConstant) | (1L << StringLiteral) | (1L << Add) | (1L << Sub) | (1L << LogicalNot) | (1L << BitInversion) | (1L << Increment) | (1L << Decrement) | (1L << LeftRoundBracket) | (1L << LeftBrace) | (1L << Identifier))) != 0)) {
				{
				setState(103);
				statements();
				}
			}

			setState(106);
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

	public static class FunctionDefinitionStatementContext extends ParserRuleContext {
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
		public FunctionDefinitionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDefinitionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterFunctionDefinitionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitFunctionDefinitionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitFunctionDefinitionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDefinitionStatementContext functionDefinitionStatement() throws RecognitionException {
		FunctionDefinitionStatementContext _localctx = new FunctionDefinitionStatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_functionDefinitionStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			type();
			setState(109);
			match(Identifier);
			setState(110);
			match(LeftRoundBracket);
			setState(111);
			paramListDefinition();
			setState(112);
			match(RightRoundBracket);
			setState(113);
			match(LeftBrace);
			setState(115);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << New) | (1L << Bool) | (1L << Int) | (1L << Void) | (1L << String) | (1L << This) | (1L << If) | (1L << For) | (1L << While) | (1L << Return) | (1L << Break) | (1L << Continue) | (1L << Null) | (1L << LogicalConstant) | (1L << IntegerConstant) | (1L << StringLiteral) | (1L << Add) | (1L << Sub) | (1L << LogicalNot) | (1L << BitInversion) | (1L << Increment) | (1L << Decrement) | (1L << LeftRoundBracket) | (1L << LeftBrace) | (1L << Identifier))) != 0)) {
				{
				setState(114);
				statements();
				}
			}

			setState(117);
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
		public List<VariableDeclarationContext> variableDeclaration() {
			return getRuleContexts(VariableDeclarationContext.class);
		}
		public VariableDeclarationContext variableDeclaration(int i) {
			return getRuleContext(VariableDeclarationContext.class,i);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitParamListDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamListDefinitionContext paramListDefinition() throws RecognitionException {
		ParamListDefinitionContext _localctx = new ParamListDefinitionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_paramListDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Bool) | (1L << Int) | (1L << Void) | (1L << String) | (1L << Identifier))) != 0)) {
				{
				setState(119);
				variableDeclaration();
				setState(124);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__0) {
					{
					{
					setState(120);
					match(T__0);
					setState(121);
					variableDeclaration();
					}
					}
					setState(126);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
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

	public static class ParamListContext extends ParserRuleContext {
		public List<ObjectContext> object() {
			return getRuleContexts(ObjectContext.class);
		}
		public ObjectContext object(int i) {
			return getRuleContext(ObjectContext.class,i);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitParamList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamListContext paramList() throws RecognitionException {
		ParamListContext _localctx = new ParamListContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_paramList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << New) | (1L << This) | (1L << Null) | (1L << LogicalConstant) | (1L << IntegerConstant) | (1L << StringLiteral) | (1L << Add) | (1L << Sub) | (1L << LogicalNot) | (1L << BitInversion) | (1L << Increment) | (1L << Decrement) | (1L << LeftRoundBracket) | (1L << Identifier))) != 0)) {
				{
				setState(129);
				object(0);
				setState(134);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__0) {
					{
					{
					setState(130);
					match(T__0);
					setState(131);
					object(0);
					}
					}
					setState(136);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
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

	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class StmtVariableDefinitionStatementContext extends StatementContext {
		public VariableDefinitionStatementContext variableDefinitionStatement() {
			return getRuleContext(VariableDefinitionStatementContext.class,0);
		}
		public StmtVariableDefinitionStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterStmtVariableDefinitionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitStmtVariableDefinitionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitStmtVariableDefinitionStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StmtObjectStatementContext extends StatementContext {
		public ObjectStatementContext objectStatement() {
			return getRuleContext(ObjectStatementContext.class,0);
		}
		public StmtObjectStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterStmtObjectStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitStmtObjectStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitStmtObjectStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StmtCompoundStatementContext extends StatementContext {
		public CompoundStatementContext compoundStatement() {
			return getRuleContext(CompoundStatementContext.class,0);
		}
		public StmtCompoundStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterStmtCompoundStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitStmtCompoundStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitStmtCompoundStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StmtConditionStatementContext extends StatementContext {
		public ConditionStatementContext conditionStatement() {
			return getRuleContext(ConditionStatementContext.class,0);
		}
		public StmtConditionStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterStmtConditionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitStmtConditionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitStmtConditionStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StmtVariableDeclarationStatementContext extends StatementContext {
		public VariableDeclarationStatementContext variableDeclarationStatement() {
			return getRuleContext(VariableDeclarationStatementContext.class,0);
		}
		public StmtVariableDeclarationStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterStmtVariableDeclarationStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitStmtVariableDeclarationStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitStmtVariableDeclarationStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StmtLoopStatementContext extends StatementContext {
		public LoopStatementContext loopStatement() {
			return getRuleContext(LoopStatementContext.class,0);
		}
		public StmtLoopStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterStmtLoopStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitStmtLoopStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitStmtLoopStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StmtJumpStatementContext extends StatementContext {
		public JumpStatementContext jumpStatement() {
			return getRuleContext(JumpStatementContext.class,0);
		}
		public StmtJumpStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterStmtJumpStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitStmtJumpStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitStmtJumpStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StmtEmptyStatementContext extends StatementContext {
		public EmptyStatementContext emptyStatement() {
			return getRuleContext(EmptyStatementContext.class,0);
		}
		public StmtEmptyStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterStmtEmptyStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitStmtEmptyStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitStmtEmptyStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StmtVariableAssignmentStatementContext extends StatementContext {
		public VariableAssignmentStatementContext variableAssignmentStatement() {
			return getRuleContext(VariableAssignmentStatementContext.class,0);
		}
		public StmtVariableAssignmentStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterStmtVariableAssignmentStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitStmtVariableAssignmentStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitStmtVariableAssignmentStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_statement);
		try {
			setState(148);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				_localctx = new StmtEmptyStatementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(139);
				emptyStatement();
				}
				break;
			case 2:
				_localctx = new StmtVariableDeclarationStatementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(140);
				variableDeclarationStatement();
				}
				break;
			case 3:
				_localctx = new StmtVariableDefinitionStatementContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(141);
				variableDefinitionStatement();
				}
				break;
			case 4:
				_localctx = new StmtVariableAssignmentStatementContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(142);
				variableAssignmentStatement();
				}
				break;
			case 5:
				_localctx = new StmtObjectStatementContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(143);
				objectStatement();
				}
				break;
			case 6:
				_localctx = new StmtLoopStatementContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(144);
				loopStatement();
				}
				break;
			case 7:
				_localctx = new StmtConditionStatementContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(145);
				conditionStatement();
				}
				break;
			case 8:
				_localctx = new StmtJumpStatementContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(146);
				jumpStatement();
				}
				break;
			case 9:
				_localctx = new StmtCompoundStatementContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(147);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitStatements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementsContext statements() throws RecognitionException {
		StatementsContext _localctx = new StatementsContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_statements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(150);
				statement();
				}
				}
				setState(153); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << New) | (1L << Bool) | (1L << Int) | (1L << Void) | (1L << String) | (1L << This) | (1L << If) | (1L << For) | (1L << While) | (1L << Return) | (1L << Break) | (1L << Continue) | (1L << Null) | (1L << LogicalConstant) | (1L << IntegerConstant) | (1L << StringLiteral) | (1L << Add) | (1L << Sub) | (1L << LogicalNot) | (1L << BitInversion) | (1L << Increment) | (1L << Decrement) | (1L << LeftRoundBracket) | (1L << LeftBrace) | (1L << Identifier))) != 0) );
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitEmptyStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EmptyStatementContext emptyStatement() throws RecognitionException {
		EmptyStatementContext _localctx = new EmptyStatementContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_emptyStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			match(T__1);
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

	public static class VariableDeclarationStatementContext extends ParserRuleContext {
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public VariableDeclarationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclarationStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterVariableDeclarationStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitVariableDeclarationStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitVariableDeclarationStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclarationStatementContext variableDeclarationStatement() throws RecognitionException {
		VariableDeclarationStatementContext _localctx = new VariableDeclarationStatementContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_variableDeclarationStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			variableDeclaration();
			setState(158);
			match(T__1);
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

	public static class VariableDefinitionStatementContext extends ParserRuleContext {
		public VariableDefinitionContext variableDefinition() {
			return getRuleContext(VariableDefinitionContext.class,0);
		}
		public VariableDefinitionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDefinitionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterVariableDefinitionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitVariableDefinitionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitVariableDefinitionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDefinitionStatementContext variableDefinitionStatement() throws RecognitionException {
		VariableDefinitionStatementContext _localctx = new VariableDefinitionStatementContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_variableDefinitionStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			variableDefinition();
			setState(161);
			match(T__1);
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

	public static class VariableAssignmentStatementContext extends ParserRuleContext {
		public VariableAssignmentContext variableAssignment() {
			return getRuleContext(VariableAssignmentContext.class,0);
		}
		public VariableAssignmentStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableAssignmentStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterVariableAssignmentStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitVariableAssignmentStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitVariableAssignmentStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableAssignmentStatementContext variableAssignmentStatement() throws RecognitionException {
		VariableAssignmentStatementContext _localctx = new VariableAssignmentStatementContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_variableAssignmentStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			variableAssignment();
			setState(164);
			match(T__1);
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

	public static class ObjectStatementContext extends ParserRuleContext {
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public ObjectStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterObjectStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitObjectStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitObjectStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjectStatementContext objectStatement() throws RecognitionException {
		ObjectStatementContext _localctx = new ObjectStatementContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_objectStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			object(0);
			setState(167);
			match(T__1);
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
		public LoopStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loopStatement; }
	 
		public LoopStatementContext() { }
		public void copyFrom(LoopStatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ForLoopContext extends LoopStatementContext {
		public TerminalNode For() { return getToken(Mx_starParser.For, 0); }
		public ForConditionContext forCondition() {
			return getRuleContext(ForConditionContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ForLoopContext(LoopStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterForLoop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitForLoop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitForLoop(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WhileLoopContext extends LoopStatementContext {
		public TerminalNode While() { return getToken(Mx_starParser.While, 0); }
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileLoopContext(LoopStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterWhileLoop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitWhileLoop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitWhileLoop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LoopStatementContext loopStatement() throws RecognitionException {
		LoopStatementContext _localctx = new LoopStatementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_loopStatement);
		try {
			setState(181);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case While:
				_localctx = new WhileLoopContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(169);
				match(While);
				setState(170);
				match(LeftRoundBracket);
				setState(171);
				object(0);
				setState(172);
				match(RightRoundBracket);
				setState(173);
				statement();
				}
				break;
			case For:
				_localctx = new ForLoopContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(175);
				match(For);
				setState(176);
				match(LeftRoundBracket);
				setState(177);
				forCondition();
				setState(178);
				match(RightRoundBracket);
				setState(179);
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
		public ForCondition2Context forCondition2() {
			return getRuleContext(ForCondition2Context.class,0);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitForCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForConditionContext forCondition() throws RecognitionException {
		ForConditionContext _localctx = new ForConditionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_forCondition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << New) | (1L << Bool) | (1L << Int) | (1L << Void) | (1L << String) | (1L << This) | (1L << Null) | (1L << LogicalConstant) | (1L << IntegerConstant) | (1L << StringLiteral) | (1L << Add) | (1L << Sub) | (1L << LogicalNot) | (1L << BitInversion) | (1L << Increment) | (1L << Decrement) | (1L << LeftRoundBracket) | (1L << Identifier))) != 0)) {
				{
				setState(183);
				forCondition1();
				}
			}

			setState(186);
			match(T__1);
			setState(188);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << New) | (1L << This) | (1L << Null) | (1L << LogicalConstant) | (1L << IntegerConstant) | (1L << StringLiteral) | (1L << Add) | (1L << Sub) | (1L << LogicalNot) | (1L << BitInversion) | (1L << Increment) | (1L << Decrement) | (1L << LeftRoundBracket) | (1L << Identifier))) != 0)) {
				{
				setState(187);
				forCondition2();
				}
			}

			setState(190);
			match(T__1);
			setState(192);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << New) | (1L << This) | (1L << Null) | (1L << LogicalConstant) | (1L << IntegerConstant) | (1L << StringLiteral) | (1L << Add) | (1L << Sub) | (1L << LogicalNot) | (1L << BitInversion) | (1L << Increment) | (1L << Decrement) | (1L << LeftRoundBracket) | (1L << Identifier))) != 0)) {
				{
				setState(191);
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
		public ForCondition1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forCondition1; }
	 
		public ForCondition1Context() { }
		public void copyFrom(ForCondition1Context ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ForCdt1VariableDeclarationContext extends ForCondition1Context {
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public ForCdt1VariableDeclarationContext(ForCondition1Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterForCdt1VariableDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitForCdt1VariableDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitForCdt1VariableDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ForCdt1VariableDefinitionContext extends ForCondition1Context {
		public VariableDefinitionContext variableDefinition() {
			return getRuleContext(VariableDefinitionContext.class,0);
		}
		public ForCdt1VariableDefinitionContext(ForCondition1Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterForCdt1VariableDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitForCdt1VariableDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitForCdt1VariableDefinition(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ForCdt1ObjectContext extends ForCondition1Context {
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public ForCdt1ObjectContext(ForCondition1Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterForCdt1Object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitForCdt1Object(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitForCdt1Object(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ForCdt1VariableAssignmentContext extends ForCondition1Context {
		public VariableAssignmentContext variableAssignment() {
			return getRuleContext(VariableAssignmentContext.class,0);
		}
		public ForCdt1VariableAssignmentContext(ForCondition1Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterForCdt1VariableAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitForCdt1VariableAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitForCdt1VariableAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForCondition1Context forCondition1() throws RecognitionException {
		ForCondition1Context _localctx = new ForCondition1Context(_ctx, getState());
		enterRule(_localctx, 34, RULE_forCondition1);
		try {
			setState(198);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				_localctx = new ForCdt1VariableDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(194);
				variableDeclaration();
				}
				break;
			case 2:
				_localctx = new ForCdt1VariableDefinitionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(195);
				variableDefinition();
				}
				break;
			case 3:
				_localctx = new ForCdt1VariableAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(196);
				variableAssignment();
				}
				break;
			case 4:
				_localctx = new ForCdt1ObjectContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(197);
				object(0);
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

	public static class ForCondition2Context extends ParserRuleContext {
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public ForCondition2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forCondition2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterForCondition2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitForCondition2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitForCondition2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForCondition2Context forCondition2() throws RecognitionException {
		ForCondition2Context _localctx = new ForCondition2Context(_ctx, getState());
		enterRule(_localctx, 36, RULE_forCondition2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
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

	public static class ForCondition3Context extends ParserRuleContext {
		public ForCondition3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forCondition3; }
	 
		public ForCondition3Context() { }
		public void copyFrom(ForCondition3Context ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ForCdt3ObjectContext extends ForCondition3Context {
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public ForCdt3ObjectContext(ForCondition3Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterForCdt3Object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitForCdt3Object(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitForCdt3Object(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ForCdt3VariableAssignmentContext extends ForCondition3Context {
		public VariableAssignmentContext variableAssignment() {
			return getRuleContext(VariableAssignmentContext.class,0);
		}
		public ForCdt3VariableAssignmentContext(ForCondition3Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterForCdt3VariableAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitForCdt3VariableAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitForCdt3VariableAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForCondition3Context forCondition3() throws RecognitionException {
		ForCondition3Context _localctx = new ForCondition3Context(_ctx, getState());
		enterRule(_localctx, 38, RULE_forCondition3);
		try {
			setState(204);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				_localctx = new ForCdt3VariableAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(202);
				variableAssignment();
				}
				break;
			case 2:
				_localctx = new ForCdt3ObjectContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(203);
				object(0);
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
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitConditionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionStatementContext conditionStatement() throws RecognitionException {
		ConditionStatementContext _localctx = new ConditionStatementContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_conditionStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			match(If);
			setState(207);
			match(LeftRoundBracket);
			setState(208);
			object(0);
			setState(209);
			match(RightRoundBracket);
			setState(210);
			statement();
			setState(213);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(211);
				match(Else);
				setState(212);
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
		public JumpStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jumpStatement; }
	 
		public JumpStatementContext() { }
		public void copyFrom(JumpStatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class JumpReturnContext extends JumpStatementContext {
		public TerminalNode Return() { return getToken(Mx_starParser.Return, 0); }
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public JumpReturnContext(JumpStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterJumpReturn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitJumpReturn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitJumpReturn(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class JumpContinueContext extends JumpStatementContext {
		public TerminalNode Continue() { return getToken(Mx_starParser.Continue, 0); }
		public JumpContinueContext(JumpStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterJumpContinue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitJumpContinue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitJumpContinue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class JumpBreakContext extends JumpStatementContext {
		public TerminalNode Break() { return getToken(Mx_starParser.Break, 0); }
		public JumpBreakContext(JumpStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterJumpBreak(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitJumpBreak(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitJumpBreak(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JumpStatementContext jumpStatement() throws RecognitionException {
		JumpStatementContext _localctx = new JumpStatementContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_jumpStatement);
		int _la;
		try {
			setState(224);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Return:
				_localctx = new JumpReturnContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(215);
				match(Return);
				setState(217);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << New) | (1L << This) | (1L << Null) | (1L << LogicalConstant) | (1L << IntegerConstant) | (1L << StringLiteral) | (1L << Add) | (1L << Sub) | (1L << LogicalNot) | (1L << BitInversion) | (1L << Increment) | (1L << Decrement) | (1L << LeftRoundBracket) | (1L << Identifier))) != 0)) {
					{
					setState(216);
					object(0);
					}
				}

				setState(219);
				match(T__1);
				}
				break;
			case Break:
				_localctx = new JumpBreakContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(220);
				match(Break);
				setState(221);
				match(T__1);
				}
				break;
			case Continue:
				_localctx = new JumpContinueContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(222);
				match(Continue);
				setState(223);
				match(T__1);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitCompoundStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompoundStatementContext compoundStatement() throws RecognitionException {
		CompoundStatementContext _localctx = new CompoundStatementContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_compoundStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			match(LeftBrace);
			setState(228);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << New) | (1L << Bool) | (1L << Int) | (1L << Void) | (1L << String) | (1L << This) | (1L << If) | (1L << For) | (1L << While) | (1L << Return) | (1L << Break) | (1L << Continue) | (1L << Null) | (1L << LogicalConstant) | (1L << IntegerConstant) | (1L << StringLiteral) | (1L << Add) | (1L << Sub) | (1L << LogicalNot) | (1L << BitInversion) | (1L << Increment) | (1L << Decrement) | (1L << LeftRoundBracket) | (1L << LeftBrace) | (1L << Identifier))) != 0)) {
				{
				setState(227);
				statements();
				}
			}

			setState(230);
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

	public static class VariableDeclarationContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(Mx_starParser.Identifier, 0); }
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitVariableDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclarationContext variableDeclaration() throws RecognitionException {
		VariableDeclarationContext _localctx = new VariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_variableDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232);
			type();
			setState(233);
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

	public static class VariableDefinitionContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(Mx_starParser.Identifier, 0); }
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public VariableDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterVariableDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitVariableDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitVariableDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDefinitionContext variableDefinition() throws RecognitionException {
		VariableDefinitionContext _localctx = new VariableDefinitionContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_variableDefinition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			type();
			setState(236);
			match(Identifier);
			setState(237);
			match(Assign);
			setState(238);
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

	public static class VariableAssignmentContext extends ParserRuleContext {
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public VariableAssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableAssignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterVariableAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitVariableAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitVariableAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableAssignmentContext variableAssignment() throws RecognitionException {
		VariableAssignmentContext _localctx = new VariableAssignmentContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_variableAssignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			lvalue(0);
			setState(241);
			match(Assign);
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

	public static class LvalueContext extends ParserRuleContext {
		public LvalueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lvalue; }
	 
		public LvalueContext() { }
		public void copyFrom(LvalueContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SubscriptLvalueContext extends LvalueContext {
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public SubscriptLvalueContext(LvalueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterSubscriptLvalue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitSubscriptLvalue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitSubscriptLvalue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MemberLvalueContext extends LvalueContext {
		public TerminalNode This() { return getToken(Mx_starParser.This, 0); }
		public TerminalNode Identifier() { return getToken(Mx_starParser.Identifier, 0); }
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public MemberLvalueContext(LvalueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterMemberLvalue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitMemberLvalue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitMemberLvalue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdentifierLvalueContext extends LvalueContext {
		public TerminalNode Identifier() { return getToken(Mx_starParser.Identifier, 0); }
		public IdentifierLvalueContext(LvalueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterIdentifierLvalue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitIdentifierLvalue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitIdentifierLvalue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LvalueContext lvalue() throws RecognitionException {
		return lvalue(0);
	}

	private LvalueContext lvalue(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		LvalueContext _localctx = new LvalueContext(_ctx, _parentState);
		LvalueContext _prevctx = _localctx;
		int _startState = 52;
		enterRecursionRule(_localctx, 52, RULE_lvalue, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Identifier:
				{
				_localctx = new IdentifierLvalueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(245);
				match(Identifier);
				}
				break;
			case This:
				{
				_localctx = new MemberLvalueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(246);
				match(This);
				setState(247);
				match(MemberAccess);
				setState(248);
				match(Identifier);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(261);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(259);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
					case 1:
						{
						_localctx = new MemberLvalueContext(new LvalueContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_lvalue);
						setState(251);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(252);
						match(MemberAccess);
						setState(253);
						match(Identifier);
						}
						break;
					case 2:
						{
						_localctx = new SubscriptLvalueContext(new LvalueContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_lvalue);
						setState(254);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(255);
						match(LeftSquareBracket);
						setState(256);
						object(0);
						setState(257);
						match(RightSquareBracket);
						}
						break;
					}
					} 
				}
				setState(263);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
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

	public static class ObjectContext extends ParserRuleContext {
		public ObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_object; }
	 
		public ObjectContext() { }
		public void copyFrom(ObjectContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FunctionReturnObjectContext extends ObjectContext {
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class,0);
		}
		public FunctionReturnObjectContext(ObjectContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterFunctionReturnObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitFunctionReturnObject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitFunctionReturnObject(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SubscriptObjectContext extends ObjectContext {
		public List<ObjectContext> object() {
			return getRuleContexts(ObjectContext.class);
		}
		public ObjectContext object(int i) {
			return getRuleContext(ObjectContext.class,i);
		}
		public SubscriptObjectContext(ObjectContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterSubscriptObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitSubscriptObject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitSubscriptObject(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewObjectContext extends ObjectContext {
		public Token op;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode New() { return getToken(Mx_starParser.New, 0); }
		public List<ObjectContext> object() {
			return getRuleContexts(ObjectContext.class);
		}
		public ObjectContext object(int i) {
			return getRuleContext(ObjectContext.class,i);
		}
		public NewObjectContext(ObjectContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterNewObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitNewObject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitNewObject(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdentifierObjectContext extends ObjectContext {
		public TerminalNode Identifier() { return getToken(Mx_starParser.Identifier, 0); }
		public IdentifierObjectContext(ObjectContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterIdentifierObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitIdentifierObject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitIdentifierObject(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ConstantObjectContext extends ObjectContext {
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public ConstantObjectContext(ObjectContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterConstantObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitConstantObject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitConstantObject(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LvalueObjectContext extends ObjectContext {
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public LvalueObjectContext(ObjectContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterLvalueObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitLvalueObject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitLvalueObject(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MemberObjectContext extends ObjectContext {
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(Mx_starParser.Identifier, 0); }
		public MemberObjectContext(ObjectContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterMemberObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitMemberObject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitMemberObject(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BracketObjectContext extends ObjectContext {
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public BracketObjectContext(ObjectContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterBracketObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitBracketObject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitBracketObject(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinaryOperatorObjectContext extends ObjectContext {
		public Token op;
		public List<ObjectContext> object() {
			return getRuleContexts(ObjectContext.class);
		}
		public ObjectContext object(int i) {
			return getRuleContext(ObjectContext.class,i);
		}
		public BinaryOperatorObjectContext(ObjectContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterBinaryOperatorObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitBinaryOperatorObject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitBinaryOperatorObject(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnaryOperatorObjectContext extends ObjectContext {
		public Token op;
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public UnaryOperatorObjectContext(ObjectContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterUnaryOperatorObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitUnaryOperatorObject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitUnaryOperatorObject(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ThisObjectContext extends ObjectContext {
		public TerminalNode This() { return getToken(Mx_starParser.This, 0); }
		public ThisObjectContext(ObjectContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterThisObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitThisObject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitThisObject(this);
			else return visitor.visitChildren(this);
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
		int _startState = 54;
		enterRecursionRule(_localctx, 54, RULE_object, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(301);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				{
				_localctx = new ThisObjectContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(265);
				match(This);
				}
				break;
			case 2:
				{
				_localctx = new IdentifierObjectContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(266);
				match(Identifier);
				}
				break;
			case 3:
				{
				_localctx = new ConstantObjectContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(267);
				constant();
				}
				break;
			case 4:
				{
				_localctx = new LvalueObjectContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(268);
				lvalue(0);
				}
				break;
			case 5:
				{
				_localctx = new BracketObjectContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(269);
				match(LeftRoundBracket);
				setState(270);
				object(0);
				setState(271);
				match(RightRoundBracket);
				}
				break;
			case 6:
				{
				_localctx = new UnaryOperatorObjectContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(273);
				lvalue(0);
				setState(274);
				((UnaryOperatorObjectContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==Increment || _la==Decrement) ) {
					((UnaryOperatorObjectContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 7:
				{
				_localctx = new UnaryOperatorObjectContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(276);
				((UnaryOperatorObjectContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==Increment || _la==Decrement) ) {
					((UnaryOperatorObjectContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(277);
				lvalue(0);
				}
				break;
			case 8:
				{
				_localctx = new UnaryOperatorObjectContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(278);
				((UnaryOperatorObjectContext)_localctx).op = match(BitInversion);
				setState(279);
				object(14);
				}
				break;
			case 9:
				{
				_localctx = new UnaryOperatorObjectContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(280);
				((UnaryOperatorObjectContext)_localctx).op = match(LogicalNot);
				setState(281);
				object(13);
				}
				break;
			case 10:
				{
				_localctx = new UnaryOperatorObjectContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(282);
				((UnaryOperatorObjectContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==Add || _la==Sub) ) {
					((UnaryOperatorObjectContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(283);
				object(12);
				}
				break;
			case 11:
				{
				_localctx = new NewObjectContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(284);
				((NewObjectContext)_localctx).op = match(New);
				setState(285);
				type();
				setState(299);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
				case 1:
					{
					setState(291); 
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
						case 1:
							{
							{
							setState(286);
							match(LeftSquareBracket);
							setState(288);
							_errHandler.sync(this);
							_la = _input.LA(1);
							if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << New) | (1L << This) | (1L << Null) | (1L << LogicalConstant) | (1L << IntegerConstant) | (1L << StringLiteral) | (1L << Add) | (1L << Sub) | (1L << LogicalNot) | (1L << BitInversion) | (1L << Increment) | (1L << Decrement) | (1L << LeftRoundBracket) | (1L << Identifier))) != 0)) {
								{
								setState(287);
								object(0);
								}
							}

							setState(290);
							match(RightSquareBracket);
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(293); 
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
					} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
					}
					break;
				case 2:
					{
					setState(297);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
					case 1:
						{
						setState(295);
						match(LeftRoundBracket);
						setState(296);
						match(RightRoundBracket);
						}
						break;
					}
					}
					break;
				}
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(348);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(346);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryOperatorObjectContext(new ObjectContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_object);
						setState(303);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(304);
						((BinaryOperatorObjectContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Mul) | (1L << Div) | (1L << Mod))) != 0)) ) {
							((BinaryOperatorObjectContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(305);
						object(11);
						}
						break;
					case 2:
						{
						_localctx = new BinaryOperatorObjectContext(new ObjectContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_object);
						setState(306);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(307);
						((BinaryOperatorObjectContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Add || _la==Sub) ) {
							((BinaryOperatorObjectContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(308);
						object(10);
						}
						break;
					case 3:
						{
						_localctx = new BinaryOperatorObjectContext(new ObjectContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_object);
						setState(309);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(310);
						((BinaryOperatorObjectContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==SHL || _la==SHR) ) {
							((BinaryOperatorObjectContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(311);
						object(9);
						}
						break;
					case 4:
						{
						_localctx = new BinaryOperatorObjectContext(new ObjectContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_object);
						setState(312);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(313);
						((BinaryOperatorObjectContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Less) | (1L << Greater) | (1L << LessEqual) | (1L << GreaterEqual))) != 0)) ) {
							((BinaryOperatorObjectContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(314);
						object(8);
						}
						break;
					case 5:
						{
						_localctx = new BinaryOperatorObjectContext(new ObjectContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_object);
						setState(315);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(316);
						((BinaryOperatorObjectContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Equal || _la==Unequal) ) {
							((BinaryOperatorObjectContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(317);
						object(7);
						}
						break;
					case 6:
						{
						_localctx = new BinaryOperatorObjectContext(new ObjectContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_object);
						setState(318);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(319);
						((BinaryOperatorObjectContext)_localctx).op = match(BitAnd);
						setState(320);
						object(6);
						}
						break;
					case 7:
						{
						_localctx = new BinaryOperatorObjectContext(new ObjectContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_object);
						setState(321);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(322);
						((BinaryOperatorObjectContext)_localctx).op = match(BitXor);
						setState(323);
						object(5);
						}
						break;
					case 8:
						{
						_localctx = new BinaryOperatorObjectContext(new ObjectContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_object);
						setState(324);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(325);
						((BinaryOperatorObjectContext)_localctx).op = match(BitOr);
						setState(326);
						object(4);
						}
						break;
					case 9:
						{
						_localctx = new BinaryOperatorObjectContext(new ObjectContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_object);
						setState(327);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(328);
						((BinaryOperatorObjectContext)_localctx).op = match(LogicalAnd);
						setState(329);
						object(3);
						}
						break;
					case 10:
						{
						_localctx = new BinaryOperatorObjectContext(new ObjectContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_object);
						setState(330);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(331);
						((BinaryOperatorObjectContext)_localctx).op = match(LogicalOr);
						setState(332);
						object(2);
						}
						break;
					case 11:
						{
						_localctx = new MemberObjectContext(new ObjectContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_object);
						setState(333);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(334);
						match(MemberAccess);
						setState(335);
						match(Identifier);
						}
						break;
					case 12:
						{
						_localctx = new FunctionReturnObjectContext(new ObjectContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_object);
						setState(336);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(337);
						match(LeftRoundBracket);
						setState(338);
						paramList();
						setState(339);
						match(RightRoundBracket);
						}
						break;
					case 13:
						{
						_localctx = new SubscriptObjectContext(new ObjectContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_object);
						setState(341);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(342);
						match(LeftSquareBracket);
						setState(343);
						object(0);
						setState(344);
						match(RightSquareBracket);
						}
						break;
					}
					} 
				}
				setState(350);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_type);
		try {
			setState(353);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(351);
				simpleType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(352);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitSimpleType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimpleTypeContext simpleType() throws RecognitionException {
		SimpleTypeContext _localctx = new SimpleTypeContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_simpleType);
		try {
			setState(357);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Bool:
			case Int:
			case Void:
			case String:
				enterOuterAlt(_localctx, 1);
				{
				setState(355);
				fundamentalType();
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(356);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitCompositeType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompositeTypeContext compositeType() throws RecognitionException {
		CompositeTypeContext _localctx = new CompositeTypeContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_compositeType);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(359);
			simpleType();
			setState(362); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(360);
					match(LeftSquareBracket);
					setState(361);
					match(RightSquareBracket);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(364); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitFundamentalType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FundamentalTypeContext fundamentalType() throws RecognitionException {
		FundamentalTypeContext _localctx = new FundamentalTypeContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_fundamentalType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(366);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitCustomType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CustomTypeContext customType() throws RecognitionException {
		CustomTypeContext _localctx = new CustomTypeContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_customType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(368);
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

	public static class ConstantContext extends ParserRuleContext {
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
	 
		public ConstantContext() { }
		public void copyFrom(ConstantContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NullContext extends ConstantContext {
		public TerminalNode Null() { return getToken(Mx_starParser.Null, 0); }
		public NullContext(ConstantContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterNull(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitNull(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitNull(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringLiteralContext extends ConstantContext {
		public TerminalNode StringLiteral() { return getToken(Mx_starParser.StringLiteral, 0); }
		public StringLiteralContext(ConstantContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterStringLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitStringLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitStringLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LogicalConstantContext extends ConstantContext {
		public TerminalNode LogicalConstant() { return getToken(Mx_starParser.LogicalConstant, 0); }
		public LogicalConstantContext(ConstantContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterLogicalConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitLogicalConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitLogicalConstant(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntegerConstantContext extends ConstantContext {
		public TerminalNode IntegerConstant() { return getToken(Mx_starParser.IntegerConstant, 0); }
		public IntegerConstantContext(ConstantContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).enterIntegerConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_starListener ) ((Mx_starListener)listener).exitIntegerConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_starVisitor ) return ((Mx_starVisitor<? extends T>)visitor).visitIntegerConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_constant);
		try {
			setState(374);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Null:
				_localctx = new NullContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(370);
				match(Null);
				}
				break;
			case LogicalConstant:
				_localctx = new LogicalConstantContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(371);
				match(LogicalConstant);
				}
				break;
			case IntegerConstant:
				_localctx = new IntegerConstantContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(372);
				match(IntegerConstant);
				}
				break;
			case StringLiteral:
				_localctx = new StringLiteralContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(373);
				match(StringLiteral);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 26:
			return lvalue_sempred((LvalueContext)_localctx, predIndex);
		case 27:
			return object_sempred((ObjectContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean lvalue_sempred(LvalueContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean object_sempred(ObjectContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 10);
		case 3:
			return precpred(_ctx, 9);
		case 4:
			return precpred(_ctx, 8);
		case 5:
			return precpred(_ctx, 7);
		case 6:
			return precpred(_ctx, 6);
		case 7:
			return precpred(_ctx, 5);
		case 8:
			return precpred(_ctx, 4);
		case 9:
			return precpred(_ctx, 3);
		case 10:
			return precpred(_ctx, 2);
		case 11:
			return precpred(_ctx, 1);
		case 12:
			return precpred(_ctx, 20);
		case 13:
			return precpred(_ctx, 18);
		case 14:
			return precpred(_ctx, 17);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3;\u017b\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\3\2\6\2H\n\2\r\2\16\2I\3\2\3\2\3\3\3\3\3\3\3\3\3\3\5"+
		"\3S\n\3\3\4\3\4\3\4\3\4\7\4Y\n\4\f\4\16\4\\\13\4\3\4\3\4\3\5\3\5\3\5\5"+
		"\5c\n\5\3\6\3\6\3\6\3\6\3\6\3\6\5\6k\n\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\5\7v\n\7\3\7\3\7\3\b\3\b\3\b\7\b}\n\b\f\b\16\b\u0080\13\b\5\b\u0082"+
		"\n\b\3\t\3\t\3\t\7\t\u0087\n\t\f\t\16\t\u008a\13\t\5\t\u008c\n\t\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u0097\n\n\3\13\6\13\u009a\n\13\r\13"+
		"\16\13\u009b\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3"+
		"\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5"+
		"\21\u00b8\n\21\3\22\5\22\u00bb\n\22\3\22\3\22\5\22\u00bf\n\22\3\22\3\22"+
		"\5\22\u00c3\n\22\3\23\3\23\3\23\3\23\5\23\u00c9\n\23\3\24\3\24\3\25\3"+
		"\25\5\25\u00cf\n\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u00d8\n\26"+
		"\3\27\3\27\5\27\u00dc\n\27\3\27\3\27\3\27\3\27\3\27\5\27\u00e3\n\27\3"+
		"\30\3\30\5\30\u00e7\n\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3\32"+
		"\3\32\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\5\34\u00fc\n\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\34\3\34\7\34\u0106\n\34\f\34\16\34\u0109\13"+
		"\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3"+
		"\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\5\35\u0123\n\35"+
		"\3\35\6\35\u0126\n\35\r\35\16\35\u0127\3\35\3\35\5\35\u012c\n\35\5\35"+
		"\u012e\n\35\5\35\u0130\n\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3"+
		"\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3"+
		"\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3"+
		"\35\3\35\3\35\3\35\3\35\3\35\3\35\7\35\u015d\n\35\f\35\16\35\u0160\13"+
		"\35\3\36\3\36\5\36\u0164\n\36\3\37\3\37\5\37\u0168\n\37\3 \3 \3 \6 \u016d"+
		"\n \r \16 \u016e\3!\3!\3\"\3\"\3#\3#\3#\3#\5#\u0179\n#\3#\2\4\668$\2\4"+
		"\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BD\2\t\3"+
		"\2./\3\2\31\32\3\2\33\35\3\2\'(\4\2\36\37\"#\3\2 !\3\2\6\t\2\u01a0\2G"+
		"\3\2\2\2\4R\3\2\2\2\6T\3\2\2\2\bb\3\2\2\2\nd\3\2\2\2\fn\3\2\2\2\16\u0081"+
		"\3\2\2\2\20\u008b\3\2\2\2\22\u0096\3\2\2\2\24\u0099\3\2\2\2\26\u009d\3"+
		"\2\2\2\30\u009f\3\2\2\2\32\u00a2\3\2\2\2\34\u00a5\3\2\2\2\36\u00a8\3\2"+
		"\2\2 \u00b7\3\2\2\2\"\u00ba\3\2\2\2$\u00c8\3\2\2\2&\u00ca\3\2\2\2(\u00ce"+
		"\3\2\2\2*\u00d0\3\2\2\2,\u00e2\3\2\2\2.\u00e4\3\2\2\2\60\u00ea\3\2\2\2"+
		"\62\u00ed\3\2\2\2\64\u00f2\3\2\2\2\66\u00fb\3\2\2\28\u012f\3\2\2\2:\u0163"+
		"\3\2\2\2<\u0167\3\2\2\2>\u0169\3\2\2\2@\u0170\3\2\2\2B\u0172\3\2\2\2D"+
		"\u0178\3\2\2\2FH\5\4\3\2GF\3\2\2\2HI\3\2\2\2IG\3\2\2\2IJ\3\2\2\2JK\3\2"+
		"\2\2KL\7\2\2\3L\3\3\2\2\2MS\5\26\f\2NS\5\30\r\2OS\5\32\16\2PS\5\6\4\2"+
		"QS\5\f\7\2RM\3\2\2\2RN\3\2\2\2RO\3\2\2\2RP\3\2\2\2RQ\3\2\2\2S\5\3\2\2"+
		"\2TU\7\n\2\2UV\7\67\2\2VZ\7\65\2\2WY\5\b\5\2XW\3\2\2\2Y\\\3\2\2\2ZX\3"+
		"\2\2\2Z[\3\2\2\2[]\3\2\2\2\\Z\3\2\2\2]^\7\66\2\2^\7\3\2\2\2_c\5\30\r\2"+
		"`c\5\n\6\2ac\5\f\7\2b_\3\2\2\2b`\3\2\2\2ba\3\2\2\2c\t\3\2\2\2de\7\67\2"+
		"\2ef\7\61\2\2fg\5\16\b\2gh\7\62\2\2hj\7\65\2\2ik\5\24\13\2ji\3\2\2\2j"+
		"k\3\2\2\2kl\3\2\2\2lm\7\66\2\2m\13\3\2\2\2no\5:\36\2op\7\67\2\2pq\7\61"+
		"\2\2qr\5\16\b\2rs\7\62\2\2su\7\65\2\2tv\5\24\13\2ut\3\2\2\2uv\3\2\2\2"+
		"vw\3\2\2\2wx\7\66\2\2x\r\3\2\2\2y~\5\60\31\2z{\7\3\2\2{}\5\60\31\2|z\3"+
		"\2\2\2}\u0080\3\2\2\2~|\3\2\2\2~\177\3\2\2\2\177\u0082\3\2\2\2\u0080~"+
		"\3\2\2\2\u0081y\3\2\2\2\u0081\u0082\3\2\2\2\u0082\17\3\2\2\2\u0083\u0088"+
		"\58\35\2\u0084\u0085\7\3\2\2\u0085\u0087\58\35\2\u0086\u0084\3\2\2\2\u0087"+
		"\u008a\3\2\2\2\u0088\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008c\3\2"+
		"\2\2\u008a\u0088\3\2\2\2\u008b\u0083\3\2\2\2\u008b\u008c\3\2\2\2\u008c"+
		"\21\3\2\2\2\u008d\u0097\5\26\f\2\u008e\u0097\5\30\r\2\u008f\u0097\5\32"+
		"\16\2\u0090\u0097\5\34\17\2\u0091\u0097\5\36\20\2\u0092\u0097\5 \21\2"+
		"\u0093\u0097\5*\26\2\u0094\u0097\5,\27\2\u0095\u0097\5.\30\2\u0096\u008d"+
		"\3\2\2\2\u0096\u008e\3\2\2\2\u0096\u008f\3\2\2\2\u0096\u0090\3\2\2\2\u0096"+
		"\u0091\3\2\2\2\u0096\u0092\3\2\2\2\u0096\u0093\3\2\2\2\u0096\u0094\3\2"+
		"\2\2\u0096\u0095\3\2\2\2\u0097\23\3\2\2\2\u0098\u009a\5\22\n\2\u0099\u0098"+
		"\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c"+
		"\25\3\2\2\2\u009d\u009e\7\4\2\2\u009e\27\3\2\2\2\u009f\u00a0\5\60\31\2"+
		"\u00a0\u00a1\7\4\2\2\u00a1\31\3\2\2\2\u00a2\u00a3\5\62\32\2\u00a3\u00a4"+
		"\7\4\2\2\u00a4\33\3\2\2\2\u00a5\u00a6\5\64\33\2\u00a6\u00a7\7\4\2\2\u00a7"+
		"\35\3\2\2\2\u00a8\u00a9\58\35\2\u00a9\u00aa\7\4\2\2\u00aa\37\3\2\2\2\u00ab"+
		"\u00ac\7\17\2\2\u00ac\u00ad\7\61\2\2\u00ad\u00ae\58\35\2\u00ae\u00af\7"+
		"\62\2\2\u00af\u00b0\5\22\n\2\u00b0\u00b8\3\2\2\2\u00b1\u00b2\7\16\2\2"+
		"\u00b2\u00b3\7\61\2\2\u00b3\u00b4\5\"\22\2\u00b4\u00b5\7\62\2\2\u00b5"+
		"\u00b6\5\22\n\2\u00b6\u00b8\3\2\2\2\u00b7\u00ab\3\2\2\2\u00b7\u00b1\3"+
		"\2\2\2\u00b8!\3\2\2\2\u00b9\u00bb\5$\23\2\u00ba\u00b9\3\2\2\2\u00ba\u00bb"+
		"\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00be\7\4\2\2\u00bd\u00bf\5&\24\2\u00be"+
		"\u00bd\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c2\7\4"+
		"\2\2\u00c1\u00c3\5(\25\2\u00c2\u00c1\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3"+
		"#\3\2\2\2\u00c4\u00c9\5\60\31\2\u00c5\u00c9\5\62\32\2\u00c6\u00c9\5\64"+
		"\33\2\u00c7\u00c9\58\35\2\u00c8\u00c4\3\2\2\2\u00c8\u00c5\3\2\2\2\u00c8"+
		"\u00c6\3\2\2\2\u00c8\u00c7\3\2\2\2\u00c9%\3\2\2\2\u00ca\u00cb\58\35\2"+
		"\u00cb\'\3\2\2\2\u00cc\u00cf\5\64\33\2\u00cd\u00cf\58\35\2\u00ce\u00cc"+
		"\3\2\2\2\u00ce\u00cd\3\2\2\2\u00cf)\3\2\2\2\u00d0\u00d1\7\f\2\2\u00d1"+
		"\u00d2\7\61\2\2\u00d2\u00d3\58\35\2\u00d3\u00d4\7\62\2\2\u00d4\u00d7\5"+
		"\22\n\2\u00d5\u00d6\7\r\2\2\u00d6\u00d8\5\22\n\2\u00d7\u00d5\3\2\2\2\u00d7"+
		"\u00d8\3\2\2\2\u00d8+\3\2\2\2\u00d9\u00db\7\20\2\2\u00da\u00dc\58\35\2"+
		"\u00db\u00da\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00e3"+
		"\7\4\2\2\u00de\u00df\7\21\2\2\u00df\u00e3\7\4\2\2\u00e0\u00e1\7\22\2\2"+
		"\u00e1\u00e3\7\4\2\2\u00e2\u00d9\3\2\2\2\u00e2\u00de\3\2\2\2\u00e2\u00e0"+
		"\3\2\2\2\u00e3-\3\2\2\2\u00e4\u00e6\7\65\2\2\u00e5\u00e7\5\24\13\2\u00e6"+
		"\u00e5\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8\u00e9\7\66"+
		"\2\2\u00e9/\3\2\2\2\u00ea\u00eb\5:\36\2\u00eb\u00ec\7\67\2\2\u00ec\61"+
		"\3\2\2\2\u00ed\u00ee\5:\36\2\u00ee\u00ef\7\67\2\2\u00ef\u00f0\7-\2\2\u00f0"+
		"\u00f1\58\35\2\u00f1\63\3\2\2\2\u00f2\u00f3\5\66\34\2\u00f3\u00f4\7-\2"+
		"\2\u00f4\u00f5\58\35\2\u00f5\65\3\2\2\2\u00f6\u00f7\b\34\1\2\u00f7\u00fc"+
		"\7\67\2\2\u00f8\u00f9\7\13\2\2\u00f9\u00fa\7\60\2\2\u00fa\u00fc\7\67\2"+
		"\2\u00fb\u00f6\3\2\2\2\u00fb\u00f8\3\2\2\2\u00fc\u0107\3\2\2\2\u00fd\u00fe"+
		"\f\4\2\2\u00fe\u00ff\7\60\2\2\u00ff\u0106\7\67\2\2\u0100\u0101\f\3\2\2"+
		"\u0101\u0102\7\63\2\2\u0102\u0103\58\35\2\u0103\u0104\7\64\2\2\u0104\u0106"+
		"\3\2\2\2\u0105\u00fd\3\2\2\2\u0105\u0100\3\2\2\2\u0106\u0109\3\2\2\2\u0107"+
		"\u0105\3\2\2\2\u0107\u0108\3\2\2\2\u0108\67\3\2\2\2\u0109\u0107\3\2\2"+
		"\2\u010a\u010b\b\35\1\2\u010b\u0130\7\13\2\2\u010c\u0130\7\67\2\2\u010d"+
		"\u0130\5D#\2\u010e\u0130\5\66\34\2\u010f\u0110\7\61\2\2\u0110\u0111\5"+
		"8\35\2\u0111\u0112\7\62\2\2\u0112\u0130\3\2\2\2\u0113\u0114\5\66\34\2"+
		"\u0114\u0115\t\2\2\2\u0115\u0130\3\2\2\2\u0116\u0117\t\2\2\2\u0117\u0130"+
		"\5\66\34\2\u0118\u0119\7)\2\2\u0119\u0130\58\35\20\u011a\u011b\7$\2\2"+
		"\u011b\u0130\58\35\17\u011c\u011d\t\3\2\2\u011d\u0130\58\35\16\u011e\u011f"+
		"\7\5\2\2\u011f\u012d\5:\36\2\u0120\u0122\7\63\2\2\u0121\u0123\58\35\2"+
		"\u0122\u0121\3\2\2\2\u0122\u0123\3\2\2\2\u0123\u0124\3\2\2\2\u0124\u0126"+
		"\7\64\2\2\u0125\u0120\3\2\2\2\u0126\u0127\3\2\2\2\u0127\u0125\3\2\2\2"+
		"\u0127\u0128\3\2\2\2\u0128\u012e\3\2\2\2\u0129\u012a\7\61\2\2\u012a\u012c"+
		"\7\62\2\2\u012b\u0129\3\2\2\2\u012b\u012c\3\2\2\2\u012c\u012e\3\2\2\2"+
		"\u012d\u0125\3\2\2\2\u012d\u012b\3\2\2\2\u012e\u0130\3\2\2\2\u012f\u010a"+
		"\3\2\2\2\u012f\u010c\3\2\2\2\u012f\u010d\3\2\2\2\u012f\u010e\3\2\2\2\u012f"+
		"\u010f\3\2\2\2\u012f\u0113\3\2\2\2\u012f\u0116\3\2\2\2\u012f\u0118\3\2"+
		"\2\2\u012f\u011a\3\2\2\2\u012f\u011c\3\2\2\2\u012f\u011e\3\2\2\2\u0130"+
		"\u015e\3\2\2\2\u0131\u0132\f\f\2\2\u0132\u0133\t\4\2\2\u0133\u015d\58"+
		"\35\r\u0134\u0135\f\13\2\2\u0135\u0136\t\3\2\2\u0136\u015d\58\35\f\u0137"+
		"\u0138\f\n\2\2\u0138\u0139\t\5\2\2\u0139\u015d\58\35\13\u013a\u013b\f"+
		"\t\2\2\u013b\u013c\t\6\2\2\u013c\u015d\58\35\n\u013d\u013e\f\b\2\2\u013e"+
		"\u013f\t\7\2\2\u013f\u015d\58\35\t\u0140\u0141\f\7\2\2\u0141\u0142\7*"+
		"\2\2\u0142\u015d\58\35\b\u0143\u0144\f\6\2\2\u0144\u0145\7,\2\2\u0145"+
		"\u015d\58\35\7\u0146\u0147\f\5\2\2\u0147\u0148\7+\2\2\u0148\u015d\58\35"+
		"\6\u0149\u014a\f\4\2\2\u014a\u014b\7%\2\2\u014b\u015d\58\35\5\u014c\u014d"+
		"\f\3\2\2\u014d\u014e\7&\2\2\u014e\u015d\58\35\4\u014f\u0150\f\26\2\2\u0150"+
		"\u0151\7\60\2\2\u0151\u015d\7\67\2\2\u0152\u0153\f\24\2\2\u0153\u0154"+
		"\7\61\2\2\u0154\u0155\5\20\t\2\u0155\u0156\7\62\2\2\u0156\u015d\3\2\2"+
		"\2\u0157\u0158\f\23\2\2\u0158\u0159\7\63\2\2\u0159\u015a\58\35\2\u015a"+
		"\u015b\7\64\2\2\u015b\u015d\3\2\2\2\u015c\u0131\3\2\2\2\u015c\u0134\3"+
		"\2\2\2\u015c\u0137\3\2\2\2\u015c\u013a\3\2\2\2\u015c\u013d\3\2\2\2\u015c"+
		"\u0140\3\2\2\2\u015c\u0143\3\2\2\2\u015c\u0146\3\2\2\2\u015c\u0149\3\2"+
		"\2\2\u015c\u014c\3\2\2\2\u015c\u014f\3\2\2\2\u015c\u0152\3\2\2\2\u015c"+
		"\u0157\3\2\2\2\u015d\u0160\3\2\2\2\u015e\u015c\3\2\2\2\u015e\u015f\3\2"+
		"\2\2\u015f9\3\2\2\2\u0160\u015e\3\2\2\2\u0161\u0164\5<\37\2\u0162\u0164"+
		"\5> \2\u0163\u0161\3\2\2\2\u0163\u0162\3\2\2\2\u0164;\3\2\2\2\u0165\u0168"+
		"\5@!\2\u0166\u0168\5B\"\2\u0167\u0165\3\2\2\2\u0167\u0166\3\2\2\2\u0168"+
		"=\3\2\2\2\u0169\u016c\5<\37\2\u016a\u016b\7\63\2\2\u016b\u016d\7\64\2"+
		"\2\u016c\u016a\3\2\2\2\u016d\u016e\3\2\2\2\u016e\u016c\3\2\2\2\u016e\u016f"+
		"\3\2\2\2\u016f?\3\2\2\2\u0170\u0171\t\b\2\2\u0171A\3\2\2\2\u0172\u0173"+
		"\7\67\2\2\u0173C\3\2\2\2\u0174\u0179\7\23\2\2\u0175\u0179\7\24\2\2\u0176"+
		"\u0179\7\27\2\2\u0177\u0179\7\30\2\2\u0178\u0174\3\2\2\2\u0178\u0175\3"+
		"\2\2\2\u0178\u0176\3\2\2\2\u0178\u0177\3\2\2\2\u0179E\3\2\2\2&IRZbju~"+
		"\u0081\u0088\u008b\u0096\u009b\u00b7\u00ba\u00be\u00c2\u00c8\u00ce\u00d7"+
		"\u00db\u00e2\u00e6\u00fb\u0105\u0107\u0122\u0127\u012b\u012d\u012f\u015c"+
		"\u015e\u0163\u0167\u016e\u0178";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}