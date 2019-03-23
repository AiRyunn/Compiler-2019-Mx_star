// Generated from /home/espylapiza/Documents/Lectures/Compiler/Compiler-2019-Mx_star/src/Mx_star/AST/Mx_star.g4 by ANTLR 4.7.1

    package Mx_star.AST;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Mx_starLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "New", "Bool", "Int", "Void", "String", "Class", "This", 
		"If", "Else", "For", "While", "Return", "Break", "Continue", "Null", "LogicalConstant", 
		"True", "False", "IntegerConstant", "StringLiteral", "Add", "Sub", "Mul", 
		"Div", "Mod", "Less", "Greater", "Equal", "Unequal", "LessEqual", "GreaterEqual", 
		"LogicalNot", "LogicalAnd", "LogicalOr", "SHL", "SHR", "BitInversion", 
		"BitAnd", "BitOr", "BitXor", "Assign", "Increment", "Decrement", "MemberAccess", 
		"LeftRoundBracket", "RightRoundBracket", "LeftSquareBracket", "RightSquareBracket", 
		"LeftBrace", "RightBrace", "Identifier", "Digit", "Zero", "NonzeroDigit", 
		"DecimalConstant", "LowercaseLetter", "UppercaseLetter", "Letter", "IdentifierStartChar", 
		"IdentifierChar", "Underline", "PrintableCharacter", "EscapeCharacter", 
		"Character", "CharacterSequence", "Whitespace", "Newline", "BlockComment", 
		"LineComment"
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


	public Mx_starLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Mx_star.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2;\u01a7\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\3\2"+
		"\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3"+
		"\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n"+
		"\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\22\3\22\3\22\3\22\3\22\3\23\3\23\5\23\u00e9\n\23\3\24\3\24\3\24\3\24"+
		"\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\27\3\27\5\27\u00fa\n\27"+
		"\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35"+
		"\3\36\3\36\3\37\3\37\3\37\3 \3 \3 \3!\3!\3!\3\"\3\"\3\"\3#\3#\3$\3$\3"+
		"$\3%\3%\3%\3&\3&\3&\3\'\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3"+
		"-\3.\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\63\3\63\3\64\3\64\3\65"+
		"\3\65\3\66\3\66\7\66\u0146\n\66\f\66\16\66\u0149\13\66\3\67\3\67\38\3"+
		"8\39\39\3:\3:\3:\7:\u0154\n:\f:\16:\u0157\13:\5:\u0159\n:\3;\3;\3<\3<"+
		"\3=\3=\5=\u0161\n=\3>\3>\5>\u0165\n>\3?\3?\3?\5?\u016a\n?\3@\3@\3A\3A"+
		"\3B\3B\3B\3B\3B\3B\5B\u0176\nB\3C\3C\3C\5C\u017b\nC\3D\6D\u017e\nD\rD"+
		"\16D\u017f\3E\6E\u0183\nE\rE\16E\u0184\3E\3E\3F\3F\3F\3F\3G\3G\3G\3G\7"+
		"G\u0191\nG\fG\16G\u0194\13G\3G\3G\3G\3G\3G\3H\3H\3H\3H\7H\u019f\nH\fH"+
		"\16H\u01a2\13H\3H\3H\3H\3H\5\u017f\u0192\u01a0\2I\3\3\5\4\7\5\t\6\13\7"+
		"\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25"+
		")\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O"+
		")Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m\2o\2q\2s\2u\2w\2y\2{\2"+
		"}\2\177\2\u0081\2\u0083\2\u0085\2\u0087\2\u00898\u008b9\u008d:\u008f;"+
		"\3\2\7\3\2\62;\3\2\63;\4\2#]_\u0080\4\2\13\13\"\"\4\2\f\f\17\17\2\u01a9"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2"+
		"\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2"+
		"U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3"+
		"\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2\u0089"+
		"\3\2\2\2\2\u008b\3\2\2\2\2\u008d\3\2\2\2\2\u008f\3\2\2\2\3\u0091\3\2\2"+
		"\2\5\u0093\3\2\2\2\7\u0095\3\2\2\2\t\u0099\3\2\2\2\13\u009e\3\2\2\2\r"+
		"\u00a2\3\2\2\2\17\u00a7\3\2\2\2\21\u00ae\3\2\2\2\23\u00b4\3\2\2\2\25\u00b9"+
		"\3\2\2\2\27\u00bc\3\2\2\2\31\u00c1\3\2\2\2\33\u00c5\3\2\2\2\35\u00cb\3"+
		"\2\2\2\37\u00d2\3\2\2\2!\u00d8\3\2\2\2#\u00e1\3\2\2\2%\u00e8\3\2\2\2\'"+
		"\u00ea\3\2\2\2)\u00ef\3\2\2\2+\u00f5\3\2\2\2-\u00f7\3\2\2\2/\u00fd\3\2"+
		"\2\2\61\u00ff\3\2\2\2\63\u0101\3\2\2\2\65\u0103\3\2\2\2\67\u0105\3\2\2"+
		"\29\u0107\3\2\2\2;\u0109\3\2\2\2=\u010b\3\2\2\2?\u010e\3\2\2\2A\u0111"+
		"\3\2\2\2C\u0114\3\2\2\2E\u0117\3\2\2\2G\u0119\3\2\2\2I\u011c\3\2\2\2K"+
		"\u011f\3\2\2\2M\u0122\3\2\2\2O\u0125\3\2\2\2Q\u0127\3\2\2\2S\u0129\3\2"+
		"\2\2U\u012b\3\2\2\2W\u012d\3\2\2\2Y\u012f\3\2\2\2[\u0132\3\2\2\2]\u0135"+
		"\3\2\2\2_\u0137\3\2\2\2a\u0139\3\2\2\2c\u013b\3\2\2\2e\u013d\3\2\2\2g"+
		"\u013f\3\2\2\2i\u0141\3\2\2\2k\u0143\3\2\2\2m\u014a\3\2\2\2o\u014c\3\2"+
		"\2\2q\u014e\3\2\2\2s\u0158\3\2\2\2u\u015a\3\2\2\2w\u015c\3\2\2\2y\u0160"+
		"\3\2\2\2{\u0164\3\2\2\2}\u0169\3\2\2\2\177\u016b\3\2\2\2\u0081\u016d\3"+
		"\2\2\2\u0083\u0175\3\2\2\2\u0085\u017a\3\2\2\2\u0087\u017d\3\2\2\2\u0089"+
		"\u0182\3\2\2\2\u008b\u0188\3\2\2\2\u008d\u018c\3\2\2\2\u008f\u019a\3\2"+
		"\2\2\u0091\u0092\7.\2\2\u0092\4\3\2\2\2\u0093\u0094\7=\2\2\u0094\6\3\2"+
		"\2\2\u0095\u0096\7p\2\2\u0096\u0097\7g\2\2\u0097\u0098\7y\2\2\u0098\b"+
		"\3\2\2\2\u0099\u009a\7d\2\2\u009a\u009b\7q\2\2\u009b\u009c\7q\2\2\u009c"+
		"\u009d\7n\2\2\u009d\n\3\2\2\2\u009e\u009f\7k\2\2\u009f\u00a0\7p\2\2\u00a0"+
		"\u00a1\7v\2\2\u00a1\f\3\2\2\2\u00a2\u00a3\7x\2\2\u00a3\u00a4\7q\2\2\u00a4"+
		"\u00a5\7k\2\2\u00a5\u00a6\7f\2\2\u00a6\16\3\2\2\2\u00a7\u00a8\7u\2\2\u00a8"+
		"\u00a9\7v\2\2\u00a9\u00aa\7t\2\2\u00aa\u00ab\7k\2\2\u00ab\u00ac\7p\2\2"+
		"\u00ac\u00ad\7i\2\2\u00ad\20\3\2\2\2\u00ae\u00af\7e\2\2\u00af\u00b0\7"+
		"n\2\2\u00b0\u00b1\7c\2\2\u00b1\u00b2\7u\2\2\u00b2\u00b3\7u\2\2\u00b3\22"+
		"\3\2\2\2\u00b4\u00b5\7v\2\2\u00b5\u00b6\7j\2\2\u00b6\u00b7\7k\2\2\u00b7"+
		"\u00b8\7u\2\2\u00b8\24\3\2\2\2\u00b9\u00ba\7k\2\2\u00ba\u00bb\7h\2\2\u00bb"+
		"\26\3\2\2\2\u00bc\u00bd\7g\2\2\u00bd\u00be\7n\2\2\u00be\u00bf\7u\2\2\u00bf"+
		"\u00c0\7g\2\2\u00c0\30\3\2\2\2\u00c1\u00c2\7h\2\2\u00c2\u00c3\7q\2\2\u00c3"+
		"\u00c4\7t\2\2\u00c4\32\3\2\2\2\u00c5\u00c6\7y\2\2\u00c6\u00c7\7j\2\2\u00c7"+
		"\u00c8\7k\2\2\u00c8\u00c9\7n\2\2\u00c9\u00ca\7g\2\2\u00ca\34\3\2\2\2\u00cb"+
		"\u00cc\7t\2\2\u00cc\u00cd\7g\2\2\u00cd\u00ce\7v\2\2\u00ce\u00cf\7w\2\2"+
		"\u00cf\u00d0\7t\2\2\u00d0\u00d1\7p\2\2\u00d1\36\3\2\2\2\u00d2\u00d3\7"+
		"d\2\2\u00d3\u00d4\7t\2\2\u00d4\u00d5\7g\2\2\u00d5\u00d6\7c\2\2\u00d6\u00d7"+
		"\7m\2\2\u00d7 \3\2\2\2\u00d8\u00d9\7e\2\2\u00d9\u00da\7q\2\2\u00da\u00db"+
		"\7p\2\2\u00db\u00dc\7v\2\2\u00dc\u00dd\7k\2\2\u00dd\u00de\7p\2\2\u00de"+
		"\u00df\7w\2\2\u00df\u00e0\7g\2\2\u00e0\"\3\2\2\2\u00e1\u00e2\7p\2\2\u00e2"+
		"\u00e3\7w\2\2\u00e3\u00e4\7n\2\2\u00e4\u00e5\7n\2\2\u00e5$\3\2\2\2\u00e6"+
		"\u00e9\5\'\24\2\u00e7\u00e9\5)\25\2\u00e8\u00e6\3\2\2\2\u00e8\u00e7\3"+
		"\2\2\2\u00e9&\3\2\2\2\u00ea\u00eb\7v\2\2\u00eb\u00ec\7t\2\2\u00ec\u00ed"+
		"\7w\2\2\u00ed\u00ee\7g\2\2\u00ee(\3\2\2\2\u00ef\u00f0\7h\2\2\u00f0\u00f1"+
		"\7c\2\2\u00f1\u00f2\7n\2\2\u00f2\u00f3\7u\2\2\u00f3\u00f4\7g\2\2\u00f4"+
		"*\3\2\2\2\u00f5\u00f6\5s:\2\u00f6,\3\2\2\2\u00f7\u00f9\7$\2\2\u00f8\u00fa"+
		"\5\u0087D\2\u00f9\u00f8\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\u00fb\3\2\2"+
		"\2\u00fb\u00fc\7$\2\2\u00fc.\3\2\2\2\u00fd\u00fe\7-\2\2\u00fe\60\3\2\2"+
		"\2\u00ff\u0100\7/\2\2\u0100\62\3\2\2\2\u0101\u0102\7,\2\2\u0102\64\3\2"+
		"\2\2\u0103\u0104\7\61\2\2\u0104\66\3\2\2\2\u0105\u0106\7\'\2\2\u01068"+
		"\3\2\2\2\u0107\u0108\7>\2\2\u0108:\3\2\2\2\u0109\u010a\7@\2\2\u010a<\3"+
		"\2\2\2\u010b\u010c\7?\2\2\u010c\u010d\7?\2\2\u010d>\3\2\2\2\u010e\u010f"+
		"\7#\2\2\u010f\u0110\7?\2\2\u0110@\3\2\2\2\u0111\u0112\7>\2\2\u0112\u0113"+
		"\7?\2\2\u0113B\3\2\2\2\u0114\u0115\7@\2\2\u0115\u0116\7?\2\2\u0116D\3"+
		"\2\2\2\u0117\u0118\7#\2\2\u0118F\3\2\2\2\u0119\u011a\7(\2\2\u011a\u011b"+
		"\7(\2\2\u011bH\3\2\2\2\u011c\u011d\7~\2\2\u011d\u011e\7~\2\2\u011eJ\3"+
		"\2\2\2\u011f\u0120\7>\2\2\u0120\u0121\7>\2\2\u0121L\3\2\2\2\u0122\u0123"+
		"\7@\2\2\u0123\u0124\7@\2\2\u0124N\3\2\2\2\u0125\u0126\7\u0080\2\2\u0126"+
		"P\3\2\2\2\u0127\u0128\7(\2\2\u0128R\3\2\2\2\u0129\u012a\7~\2\2\u012aT"+
		"\3\2\2\2\u012b\u012c\7`\2\2\u012cV\3\2\2\2\u012d\u012e\7?\2\2\u012eX\3"+
		"\2\2\2\u012f\u0130\7-\2\2\u0130\u0131\7-\2\2\u0131Z\3\2\2\2\u0132\u0133"+
		"\7/\2\2\u0133\u0134\7/\2\2\u0134\\\3\2\2\2\u0135\u0136\7\60\2\2\u0136"+
		"^\3\2\2\2\u0137\u0138\7*\2\2\u0138`\3\2\2\2\u0139\u013a\7+\2\2\u013ab"+
		"\3\2\2\2\u013b\u013c\7]\2\2\u013cd\3\2\2\2\u013d\u013e\7_\2\2\u013ef\3"+
		"\2\2\2\u013f\u0140\7}\2\2\u0140h\3\2\2\2\u0141\u0142\7\177\2\2\u0142j"+
		"\3\2\2\2\u0143\u0147\5{>\2\u0144\u0146\5}?\2\u0145\u0144\3\2\2\2\u0146"+
		"\u0149\3\2\2\2\u0147\u0145\3\2\2\2\u0147\u0148\3\2\2\2\u0148l\3\2\2\2"+
		"\u0149\u0147\3\2\2\2\u014a\u014b\t\2\2\2\u014bn\3\2\2\2\u014c\u014d\7"+
		"\62\2\2\u014dp\3\2\2\2\u014e\u014f\t\3\2\2\u014fr\3\2\2\2\u0150\u0159"+
		"\5o8\2\u0151\u0155\5q9\2\u0152\u0154\5m\67\2\u0153\u0152\3\2\2\2\u0154"+
		"\u0157\3\2\2\2\u0155\u0153\3\2\2\2\u0155\u0156\3\2\2\2\u0156\u0159\3\2"+
		"\2\2\u0157\u0155\3\2\2\2\u0158\u0150\3\2\2\2\u0158\u0151\3\2\2\2\u0159"+
		"t\3\2\2\2\u015a\u015b\4c|\2\u015bv\3\2\2\2\u015c\u015d\4C\\\2\u015dx\3"+
		"\2\2\2\u015e\u0161\5u;\2\u015f\u0161\5w<\2\u0160\u015e\3\2\2\2\u0160\u015f"+
		"\3\2\2\2\u0161z\3\2\2\2\u0162\u0165\5y=\2\u0163\u0165\5\177@\2\u0164\u0162"+
		"\3\2\2\2\u0164\u0163\3\2\2\2\u0165|\3\2\2\2\u0166\u016a\5y=\2\u0167\u016a"+
		"\5m\67\2\u0168\u016a\5\177@\2\u0169\u0166\3\2\2\2\u0169\u0167\3\2\2\2"+
		"\u0169\u0168\3\2\2\2\u016a~\3\2\2\2\u016b\u016c\7a\2\2\u016c\u0080\3\2"+
		"\2\2\u016d\u016e\t\4\2\2\u016e\u0082\3\2\2\2\u016f\u0170\7^\2\2\u0170"+
		"\u0176\7p\2\2\u0171\u0172\7^\2\2\u0172\u0176\7^\2\2\u0173\u0174\7^\2\2"+
		"\u0174\u0176\7$\2\2\u0175\u016f\3\2\2\2\u0175\u0171\3\2\2\2\u0175\u0173"+
		"\3\2\2\2\u0176\u0084\3\2\2\2\u0177\u017b\5\u0081A\2\u0178\u017b\5\u0089"+
		"E\2\u0179\u017b\5\u0083B\2\u017a\u0177\3\2\2\2\u017a\u0178\3\2\2\2\u017a"+
		"\u0179\3\2\2\2\u017b\u0086\3\2\2\2\u017c\u017e\5\u0085C\2\u017d\u017c"+
		"\3\2\2\2\u017e\u017f\3\2\2\2\u017f\u0180\3\2\2\2\u017f\u017d\3\2\2\2\u0180"+
		"\u0088\3\2\2\2\u0181\u0183\t\5\2\2\u0182\u0181\3\2\2\2\u0183\u0184\3\2"+
		"\2\2\u0184\u0182\3\2\2\2\u0184\u0185\3\2\2\2\u0185\u0186\3\2\2\2\u0186"+
		"\u0187\bE\2\2\u0187\u008a\3\2\2\2\u0188\u0189\t\6\2\2\u0189\u018a\3\2"+
		"\2\2\u018a\u018b\bF\2\2\u018b\u008c\3\2\2\2\u018c\u018d\7\61\2\2\u018d"+
		"\u018e\7,\2\2\u018e\u0192\3\2\2\2\u018f\u0191\13\2\2\2\u0190\u018f\3\2"+
		"\2\2\u0191\u0194\3\2\2\2\u0192\u0193\3\2\2\2\u0192\u0190\3\2\2\2\u0193"+
		"\u0195\3\2\2\2\u0194\u0192\3\2\2\2\u0195\u0196\7,\2\2\u0196\u0197\7\61"+
		"\2\2\u0197\u0198\3\2\2\2\u0198\u0199\bG\2\2\u0199\u008e\3\2\2\2\u019a"+
		"\u019b\7\61\2\2\u019b\u019c\7\61\2\2\u019c\u01a0\3\2\2\2\u019d\u019f\13"+
		"\2\2\2\u019e\u019d\3\2\2\2\u019f\u01a2\3\2\2\2\u01a0\u01a1\3\2\2\2\u01a0"+
		"\u019e\3\2\2\2\u01a1\u01a3\3\2\2\2\u01a2\u01a0\3\2\2\2\u01a3\u01a4\t\6"+
		"\2\2\u01a4\u01a5\3\2\2\2\u01a5\u01a6\bH\2\2\u01a6\u0090\3\2\2\2\21\2\u00e8"+
		"\u00f9\u0147\u0155\u0158\u0160\u0164\u0169\u0175\u017a\u017f\u0184\u0192"+
		"\u01a0\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}