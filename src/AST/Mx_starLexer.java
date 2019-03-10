// Generated from Mx_star.g4 by ANTLR 4.7.1
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
		If=10, Else=11, For=12, While=13, Return=14, Break=15, Continue=16, Constant=17, 
		Null=18, True=19, False=20, LogicalConstant=21, IntegerConstant=22, StringLiteral=23, 
		Add=24, Sub=25, Mul=26, Div=27, Mod=28, Less=29, Greater=30, Equal=31, 
		Unequal=32, LessEqual=33, GreaterEqual=34, LogicalNot=35, LogicalAnd=36, 
		LogicalOr=37, ShiftLeft=38, ShiftRight=39, BitInversion=40, BitAnd=41, 
		BitOr=42, BitXor=43, Assign=44, Increment=45, Decrement=46, MemberAccess=47, 
		LeftRoundBracket=48, RightRoundBracket=49, LeftSquareBracket=50, RightSquareBracket=51, 
		LeftBrace=52, RightBrace=53, Identifier=54, Whitespace=55, Newline=56, 
		BlockComment=57, LineComment=58;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "New", "Bool", "Int", "Void", "String", "Class", "This", 
		"If", "Else", "For", "While", "Return", "Break", "Continue", "Constant", 
		"Null", "True", "False", "LogicalConstant", "IntegerConstant", "StringLiteral", 
		"Add", "Sub", "Mul", "Div", "Mod", "Less", "Greater", "Equal", "Unequal", 
		"LessEqual", "GreaterEqual", "LogicalNot", "LogicalAnd", "LogicalOr", 
		"ShiftLeft", "ShiftRight", "BitInversion", "BitAnd", "BitOr", "BitXor", 
		"Assign", "Increment", "Decrement", "MemberAccess", "LeftRoundBracket", 
		"RightRoundBracket", "LeftSquareBracket", "RightSquareBracket", "LeftBrace", 
		"RightBrace", "Identifier", "Digit", "Zero", "NonzeroDigit", "DecimalConstant", 
		"LowercaseLetter", "UppercaseLetter", "Letter", "IdentifierStartChar", 
		"IdentifierChar", "Underline", "PrintableCharacter", "EscapeCharacter", 
		"Character", "CharacterSequence", "Whitespace", "Newline", "BlockComment", 
		"LineComment"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2<\u01ad\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3"+
		"\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3"+
		"\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\22\3\22\3\22\3\22\5\22\u00e8\n\22\3\23\3\23\3\23\3\23\3\23\3\24"+
		"\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\5\26\u00fc"+
		"\n\26\3\27\3\27\3\30\3\30\5\30\u0102\n\30\3\30\3\30\3\31\3\31\3\32\3\32"+
		"\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3 \3!\3!\3!\3"+
		"\"\3\"\3\"\3#\3#\3#\3$\3$\3%\3%\3%\3&\3&\3&\3\'\3\'\3\'\3(\3(\3(\3)\3"+
		")\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3.\3/\3/\3/\3\60\3\60\3\61\3\61\3\62\3"+
		"\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66\3\67\3\67\7\67\u014e\n\67"+
		"\f\67\16\67\u0151\13\67\38\38\39\39\3:\3:\3;\3;\3;\7;\u015c\n;\f;\16;"+
		"\u015f\13;\5;\u0161\n;\3<\3<\3=\3=\3>\3>\5>\u0169\n>\3?\3?\3@\3@\3@\5"+
		"@\u0170\n@\3A\3A\3B\3B\3C\3C\3C\3C\3C\3C\5C\u017c\nC\3D\3D\3D\5D\u0181"+
		"\nD\3E\6E\u0184\nE\rE\16E\u0185\3F\6F\u0189\nF\rF\16F\u018a\3F\3F\3G\3"+
		"G\3G\3G\3H\3H\3H\3H\7H\u0197\nH\fH\16H\u019a\13H\3H\3H\3H\3H\3H\3I\3I"+
		"\3I\3I\7I\u01a5\nI\fI\16I\u01a8\13I\3I\3I\3I\3I\4\u0198\u01a6\2J\3\3\5"+
		"\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!"+
		"A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o\2q\2"+
		"s\2u\2w\2y\2{\2}\2\177\2\u0081\2\u0083\2\u0085\2\u0087\2\u0089\2\u008b"+
		"9\u008d:\u008f;\u0091<\3\2\6\3\2\62;\3\2\63;\4\2\13\13\"\"\4\2\f\f\17"+
		"\17\2\u01b1\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2"+
		"\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2"+
		"\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2"+
		"\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S"+
		"\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2"+
		"\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2"+
		"\2m\3\2\2\2\2\u008b\3\2\2\2\2\u008d\3\2\2\2\2\u008f\3\2\2\2\2\u0091\3"+
		"\2\2\2\3\u0093\3\2\2\2\5\u0095\3\2\2\2\7\u0097\3\2\2\2\t\u009b\3\2\2\2"+
		"\13\u00a0\3\2\2\2\r\u00a4\3\2\2\2\17\u00a9\3\2\2\2\21\u00b0\3\2\2\2\23"+
		"\u00b6\3\2\2\2\25\u00bb\3\2\2\2\27\u00be\3\2\2\2\31\u00c3\3\2\2\2\33\u00c7"+
		"\3\2\2\2\35\u00cd\3\2\2\2\37\u00d4\3\2\2\2!\u00da\3\2\2\2#\u00e7\3\2\2"+
		"\2%\u00e9\3\2\2\2\'\u00ee\3\2\2\2)\u00f3\3\2\2\2+\u00fb\3\2\2\2-\u00fd"+
		"\3\2\2\2/\u00ff\3\2\2\2\61\u0105\3\2\2\2\63\u0107\3\2\2\2\65\u0109\3\2"+
		"\2\2\67\u010b\3\2\2\29\u010d\3\2\2\2;\u010f\3\2\2\2=\u0111\3\2\2\2?\u0113"+
		"\3\2\2\2A\u0116\3\2\2\2C\u0119\3\2\2\2E\u011c\3\2\2\2G\u011f\3\2\2\2I"+
		"\u0121\3\2\2\2K\u0124\3\2\2\2M\u0127\3\2\2\2O\u012a\3\2\2\2Q\u012d\3\2"+
		"\2\2S\u012f\3\2\2\2U\u0131\3\2\2\2W\u0133\3\2\2\2Y\u0135\3\2\2\2[\u0137"+
		"\3\2\2\2]\u013a\3\2\2\2_\u013d\3\2\2\2a\u013f\3\2\2\2c\u0141\3\2\2\2e"+
		"\u0143\3\2\2\2g\u0145\3\2\2\2i\u0147\3\2\2\2k\u0149\3\2\2\2m\u014b\3\2"+
		"\2\2o\u0152\3\2\2\2q\u0154\3\2\2\2s\u0156\3\2\2\2u\u0160\3\2\2\2w\u0162"+
		"\3\2\2\2y\u0164\3\2\2\2{\u0168\3\2\2\2}\u016a\3\2\2\2\177\u016f\3\2\2"+
		"\2\u0081\u0171\3\2\2\2\u0083\u0173\3\2\2\2\u0085\u017b\3\2\2\2\u0087\u0180"+
		"\3\2\2\2\u0089\u0183\3\2\2\2\u008b\u0188\3\2\2\2\u008d\u018e\3\2\2\2\u008f"+
		"\u0192\3\2\2\2\u0091\u01a0\3\2\2\2\u0093\u0094\7=\2\2\u0094\4\3\2\2\2"+
		"\u0095\u0096\7.\2\2\u0096\6\3\2\2\2\u0097\u0098\7p\2\2\u0098\u0099\7g"+
		"\2\2\u0099\u009a\7y\2\2\u009a\b\3\2\2\2\u009b\u009c\7d\2\2\u009c\u009d"+
		"\7q\2\2\u009d\u009e\7q\2\2\u009e\u009f\7n\2\2\u009f\n\3\2\2\2\u00a0\u00a1"+
		"\7k\2\2\u00a1\u00a2\7p\2\2\u00a2\u00a3\7v\2\2\u00a3\f\3\2\2\2\u00a4\u00a5"+
		"\7x\2\2\u00a5\u00a6\7q\2\2\u00a6\u00a7\7k\2\2\u00a7\u00a8\7f\2\2\u00a8"+
		"\16\3\2\2\2\u00a9\u00aa\7u\2\2\u00aa\u00ab\7v\2\2\u00ab\u00ac\7t\2\2\u00ac"+
		"\u00ad\7k\2\2\u00ad\u00ae\7p\2\2\u00ae\u00af\7i\2\2\u00af\20\3\2\2\2\u00b0"+
		"\u00b1\7e\2\2\u00b1\u00b2\7n\2\2\u00b2\u00b3\7c\2\2\u00b3\u00b4\7u\2\2"+
		"\u00b4\u00b5\7u\2\2\u00b5\22\3\2\2\2\u00b6\u00b7\7v\2\2\u00b7\u00b8\7"+
		"j\2\2\u00b8\u00b9\7k\2\2\u00b9\u00ba\7u\2\2\u00ba\24\3\2\2\2\u00bb\u00bc"+
		"\7k\2\2\u00bc\u00bd\7h\2\2\u00bd\26\3\2\2\2\u00be\u00bf\7g\2\2\u00bf\u00c0"+
		"\7n\2\2\u00c0\u00c1\7u\2\2\u00c1\u00c2\7g\2\2\u00c2\30\3\2\2\2\u00c3\u00c4"+
		"\7h\2\2\u00c4\u00c5\7q\2\2\u00c5\u00c6\7t\2\2\u00c6\32\3\2\2\2\u00c7\u00c8"+
		"\7y\2\2\u00c8\u00c9\7j\2\2\u00c9\u00ca\7k\2\2\u00ca\u00cb\7n\2\2\u00cb"+
		"\u00cc\7g\2\2\u00cc\34\3\2\2\2\u00cd\u00ce\7t\2\2\u00ce\u00cf\7g\2\2\u00cf"+
		"\u00d0\7v\2\2\u00d0\u00d1\7w\2\2\u00d1\u00d2\7t\2\2\u00d2\u00d3\7p\2\2"+
		"\u00d3\36\3\2\2\2\u00d4\u00d5\7d\2\2\u00d5\u00d6\7t\2\2\u00d6\u00d7\7"+
		"g\2\2\u00d7\u00d8\7c\2\2\u00d8\u00d9\7m\2\2\u00d9 \3\2\2\2\u00da\u00db"+
		"\7e\2\2\u00db\u00dc\7q\2\2\u00dc\u00dd\7p\2\2\u00dd\u00de\7v\2\2\u00de"+
		"\u00df\7k\2\2\u00df\u00e0\7p\2\2\u00e0\u00e1\7w\2\2\u00e1\u00e2\7g\2\2"+
		"\u00e2\"\3\2\2\2\u00e3\u00e8\5%\23\2\u00e4\u00e8\5+\26\2\u00e5\u00e8\5"+
		"-\27\2\u00e6\u00e8\5/\30\2\u00e7\u00e3\3\2\2\2\u00e7\u00e4\3\2\2\2\u00e7"+
		"\u00e5\3\2\2\2\u00e7\u00e6\3\2\2\2\u00e8$\3\2\2\2\u00e9\u00ea\7p\2\2\u00ea"+
		"\u00eb\7w\2\2\u00eb\u00ec\7n\2\2\u00ec\u00ed\7n\2\2\u00ed&\3\2\2\2\u00ee"+
		"\u00ef\7v\2\2\u00ef\u00f0\7t\2\2\u00f0\u00f1\7w\2\2\u00f1\u00f2\7g\2\2"+
		"\u00f2(\3\2\2\2\u00f3\u00f4\7h\2\2\u00f4\u00f5\7c\2\2\u00f5\u00f6\7n\2"+
		"\2\u00f6\u00f7\7u\2\2\u00f7\u00f8\7g\2\2\u00f8*\3\2\2\2\u00f9\u00fc\5"+
		"\'\24\2\u00fa\u00fc\5)\25\2\u00fb\u00f9\3\2\2\2\u00fb\u00fa\3\2\2\2\u00fc"+
		",\3\2\2\2\u00fd\u00fe\5u;\2\u00fe.\3\2\2\2\u00ff\u0101\7$\2\2\u0100\u0102"+
		"\5\u0089E\2\u0101\u0100\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0103\3\2\2"+
		"\2\u0103\u0104\7$\2\2\u0104\60\3\2\2\2\u0105\u0106\7-\2\2\u0106\62\3\2"+
		"\2\2\u0107\u0108\7/\2\2\u0108\64\3\2\2\2\u0109\u010a\7,\2\2\u010a\66\3"+
		"\2\2\2\u010b\u010c\7\61\2\2\u010c8\3\2\2\2\u010d\u010e\7\'\2\2\u010e:"+
		"\3\2\2\2\u010f\u0110\7>\2\2\u0110<\3\2\2\2\u0111\u0112\7@\2\2\u0112>\3"+
		"\2\2\2\u0113\u0114\7?\2\2\u0114\u0115\7?\2\2\u0115@\3\2\2\2\u0116\u0117"+
		"\7#\2\2\u0117\u0118\7?\2\2\u0118B\3\2\2\2\u0119\u011a\7>\2\2\u011a\u011b"+
		"\7?\2\2\u011bD\3\2\2\2\u011c\u011d\7@\2\2\u011d\u011e\7?\2\2\u011eF\3"+
		"\2\2\2\u011f\u0120\7#\2\2\u0120H\3\2\2\2\u0121\u0122\7(\2\2\u0122\u0123"+
		"\7(\2\2\u0123J\3\2\2\2\u0124\u0125\7~\2\2\u0125\u0126\7~\2\2\u0126L\3"+
		"\2\2\2\u0127\u0128\7>\2\2\u0128\u0129\7>\2\2\u0129N\3\2\2\2\u012a\u012b"+
		"\7@\2\2\u012b\u012c\7@\2\2\u012cP\3\2\2\2\u012d\u012e\7\u0080\2\2\u012e"+
		"R\3\2\2\2\u012f\u0130\7(\2\2\u0130T\3\2\2\2\u0131\u0132\7~\2\2\u0132V"+
		"\3\2\2\2\u0133\u0134\7`\2\2\u0134X\3\2\2\2\u0135\u0136\7?\2\2\u0136Z\3"+
		"\2\2\2\u0137\u0138\7-\2\2\u0138\u0139\7-\2\2\u0139\\\3\2\2\2\u013a\u013b"+
		"\7/\2\2\u013b\u013c\7/\2\2\u013c^\3\2\2\2\u013d\u013e\7\60\2\2\u013e`"+
		"\3\2\2\2\u013f\u0140\7*\2\2\u0140b\3\2\2\2\u0141\u0142\7+\2\2\u0142d\3"+
		"\2\2\2\u0143\u0144\7]\2\2\u0144f\3\2\2\2\u0145\u0146\7_\2\2\u0146h\3\2"+
		"\2\2\u0147\u0148\7}\2\2\u0148j\3\2\2\2\u0149\u014a\7\177\2\2\u014al\3"+
		"\2\2\2\u014b\u014f\5}?\2\u014c\u014e\5\177@\2\u014d\u014c\3\2\2\2\u014e"+
		"\u0151\3\2\2\2\u014f\u014d\3\2\2\2\u014f\u0150\3\2\2\2\u0150n\3\2\2\2"+
		"\u0151\u014f\3\2\2\2\u0152\u0153\t\2\2\2\u0153p\3\2\2\2\u0154\u0155\7"+
		"\62\2\2\u0155r\3\2\2\2\u0156\u0157\t\3\2\2\u0157t\3\2\2\2\u0158\u0161"+
		"\5q9\2\u0159\u015d\5s:\2\u015a\u015c\5o8\2\u015b\u015a\3\2\2\2\u015c\u015f"+
		"\3\2\2\2\u015d\u015b\3\2\2\2\u015d\u015e\3\2\2\2\u015e\u0161\3\2\2\2\u015f"+
		"\u015d\3\2\2\2\u0160\u0158\3\2\2\2\u0160\u0159\3\2\2\2\u0161v\3\2\2\2"+
		"\u0162\u0163\4c|\2\u0163x\3\2\2\2\u0164\u0165\4C\\\2\u0165z\3\2\2\2\u0166"+
		"\u0169\5w<\2\u0167\u0169\5y=\2\u0168\u0166\3\2\2\2\u0168\u0167\3\2\2\2"+
		"\u0169|\3\2\2\2\u016a\u016b\5{>\2\u016b~\3\2\2\2\u016c\u0170\5{>\2\u016d"+
		"\u0170\5o8\2\u016e\u0170\5\u0081A\2\u016f\u016c\3\2\2\2\u016f\u016d\3"+
		"\2\2\2\u016f\u016e\3\2\2\2\u0170\u0080\3\2\2\2\u0171\u0172\7a\2\2\u0172"+
		"\u0082\3\2\2\2\u0173\u0174\4#\u0080\2\u0174\u0084\3\2\2\2\u0175\u0176"+
		"\7^\2\2\u0176\u017c\7p\2\2\u0177\u0178\7^\2\2\u0178\u017c\7^\2\2\u0179"+
		"\u017a\7^\2\2\u017a\u017c\7$\2\2\u017b\u0175\3\2\2\2\u017b\u0177\3\2\2"+
		"\2\u017b\u0179\3\2\2\2\u017c\u0086\3\2\2\2\u017d\u0181\5\u0083B\2\u017e"+
		"\u0181\5\u008bF\2\u017f\u0181\5\u0085C\2\u0180\u017d\3\2\2\2\u0180\u017e"+
		"\3\2\2\2\u0180\u017f\3\2\2\2\u0181\u0088\3\2\2\2\u0182\u0184\5\u0087D"+
		"\2\u0183\u0182\3\2\2\2\u0184\u0185\3\2\2\2\u0185\u0183\3\2\2\2\u0185\u0186"+
		"\3\2\2\2\u0186\u008a\3\2\2\2\u0187\u0189\t\4\2\2\u0188\u0187\3\2\2\2\u0189"+
		"\u018a\3\2\2\2\u018a\u0188\3\2\2\2\u018a\u018b\3\2\2\2\u018b\u018c\3\2"+
		"\2\2\u018c\u018d\bF\2\2\u018d\u008c\3\2\2\2\u018e\u018f\t\5\2\2\u018f"+
		"\u0190\3\2\2\2\u0190\u0191\bG\2\2\u0191\u008e\3\2\2\2\u0192\u0193\7\61"+
		"\2\2\u0193\u0194\7,\2\2\u0194\u0198\3\2\2\2\u0195\u0197\13\2\2\2\u0196"+
		"\u0195\3\2\2\2\u0197\u019a\3\2\2\2\u0198\u0199\3\2\2\2\u0198\u0196\3\2"+
		"\2\2\u0199\u019b\3\2\2\2\u019a\u0198\3\2\2\2\u019b\u019c\7,\2\2\u019c"+
		"\u019d\7\61\2\2\u019d\u019e\3\2\2\2\u019e\u019f\bH\2\2\u019f\u0090\3\2"+
		"\2\2\u01a0\u01a1\7\61\2\2\u01a1\u01a2\7\61\2\2\u01a2\u01a6\3\2\2\2\u01a3"+
		"\u01a5\13\2\2\2\u01a4\u01a3\3\2\2\2\u01a5\u01a8\3\2\2\2\u01a6\u01a7\3"+
		"\2\2\2\u01a6\u01a4\3\2\2\2\u01a7\u01a9\3\2\2\2\u01a8\u01a6\3\2\2\2\u01a9"+
		"\u01aa\t\5\2\2\u01aa\u01ab\3\2\2\2\u01ab\u01ac\bI\2\2\u01ac\u0092\3\2"+
		"\2\2\21\2\u00e7\u00fb\u0101\u014f\u015d\u0160\u0168\u016f\u017b\u0180"+
		"\u0185\u018a\u0198\u01a6\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}