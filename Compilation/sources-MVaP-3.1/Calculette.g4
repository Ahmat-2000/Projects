grammar Calculette;
/*
C'est pour définir une fonction et la utilisé dans nos codes
- PUSHI 0 : correspond à la declaration d'un entier.
- PUSHG n : pour copier la valeur à l'addresse vi.address à la tête de la pile
*/

@parser::members {
    private int _cur_label = 1;
    /** générateur de nom d'étiquettes pour les boucles */
    private String getNewLabel() {
        return "Label" +(_cur_label++) + "\n"; 
    }
    // ...
    private TablesSymboles tablesSymboles = new TablesSymboles(); // une variable global
    // C'est pour factoriser le code des expressions arithmetiques
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
    // pour evaluer differents types, c'est une expression boolenne
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
}
decl returns [ String code ] // déclaration de variable
    : TYPE IDENTIFIANT  finInstruction
        {
            tablesSymboles.addVarDecl($IDENTIFIANT.text,$TYPE.text); // on reserve une addresse pour la variable
            VariableInfo vi = tablesSymboles.getVar($IDENTIFIANT.text); //pour recupèrer les info de la variable(addresse, type , scoope)
            $code = vi.type.equals("double") ? "PUSHF 0.0 \n" : "PUSHI 0 \n";
        }
    | TYPE IDENTIFIANT  '='  expression  finInstruction // PUSHI 0\nNNEQ\n c'est pour convertir un entier en boolean par exemple 50 est true 
        {
            tablesSymboles.addVarDecl($IDENTIFIANT.text,$TYPE.text); // on reserve une addresse pour la variable
            VariableInfo vi = tablesSymboles.getVar($IDENTIFIANT.text); //pour recupèrer les info de la variable(addresse, type , scoope)
            $code = vi.type.equals("double") ? "PUSHF 0.0 \n" : "PUSHI 0 \n";
            String storeG_L = (vi.scope == VariableInfo.Scope.GLOBAL) ? "STOREG " : "STOREL " ;
            // Gestion du Casting entre différents types
            if(vi.type.equals($expression.type)){
                $code += $expression.code;
                $code += vi.type.equals("double") ? storeG_L + (vi.address + 1) + "\n" : "";
            }
            else{
                System.err.println("WARRNING : le type de la variable " +$IDENTIFIANT.text+ " ne matche pas à celui de l'expression \n"
                + "Un casting sera appliqué pour convertir le type de l'expression vers le type "+ vi.type);
                if(vi.type.equals("double")){
                    $code += $expression.code + "ITOF\n";
                    $code += storeG_L + (vi.address + 1) + "\n";
                }else if(vi.type.equals("int")){
                    $code += !$expression.type.equals("double") ? $expression.code  : $expression.code + "FTOI\n";
                }else{
                    $code += $expression.code;
                    $code += !$expression.type.equals("double") ? "PUSHI 0\nNEQ\n" : "PUSHFI 0.0\nFNEQ\n"; // En faisant le casting d'un nombre vers un boolean, on le compare avec 0
                }
            }
            $code += storeG_L + vi.address + "\n";
        }
    ;
assignation returns [ String code ] 
    : IDENTIFIANT  '='  expression 
        {  
            VariableInfo vi = tablesSymboles.getVar($IDENTIFIANT.text); //pour recupèrer les info de la variable(addresse, type , scoope)
            String storeG_L = (vi.scope == VariableInfo.Scope.GLOBAL) ? "STOREG " : "STOREL " ;
            // Gestion du Casting entre différents types
            if(vi.type.equals($expression.type)){
                $code  = $expression.code;
                $code += vi.type.equals("double") ? storeG_L + (vi.address + 1) + "\n" : "";
            }
            else{
                System.err.println("WARRNING : le type de la variable " +vi.type+ " ne matche pas à celui de l'expression \n"
                + "Un casting sera appliqué pour convertir le type de l'expression vers le type "+ vi.type);
                if(vi.type.equals("double")){
                    $code  = $expression.type.equals("double") ? $expression.code : $expression.code + "ITOF\n";
                    $code += storeG_L + (vi.address + 1) + "\n";
                }else if(vi.type.equals("int")){
                    $code = !$expression.type.equals("double") ? $expression.code  : $expression.code + "FTOI\n";
                }else{
                    $code  = $expression.code;
                    $code += !$expression.type.equals("double") ? "PUSHI 0\nNEQ\n" : "PUSHF 0.0\nFNEQ\n"; // En faisant le casting d'un nombre vers un boolean, on le compare avec 0
                }
            }
            $code += storeG_L + vi.address + "\n";
        }
    | IDENTIFIANT  '+='  expression // On recupère d'abord l'ancienne valeur puis on fait l'addition et enfin on stocke le resultat
        {  
            VariableInfo vi = tablesSymboles.getVar($IDENTIFIANT.text); //pour recupèrer les info de la variable(addresse, type , scoope)
            String storeG_L = (vi.scope == VariableInfo.Scope.GLOBAL) ? "STOREG " : "STOREL " ;
            String pushG_L  = (vi.scope == VariableInfo.Scope.GLOBAL) ? "PUSHG " : "PUSHL " ;
            $code = pushG_L + vi.address + "\n" ; 
            if(vi.type.equals($expression.type)){
                $code += vi.type.equals("double") ? pushG_L + (vi.address + 1) + "\n" : "";
                $code += $expression.code;
                $code += vi.type.equals("double") ? "FADD\n" + storeG_L + (vi.address + 1) + "\n" : "ADD\n";
            }
            // Gestion des erreurs et casting explicite entre les types 
            else{
                System.err.println("WARRNING : le type de la variable " +vi.type+ " ne matche pas à celui de l'expression \n"
                + "Un casting sera appliqué pour convertir le type de l'expression vers le type "+ vi.type);
                if(vi.type.equals("double")){
                    $code += pushG_L + (vi.address + 1)  + "\n" + $expression.code + "ITOF\n";
                    $code += "FADD\n" + storeG_L + (vi.address + 1) + "\n";
                }else if(vi.type.equals("int")){
                    $code += !$expression.type.equals("double") ? $expression.code  : $expression.code + "FTOI\n";
                    $code += "ADD\n" + storeG_L + vi.address + "\n" ;
                }else{
                    $code += !$expression.type.equals("double") ? $expression.code : $expression.code + "FTOI\n"; // En faisant le casting d'un nombre vers un boolean, on le compare avec 0
                    $code += "ADD\nPUSHI 0\nNEQ\n";
                }
            }
            $code += storeG_L + vi.address + "\n" ;
        }
    ;
start : calcul  EOF ;
calcul returns [ String code ] 
@init{ $code = new String(); }   // On initialise $code, pour ensuite l'utiliser comme accumulateur 
@after{ System.out.println($code); }
    :   (decl { $code += $decl.code; })*        
        { $code += "JUMP Main\n"; }
        NEWLINE*
        
        (fonction { $code += $fonction.code; })* 
        NEWLINE*
        
        { $code += "LABEL Main\n"; }
        (instruction { $code += $instruction.code; })*

        { $code += "HALT\n"; } 
    ;
instruction returns [ String code ] 
    :  expression finInstruction 
        { $code=$expression.code; }
    | bloc 
        { $code = $bloc.code;}
    | boucleWhile 
        { $code = $boucleWhile.code;}
    | boucleFor
        { $code = $boucleFor.code;}
    | ifElse 
        { $code = $ifElse.code; }
    | affichage finInstruction 
        { $code = $affichage.code;}
    | ecriture finInstruction
        { $code = $ecriture.code;}
    | assignation finInstruction
        { $code = $assignation.code;}
    | RETURN expression finInstruction    
        { 
            VariableInfo vi = tablesSymboles.getReturn(); // pour recupérer la variable de retour
            if(!vi.type.equals($expression.type)){
                System.err.println("WARRNING : le type de la variable de retour ne matche pas à celui de l'expression \n"
                + "Un casting sera appliqué pour convertir le type de l'expression vers le type "+ vi.type);
            }
            if( vi.type.equals("double")){
                $code   = $expression.type.equals("double") ? $expression.code : $expression.code + "ITOF\n";
                $code  += "STOREL " +(vi.address+1)+ "\n";
            }else if( vi.type.equals("int")){
                $code  = !$expression.type.equals("double") ? $expression.code : $expression.code + "FTOI\n";
            }else{
                $code += $expression.code ;
                $code += !$expression.type.equals("double") ?  "PUSHI 0\nNEQ\n": "PUSHF 0.0\nFNEQ\n"; // En faisant le casting d'un nombre vers un boolean, on le compare avec 0
            }
            $code +=  "STOREL " + vi.address + "\n  RETURN\n";
        }
    | finInstruction
        { $code=""; }
    ;
fonction returns [ String code ]
@init{tablesSymboles.enterFunction();}   // On initialise $code, pour ensuite l'utiliser comme accumulateur 
@after{ tablesSymboles.exitFunction();}
    : TYPE IDENTIFIANT
        {
            tablesSymboles.addFunction($IDENTIFIANT.text,$TYPE.text);
            $code = "LABEL " + $IDENTIFIANT.text + "\n"; // pour stocker le type de la fonction, ici c'est $TYPE.text
        } 
        '('  ')'
	    '{'  NEWLINE? (decl { $code += $decl.code; })* NEWLINE* (  instruction  { $code += $instruction.code ;} )* '}' 
	    { $code += "RETURN\n"; }
        NEWLINE* 
    |  TYPE IDENTIFIANT
        {
            tablesSymboles.addFunction($IDENTIFIANT.text,$TYPE.text);
            $code = "LABEL " + $IDENTIFIANT.text + "\n"; // pour stocker le type de la fonction, ici c'est $TYPE.text
        } 
        '('  params ? ')'
	    '{'  NEWLINE? (decl { $code += $decl.code; })* NEWLINE* (  instruction  { $code += $instruction.code; } )* '}' 
	    { $code += "RETURN\n"; }
        NEWLINE*
    ;
params
    : TYPE IDENTIFIANT
        {
            // code java gérant une variable locale (arg0)
            tablesSymboles.addParam($IDENTIFIANT.text,$TYPE.text);
        }
        ( ',' TYPE IDENTIFIANT
            {
                // code java gérant une variable locale (argi)
                tablesSymboles.addParam($IDENTIFIANT.text,$TYPE.text);
            }
        )*
    ;
expression returns [ String code, String type ]
    : '-' a=expression {
            $type = $a.type;
            $code = $a.type.equals("double") ? "PUSHF 0.0 \n" + $a.code + evalexpr("-","F") : "PUSHI 0 \n" + $a.code + evalexpr("-"," ") ;
        }     // pour les nombres négatifs
    |   a=expression op=('*'|'/') b=expression 
        {   $type = $a.type;
            if($a.type.equals($b.type)){
                $code  = $a.code + $b.code;
                $code += $type.equals("double") ? evalexpr($op.text,"F") : evalexpr($op.text," ") ;
            }else{
                $type = "double";
                System.err.println("WARRNING : le type de la variable " +$IDENTIFIANT.text+ " ne matche pas à celui de l'expression \n"
                + "Un casting sera appliqué pour convertir le type de l'expression vers le type double ou int" );
                if($a.type.equals("double")){
                    $code = $a.code;
                    $code += $b.type.equals("double") ? $b.code : $b.code+ "ITOF\n";
                    $code += evalexpr($op.text,"F");
                }
                else if($b.type.equals("double")){
                    $code  = $a.type.equals("double") ? $a.code : $a.code+ "ITOF\n";
                    $code += $b.code + evalexpr($op.text,"F");
                }
                else{
                    $type = "int";
                    $code = $a.code + $b.code +  evalexpr($op.text," ");
                }
            }
        }
    |   a=expression op=('+'|'-') b=expression
        {  
            $type = $a.type;
            if($a.type.equals($b.type)){
                $code  = $a.code + $b.code;
                $code += $type.equals("double") ? evalexpr($op.text,"F") : evalexpr($op.text," ") ;
            }else{
                $type = "double";
                System.err.println("WARRNING : le type de la variable " +$IDENTIFIANT.text+ " ne matche pas à celui de l'expression \n"
                + "Un casting sera appliqué pour convertir le type de l'expression vers le type double ou int" );
                if($a.type.equals("double")){
                    $code = $a.code;
                    $code += $b.type.equals("double") ? $b.code : $b.code+ "ITOF\n";
                    $code += evalexpr($op.text,"F");
                }
                else if($b.type.equals("double")){
                    $code  = $a.type.equals("double") ? $a.code : $a.code+ "ITOF\n";
                    $code += $b.code + evalexpr($op.text,"F");
                }
                else{
                    $type = "int";
                    $code = $a.code + $b.code +  evalexpr($op.text," ");
                }
            }
        }
    | '(' a=expression ')' {$code = $a.code; $type = $a.type;}
    | '(' TYPE ')' a=expression
        {
            $type = $TYPE.text;
            if($TYPE.text.equals($a.type)){
                $code = $a.code;
            }
            else if($TYPE.text.equals("double")){
                $code = $a.code + "ITOF\n";
            }
            else if($TYPE.text.equals("int")){
                $code = $a.type.equals("double") ? $a.code + "FTOI\n" : $a.code;
            }
            else{ // bool
                $code  = $a.code ;
                $code += $a.type.equals("double") ?  "PUSHF 0.0\nFNEQ\n" : "PUSHI 0\nNEQ\n";
            }
        }
    | BOOLEAN   { $type = "bool"; $code = $BOOLEAN.text.equals("true") ? "PUSHI 1\n" : "PUSHI 0\n";  }
    | ENTIER    { $type = "int" ; $code = "PUSHI " + $ENTIER.text + "\n" ;}
    | DOUBLE    { $type = "double" ; $code = "PUSHF " + $DOUBLE.text + "\n"; }
    | IDENTIFIANT '('')' // appel de fonction
        { 
            $type = tablesSymboles.getFunction($IDENTIFIANT.text);
            $code = $type.equals("double") ? "PUSHF 0.0\n" : "PUSHI 0\n" ;
            $code += "CALL " + $IDENTIFIANT.text + "\n";
        }
    | IDENTIFIANT '(' args ')' // appel de fonction , il reste à gèrer le type de arguments
        {
            // PUSHI 0 pour reserver de la place pour la valeur retournée
            $type = tablesSymboles.getFunction($IDENTIFIANT.text);
            $code = $type.equals("double") ? "PUSHF 0.0\n" : "PUSHI 0\n" ;
            $code += $args.code + "CALL " + $IDENTIFIANT.text + "\n" ;
            for(int i =0 ; i < $args.size; i++){ $code += "POP\n";}
        }
    | IDENTIFIANT   { 
        VariableInfo vi = tablesSymboles.getVar($IDENTIFIANT.text); //pour recupèrer les info de la variable(addresse, type , scoope)
        $type = vi.type;
        String pushLorG = (vi.scope == VariableInfo.Scope.GLOBAL) ? "PUSHG " : "PUSHL ";
        $code = pushLorG +  vi.address + "\n";
        if(vi.type.equals("double"))
            $code += pushLorG + (vi.address + 1) + "\n";
    }
    ;
args returns [ String code, int size] @init{ $code = new String(); $size = 0; }
    : ( expression 
    {
        // code java pour première expression pour arg
        $code = $expression.code;
        $size += $expression.type.equals("double") ? 2 : 1;
    }
    ( ',' expression
    {
        // code java pour expression suivante pour arg
        $code += $expression.code;
        $size += $expression.type.equals("double") ? 2 : 1;
    }
    )*
      )?
    ;

affichage returns [ String code ]
    :  'print'  '('  expression  ')'
        {
            String onPopOrTwoPop = $expression.type.equals("double") ? "F\nPOP\n" : "\n";
            $code = $expression.code + "WRITE" + onPopOrTwoPop + "POP\n" ;
        }
    ;
ecriture returns [ String code ]
    :  'input'  '('  IDENTIFIANT ')'
        {
            VariableInfo vi = tablesSymboles.getVar($IDENTIFIANT.text); //pour recupèrer les info de la variable(addresse, type , scoope)
            String scope = (vi.scope == VariableInfo.Scope.GLOBAL) ? "G " : "L ";
            if(vi.type.equals("double"))
                $code = "READF\nSTORE" + scope + (vi.address + 1) + "\nSTORE"+ scope + vi.address + "\n"; 
            else
                $code = "READ\nSTORE" + scope + vi.address + "\n" ; 
        }
    ;
bloc returns [ String code ]  @init{ $code = new String(); } 
    : '{' 
           (instruction {$code+=$instruction.code;})* // break et CONTINUE doivent être ici
      '}'  
      NEWLINE*
    ;
boucleWhile returns [ String code ]
    :  'while' '(' condition ')' NEWLINE* instruction 
        {
            String labelDebut = getNewLabel();
            String labelFin = getNewLabel();
            $code = "LABEL " + labelDebut + $condition.code + "JUMPF " + labelFin + 
            $instruction.code + "JUMP " + labelDebut + "LABEL " + labelFin ;
        }
    /*| $code = "LABEL " + labelDebut + $condition.code + "JUMPF " + labelFin +  // code MVaP pour le break
            $instruction.code + "JUMP " + labelFin + "LABEL " + labelFin ;
    */
    ;
boucleFor returns [ String code]
    :   'for' '('  a=assignation ';' b=condition ';' c=assignation ')' NEWLINE* d=instruction
        {
            String labelDebut = getNewLabel();
            String labelFin = getNewLabel();
            $code = $a.code + "LABEL " + labelDebut + $b.code + "JUMPF " + labelFin + $d.code + $c.code + "JUMP " + labelDebut + "LABEL " + labelFin;
        }
    ;
ifElse returns [ String code]
    :  'if'  '(' condition ')'  NEWLINE* instruction
        {
            String labelFinIf = getNewLabel();
            $code = $condition.code + "JUMPF " + labelFinIf + $instruction.code + "LABEL " + labelFinIf;
        }
    |  'if'  '(' condition ')' NEWLINE* a=instruction NEWLINE* 'else'  b=instruction
        {
            String labelFinIf = getNewLabel();
            String labelFinElseIf = getNewLabel();
            $code = $condition.code + "JUMPF " + labelFinIf + $a.code + "JUMP " + labelFinElseIf + "LABEL " + labelFinIf + $b.code + "LABEL " + labelFinElseIf;
        } 
    ;
condition returns [String code]
    :   operateurBooleen   { $code = $operateurBooleen.code;}
    |   operateurRationnel { $code = $operateurRationnel.code;}
    ;
  
operateurRationnel returns [ String code ]
    :   a=expression  op=('=='| '!=' | '<>' | '<' | '>' | '<=' | '>=')  b=expression
        {
            if($a.type.equals($b.type)){
                $code  = $a.code + $b.code;
                $code += $a.type.equals("double") ? evalType($op.text,"F") : evalType($op.text,"") ;
            }else{
                System.err.println("WARRNING casting explicite de int vers double ");
                if($a.type.equals("double")){
                    $code = $a.code;
                    $code += $b.type.equals("double") ? $b.code : $b.code + "ITOF\n";
                    $code += evalType($op.text,"F");
                }
                else if($b.type.equals("double")){
                    $code  = $a.type.equals("double") ? $a.code : $a.code + "ITOF\n";
                    $code += $b.code + evalType($op.text,"F");
                }
                else{
                    $code = $a.code + $b.code + evalType($op.text,"");
                }
            }

           /*  String cast = ""; // pour faire un casting explicite
            if($a.type.equals("bool") || $b.type.equals("bool")){
                System.err.println("Opérateur incorrect : '"+$op.text+"'");
                throw new IllegalArgumentException("On ne peut pas comparer des boolean avec cet opérateur '"+$op.text+"'");
            }
            else if($a.type.equals("double")){
                cast = $b.type.equals("double") ? $b.code : $b.code + "ITOF\n";
                $code = $a.code + cast + evalType($op.text,"F");
            }
            else{
                cast = $b.type.equals("int") ? $b.code : $b.code + "FTOI\n";
                $code = $a.code + cast + evalType($op.text,"");
            }*/
        }
    |   a=expression 
        { 
            $code  = $a.code ;
            $code += $a.type.equals("double") ? "PUSHF 0.0\nFNEQ\n" : "PUSHI 0\nNEQ\n";
        } 
    ;
operateurBooleen returns [ String code ]
    :   BOOLEAN
        { $code = $BOOLEAN.text.equals("true") ? "PUSHI 1\n" : "PUSHI 0\n"; }
    |   a=operateurBooleen  '&&'  b=operateurBooleen // operateur AND
        { $code = $a.code + $b.code + "PUSHI 1\nNEQ\nSUP\n";}  
    |   a=operateurBooleen  '||'  b=operateurBooleen // operateur OR
        { $code = $a.code + $b.code + "PUSHI 1\nSUPEQ\nADD\n";}
    |   a=operateurBooleen  '^^'  b=operateurBooleen // c'est Le XOR
        { $code = $a.code + $b.code + "PUSHI 1\nEQUAL\nNEQ\n";} 
    |   '!'  operateurBooleen    // operateur NOT
        { $code = $operateurBooleen.code + "PUSHI 1\nNEQ\n";}
    |   operateurRationnel { $code = $operateurRationnel.code;}
    ;
finInstruction : ( NEWLINE | ';' )+ ;

// lexer
OPEARTION : '*' | '/' | '+' |'-';
RETURN: 'return';
COMM :  ( ('#')(~('\n'))* ) -> skip;
COMMLIGNE : ( ('%')(~('\n'))* )  -> skip;
COMMMULTI : ( ('/*') .*? ('*/') ) -> skip;
WS :   (' '|'\t')+ -> skip  ;
BOOLEAN : 'true' | 'false';
ENTIER : ('0'..'9')+  ;
DOUBLE :  ('0'..'9')+ '.' ('0'..'9')*  ;
BREAK : 'break';
CONTINUE : 'continue';
TYPE : 'int' | 'double' | 'bool'; // il peut être un Identifiant, donc on l'ajoute au debut
IDENTIFIANT :   ('a'..'z' | 'A'..'Z' | '_')('a'..'z' | 'A'..'Z' | '_' | '0'..'9')* ;// il peut être un entier ou UNMATCH, donc il vient avant
NEWLINE : '\r'? '\n'; // skip permet de cacher les élément de la directive NEWLINE de l'arbre
UNMATCH : . ;

