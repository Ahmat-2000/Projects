// Generated from MVaP.g4 by ANTLR 4.7.2

    import java.util.HashMap;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MVaPLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, ENTIER=2, FLOAT=3, ADD=4, SUB=5, MUL=6, DIV=7, INF=8, INFEQ=9, SUP=10, 
		SUPEQ=11, EQUAL=12, NEQ=13, FADD=14, FSUB=15, FMUL=16, FDIV=17, FINF=18, 
		FINFEQ=19, FSUP=20, FSUPEQ=21, FEQUAL=22, FNEQ=23, ITOF=24, FTOI=25, RETURN=26, 
		POP=27, POPF=28, READ=29, READF=30, WRITE=31, WRITEF=32, PADD=33, PUSHGP=34, 
		PUSHFP=35, DUP=36, XCHG=37, PUSHI=38, PUSHG=39, STOREG=40, PUSHL=41, STOREL=42, 
		PUSHR=43, STORER=44, FREE=45, ALLOC=46, PUSHF=47, CALL=48, JUMP=49, JUMPF=50, 
		JUMPI=51, HALT=52, LABEL=53, IDENTIFIANT=54, NEWLINE=55;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"WS", "ENTIER", "FLOAT", "EXPONENT", "ADD", "SUB", "MUL", "DIV", "INF", 
			"INFEQ", "SUP", "SUPEQ", "EQUAL", "NEQ", "FADD", "FSUB", "FMUL", "FDIV", 
			"FINF", "FINFEQ", "FSUP", "FSUPEQ", "FEQUAL", "FNEQ", "ITOF", "FTOI", 
			"RETURN", "POP", "POPF", "READ", "READF", "WRITE", "WRITEF", "PADD", 
			"PUSHGP", "PUSHFP", "DUP", "XCHG", "PUSHI", "PUSHG", "STOREG", "PUSHL", 
			"STOREL", "PUSHR", "STORER", "FREE", "ALLOC", "PUSHF", "CALL", "JUMP", 
			"JUMPF", "JUMPI", "HALT", "LABEL", "IDENTIFIANT", "NEWLINE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, "'ADD'", "'SUB'", "'MUL'", "'DIV'", "'INF'", 
			"'INFEQ'", "'SUP'", "'SUPEQ'", "'EQUAL'", "'NEQ'", "'FADD'", "'FSUB'", 
			"'FMUL'", "'FDIV'", "'FINF'", "'FINFEQ'", "'FSUP'", "'FSUPEQ'", "'FEQUAL'", 
			"'FNEQ'", "'ITOF'", "'FTOI'", "'RETURN'", "'POP'", "'POPF'", "'READ'", 
			"'READF'", "'WRITE'", "'WRITEF'", "'PADD'", "'PUSHGP'", "'PUSHFP'", "'DUP'", 
			"'XCHG'", "'PUSHI'", "'PUSHG'", "'STOREG'", "'PUSHL'", "'STOREL'", "'PUSHR'", 
			"'STORER'", "'FREE'", "'ALLOC'", "'PUSHF'", "'CALL'", "'JUMP '", "'JUMPF'", 
			"'JUMPI'", "'HALT'", "'LABEL'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WS", "ENTIER", "FLOAT", "ADD", "SUB", "MUL", "DIV", "INF", "INFEQ", 
			"SUP", "SUPEQ", "EQUAL", "NEQ", "FADD", "FSUB", "FMUL", "FDIV", "FINF", 
			"FINFEQ", "FSUP", "FSUPEQ", "FEQUAL", "FNEQ", "ITOF", "FTOI", "RETURN", 
			"POP", "POPF", "READ", "READF", "WRITE", "WRITEF", "PADD", "PUSHGP", 
			"PUSHFP", "DUP", "XCHG", "PUSHI", "PUSHG", "STOREG", "PUSHL", "STOREL", 
			"PUSHR", "STORER", "FREE", "ALLOC", "PUSHF", "CALL", "JUMP", "JUMPF", 
			"JUMPI", "HALT", "LABEL", "IDENTIFIANT", "NEWLINE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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


	    /** La map pour mémoriser les addresses des étiquettes */
	    private HashMap<String, Integer> labels = new HashMap<String, Integer>();
	    /** adresse instruction */
	    private int instrAddress = 0;
	    /** Récupère le dictionnaire des étiquettes */
	    public HashMap<String, Integer> getLabels() { return labels; }
	    public int getProgramSize() { return instrAddress; }


	public MVaPLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MVaP.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\29\u01d4\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\3\2\6\2u\n\2\r\2\16\2v\3"+
		"\2\3\2\3\3\5\3|\n\3\3\3\6\3\177\n\3\r\3\16\3\u0080\3\4\6\4\u0084\n\4\r"+
		"\4\16\4\u0085\3\4\3\4\7\4\u008a\n\4\f\4\16\4\u008d\13\4\3\4\5\4\u0090"+
		"\n\4\3\4\3\4\6\4\u0094\n\4\r\4\16\4\u0095\3\4\5\4\u0099\n\4\3\4\6\4\u009c"+
		"\n\4\r\4\16\4\u009d\3\4\5\4\u00a1\n\4\3\5\3\5\5\5\u00a5\n\5\3\5\6\5\u00a8"+
		"\n\5\r\5\16\5\u00a9\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3"+
		"\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3"+
		"\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22"+
		"\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31"+
		"\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36"+
		"\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3\"\3\""+
		"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3"+
		"%\3%\3%\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3)\3)\3)\3)"+
		"\3)\3)\3*\3*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3+\3,\3,\3,\3,\3,\3,\3,\3-"+
		"\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\3.\3.\3/\3/\3/\3/\3/\3\60\3\60\3\60\3\60"+
		"\3\60\3\60\3\61\3\61\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\3\62\3\63"+
		"\3\63\3\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\64\3\65\3\65\3\65"+
		"\3\65\3\65\3\65\3\66\3\66\3\66\3\66\3\66\3\67\3\67\3\67\3\67\3\67\3\67"+
		"\38\38\78\u01c2\n8\f8\168\u01c5\138\39\39\79\u01c9\n9\f9\169\u01cc\13"+
		"9\59\u01ce\n9\39\59\u01d1\n9\39\39\2\2:\3\3\5\4\7\5\t\2\13\6\r\7\17\b"+
		"\21\t\23\n\25\13\27\f\31\r\33\16\35\17\37\20!\21#\22%\23\'\24)\25+\26"+
		"-\27/\30\61\31\63\32\65\33\67\349\35;\36=\37? A!C\"E#G$I%K&M\'O(Q)S*U"+
		"+W,Y-[.]/_\60a\61c\62e\63g\64i\65k\66m\67o8q9\3\2\b\4\2\13\13\"\"\4\2"+
		"--//\4\2GGgg\5\2C\\aac|\6\2\62;C\\aac|\4\2\f\f\17\17\2\u01e3\2\3\3\2\2"+
		"\2\2\5\3\2\2\2\2\7\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3"+
		"\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2"+
		"\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2"+
		"Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3"+
		"\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2"+
		"\2\3t\3\2\2\2\5{\3\2\2\2\7\u00a0\3\2\2\2\t\u00a2\3\2\2\2\13\u00ab\3\2"+
		"\2\2\r\u00af\3\2\2\2\17\u00b3\3\2\2\2\21\u00b7\3\2\2\2\23\u00bb\3\2\2"+
		"\2\25\u00bf\3\2\2\2\27\u00c5\3\2\2\2\31\u00c9\3\2\2\2\33\u00cf\3\2\2\2"+
		"\35\u00d5\3\2\2\2\37\u00d9\3\2\2\2!\u00de\3\2\2\2#\u00e3\3\2\2\2%\u00e8"+
		"\3\2\2\2\'\u00ed\3\2\2\2)\u00f2\3\2\2\2+\u00f9\3\2\2\2-\u00fe\3\2\2\2"+
		"/\u0105\3\2\2\2\61\u010c\3\2\2\2\63\u0111\3\2\2\2\65\u0116\3\2\2\2\67"+
		"\u011b\3\2\2\29\u0122\3\2\2\2;\u0126\3\2\2\2=\u012b\3\2\2\2?\u0130\3\2"+
		"\2\2A\u0136\3\2\2\2C\u013c\3\2\2\2E\u0143\3\2\2\2G\u0148\3\2\2\2I\u014f"+
		"\3\2\2\2K\u0156\3\2\2\2M\u015a\3\2\2\2O\u015f\3\2\2\2Q\u0165\3\2\2\2S"+
		"\u016b\3\2\2\2U\u0172\3\2\2\2W\u0178\3\2\2\2Y\u017f\3\2\2\2[\u0185\3\2"+
		"\2\2]\u018c\3\2\2\2_\u0191\3\2\2\2a\u0197\3\2\2\2c\u019d\3\2\2\2e\u01a2"+
		"\3\2\2\2g\u01a8\3\2\2\2i\u01ae\3\2\2\2k\u01b4\3\2\2\2m\u01b9\3\2\2\2o"+
		"\u01bf\3\2\2\2q\u01cd\3\2\2\2su\t\2\2\2ts\3\2\2\2uv\3\2\2\2vt\3\2\2\2"+
		"vw\3\2\2\2wx\3\2\2\2xy\b\2\2\2y\4\3\2\2\2z|\t\3\2\2{z\3\2\2\2{|\3\2\2"+
		"\2|~\3\2\2\2}\177\4\62;\2~}\3\2\2\2\177\u0080\3\2\2\2\u0080~\3\2\2\2\u0080"+
		"\u0081\3\2\2\2\u0081\6\3\2\2\2\u0082\u0084\4\62;\2\u0083\u0082\3\2\2\2"+
		"\u0084\u0085\3\2\2\2\u0085\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0087"+
		"\3\2\2\2\u0087\u008b\7\60\2\2\u0088\u008a\4\62;\2\u0089\u0088\3\2\2\2"+
		"\u008a\u008d\3\2\2\2\u008b\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008f"+
		"\3\2\2\2\u008d\u008b\3\2\2\2\u008e\u0090\5\t\5\2\u008f\u008e\3\2\2\2\u008f"+
		"\u0090\3\2\2\2\u0090\u00a1\3\2\2\2\u0091\u0093\7\60\2\2\u0092\u0094\4"+
		"\62;\2\u0093\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0093\3\2\2\2\u0095"+
		"\u0096\3\2\2\2\u0096\u0098\3\2\2\2\u0097\u0099\5\t\5\2\u0098\u0097\3\2"+
		"\2\2\u0098\u0099\3\2\2\2\u0099\u00a1\3\2\2\2\u009a\u009c\4\62;\2\u009b"+
		"\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009b\3\2\2\2\u009d\u009e\3\2"+
		"\2\2\u009e\u009f\3\2\2\2\u009f\u00a1\5\t\5\2\u00a0\u0083\3\2\2\2\u00a0"+
		"\u0091\3\2\2\2\u00a0\u009b\3\2\2\2\u00a1\b\3\2\2\2\u00a2\u00a4\t\4\2\2"+
		"\u00a3\u00a5\t\3\2\2\u00a4\u00a3\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a7"+
		"\3\2\2\2\u00a6\u00a8\4\62;\2\u00a7\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9"+
		"\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\n\3\2\2\2\u00ab\u00ac\7C\2\2"+
		"\u00ac\u00ad\7F\2\2\u00ad\u00ae\7F\2\2\u00ae\f\3\2\2\2\u00af\u00b0\7U"+
		"\2\2\u00b0\u00b1\7W\2\2\u00b1\u00b2\7D\2\2\u00b2\16\3\2\2\2\u00b3\u00b4"+
		"\7O\2\2\u00b4\u00b5\7W\2\2\u00b5\u00b6\7N\2\2\u00b6\20\3\2\2\2\u00b7\u00b8"+
		"\7F\2\2\u00b8\u00b9\7K\2\2\u00b9\u00ba\7X\2\2\u00ba\22\3\2\2\2\u00bb\u00bc"+
		"\7K\2\2\u00bc\u00bd\7P\2\2\u00bd\u00be\7H\2\2\u00be\24\3\2\2\2\u00bf\u00c0"+
		"\7K\2\2\u00c0\u00c1\7P\2\2\u00c1\u00c2\7H\2\2\u00c2\u00c3\7G\2\2\u00c3"+
		"\u00c4\7S\2\2\u00c4\26\3\2\2\2\u00c5\u00c6\7U\2\2\u00c6\u00c7\7W\2\2\u00c7"+
		"\u00c8\7R\2\2\u00c8\30\3\2\2\2\u00c9\u00ca\7U\2\2\u00ca\u00cb\7W\2\2\u00cb"+
		"\u00cc\7R\2\2\u00cc\u00cd\7G\2\2\u00cd\u00ce\7S\2\2\u00ce\32\3\2\2\2\u00cf"+
		"\u00d0\7G\2\2\u00d0\u00d1\7S\2\2\u00d1\u00d2\7W\2\2\u00d2\u00d3\7C\2\2"+
		"\u00d3\u00d4\7N\2\2\u00d4\34\3\2\2\2\u00d5\u00d6\7P\2\2\u00d6\u00d7\7"+
		"G\2\2\u00d7\u00d8\7S\2\2\u00d8\36\3\2\2\2\u00d9\u00da\7H\2\2\u00da\u00db"+
		"\7C\2\2\u00db\u00dc\7F\2\2\u00dc\u00dd\7F\2\2\u00dd \3\2\2\2\u00de\u00df"+
		"\7H\2\2\u00df\u00e0\7U\2\2\u00e0\u00e1\7W\2\2\u00e1\u00e2\7D\2\2\u00e2"+
		"\"\3\2\2\2\u00e3\u00e4\7H\2\2\u00e4\u00e5\7O\2\2\u00e5\u00e6\7W\2\2\u00e6"+
		"\u00e7\7N\2\2\u00e7$\3\2\2\2\u00e8\u00e9\7H\2\2\u00e9\u00ea\7F\2\2\u00ea"+
		"\u00eb\7K\2\2\u00eb\u00ec\7X\2\2\u00ec&\3\2\2\2\u00ed\u00ee\7H\2\2\u00ee"+
		"\u00ef\7K\2\2\u00ef\u00f0\7P\2\2\u00f0\u00f1\7H\2\2\u00f1(\3\2\2\2\u00f2"+
		"\u00f3\7H\2\2\u00f3\u00f4\7K\2\2\u00f4\u00f5\7P\2\2\u00f5\u00f6\7H\2\2"+
		"\u00f6\u00f7\7G\2\2\u00f7\u00f8\7S\2\2\u00f8*\3\2\2\2\u00f9\u00fa\7H\2"+
		"\2\u00fa\u00fb\7U\2\2\u00fb\u00fc\7W\2\2\u00fc\u00fd\7R\2\2\u00fd,\3\2"+
		"\2\2\u00fe\u00ff\7H\2\2\u00ff\u0100\7U\2\2\u0100\u0101\7W\2\2\u0101\u0102"+
		"\7R\2\2\u0102\u0103\7G\2\2\u0103\u0104\7S\2\2\u0104.\3\2\2\2\u0105\u0106"+
		"\7H\2\2\u0106\u0107\7G\2\2\u0107\u0108\7S\2\2\u0108\u0109\7W\2\2\u0109"+
		"\u010a\7C\2\2\u010a\u010b\7N\2\2\u010b\60\3\2\2\2\u010c\u010d\7H\2\2\u010d"+
		"\u010e\7P\2\2\u010e\u010f\7G\2\2\u010f\u0110\7S\2\2\u0110\62\3\2\2\2\u0111"+
		"\u0112\7K\2\2\u0112\u0113\7V\2\2\u0113\u0114\7Q\2\2\u0114\u0115\7H\2\2"+
		"\u0115\64\3\2\2\2\u0116\u0117\7H\2\2\u0117\u0118\7V\2\2\u0118\u0119\7"+
		"Q\2\2\u0119\u011a\7K\2\2\u011a\66\3\2\2\2\u011b\u011c\7T\2\2\u011c\u011d"+
		"\7G\2\2\u011d\u011e\7V\2\2\u011e\u011f\7W\2\2\u011f\u0120\7T\2\2\u0120"+
		"\u0121\7P\2\2\u01218\3\2\2\2\u0122\u0123\7R\2\2\u0123\u0124\7Q\2\2\u0124"+
		"\u0125\7R\2\2\u0125:\3\2\2\2\u0126\u0127\7R\2\2\u0127\u0128\7Q\2\2\u0128"+
		"\u0129\7R\2\2\u0129\u012a\7H\2\2\u012a<\3\2\2\2\u012b\u012c\7T\2\2\u012c"+
		"\u012d\7G\2\2\u012d\u012e\7C\2\2\u012e\u012f\7F\2\2\u012f>\3\2\2\2\u0130"+
		"\u0131\7T\2\2\u0131\u0132\7G\2\2\u0132\u0133\7C\2\2\u0133\u0134\7F\2\2"+
		"\u0134\u0135\7H\2\2\u0135@\3\2\2\2\u0136\u0137\7Y\2\2\u0137\u0138\7T\2"+
		"\2\u0138\u0139\7K\2\2\u0139\u013a\7V\2\2\u013a\u013b\7G\2\2\u013bB\3\2"+
		"\2\2\u013c\u013d\7Y\2\2\u013d\u013e\7T\2\2\u013e\u013f\7K\2\2\u013f\u0140"+
		"\7V\2\2\u0140\u0141\7G\2\2\u0141\u0142\7H\2\2\u0142D\3\2\2\2\u0143\u0144"+
		"\7R\2\2\u0144\u0145\7C\2\2\u0145\u0146\7F\2\2\u0146\u0147\7F\2\2\u0147"+
		"F\3\2\2\2\u0148\u0149\7R\2\2\u0149\u014a\7W\2\2\u014a\u014b\7U\2\2\u014b"+
		"\u014c\7J\2\2\u014c\u014d\7I\2\2\u014d\u014e\7R\2\2\u014eH\3\2\2\2\u014f"+
		"\u0150\7R\2\2\u0150\u0151\7W\2\2\u0151\u0152\7U\2\2\u0152\u0153\7J\2\2"+
		"\u0153\u0154\7H\2\2\u0154\u0155\7R\2\2\u0155J\3\2\2\2\u0156\u0157\7F\2"+
		"\2\u0157\u0158\7W\2\2\u0158\u0159\7R\2\2\u0159L\3\2\2\2\u015a\u015b\7"+
		"Z\2\2\u015b\u015c\7E\2\2\u015c\u015d\7J\2\2\u015d\u015e\7I\2\2\u015eN"+
		"\3\2\2\2\u015f\u0160\7R\2\2\u0160\u0161\7W\2\2\u0161\u0162\7U\2\2\u0162"+
		"\u0163\7J\2\2\u0163\u0164\7K\2\2\u0164P\3\2\2\2\u0165\u0166\7R\2\2\u0166"+
		"\u0167\7W\2\2\u0167\u0168\7U\2\2\u0168\u0169\7J\2\2\u0169\u016a\7I\2\2"+
		"\u016aR\3\2\2\2\u016b\u016c\7U\2\2\u016c\u016d\7V\2\2\u016d\u016e\7Q\2"+
		"\2\u016e\u016f\7T\2\2\u016f\u0170\7G\2\2\u0170\u0171\7I\2\2\u0171T\3\2"+
		"\2\2\u0172\u0173\7R\2\2\u0173\u0174\7W\2\2\u0174\u0175\7U\2\2\u0175\u0176"+
		"\7J\2\2\u0176\u0177\7N\2\2\u0177V\3\2\2\2\u0178\u0179\7U\2\2\u0179\u017a"+
		"\7V\2\2\u017a\u017b\7Q\2\2\u017b\u017c\7T\2\2\u017c\u017d\7G\2\2\u017d"+
		"\u017e\7N\2\2\u017eX\3\2\2\2\u017f\u0180\7R\2\2\u0180\u0181\7W\2\2\u0181"+
		"\u0182\7U\2\2\u0182\u0183\7J\2\2\u0183\u0184\7T\2\2\u0184Z\3\2\2\2\u0185"+
		"\u0186\7U\2\2\u0186\u0187\7V\2\2\u0187\u0188\7Q\2\2\u0188\u0189\7T\2\2"+
		"\u0189\u018a\7G\2\2\u018a\u018b\7T\2\2\u018b\\\3\2\2\2\u018c\u018d\7H"+
		"\2\2\u018d\u018e\7T\2\2\u018e\u018f\7G\2\2\u018f\u0190\7G\2\2\u0190^\3"+
		"\2\2\2\u0191\u0192\7C\2\2\u0192\u0193\7N\2\2\u0193\u0194\7N\2\2\u0194"+
		"\u0195\7Q\2\2\u0195\u0196\7E\2\2\u0196`\3\2\2\2\u0197\u0198\7R\2\2\u0198"+
		"\u0199\7W\2\2\u0199\u019a\7U\2\2\u019a\u019b\7J\2\2\u019b\u019c\7H\2\2"+
		"\u019cb\3\2\2\2\u019d\u019e\7E\2\2\u019e\u019f\7C\2\2\u019f\u01a0\7N\2"+
		"\2\u01a0\u01a1\7N\2\2\u01a1d\3\2\2\2\u01a2\u01a3\7L\2\2\u01a3\u01a4\7"+
		"W\2\2\u01a4\u01a5\7O\2\2\u01a5\u01a6\7R\2\2\u01a6\u01a7\7\"\2\2\u01a7"+
		"f\3\2\2\2\u01a8\u01a9\7L\2\2\u01a9\u01aa\7W\2\2\u01aa\u01ab\7O\2\2\u01ab"+
		"\u01ac\7R\2\2\u01ac\u01ad\7H\2\2\u01adh\3\2\2\2\u01ae\u01af\7L\2\2\u01af"+
		"\u01b0\7W\2\2\u01b0\u01b1\7O\2\2\u01b1\u01b2\7R\2\2\u01b2\u01b3\7K\2\2"+
		"\u01b3j\3\2\2\2\u01b4\u01b5\7J\2\2\u01b5\u01b6\7C\2\2\u01b6\u01b7\7N\2"+
		"\2\u01b7\u01b8\7V\2\2\u01b8l\3\2\2\2\u01b9\u01ba\7N\2\2\u01ba\u01bb\7"+
		"C\2\2\u01bb\u01bc\7D\2\2\u01bc\u01bd\7G\2\2\u01bd\u01be\7N\2\2\u01ben"+
		"\3\2\2\2\u01bf\u01c3\t\5\2\2\u01c0\u01c2\t\6\2\2\u01c1\u01c0\3\2\2\2\u01c2"+
		"\u01c5\3\2\2\2\u01c3\u01c1\3\2\2\2\u01c3\u01c4\3\2\2\2\u01c4p\3\2\2\2"+
		"\u01c5\u01c3\3\2\2\2\u01c6\u01ca\7%\2\2\u01c7\u01c9\n\7\2\2\u01c8\u01c7"+
		"\3\2\2\2\u01c9\u01cc\3\2\2\2\u01ca\u01c8\3\2\2\2\u01ca\u01cb\3\2\2\2\u01cb"+
		"\u01ce\3\2\2\2\u01cc\u01ca\3\2\2\2\u01cd\u01c6\3\2\2\2\u01cd\u01ce\3\2"+
		"\2\2\u01ce\u01d0\3\2\2\2\u01cf\u01d1\7\17\2\2\u01d0\u01cf\3\2\2\2\u01d0"+
		"\u01d1\3\2\2\2\u01d1\u01d2\3\2\2\2\u01d2\u01d3\7\f\2\2\u01d3r\3\2\2\2"+
		"\23\2v{\u0080\u0085\u008b\u008f\u0095\u0098\u009d\u00a0\u00a4\u00a9\u01c3"+
		"\u01ca\u01cd\u01d0\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}