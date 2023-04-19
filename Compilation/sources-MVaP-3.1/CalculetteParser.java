// Generated from Calculette.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CalculetteParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, OPEARTION=30, RETURN=31, 
		COMM=32, COMMLIGNE=33, COMMMULTI=34, WS=35, BOOLEAN=36, ENTIER=37, DOUBLE=38, 
		BREAK=39, CONTINUE=40, TYPE=41, IDENTIFIANT=42, NEWLINE=43, UNMATCH=44;
	public static final int
		RULE_decl = 0, RULE_assignation = 1, RULE_start = 2, RULE_calcul = 3, 
		RULE_instruction = 4, RULE_fonction = 5, RULE_params = 6, RULE_expression = 7, 
		RULE_args = 8, RULE_affichage = 9, RULE_ecriture = 10, RULE_bloc = 11, 
		RULE_boucleWhile = 12, RULE_boucleFor = 13, RULE_ifElse = 14, RULE_condition = 15, 
		RULE_operateurRationnel = 16, RULE_operateurBooleen = 17, RULE_finInstruction = 18;
	private static String[] makeRuleNames() {
		return new String[] {
			"decl", "assignation", "start", "calcul", "instruction", "fonction", 
			"params", "expression", "args", "affichage", "ecriture", "bloc", "boucleWhile", 
			"boucleFor", "ifElse", "condition", "operateurRationnel", "operateurBooleen", 
			"finInstruction"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'='", "'+='", "'('", "')'", "'{'", "'}'", "','", "'-'", "'*'", 
			"'/'", "'+'", "'print'", "'input'", "'while'", "'for'", "';'", "'if'", 
			"'else'", "'=='", "'!='", "'<>'", "'<'", "'>'", "'<='", "'>='", "'&&'", 
			"'||'", "'^^'", "'!'", null, "'return'", null, null, null, null, null, 
			null, null, "'break'", "'continue'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, "OPEARTION", "RETURN", "COMM", "COMMLIGNE", 
			"COMMMULTI", "WS", "BOOLEAN", "ENTIER", "DOUBLE", "BREAK", "CONTINUE", 
			"TYPE", "IDENTIFIANT", "NEWLINE", "UNMATCH"
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

	@Override
	public String getGrammarFileName() { return "Calculette.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	    private int _cur_label = 1;
	    /** générateur de nom d'étiquettes pour les boucles */
	    private String getNewLabel() {
	        return "Label" +(_cur_label++) + "\n"; 
	    }
	    // ...
	    private TablesSymboles tablesSymboles = new TablesSymboles(); // une variable global
	    private String evalexpr (String op,String type) {
	        if ( op.equals("*")){
	            return type.equals(" ") ? "MUL \n" : type +"MUL\n";
	        } else if ( op.equals("+") ){
	            return type.equals(" ") ? "ADD \n" : type+ "ADD\n";
	        }
	        else if ( op.equals("/") ){
	            return type.equals(" ") ? "DIV \n" : type+ "DIV\n";
	        }
	        else if ( op.equals("-") ){
	            return type.equals(" ") ? "SUB \n" : type+ "SUB\n";
	        } 
	        else {
	           System.err.println("Opérateur arithmétique incorrect : '"+op+"'");
	           throw new IllegalArgumentException("Opérateur arithmétique incorrect : '"+op+"'");
	        }
	    }
	    // pour evaluer differents types
	    private String evalType (String op,String type) {
	        String res = "";
	        switch (op) {
	            case "==":
	                res = type + "EQUAL\n";
	                break;
	            case "!=":
	            case "<>":
	                res = type + "NEQ\n";
	                break;
	            case "<=":
	                res = type + "INFEQ\n";
	                break;
	            case ">=":
	                res = type + "SUPEQ\n";
	                break;
	            case "<":
	                res = type + "INF\n";
	                break;
	            case ">":
	                res = type + "SUP\n";
	                break;
	            default:
	                System.err.println("Opérateur de comparaison incorrect : '"+op+"'");
	                throw new IllegalArgumentException("Opérateur de comparaison incorrect : '"+op+"'");
	        }
	        return res;
	    }

	public CalculetteParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class DeclContext extends ParserRuleContext {
		public String code;
		public Token TYPE;
		public Token IDENTIFIANT;
		public ExpressionContext expression;
		public TerminalNode TYPE() { return getToken(CalculetteParser.TYPE, 0); }
		public TerminalNode IDENTIFIANT() { return getToken(CalculetteParser.IDENTIFIANT, 0); }
		public FinInstructionContext finInstruction() {
			return getRuleContext(FinInstructionContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitDecl(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_decl);
		try {
			setState(50);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(38);
				((DeclContext)_localctx).TYPE = match(TYPE);
				setState(39);
				((DeclContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(40);
				finInstruction();

				            tablesSymboles.addVarDecl((((DeclContext)_localctx).IDENTIFIANT!=null?((DeclContext)_localctx).IDENTIFIANT.getText():null),(((DeclContext)_localctx).TYPE!=null?((DeclContext)_localctx).TYPE.getText():null)); // on reserve une addresse pour la variable
				            VariableInfo vi = tablesSymboles.getVar((((DeclContext)_localctx).IDENTIFIANT!=null?((DeclContext)_localctx).IDENTIFIANT.getText():null)); //pour recupèrer les info de la variable(addresse, type , scoope)
				            ((DeclContext)_localctx).code =  vi.type.equals("double") ? "PUSHF 0.0 \n" : "PUSHI 0 \n";
				        
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(43);
				((DeclContext)_localctx).TYPE = match(TYPE);
				setState(44);
				((DeclContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(45);
				match(T__0);
				setState(46);
				((DeclContext)_localctx).expression = expression(0);
				setState(47);
				finInstruction();

				            tablesSymboles.addVarDecl((((DeclContext)_localctx).IDENTIFIANT!=null?((DeclContext)_localctx).IDENTIFIANT.getText():null),(((DeclContext)_localctx).TYPE!=null?((DeclContext)_localctx).TYPE.getText():null)); // on reserve une addresse pour la variable
				            VariableInfo vi = tablesSymboles.getVar((((DeclContext)_localctx).IDENTIFIANT!=null?((DeclContext)_localctx).IDENTIFIANT.getText():null)); //pour recupèrer les info de la variable(addresse, type , scoope)
				            ((DeclContext)_localctx).code =  vi.type.equals("double") ? "PUSHF 0.0 \n" : "PUSHI 0 \n";
				            String storeG_L = (vi.scope == VariableInfo.Scope.GLOBAL) ? "STOREG " : "STOREL " ;
				            // Gestion du Casting entre différents types
				            if(vi.type.equals(((DeclContext)_localctx).expression.type)){
				                _localctx.code += ((DeclContext)_localctx).expression.code;
				                _localctx.code += vi.type.equals("double") ? storeG_L + (vi.address + 1) + "\n" : "";
				            }
				            else{
				                System.err.println("WARRNING : le type de la variable " +(((DeclContext)_localctx).IDENTIFIANT!=null?((DeclContext)_localctx).IDENTIFIANT.getText():null)+ " ne matche pas à celui de l'expression \n"
				                + "Un casting sera appliqué pour convertir le type de l'expression vers le type "+ vi.type);
				                if(vi.type.equals("double")){
				                    _localctx.code += ((DeclContext)_localctx).expression.code + "ITOF\n";
				                    _localctx.code += storeG_L + (vi.address + 1) + "\n";
				                }else if(vi.type.equals("int")){
				                    _localctx.code += !((DeclContext)_localctx).expression.type.equals("double") ? ((DeclContext)_localctx).expression.code  : ((DeclContext)_localctx).expression.code + "FTOI\n";
				                }else{
				                    _localctx.code += ((DeclContext)_localctx).expression.code;
				                    _localctx.code += !((DeclContext)_localctx).expression.type.equals("double") ? "PUSHI 0\nNEQ\n" : "PUSHFI 0.0\nFNEQ\n"; // En faisant le casting d'un nombre vers un boolean, on le compare avec 0
				                }
				            }
				            _localctx.code += storeG_L + vi.address + "\n";
				        
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

	public static class AssignationContext extends ParserRuleContext {
		public String code;
		public Token IDENTIFIANT;
		public ExpressionContext expression;
		public TerminalNode IDENTIFIANT() { return getToken(CalculetteParser.IDENTIFIANT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterAssignation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitAssignation(this);
		}
	}

	public final AssignationContext assignation() throws RecognitionException {
		AssignationContext _localctx = new AssignationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_assignation);
		try {
			setState(62);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(52);
				((AssignationContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(53);
				match(T__0);
				setState(54);
				((AssignationContext)_localctx).expression = expression(0);
				  
				            VariableInfo vi = tablesSymboles.getVar((((AssignationContext)_localctx).IDENTIFIANT!=null?((AssignationContext)_localctx).IDENTIFIANT.getText():null)); //pour recupèrer les info de la variable(addresse, type , scoope)
				            String storeG_L = (vi.scope == VariableInfo.Scope.GLOBAL) ? "STOREG " : "STOREL " ;
				            // Gestion du Casting entre différents types
				            if(vi.type.equals(((AssignationContext)_localctx).expression.type)){
				                ((AssignationContext)_localctx).code =  ((AssignationContext)_localctx).expression.code;
				                _localctx.code += vi.type.equals("double") ? storeG_L + (vi.address + 1) + "\n" : "";
				            }
				            else{
				                System.err.println("WARRNING : le type de la variable " +(((AssignationContext)_localctx).IDENTIFIANT!=null?((AssignationContext)_localctx).IDENTIFIANT.getText():null)+ " ne matche pas à celui de l'expression \n"
				                + "Un casting sera appliqué pour convertir le type de l'expression vers le type "+ vi.type);
				                if(vi.type.equals("double")){
				                    ((AssignationContext)_localctx).code =  ((AssignationContext)_localctx).expression.type.equals("double") ? ((AssignationContext)_localctx).expression.code : ((AssignationContext)_localctx).expression.code + "ITOF\n";
				                    _localctx.code += storeG_L + (vi.address + 1) + "\n";
				                }else if(vi.type.equals("int")){
				                    ((AssignationContext)_localctx).code =  !((AssignationContext)_localctx).expression.type.equals("double") ? ((AssignationContext)_localctx).expression.code  : ((AssignationContext)_localctx).expression.code + "FTOI\n";
				                }else{
				                    ((AssignationContext)_localctx).code =  ((AssignationContext)_localctx).expression.code;
				                    _localctx.code += !((AssignationContext)_localctx).expression.type.equals("double") ? "PUSHI 0\nNEQ\n" : "PUSHF 0.0\nFNEQ\n"; // En faisant le casting d'un nombre vers un boolean, on le compare avec 0
				                }
				            }
				            _localctx.code += storeG_L + vi.address + "\n";
				        
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(57);
				((AssignationContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(58);
				match(T__1);
				setState(59);
				((AssignationContext)_localctx).expression = expression(0);
				  
				            VariableInfo vi = tablesSymboles.getVar((((AssignationContext)_localctx).IDENTIFIANT!=null?((AssignationContext)_localctx).IDENTIFIANT.getText():null)); //pour recupèrer les info de la variable(addresse, type , scoope)
				            String storeG_L = (vi.scope == VariableInfo.Scope.GLOBAL) ? "STOREG " : "STOREL " ;
				            String pushG_L  = (vi.scope == VariableInfo.Scope.GLOBAL) ? "PUSHG " : "PUSHL " ;
				            ((AssignationContext)_localctx).code =  pushG_L + vi.address + "\n" ; 
				            if(vi.type.equals(((AssignationContext)_localctx).expression.type)){
				                _localctx.code += vi.type.equals("double") ? pushG_L + (vi.address + 1) + "\n" : "";
				                _localctx.code += ((AssignationContext)_localctx).expression.code;
				                _localctx.code += vi.type.equals("double") ? "FADD\n" + storeG_L + (vi.address + 1) + "\n" : "ADD\n";
				            }
				            // Gestion des erreurs et casting explicite entre les types 
				            else{
				                System.err.println("WARRNING : le type de la variable " +(((AssignationContext)_localctx).IDENTIFIANT!=null?((AssignationContext)_localctx).IDENTIFIANT.getText():null)+ " ne matche pas à celui de l'expression \n"
				                + "Un casting sera appliqué pour convertir le type de l'expression vers le type "+ vi.type);
				                if(vi.type.equals("double")){
				                    _localctx.code += pushG_L + (vi.address + 1)  + "\n" + ((AssignationContext)_localctx).expression.code + "ITOF\n";
				                    _localctx.code += "FADD\n" + storeG_L + (vi.address + 1) + "\n";
				                }else if(vi.type.equals("int")){
				                    _localctx.code += !((AssignationContext)_localctx).expression.type.equals("double") ? ((AssignationContext)_localctx).expression.code  : ((AssignationContext)_localctx).expression.code + "FTOI\n";
				                    _localctx.code += "ADD\n" + storeG_L + vi.address + "\n" ;
				                }else{
				                    _localctx.code += !((AssignationContext)_localctx).expression.type.equals("double") ? ((AssignationContext)_localctx).expression.code : ((AssignationContext)_localctx).expression.code + "FTOI\n"; // En faisant le casting d'un nombre vers un boolean, on le compare avec 0
				                    _localctx.code += "ADD\nPUSHI 0\nNEQ\n";
				                }
				            }
				            _localctx.code += storeG_L + vi.address + "\n" ;
				        
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

	public static class StartContext extends ParserRuleContext {
		public CalculContext calcul() {
			return getRuleContext(CalculContext.class,0);
		}
		public TerminalNode EOF() { return getToken(CalculetteParser.EOF, 0); }
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitStart(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			calcul();
			setState(65);
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

	public static class CalculContext extends ParserRuleContext {
		public String code;
		public DeclContext decl;
		public FonctionContext fonction;
		public InstructionContext instruction;
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(CalculetteParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(CalculetteParser.NEWLINE, i);
		}
		public List<FonctionContext> fonction() {
			return getRuleContexts(FonctionContext.class);
		}
		public FonctionContext fonction(int i) {
			return getRuleContext(FonctionContext.class,i);
		}
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public CalculContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_calcul; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterCalcul(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitCalcul(this);
		}
	}

	public final CalculContext calcul() throws RecognitionException {
		CalculContext _localctx = new CalculContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_calcul);
		 ((CalculContext)_localctx).code =  new String(); 
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(67);
					((CalculContext)_localctx).decl = decl();
					 _localctx.code += ((CalculContext)_localctx).decl.code; 
					}
					} 
				}
				setState(74);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			 _localctx.code += "JUMP Main\n"; 
			setState(79);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(76);
					match(NEWLINE);
					}
					} 
				}
				setState(81);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			setState(87);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TYPE) {
				{
				{
				setState(82);
				((CalculContext)_localctx).fonction = fonction();
				 _localctx.code += ((CalculContext)_localctx).fonction.code; 
				}
				}
				setState(89);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(93);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(90);
					match(NEWLINE);
					}
					} 
				}
				setState(95);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			 _localctx.code += "LABEL Main\n"; 
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__4) | (1L << T__7) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << RETURN) | (1L << BOOLEAN) | (1L << ENTIER) | (1L << DOUBLE) | (1L << IDENTIFIANT) | (1L << NEWLINE))) != 0)) {
				{
				{
				setState(97);
				((CalculContext)_localctx).instruction = instruction();
				 _localctx.code += ((CalculContext)_localctx).instruction.code; 
				}
				}
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			 _localctx.code += "HALT\n"; 
			}
			_ctx.stop = _input.LT(-1);
			 System.out.println(_localctx.code); 
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

	public static class InstructionContext extends ParserRuleContext {
		public String code;
		public ExpressionContext expression;
		public BlocContext bloc;
		public BoucleWhileContext boucleWhile;
		public BoucleForContext boucleFor;
		public IfElseContext ifElse;
		public AffichageContext affichage;
		public EcritureContext ecriture;
		public AssignationContext assignation;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public FinInstructionContext finInstruction() {
			return getRuleContext(FinInstructionContext.class,0);
		}
		public BlocContext bloc() {
			return getRuleContext(BlocContext.class,0);
		}
		public BoucleWhileContext boucleWhile() {
			return getRuleContext(BoucleWhileContext.class,0);
		}
		public BoucleForContext boucleFor() {
			return getRuleContext(BoucleForContext.class,0);
		}
		public IfElseContext ifElse() {
			return getRuleContext(IfElseContext.class,0);
		}
		public AffichageContext affichage() {
			return getRuleContext(AffichageContext.class,0);
		}
		public EcritureContext ecriture() {
			return getRuleContext(EcritureContext.class,0);
		}
		public AssignationContext assignation() {
			return getRuleContext(AssignationContext.class,0);
		}
		public TerminalNode RETURN() { return getToken(CalculetteParser.RETURN, 0); }
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitInstruction(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_instruction);
		try {
			setState(143);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(107);
				((InstructionContext)_localctx).expression = expression(0);
				setState(108);
				finInstruction();
				 ((InstructionContext)_localctx).code = ((InstructionContext)_localctx).expression.code; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(111);
				((InstructionContext)_localctx).bloc = bloc();
				 ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).bloc.code;
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(114);
				((InstructionContext)_localctx).boucleWhile = boucleWhile();
				 ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).boucleWhile.code;
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(117);
				((InstructionContext)_localctx).boucleFor = boucleFor();
				 ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).boucleFor.code;
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(120);
				((InstructionContext)_localctx).ifElse = ifElse();
				 ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).ifElse.code; 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(123);
				((InstructionContext)_localctx).affichage = affichage();
				setState(124);
				finInstruction();
				 ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).affichage.code;
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(127);
				((InstructionContext)_localctx).ecriture = ecriture();
				setState(128);
				finInstruction();
				 ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).ecriture.code;
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(131);
				((InstructionContext)_localctx).assignation = assignation();
				setState(132);
				finInstruction();
				 ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).assignation.code;
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(135);
				match(RETURN);
				setState(136);
				((InstructionContext)_localctx).expression = expression(0);
				setState(137);
				finInstruction();
				 
				            VariableInfo vi = tablesSymboles.getReturn();
				            if(!vi.type.equals(((InstructionContext)_localctx).expression.type)){
				                System.err.println("WARRNING : le type de la variable de retour ne matche pas à celui de l'expression \n"
				                + "Un casting sera appliqué pour convertir le type de l'expression vers le type "+ vi.type);
				            }
				            if( vi.type.equals("double")){
				                ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).expression.type.equals("double") ? ((InstructionContext)_localctx).expression.code : ((InstructionContext)_localctx).expression.code + "ITOF\n";
				                _localctx.code  += "STOREL " +(vi.address+1)+ "\n";
				            }else if( vi.type.equals("int")){
				                ((InstructionContext)_localctx).code =  !((InstructionContext)_localctx).expression.type.equals("double") ? ((InstructionContext)_localctx).expression.code : ((InstructionContext)_localctx).expression.code + "FTOI\n";
				            }else{
				                _localctx.code += ((InstructionContext)_localctx).expression.code ;
				                _localctx.code += !((InstructionContext)_localctx).expression.type.equals("double") ?  "PUSHI 0\nNEQ\n": "PUSHF 0.0\nFNEQ\n"; // En faisant le casting d'un nombre vers un boolean, on le compare avec 0
				            }
				            _localctx.code +=  "STOREL " + vi.address + "\n  RETURN\n";
				        
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(140);
				finInstruction();
				 ((InstructionContext)_localctx).code = ""; 
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

	public static class FonctionContext extends ParserRuleContext {
		public String code;
		public Token TYPE;
		public Token IDENTIFIANT;
		public DeclContext decl;
		public InstructionContext instruction;
		public TerminalNode TYPE() { return getToken(CalculetteParser.TYPE, 0); }
		public TerminalNode IDENTIFIANT() { return getToken(CalculetteParser.IDENTIFIANT, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(CalculetteParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(CalculetteParser.NEWLINE, i);
		}
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public FonctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fonction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterFonction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitFonction(this);
		}
	}

	public final FonctionContext fonction() throws RecognitionException {
		FonctionContext _localctx = new FonctionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_fonction);
		tablesSymboles.enterFunction();
		int _la;
		try {
			int _alt;
			setState(226);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(145);
				((FonctionContext)_localctx).TYPE = match(TYPE);
				setState(146);
				((FonctionContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);

				            tablesSymboles.addFunction((((FonctionContext)_localctx).IDENTIFIANT!=null?((FonctionContext)_localctx).IDENTIFIANT.getText():null),(((FonctionContext)_localctx).TYPE!=null?((FonctionContext)_localctx).TYPE.getText():null));
				            ((FonctionContext)_localctx).code =  "LABEL " + (((FonctionContext)_localctx).IDENTIFIANT!=null?((FonctionContext)_localctx).IDENTIFIANT.getText():null) + "\n"; // pour stocker le type de la fonction, ici c'est (((FonctionContext)_localctx).TYPE!=null?((FonctionContext)_localctx).TYPE.getText():null)
				        
				setState(148);
				match(T__2);
				setState(149);
				match(T__3);
				setState(150);
				match(T__4);
				setState(152);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					{
					setState(151);
					match(NEWLINE);
					}
					break;
				}
				setState(159);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==TYPE) {
					{
					{
					setState(154);
					((FonctionContext)_localctx).decl = decl();
					 _localctx.code += ((FonctionContext)_localctx).decl.code; 
					}
					}
					setState(161);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(165);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(162);
						match(NEWLINE);
						}
						} 
					}
					setState(167);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				}
				setState(173);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__4) | (1L << T__7) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << RETURN) | (1L << BOOLEAN) | (1L << ENTIER) | (1L << DOUBLE) | (1L << IDENTIFIANT) | (1L << NEWLINE))) != 0)) {
					{
					{
					setState(168);
					((FonctionContext)_localctx).instruction = instruction();
					 _localctx.code += ((FonctionContext)_localctx).instruction.code ;
					}
					}
					setState(175);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(176);
				match(T__5);
				 _localctx.code += "RETURN\n"; 
				setState(181);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(178);
						match(NEWLINE);
						}
						} 
					}
					setState(183);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(184);
				((FonctionContext)_localctx).TYPE = match(TYPE);
				setState(185);
				((FonctionContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);

				            tablesSymboles.addFunction((((FonctionContext)_localctx).IDENTIFIANT!=null?((FonctionContext)_localctx).IDENTIFIANT.getText():null),(((FonctionContext)_localctx).TYPE!=null?((FonctionContext)_localctx).TYPE.getText():null));
				            ((FonctionContext)_localctx).code =  "LABEL " + (((FonctionContext)_localctx).IDENTIFIANT!=null?((FonctionContext)_localctx).IDENTIFIANT.getText():null) + "\n"; // pour stocker le type de la fonction, ici c'est (((FonctionContext)_localctx).TYPE!=null?((FonctionContext)_localctx).TYPE.getText():null)
				        
				setState(187);
				match(T__2);
				setState(189);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TYPE) {
					{
					setState(188);
					params();
					}
				}

				setState(191);
				match(T__3);
				setState(192);
				match(T__4);
				setState(194);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
				case 1:
					{
					setState(193);
					match(NEWLINE);
					}
					break;
				}
				setState(201);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==TYPE) {
					{
					{
					setState(196);
					((FonctionContext)_localctx).decl = decl();
					 _localctx.code += ((FonctionContext)_localctx).decl.code; 
					}
					}
					setState(203);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(207);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(204);
						match(NEWLINE);
						}
						} 
					}
					setState(209);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
				}
				setState(215);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__4) | (1L << T__7) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << RETURN) | (1L << BOOLEAN) | (1L << ENTIER) | (1L << DOUBLE) | (1L << IDENTIFIANT) | (1L << NEWLINE))) != 0)) {
					{
					{
					setState(210);
					((FonctionContext)_localctx).instruction = instruction();
					 _localctx.code += ((FonctionContext)_localctx).instruction.code; 
					}
					}
					setState(217);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(218);
				match(T__5);
				 _localctx.code += "RETURN\n"; 
				setState(223);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(220);
						match(NEWLINE);
						}
						} 
					}
					setState(225);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
				}
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			 tablesSymboles.exitFunction();
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

	public static class ParamsContext extends ParserRuleContext {
		public Token TYPE;
		public Token IDENTIFIANT;
		public List<TerminalNode> TYPE() { return getTokens(CalculetteParser.TYPE); }
		public TerminalNode TYPE(int i) {
			return getToken(CalculetteParser.TYPE, i);
		}
		public List<TerminalNode> IDENTIFIANT() { return getTokens(CalculetteParser.IDENTIFIANT); }
		public TerminalNode IDENTIFIANT(int i) {
			return getToken(CalculetteParser.IDENTIFIANT, i);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitParams(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_params);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			((ParamsContext)_localctx).TYPE = match(TYPE);
			setState(229);
			((ParamsContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);

			            // code java gérant une variable locale (arg0)
			            tablesSymboles.addParam((((ParamsContext)_localctx).IDENTIFIANT!=null?((ParamsContext)_localctx).IDENTIFIANT.getText():null),(((ParamsContext)_localctx).TYPE!=null?((ParamsContext)_localctx).TYPE.getText():null));
			        
			setState(237);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6) {
				{
				{
				setState(231);
				match(T__6);
				setState(232);
				((ParamsContext)_localctx).TYPE = match(TYPE);
				setState(233);
				((ParamsContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);

				                // code java gérant une variable locale (argi)
				                tablesSymboles.addParam((((ParamsContext)_localctx).IDENTIFIANT!=null?((ParamsContext)_localctx).IDENTIFIANT.getText():null),(((ParamsContext)_localctx).TYPE!=null?((ParamsContext)_localctx).TYPE.getText():null));
				            
				}
				}
				setState(239);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class ExpressionContext extends ParserRuleContext {
		public String code;
		public String type;
		public ExpressionContext a;
		public Token TYPE;
		public Token BOOLEAN;
		public Token ENTIER;
		public Token DOUBLE;
		public Token IDENTIFIANT;
		public ArgsContext args;
		public Token op;
		public ExpressionContext b;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode TYPE() { return getToken(CalculetteParser.TYPE, 0); }
		public TerminalNode BOOLEAN() { return getToken(CalculetteParser.BOOLEAN, 0); }
		public TerminalNode ENTIER() { return getToken(CalculetteParser.ENTIER, 0); }
		public TerminalNode DOUBLE() { return getToken(CalculetteParser.DOUBLE, 0); }
		public TerminalNode IDENTIFIANT() { return getToken(CalculetteParser.IDENTIFIANT, 0); }
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(241);
				match(T__7);
				setState(242);
				((ExpressionContext)_localctx).a = expression(11);

				            ((ExpressionContext)_localctx).type =  ((ExpressionContext)_localctx).a.type;
				            ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).a.type.equals("double") ? "PUSHF 0.0 \n" + ((ExpressionContext)_localctx).a.code + evalexpr("-","F") : "PUSHI 0 \n" + ((ExpressionContext)_localctx).a.code + evalexpr("-"," ") ;
				        
				}
				break;
			case 2:
				{
				setState(245);
				match(T__2);
				setState(246);
				((ExpressionContext)_localctx).a = expression(0);
				setState(247);
				match(T__3);
				((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).a.code; ((ExpressionContext)_localctx).type =  ((ExpressionContext)_localctx).a.type;
				}
				break;
			case 3:
				{
				setState(250);
				match(T__2);
				setState(251);
				((ExpressionContext)_localctx).TYPE = match(TYPE);
				setState(252);
				match(T__3);
				setState(253);
				((ExpressionContext)_localctx).a = expression(7);

				            ((ExpressionContext)_localctx).type =  (((ExpressionContext)_localctx).TYPE!=null?((ExpressionContext)_localctx).TYPE.getText():null);
				            if((((ExpressionContext)_localctx).TYPE!=null?((ExpressionContext)_localctx).TYPE.getText():null).equals(((ExpressionContext)_localctx).a.type)){
				                ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).a.code;
				            }
				            else if((((ExpressionContext)_localctx).TYPE!=null?((ExpressionContext)_localctx).TYPE.getText():null).equals("double")){
				                ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).a.code + "ITOF\n";
				            }
				            else if((((ExpressionContext)_localctx).TYPE!=null?((ExpressionContext)_localctx).TYPE.getText():null).equals("int")){
				                ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).a.type.equals("double") ? ((ExpressionContext)_localctx).a.code + "FTOI\n" : ((ExpressionContext)_localctx).a.code;
				            }
				            else{ // bool
				                ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).a.code ;
				                _localctx.code += ((ExpressionContext)_localctx).a.type.equals("double") ?  "PUSHF 0.0\nFNEQ\n" : "PUSHI 0\nNEQ\n";
				            }
				        
				}
				break;
			case 4:
				{
				setState(256);
				((ExpressionContext)_localctx).BOOLEAN = match(BOOLEAN);
				 ((ExpressionContext)_localctx).type =  "bool"; ((ExpressionContext)_localctx).code =  (((ExpressionContext)_localctx).BOOLEAN!=null?((ExpressionContext)_localctx).BOOLEAN.getText():null).equals("true") ? "PUSHI 1\n" : "PUSHI 0\n";  
				}
				break;
			case 5:
				{
				setState(258);
				((ExpressionContext)_localctx).ENTIER = match(ENTIER);
				 ((ExpressionContext)_localctx).type =  "int" ; ((ExpressionContext)_localctx).code =  "PUSHI " + (((ExpressionContext)_localctx).ENTIER!=null?((ExpressionContext)_localctx).ENTIER.getText():null) + "\n" ;
				}
				break;
			case 6:
				{
				setState(260);
				((ExpressionContext)_localctx).DOUBLE = match(DOUBLE);
				 ((ExpressionContext)_localctx).type =  "double" ; ((ExpressionContext)_localctx).code =  "PUSHF " + (((ExpressionContext)_localctx).DOUBLE!=null?((ExpressionContext)_localctx).DOUBLE.getText():null) + "\n"; 
				}
				break;
			case 7:
				{
				setState(262);
				((ExpressionContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(263);
				match(T__2);
				setState(264);
				match(T__3);
				 
				            ((ExpressionContext)_localctx).type =  tablesSymboles.getFunction((((ExpressionContext)_localctx).IDENTIFIANT!=null?((ExpressionContext)_localctx).IDENTIFIANT.getText():null));
				            ((ExpressionContext)_localctx).code =  _localctx.type.equals("double") ? "PUSHF 0.0\n" : "PUSHI 0\n" ;
				            _localctx.code += "CALL " + (((ExpressionContext)_localctx).IDENTIFIANT!=null?((ExpressionContext)_localctx).IDENTIFIANT.getText():null) + "\n";
				        
				}
				break;
			case 8:
				{
				setState(266);
				((ExpressionContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(267);
				match(T__2);
				setState(268);
				((ExpressionContext)_localctx).args = args();
				setState(269);
				match(T__3);

				            // PUSHI 0 pour reserver de la place pour la valeur retournée
				            ((ExpressionContext)_localctx).type =  tablesSymboles.getFunction((((ExpressionContext)_localctx).IDENTIFIANT!=null?((ExpressionContext)_localctx).IDENTIFIANT.getText():null));
				            ((ExpressionContext)_localctx).code =  _localctx.type.equals("double") ? "PUSHF 0.0\n" : "PUSHI 0\n" ;
				            _localctx.code += ((ExpressionContext)_localctx).args.code + "CALL " + (((ExpressionContext)_localctx).IDENTIFIANT!=null?((ExpressionContext)_localctx).IDENTIFIANT.getText():null) + "\n" ;
				            for(int i =0 ; i < ((ExpressionContext)_localctx).args.size; i++){ _localctx.code += "POP\n";}
				        
				}
				break;
			case 9:
				{
				setState(272);
				((ExpressionContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				 
				        VariableInfo vi = tablesSymboles.getVar((((ExpressionContext)_localctx).IDENTIFIANT!=null?((ExpressionContext)_localctx).IDENTIFIANT.getText():null)); //pour recupèrer les info de la variable(addresse, type , scoope)
				        ((ExpressionContext)_localctx).type =  vi.type;
				        String pushLorG = (vi.scope == VariableInfo.Scope.GLOBAL) ? "PUSHG " : "PUSHL ";
				        ((ExpressionContext)_localctx).code =  pushLorG +  vi.address + "\n";
				        if(vi.type.equals("double"))
				            _localctx.code += pushLorG + (vi.address + 1) + "\n";
				    
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(288);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(286);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.a = _prevctx;
						_localctx.a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(276);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(277);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__8 || _la==T__9) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(278);
						((ExpressionContext)_localctx).b = expression(11);
						   ((ExpressionContext)_localctx).type =  ((ExpressionContext)_localctx).a.type;
						                      if(((ExpressionContext)_localctx).a.type.equals(((ExpressionContext)_localctx).b.type)){
						                          ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).a.code + ((ExpressionContext)_localctx).b.code;
						                          _localctx.code += _localctx.type.equals("double") ? evalexpr((((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null),"F") : evalexpr((((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null)," ") ;
						                      }else{
						                          ((ExpressionContext)_localctx).type =  "double";
						                          System.err.println("WARRNING : le type de la variable " +(((ExpressionContext)_localctx).IDENTIFIANT!=null?((ExpressionContext)_localctx).IDENTIFIANT.getText():null)+ " ne matche pas à celui de l'expression \n"
						                          + "Un casting sera appliqué pour convertir le type de l'expression vers le type double ou int" );
						                          if(((ExpressionContext)_localctx).a.type.equals("double")){
						                              ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).a.code;
						                              _localctx.code += ((ExpressionContext)_localctx).b.type.equals("double") ? ((ExpressionContext)_localctx).b.code : ((ExpressionContext)_localctx).b.code+ "ITOF\n";
						                              _localctx.code += evalexpr((((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null),"F");
						                          }
						                          else if(((ExpressionContext)_localctx).b.type.equals("double")){
						                              ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).a.type.equals("double") ? ((ExpressionContext)_localctx).a.code : ((ExpressionContext)_localctx).a.code+ "ITOF\n";
						                              _localctx.code += ((ExpressionContext)_localctx).b.code + evalexpr((((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null),"F");
						                          }
						                          else{
						                              ((ExpressionContext)_localctx).type =  "int";
						                              ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).a.code + ((ExpressionContext)_localctx).b.code +  evalexpr((((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null)," ");
						                          }
						                      }
						                  
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.a = _prevctx;
						_localctx.a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(281);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(282);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__7 || _la==T__10) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(283);
						((ExpressionContext)_localctx).b = expression(10);
						  
						                      ((ExpressionContext)_localctx).type =  ((ExpressionContext)_localctx).a.type;
						                      if(((ExpressionContext)_localctx).a.type.equals(((ExpressionContext)_localctx).b.type)){
						                          ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).a.code + ((ExpressionContext)_localctx).b.code;
						                          _localctx.code += _localctx.type.equals("double") ? evalexpr((((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null),"F") : evalexpr((((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null)," ") ;
						                      }else{
						                          ((ExpressionContext)_localctx).type =  "double";
						                          System.err.println("WARRNING : le type de la variable " +(((ExpressionContext)_localctx).IDENTIFIANT!=null?((ExpressionContext)_localctx).IDENTIFIANT.getText():null)+ " ne matche pas à celui de l'expression \n"
						                          + "Un casting sera appliqué pour convertir le type de l'expression vers le type double ou int" );
						                          if(((ExpressionContext)_localctx).a.type.equals("double")){
						                              ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).a.code;
						                              _localctx.code += ((ExpressionContext)_localctx).b.type.equals("double") ? ((ExpressionContext)_localctx).b.code : ((ExpressionContext)_localctx).b.code+ "ITOF\n";
						                              _localctx.code += evalexpr((((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null),"F");
						                          }
						                          else if(((ExpressionContext)_localctx).b.type.equals("double")){
						                              ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).a.type.equals("double") ? ((ExpressionContext)_localctx).a.code : ((ExpressionContext)_localctx).a.code+ "ITOF\n";
						                              _localctx.code += ((ExpressionContext)_localctx).b.code + evalexpr((((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null),"F");
						                          }
						                          else{
						                              ((ExpressionContext)_localctx).type =  "int";
						                              ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).a.code + ((ExpressionContext)_localctx).b.code +  evalexpr((((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null)," ");
						                          }
						                      }
						                  
						}
						break;
					}
					} 
				}
				setState(290);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
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

	public static class ArgsContext extends ParserRuleContext {
		public String code;
		public int size;
		public ExpressionContext expression;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitArgs(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_args);
		 ((ArgsContext)_localctx).code =  new String(); ((ArgsContext)_localctx).size =  0; 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(302);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__7) | (1L << BOOLEAN) | (1L << ENTIER) | (1L << DOUBLE) | (1L << IDENTIFIANT))) != 0)) {
				{
				setState(291);
				((ArgsContext)_localctx).expression = expression(0);

				        // code java pour première expression pour arg
				        ((ArgsContext)_localctx).code =  ((ArgsContext)_localctx).expression.code;
				        _localctx.size += ((ArgsContext)_localctx).expression.type.equals("double") ? 2 : 1;
				    
				setState(299);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__6) {
					{
					{
					setState(293);
					match(T__6);
					setState(294);
					((ArgsContext)_localctx).expression = expression(0);

					        // code java pour expression suivante pour arg
					        _localctx.code += ((ArgsContext)_localctx).expression.code;
					        _localctx.size += ((ArgsContext)_localctx).expression.type.equals("double") ? 2 : 1;
					    
					}
					}
					setState(301);
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

	public static class AffichageContext extends ParserRuleContext {
		public String code;
		public ExpressionContext expression;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AffichageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_affichage; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterAffichage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitAffichage(this);
		}
	}

	public final AffichageContext affichage() throws RecognitionException {
		AffichageContext _localctx = new AffichageContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_affichage);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304);
			match(T__11);
			setState(305);
			match(T__2);
			setState(306);
			((AffichageContext)_localctx).expression = expression(0);
			setState(307);
			match(T__3);

			            String onPopOrTwoPop = ((AffichageContext)_localctx).expression.type.equals("double") ? "F\nPOP\n" : "\n";
			            ((AffichageContext)_localctx).code =  ((AffichageContext)_localctx).expression.code + "WRITE" + onPopOrTwoPop + "POP\n" ;
			        
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

	public static class EcritureContext extends ParserRuleContext {
		public String code;
		public Token IDENTIFIANT;
		public TerminalNode IDENTIFIANT() { return getToken(CalculetteParser.IDENTIFIANT, 0); }
		public EcritureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ecriture; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterEcriture(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitEcriture(this);
		}
	}

	public final EcritureContext ecriture() throws RecognitionException {
		EcritureContext _localctx = new EcritureContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_ecriture);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(310);
			match(T__12);
			setState(311);
			match(T__2);
			setState(312);
			((EcritureContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
			setState(313);
			match(T__3);

			            VariableInfo vi = tablesSymboles.getVar((((EcritureContext)_localctx).IDENTIFIANT!=null?((EcritureContext)_localctx).IDENTIFIANT.getText():null)); //pour recupèrer les info de la variable(addresse, type , scoope)
			            String scope = (vi.scope == VariableInfo.Scope.GLOBAL) ? "G " : "L ";
			            if(vi.type.equals("double"))
			                ((EcritureContext)_localctx).code =  "READF\nSTORE" + scope + (vi.address + 1) + "\nSTORE"+ scope + vi.address + "\n"; 
			            else
			                ((EcritureContext)_localctx).code =  "READ\nSTORE" + scope + vi.address + "\n" ; 
			        
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

	public static class BlocContext extends ParserRuleContext {
		public String code;
		public InstructionContext instruction;
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(CalculetteParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(CalculetteParser.NEWLINE, i);
		}
		public BlocContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterBloc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitBloc(this);
		}
	}

	public final BlocContext bloc() throws RecognitionException {
		BlocContext _localctx = new BlocContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_bloc);
		 ((BlocContext)_localctx).code =  new String(); 
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(316);
			match(T__4);
			setState(322);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__4) | (1L << T__7) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << RETURN) | (1L << BOOLEAN) | (1L << ENTIER) | (1L << DOUBLE) | (1L << IDENTIFIANT) | (1L << NEWLINE))) != 0)) {
				{
				{
				setState(317);
				((BlocContext)_localctx).instruction = instruction();
				_localctx.code+=((BlocContext)_localctx).instruction.code;
				}
				}
				setState(324);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(325);
			match(T__5);
			setState(329);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(326);
					match(NEWLINE);
					}
					} 
				}
				setState(331);
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
			exitRule();
		}
		return _localctx;
	}

	public static class BoucleWhileContext extends ParserRuleContext {
		public String code;
		public ConditionContext condition;
		public InstructionContext instruction;
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public InstructionContext instruction() {
			return getRuleContext(InstructionContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(CalculetteParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(CalculetteParser.NEWLINE, i);
		}
		public BoucleWhileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boucleWhile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterBoucleWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitBoucleWhile(this);
		}
	}

	public final BoucleWhileContext boucleWhile() throws RecognitionException {
		BoucleWhileContext _localctx = new BoucleWhileContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_boucleWhile);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(332);
			match(T__13);
			setState(333);
			match(T__2);
			setState(334);
			((BoucleWhileContext)_localctx).condition = condition();
			setState(335);
			match(T__3);
			setState(339);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(336);
					match(NEWLINE);
					}
					} 
				}
				setState(341);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			}
			setState(342);
			((BoucleWhileContext)_localctx).instruction = instruction();

			            String labelDebut = getNewLabel();
			            String labelFin = getNewLabel();
			            ((BoucleWhileContext)_localctx).code =  "LABEL " + labelDebut + ((BoucleWhileContext)_localctx).condition.code + "JUMPF " + labelFin + 
			            ((BoucleWhileContext)_localctx).instruction.code + "JUMP " + labelDebut + "LABEL " + labelFin ;
			        
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

	public static class BoucleForContext extends ParserRuleContext {
		public String code;
		public AssignationContext a;
		public ConditionContext b;
		public AssignationContext c;
		public InstructionContext d;
		public List<AssignationContext> assignation() {
			return getRuleContexts(AssignationContext.class);
		}
		public AssignationContext assignation(int i) {
			return getRuleContext(AssignationContext.class,i);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public InstructionContext instruction() {
			return getRuleContext(InstructionContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(CalculetteParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(CalculetteParser.NEWLINE, i);
		}
		public BoucleForContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boucleFor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterBoucleFor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitBoucleFor(this);
		}
	}

	public final BoucleForContext boucleFor() throws RecognitionException {
		BoucleForContext _localctx = new BoucleForContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_boucleFor);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(345);
			match(T__14);
			setState(346);
			match(T__2);
			setState(347);
			((BoucleForContext)_localctx).a = assignation();
			setState(348);
			match(T__15);
			setState(349);
			((BoucleForContext)_localctx).b = condition();
			setState(350);
			match(T__15);
			setState(351);
			((BoucleForContext)_localctx).c = assignation();
			setState(352);
			match(T__3);
			setState(356);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(353);
					match(NEWLINE);
					}
					} 
				}
				setState(358);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			}
			setState(359);
			((BoucleForContext)_localctx).d = instruction();

			            String labelDebut = getNewLabel();
			            String labelFin = getNewLabel();
			            ((BoucleForContext)_localctx).code =  ((BoucleForContext)_localctx).a.code + "LABEL " + labelDebut + ((BoucleForContext)_localctx).b.code + "JUMPF " + labelFin + ((BoucleForContext)_localctx).d.code + ((BoucleForContext)_localctx).c.code + "JUMP " + labelDebut + "LABEL " + labelFin;
			        
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

	public static class IfElseContext extends ParserRuleContext {
		public String code;
		public ConditionContext condition;
		public InstructionContext instruction;
		public InstructionContext a;
		public InstructionContext b;
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(CalculetteParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(CalculetteParser.NEWLINE, i);
		}
		public IfElseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifElse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterIfElse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitIfElse(this);
		}
	}

	public final IfElseContext ifElse() throws RecognitionException {
		IfElseContext _localctx = new IfElseContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_ifElse);
		int _la;
		try {
			int _alt;
			setState(396);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(362);
				match(T__16);
				setState(363);
				match(T__2);
				setState(364);
				((IfElseContext)_localctx).condition = condition();
				setState(365);
				match(T__3);
				setState(369);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(366);
						match(NEWLINE);
						}
						} 
					}
					setState(371);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
				}
				setState(372);
				((IfElseContext)_localctx).instruction = instruction();

				            String labelFinIf = getNewLabel();
				            ((IfElseContext)_localctx).code =  ((IfElseContext)_localctx).condition.code + "JUMPF " + labelFinIf + ((IfElseContext)_localctx).instruction.code + "LABEL " + labelFinIf;
				        
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(375);
				match(T__16);
				setState(376);
				match(T__2);
				setState(377);
				((IfElseContext)_localctx).condition = condition();
				setState(378);
				match(T__3);
				setState(382);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(379);
						match(NEWLINE);
						}
						} 
					}
					setState(384);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
				}
				setState(385);
				((IfElseContext)_localctx).a = instruction();
				setState(389);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(386);
					match(NEWLINE);
					}
					}
					setState(391);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(392);
				match(T__17);
				setState(393);
				((IfElseContext)_localctx).b = instruction();

				            String labelFinIf = getNewLabel();
				            String labelFinElseIf = getNewLabel();
				            ((IfElseContext)_localctx).code =  ((IfElseContext)_localctx).condition.code + "JUMPF " + labelFinIf + ((IfElseContext)_localctx).a.code + "JUMP " + labelFinElseIf + "LABEL " + labelFinIf + ((IfElseContext)_localctx).b.code + "LABEL " + labelFinElseIf;
				        
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

	public static class ConditionContext extends ParserRuleContext {
		public String code;
		public OperateurBooleenContext operateurBooleen;
		public OperateurRationnelContext operateurRationnel;
		public OperateurBooleenContext operateurBooleen() {
			return getRuleContext(OperateurBooleenContext.class,0);
		}
		public OperateurRationnelContext operateurRationnel() {
			return getRuleContext(OperateurRationnelContext.class,0);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitCondition(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_condition);
		try {
			setState(404);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(398);
				((ConditionContext)_localctx).operateurBooleen = operateurBooleen(0);
				 ((ConditionContext)_localctx).code =  ((ConditionContext)_localctx).operateurBooleen.code;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(401);
				((ConditionContext)_localctx).operateurRationnel = operateurRationnel();
				 ((ConditionContext)_localctx).code =  ((ConditionContext)_localctx).operateurRationnel.code;
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

	public static class OperateurRationnelContext extends ParserRuleContext {
		public String code;
		public ExpressionContext a;
		public Token op;
		public ExpressionContext b;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public OperateurRationnelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operateurRationnel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterOperateurRationnel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitOperateurRationnel(this);
		}
	}

	public final OperateurRationnelContext operateurRationnel() throws RecognitionException {
		OperateurRationnelContext _localctx = new OperateurRationnelContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_operateurRationnel);
		int _la;
		try {
			setState(414);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(406);
				((OperateurRationnelContext)_localctx).a = expression(0);
				setState(407);
				((OperateurRationnelContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24))) != 0)) ) {
					((OperateurRationnelContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(408);
				((OperateurRationnelContext)_localctx).b = expression(0);

				            if(((OperateurRationnelContext)_localctx).a.type.equals(((OperateurRationnelContext)_localctx).b.type)){
				                ((OperateurRationnelContext)_localctx).code =  ((OperateurRationnelContext)_localctx).a.code + ((OperateurRationnelContext)_localctx).b.code;
				                _localctx.code += ((OperateurRationnelContext)_localctx).a.type.equals("double") ? evalType((((OperateurRationnelContext)_localctx).op!=null?((OperateurRationnelContext)_localctx).op.getText():null),"F") : evalType((((OperateurRationnelContext)_localctx).op!=null?((OperateurRationnelContext)_localctx).op.getText():null),"") ;
				            }else{
				                System.err.println("WARRNING casting explicite de int vers double ");
				                if(((OperateurRationnelContext)_localctx).a.type.equals("double")){
				                    ((OperateurRationnelContext)_localctx).code =  ((OperateurRationnelContext)_localctx).a.code;
				                    _localctx.code += ((OperateurRationnelContext)_localctx).b.type.equals("double") ? ((OperateurRationnelContext)_localctx).b.code : ((OperateurRationnelContext)_localctx).b.code + "ITOF\n";
				                    _localctx.code += evalType((((OperateurRationnelContext)_localctx).op!=null?((OperateurRationnelContext)_localctx).op.getText():null),"F");
				                }
				                else if(((OperateurRationnelContext)_localctx).b.type.equals("double")){
				                    ((OperateurRationnelContext)_localctx).code =  ((OperateurRationnelContext)_localctx).a.type.equals("double") ? ((OperateurRationnelContext)_localctx).a.code : ((OperateurRationnelContext)_localctx).a.code + "ITOF\n";
				                    _localctx.code += ((OperateurRationnelContext)_localctx).b.code + evalType((((OperateurRationnelContext)_localctx).op!=null?((OperateurRationnelContext)_localctx).op.getText():null),"F");
				                }
				                else{
				                    ((OperateurRationnelContext)_localctx).code =  ((OperateurRationnelContext)_localctx).a.code + ((OperateurRationnelContext)_localctx).b.code + evalType((((OperateurRationnelContext)_localctx).op!=null?((OperateurRationnelContext)_localctx).op.getText():null),"");
				                }
				            }

				           /*  String cast = ""; // pour faire un casting explicite
				            if(((OperateurRationnelContext)_localctx).a.type.equals("bool") || ((OperateurRationnelContext)_localctx).b.type.equals("bool")){
				                System.err.println("Opérateur incorrect : '"+(((OperateurRationnelContext)_localctx).op!=null?((OperateurRationnelContext)_localctx).op.getText():null)+"'");
				                throw new IllegalArgumentException("On ne peut pas comparer des boolean avec cet opérateur '"+(((OperateurRationnelContext)_localctx).op!=null?((OperateurRationnelContext)_localctx).op.getText():null)+"'");
				            }
				            else if(((OperateurRationnelContext)_localctx).a.type.equals("double")){
				                cast = ((OperateurRationnelContext)_localctx).b.type.equals("double") ? ((OperateurRationnelContext)_localctx).b.code : ((OperateurRationnelContext)_localctx).b.code + "ITOF\n";
				                ((OperateurRationnelContext)_localctx).code =  ((OperateurRationnelContext)_localctx).a.code + cast + evalType((((OperateurRationnelContext)_localctx).op!=null?((OperateurRationnelContext)_localctx).op.getText():null),"F");
				            }
				            else{
				                cast = ((OperateurRationnelContext)_localctx).b.type.equals("int") ? ((OperateurRationnelContext)_localctx).b.code : ((OperateurRationnelContext)_localctx).b.code + "FTOI\n";
				                ((OperateurRationnelContext)_localctx).code =  ((OperateurRationnelContext)_localctx).a.code + cast + evalType((((OperateurRationnelContext)_localctx).op!=null?((OperateurRationnelContext)_localctx).op.getText():null),"");
				            }*/
				        
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(411);
				((OperateurRationnelContext)_localctx).a = expression(0);
				 
				            ((OperateurRationnelContext)_localctx).code =  ((OperateurRationnelContext)_localctx).a.code ;
				            _localctx.code += ((OperateurRationnelContext)_localctx).a.type.equals("double") ? "PUSHF 0.0\nFNEQ\n" : "PUSHI 0\nNEQ\n";
				        
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

	public static class OperateurBooleenContext extends ParserRuleContext {
		public String code;
		public OperateurBooleenContext a;
		public Token BOOLEAN;
		public OperateurBooleenContext operateurBooleen;
		public OperateurRationnelContext operateurRationnel;
		public OperateurBooleenContext b;
		public TerminalNode BOOLEAN() { return getToken(CalculetteParser.BOOLEAN, 0); }
		public List<OperateurBooleenContext> operateurBooleen() {
			return getRuleContexts(OperateurBooleenContext.class);
		}
		public OperateurBooleenContext operateurBooleen(int i) {
			return getRuleContext(OperateurBooleenContext.class,i);
		}
		public OperateurRationnelContext operateurRationnel() {
			return getRuleContext(OperateurRationnelContext.class,0);
		}
		public OperateurBooleenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operateurBooleen; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterOperateurBooleen(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitOperateurBooleen(this);
		}
	}

	public final OperateurBooleenContext operateurBooleen() throws RecognitionException {
		return operateurBooleen(0);
	}

	private OperateurBooleenContext operateurBooleen(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		OperateurBooleenContext _localctx = new OperateurBooleenContext(_ctx, _parentState);
		OperateurBooleenContext _prevctx = _localctx;
		int _startState = 34;
		enterRecursionRule(_localctx, 34, RULE_operateurBooleen, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(426);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				{
				setState(417);
				((OperateurBooleenContext)_localctx).BOOLEAN = match(BOOLEAN);
				 ((OperateurBooleenContext)_localctx).code =  (((OperateurBooleenContext)_localctx).BOOLEAN!=null?((OperateurBooleenContext)_localctx).BOOLEAN.getText():null).equals("true") ? "PUSHI 1\n" : "PUSHI 0\n"; 
				}
				break;
			case 2:
				{
				setState(419);
				match(T__28);
				setState(420);
				((OperateurBooleenContext)_localctx).operateurBooleen = operateurBooleen(2);
				 ((OperateurBooleenContext)_localctx).code =  ((OperateurBooleenContext)_localctx).operateurBooleen.code + "PUSHI 1\nNEQ\n";
				}
				break;
			case 3:
				{
				setState(423);
				((OperateurBooleenContext)_localctx).operateurRationnel = operateurRationnel();
				 ((OperateurBooleenContext)_localctx).code =  ((OperateurBooleenContext)_localctx).operateurRationnel.code;
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(445);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(443);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
					case 1:
						{
						_localctx = new OperateurBooleenContext(_parentctx, _parentState);
						_localctx.a = _prevctx;
						_localctx.a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_operateurBooleen);
						setState(428);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(429);
						match(T__25);
						setState(430);
						((OperateurBooleenContext)_localctx).b = ((OperateurBooleenContext)_localctx).operateurBooleen = operateurBooleen(6);
						 ((OperateurBooleenContext)_localctx).code =  ((OperateurBooleenContext)_localctx).a.code + ((OperateurBooleenContext)_localctx).b.code + "PUSHI 1\nNEQ\nSUP\n";
						}
						break;
					case 2:
						{
						_localctx = new OperateurBooleenContext(_parentctx, _parentState);
						_localctx.a = _prevctx;
						_localctx.a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_operateurBooleen);
						setState(433);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(434);
						match(T__26);
						setState(435);
						((OperateurBooleenContext)_localctx).b = ((OperateurBooleenContext)_localctx).operateurBooleen = operateurBooleen(5);
						 ((OperateurBooleenContext)_localctx).code =  ((OperateurBooleenContext)_localctx).a.code + ((OperateurBooleenContext)_localctx).b.code + "PUSHI 1\nSUPEQ\nADD\n";
						}
						break;
					case 3:
						{
						_localctx = new OperateurBooleenContext(_parentctx, _parentState);
						_localctx.a = _prevctx;
						_localctx.a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_operateurBooleen);
						setState(438);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(439);
						match(T__27);
						setState(440);
						((OperateurBooleenContext)_localctx).b = ((OperateurBooleenContext)_localctx).operateurBooleen = operateurBooleen(4);
						 ((OperateurBooleenContext)_localctx).code =  ((OperateurBooleenContext)_localctx).a.code + ((OperateurBooleenContext)_localctx).b.code + "PUSHI 1\nEQUAL\nNEQ\n";
						}
						break;
					}
					} 
				}
				setState(447);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
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

	public static class FinInstructionContext extends ParserRuleContext {
		public List<TerminalNode> NEWLINE() { return getTokens(CalculetteParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(CalculetteParser.NEWLINE, i);
		}
		public FinInstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_finInstruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterFinInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitFinInstruction(this);
		}
	}

	public final FinInstructionContext finInstruction() throws RecognitionException {
		FinInstructionContext _localctx = new FinInstructionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_finInstruction);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(449); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(448);
					_la = _input.LA(1);
					if ( !(_la==T__15 || _la==NEWLINE) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(451); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 7:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		case 17:
			return operateurBooleen_sempred((OperateurBooleenContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 10);
		case 1:
			return precpred(_ctx, 9);
		}
		return true;
	}
	private boolean operateurBooleen_sempred(OperateurBooleenContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 5);
		case 3:
			return precpred(_ctx, 4);
		case 4:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3.\u01c8\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5"+
		"\2\65\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3A\n\3\3\4\3\4\3\4"+
		"\3\5\3\5\3\5\7\5I\n\5\f\5\16\5L\13\5\3\5\3\5\7\5P\n\5\f\5\16\5S\13\5\3"+
		"\5\3\5\3\5\7\5X\n\5\f\5\16\5[\13\5\3\5\7\5^\n\5\f\5\16\5a\13\5\3\5\3\5"+
		"\3\5\3\5\7\5g\n\5\f\5\16\5j\13\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u0092\n\6\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\5\7\u009b\n\7\3\7\3\7\3\7\7\7\u00a0\n\7\f\7\16\7\u00a3"+
		"\13\7\3\7\7\7\u00a6\n\7\f\7\16\7\u00a9\13\7\3\7\3\7\3\7\7\7\u00ae\n\7"+
		"\f\7\16\7\u00b1\13\7\3\7\3\7\3\7\7\7\u00b6\n\7\f\7\16\7\u00b9\13\7\3\7"+
		"\3\7\3\7\3\7\3\7\5\7\u00c0\n\7\3\7\3\7\3\7\5\7\u00c5\n\7\3\7\3\7\3\7\7"+
		"\7\u00ca\n\7\f\7\16\7\u00cd\13\7\3\7\7\7\u00d0\n\7\f\7\16\7\u00d3\13\7"+
		"\3\7\3\7\3\7\7\7\u00d8\n\7\f\7\16\7\u00db\13\7\3\7\3\7\3\7\7\7\u00e0\n"+
		"\7\f\7\16\7\u00e3\13\7\5\7\u00e5\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u00ee"+
		"\n\b\f\b\16\b\u00f1\13\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\5\t\u0115\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\7\t\u0121\n\t\f\t\16\t\u0124\13\t\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u012c"+
		"\n\n\f\n\16\n\u012f\13\n\5\n\u0131\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\7\r\u0143\n\r\f\r\16\r\u0146\13"+
		"\r\3\r\3\r\7\r\u014a\n\r\f\r\16\r\u014d\13\r\3\16\3\16\3\16\3\16\3\16"+
		"\7\16\u0154\n\16\f\16\16\16\u0157\13\16\3\16\3\16\3\16\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\7\17\u0165\n\17\f\17\16\17\u0168\13\17"+
		"\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\7\20\u0172\n\20\f\20\16\20\u0175"+
		"\13\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u017f\n\20\f\20\16"+
		"\20\u0182\13\20\3\20\3\20\7\20\u0186\n\20\f\20\16\20\u0189\13\20\3\20"+
		"\3\20\3\20\3\20\5\20\u018f\n\20\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u0197"+
		"\n\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u01a1\n\22\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u01ad\n\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\7\23\u01be"+
		"\n\23\f\23\16\23\u01c1\13\23\3\24\6\24\u01c4\n\24\r\24\16\24\u01c5\3\24"+
		"\2\4\20$\25\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&\2\6\3\2\13\f\4"+
		"\2\n\n\r\r\3\2\25\33\4\2\22\22--\2\u01ed\2\64\3\2\2\2\4@\3\2\2\2\6B\3"+
		"\2\2\2\bJ\3\2\2\2\n\u0091\3\2\2\2\f\u00e4\3\2\2\2\16\u00e6\3\2\2\2\20"+
		"\u0114\3\2\2\2\22\u0130\3\2\2\2\24\u0132\3\2\2\2\26\u0138\3\2\2\2\30\u013e"+
		"\3\2\2\2\32\u014e\3\2\2\2\34\u015b\3\2\2\2\36\u018e\3\2\2\2 \u0196\3\2"+
		"\2\2\"\u01a0\3\2\2\2$\u01ac\3\2\2\2&\u01c3\3\2\2\2()\7+\2\2)*\7,\2\2*"+
		"+\5&\24\2+,\b\2\1\2,\65\3\2\2\2-.\7+\2\2./\7,\2\2/\60\7\3\2\2\60\61\5"+
		"\20\t\2\61\62\5&\24\2\62\63\b\2\1\2\63\65\3\2\2\2\64(\3\2\2\2\64-\3\2"+
		"\2\2\65\3\3\2\2\2\66\67\7,\2\2\678\7\3\2\289\5\20\t\29:\b\3\1\2:A\3\2"+
		"\2\2;<\7,\2\2<=\7\4\2\2=>\5\20\t\2>?\b\3\1\2?A\3\2\2\2@\66\3\2\2\2@;\3"+
		"\2\2\2A\5\3\2\2\2BC\5\b\5\2CD\7\2\2\3D\7\3\2\2\2EF\5\2\2\2FG\b\5\1\2G"+
		"I\3\2\2\2HE\3\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2KM\3\2\2\2LJ\3\2\2\2"+
		"MQ\b\5\1\2NP\7-\2\2ON\3\2\2\2PS\3\2\2\2QO\3\2\2\2QR\3\2\2\2RY\3\2\2\2"+
		"SQ\3\2\2\2TU\5\f\7\2UV\b\5\1\2VX\3\2\2\2WT\3\2\2\2X[\3\2\2\2YW\3\2\2\2"+
		"YZ\3\2\2\2Z_\3\2\2\2[Y\3\2\2\2\\^\7-\2\2]\\\3\2\2\2^a\3\2\2\2_]\3\2\2"+
		"\2_`\3\2\2\2`b\3\2\2\2a_\3\2\2\2bh\b\5\1\2cd\5\n\6\2de\b\5\1\2eg\3\2\2"+
		"\2fc\3\2\2\2gj\3\2\2\2hf\3\2\2\2hi\3\2\2\2ik\3\2\2\2jh\3\2\2\2kl\b\5\1"+
		"\2l\t\3\2\2\2mn\5\20\t\2no\5&\24\2op\b\6\1\2p\u0092\3\2\2\2qr\5\30\r\2"+
		"rs\b\6\1\2s\u0092\3\2\2\2tu\5\32\16\2uv\b\6\1\2v\u0092\3\2\2\2wx\5\34"+
		"\17\2xy\b\6\1\2y\u0092\3\2\2\2z{\5\36\20\2{|\b\6\1\2|\u0092\3\2\2\2}~"+
		"\5\24\13\2~\177\5&\24\2\177\u0080\b\6\1\2\u0080\u0092\3\2\2\2\u0081\u0082"+
		"\5\26\f\2\u0082\u0083\5&\24\2\u0083\u0084\b\6\1\2\u0084\u0092\3\2\2\2"+
		"\u0085\u0086\5\4\3\2\u0086\u0087\5&\24\2\u0087\u0088\b\6\1\2\u0088\u0092"+
		"\3\2\2\2\u0089\u008a\7!\2\2\u008a\u008b\5\20\t\2\u008b\u008c\5&\24\2\u008c"+
		"\u008d\b\6\1\2\u008d\u0092\3\2\2\2\u008e\u008f\5&\24\2\u008f\u0090\b\6"+
		"\1\2\u0090\u0092\3\2\2\2\u0091m\3\2\2\2\u0091q\3\2\2\2\u0091t\3\2\2\2"+
		"\u0091w\3\2\2\2\u0091z\3\2\2\2\u0091}\3\2\2\2\u0091\u0081\3\2\2\2\u0091"+
		"\u0085\3\2\2\2\u0091\u0089\3\2\2\2\u0091\u008e\3\2\2\2\u0092\13\3\2\2"+
		"\2\u0093\u0094\7+\2\2\u0094\u0095\7,\2\2\u0095\u0096\b\7\1\2\u0096\u0097"+
		"\7\5\2\2\u0097\u0098\7\6\2\2\u0098\u009a\7\7\2\2\u0099\u009b\7-\2\2\u009a"+
		"\u0099\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u00a1\3\2\2\2\u009c\u009d\5\2"+
		"\2\2\u009d\u009e\b\7\1\2\u009e\u00a0\3\2\2\2\u009f\u009c\3\2\2\2\u00a0"+
		"\u00a3\3\2\2\2\u00a1\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a7\3\2"+
		"\2\2\u00a3\u00a1\3\2\2\2\u00a4\u00a6\7-\2\2\u00a5\u00a4\3\2\2\2\u00a6"+
		"\u00a9\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00af\3\2"+
		"\2\2\u00a9\u00a7\3\2\2\2\u00aa\u00ab\5\n\6\2\u00ab\u00ac\b\7\1\2\u00ac"+
		"\u00ae\3\2\2\2\u00ad\u00aa\3\2\2\2\u00ae\u00b1\3\2\2\2\u00af\u00ad\3\2"+
		"\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b2\3\2\2\2\u00b1\u00af\3\2\2\2\u00b2"+
		"\u00b3\7\b\2\2\u00b3\u00b7\b\7\1\2\u00b4\u00b6\7-\2\2\u00b5\u00b4\3\2"+
		"\2\2\u00b6\u00b9\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8"+
		"\u00e5\3\2\2\2\u00b9\u00b7\3\2\2\2\u00ba\u00bb\7+\2\2\u00bb\u00bc\7,\2"+
		"\2\u00bc\u00bd\b\7\1\2\u00bd\u00bf\7\5\2\2\u00be\u00c0\5\16\b\2\u00bf"+
		"\u00be\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c2\7\6"+
		"\2\2\u00c2\u00c4\7\7\2\2\u00c3\u00c5\7-\2\2\u00c4\u00c3\3\2\2\2\u00c4"+
		"\u00c5\3\2\2\2\u00c5\u00cb\3\2\2\2\u00c6\u00c7\5\2\2\2\u00c7\u00c8\b\7"+
		"\1\2\u00c8\u00ca\3\2\2\2\u00c9\u00c6\3\2\2\2\u00ca\u00cd\3\2\2\2\u00cb"+
		"\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00d1\3\2\2\2\u00cd\u00cb\3\2"+
		"\2\2\u00ce\u00d0\7-\2\2\u00cf\u00ce\3\2\2\2\u00d0\u00d3\3\2\2\2\u00d1"+
		"\u00cf\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d9\3\2\2\2\u00d3\u00d1\3\2"+
		"\2\2\u00d4\u00d5\5\n\6\2\u00d5\u00d6\b\7\1\2\u00d6\u00d8\3\2\2\2\u00d7"+
		"\u00d4\3\2\2\2\u00d8\u00db\3\2\2\2\u00d9\u00d7\3\2\2\2\u00d9\u00da\3\2"+
		"\2\2\u00da\u00dc\3\2\2\2\u00db\u00d9\3\2\2\2\u00dc\u00dd\7\b\2\2\u00dd"+
		"\u00e1\b\7\1\2\u00de\u00e0\7-\2\2\u00df\u00de\3\2\2\2\u00e0\u00e3\3\2"+
		"\2\2\u00e1\u00df\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e5\3\2\2\2\u00e3"+
		"\u00e1\3\2\2\2\u00e4\u0093\3\2\2\2\u00e4\u00ba\3\2\2\2\u00e5\r\3\2\2\2"+
		"\u00e6\u00e7\7+\2\2\u00e7\u00e8\7,\2\2\u00e8\u00ef\b\b\1\2\u00e9\u00ea"+
		"\7\t\2\2\u00ea\u00eb\7+\2\2\u00eb\u00ec\7,\2\2\u00ec\u00ee\b\b\1\2\u00ed"+
		"\u00e9\3\2\2\2\u00ee\u00f1\3\2\2\2\u00ef\u00ed\3\2\2\2\u00ef\u00f0\3\2"+
		"\2\2\u00f0\17\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f2\u00f3\b\t\1\2\u00f3\u00f4"+
		"\7\n\2\2\u00f4\u00f5\5\20\t\r\u00f5\u00f6\b\t\1\2\u00f6\u0115\3\2\2\2"+
		"\u00f7\u00f8\7\5\2\2\u00f8\u00f9\5\20\t\2\u00f9\u00fa\7\6\2\2\u00fa\u00fb"+
		"\b\t\1\2\u00fb\u0115\3\2\2\2\u00fc\u00fd\7\5\2\2\u00fd\u00fe\7+\2\2\u00fe"+
		"\u00ff\7\6\2\2\u00ff\u0100\5\20\t\t\u0100\u0101\b\t\1\2\u0101\u0115\3"+
		"\2\2\2\u0102\u0103\7&\2\2\u0103\u0115\b\t\1\2\u0104\u0105\7\'\2\2\u0105"+
		"\u0115\b\t\1\2\u0106\u0107\7(\2\2\u0107\u0115\b\t\1\2\u0108\u0109\7,\2"+
		"\2\u0109\u010a\7\5\2\2\u010a\u010b\7\6\2\2\u010b\u0115\b\t\1\2\u010c\u010d"+
		"\7,\2\2\u010d\u010e\7\5\2\2\u010e\u010f\5\22\n\2\u010f\u0110\7\6\2\2\u0110"+
		"\u0111\b\t\1\2\u0111\u0115\3\2\2\2\u0112\u0113\7,\2\2\u0113\u0115\b\t"+
		"\1\2\u0114\u00f2\3\2\2\2\u0114\u00f7\3\2\2\2\u0114\u00fc\3\2\2\2\u0114"+
		"\u0102\3\2\2\2\u0114\u0104\3\2\2\2\u0114\u0106\3\2\2\2\u0114\u0108\3\2"+
		"\2\2\u0114\u010c\3\2\2\2\u0114\u0112\3\2\2\2\u0115\u0122\3\2\2\2\u0116"+
		"\u0117\f\f\2\2\u0117\u0118\t\2\2\2\u0118\u0119\5\20\t\r\u0119\u011a\b"+
		"\t\1\2\u011a\u0121\3\2\2\2\u011b\u011c\f\13\2\2\u011c\u011d\t\3\2\2\u011d"+
		"\u011e\5\20\t\f\u011e\u011f\b\t\1\2\u011f\u0121\3\2\2\2\u0120\u0116\3"+
		"\2\2\2\u0120\u011b\3\2\2\2\u0121\u0124\3\2\2\2\u0122\u0120\3\2\2\2\u0122"+
		"\u0123\3\2\2\2\u0123\21\3\2\2\2\u0124\u0122\3\2\2\2\u0125\u0126\5\20\t"+
		"\2\u0126\u012d\b\n\1\2\u0127\u0128\7\t\2\2\u0128\u0129\5\20\t\2\u0129"+
		"\u012a\b\n\1\2\u012a\u012c\3\2\2\2\u012b\u0127\3\2\2\2\u012c\u012f\3\2"+
		"\2\2\u012d\u012b\3\2\2\2\u012d\u012e\3\2\2\2\u012e\u0131\3\2\2\2\u012f"+
		"\u012d\3\2\2\2\u0130\u0125\3\2\2\2\u0130\u0131\3\2\2\2\u0131\23\3\2\2"+
		"\2\u0132\u0133\7\16\2\2\u0133\u0134\7\5\2\2\u0134\u0135\5\20\t\2\u0135"+
		"\u0136\7\6\2\2\u0136\u0137\b\13\1\2\u0137\25\3\2\2\2\u0138\u0139\7\17"+
		"\2\2\u0139\u013a\7\5\2\2\u013a\u013b\7,\2\2\u013b\u013c\7\6\2\2\u013c"+
		"\u013d\b\f\1\2\u013d\27\3\2\2\2\u013e\u0144\7\7\2\2\u013f\u0140\5\n\6"+
		"\2\u0140\u0141\b\r\1\2\u0141\u0143\3\2\2\2\u0142\u013f\3\2\2\2\u0143\u0146"+
		"\3\2\2\2\u0144\u0142\3\2\2\2\u0144\u0145\3\2\2\2\u0145\u0147\3\2\2\2\u0146"+
		"\u0144\3\2\2\2\u0147\u014b\7\b\2\2\u0148\u014a\7-\2\2\u0149\u0148\3\2"+
		"\2\2\u014a\u014d\3\2\2\2\u014b\u0149\3\2\2\2\u014b\u014c\3\2\2\2\u014c"+
		"\31\3\2\2\2\u014d\u014b\3\2\2\2\u014e\u014f\7\20\2\2\u014f\u0150\7\5\2"+
		"\2\u0150\u0151\5 \21\2\u0151\u0155\7\6\2\2\u0152\u0154\7-\2\2\u0153\u0152"+
		"\3\2\2\2\u0154\u0157\3\2\2\2\u0155\u0153\3\2\2\2\u0155\u0156\3\2\2\2\u0156"+
		"\u0158\3\2\2\2\u0157\u0155\3\2\2\2\u0158\u0159\5\n\6\2\u0159\u015a\b\16"+
		"\1\2\u015a\33\3\2\2\2\u015b\u015c\7\21\2\2\u015c\u015d\7\5\2\2\u015d\u015e"+
		"\5\4\3\2\u015e\u015f\7\22\2\2\u015f\u0160\5 \21\2\u0160\u0161\7\22\2\2"+
		"\u0161\u0162\5\4\3\2\u0162\u0166\7\6\2\2\u0163\u0165\7-\2\2\u0164\u0163"+
		"\3\2\2\2\u0165\u0168\3\2\2\2\u0166\u0164\3\2\2\2\u0166\u0167\3\2\2\2\u0167"+
		"\u0169\3\2\2\2\u0168\u0166\3\2\2\2\u0169\u016a\5\n\6\2\u016a\u016b\b\17"+
		"\1\2\u016b\35\3\2\2\2\u016c\u016d\7\23\2\2\u016d\u016e\7\5\2\2\u016e\u016f"+
		"\5 \21\2\u016f\u0173\7\6\2\2\u0170\u0172\7-\2\2\u0171\u0170\3\2\2\2\u0172"+
		"\u0175\3\2\2\2\u0173\u0171\3\2\2\2\u0173\u0174\3\2\2\2\u0174\u0176\3\2"+
		"\2\2\u0175\u0173\3\2\2\2\u0176\u0177\5\n\6\2\u0177\u0178\b\20\1\2\u0178"+
		"\u018f\3\2\2\2\u0179\u017a\7\23\2\2\u017a\u017b\7\5\2\2\u017b\u017c\5"+
		" \21\2\u017c\u0180\7\6\2\2\u017d\u017f\7-\2\2\u017e\u017d\3\2\2\2\u017f"+
		"\u0182\3\2\2\2\u0180\u017e\3\2\2\2\u0180\u0181\3\2\2\2\u0181\u0183\3\2"+
		"\2\2\u0182\u0180\3\2\2\2\u0183\u0187\5\n\6\2\u0184\u0186\7-\2\2\u0185"+
		"\u0184\3\2\2\2\u0186\u0189\3\2\2\2\u0187\u0185\3\2\2\2\u0187\u0188\3\2"+
		"\2\2\u0188\u018a\3\2\2\2\u0189\u0187\3\2\2\2\u018a\u018b\7\24\2\2\u018b"+
		"\u018c\5\n\6\2\u018c\u018d\b\20\1\2\u018d\u018f\3\2\2\2\u018e\u016c\3"+
		"\2\2\2\u018e\u0179\3\2\2\2\u018f\37\3\2\2\2\u0190\u0191\5$\23\2\u0191"+
		"\u0192\b\21\1\2\u0192\u0197\3\2\2\2\u0193\u0194\5\"\22\2\u0194\u0195\b"+
		"\21\1\2\u0195\u0197\3\2\2\2\u0196\u0190\3\2\2\2\u0196\u0193\3\2\2\2\u0197"+
		"!\3\2\2\2\u0198\u0199\5\20\t\2\u0199\u019a\t\4\2\2\u019a\u019b\5\20\t"+
		"\2\u019b\u019c\b\22\1\2\u019c\u01a1\3\2\2\2\u019d\u019e\5\20\t\2\u019e"+
		"\u019f\b\22\1\2\u019f\u01a1\3\2\2\2\u01a0\u0198\3\2\2\2\u01a0\u019d\3"+
		"\2\2\2\u01a1#\3\2\2\2\u01a2\u01a3\b\23\1\2\u01a3\u01a4\7&\2\2\u01a4\u01ad"+
		"\b\23\1\2\u01a5\u01a6\7\37\2\2\u01a6\u01a7\5$\23\4\u01a7\u01a8\b\23\1"+
		"\2\u01a8\u01ad\3\2\2\2\u01a9\u01aa\5\"\22\2\u01aa\u01ab\b\23\1\2\u01ab"+
		"\u01ad\3\2\2\2\u01ac\u01a2\3\2\2\2\u01ac\u01a5\3\2\2\2\u01ac\u01a9\3\2"+
		"\2\2\u01ad\u01bf\3\2\2\2\u01ae\u01af\f\7\2\2\u01af\u01b0\7\34\2\2\u01b0"+
		"\u01b1\5$\23\b\u01b1\u01b2\b\23\1\2\u01b2\u01be\3\2\2\2\u01b3\u01b4\f"+
		"\6\2\2\u01b4\u01b5\7\35\2\2\u01b5\u01b6\5$\23\7\u01b6\u01b7\b\23\1\2\u01b7"+
		"\u01be\3\2\2\2\u01b8\u01b9\f\5\2\2\u01b9\u01ba\7\36\2\2\u01ba\u01bb\5"+
		"$\23\6\u01bb\u01bc\b\23\1\2\u01bc\u01be\3\2\2\2\u01bd\u01ae\3\2\2\2\u01bd"+
		"\u01b3\3\2\2\2\u01bd\u01b8\3\2\2\2\u01be\u01c1\3\2\2\2\u01bf\u01bd\3\2"+
		"\2\2\u01bf\u01c0\3\2\2\2\u01c0%\3\2\2\2\u01c1\u01bf\3\2\2\2\u01c2\u01c4"+
		"\t\5\2\2\u01c3\u01c2\3\2\2\2\u01c4\u01c5\3\2\2\2\u01c5\u01c3\3\2\2\2\u01c5"+
		"\u01c6\3\2\2\2\u01c6\'\3\2\2\2*\64@JQY_h\u0091\u009a\u00a1\u00a7\u00af"+
		"\u00b7\u00bf\u00c4\u00cb\u00d1\u00d9\u00e1\u00e4\u00ef\u0114\u0120\u0122"+
		"\u012d\u0130\u0144\u014b\u0155\u0166\u0173\u0180\u0187\u018e\u0196\u01a0"+
		"\u01ac\u01bd\u01bf\u01c5";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}