grammar Mx_star;

@header {
    package Mx_star.AST;
}

program: programSection+ EOF;

programSection:
	emptyStatement					# ProgramEmptyStatement
	| variableDeclarationStatement	# ProgramVariableDeclarationStatement
	| variableDefinitionStatement	# ProgramVariableDefinitionStatement
	| classDefinitionStatement		# ProgramClassDefinitionStatement
	| functionDefinitionStatement	# ProgramFunctionDefinitionStatement;

////////// Classes //////////

classDefinitionStatement: Class Identifier '{' classMember* '}';

classMember:
	variableDeclarationStatement	# ClassVariableDeclarationStatement
	| constructionFunctionStatement	# ClassConstructionFunctionStatement
	| functionDefinitionStatement	# ClassFunctionDefinitionStatement;

constructionFunctionStatement:
	Identifier '(' paramListDefinition ')' '{' statements? '}';

////////// Functions //////////

functionDefinitionStatement:
	type Identifier '(' paramListDefinition ')' '{' statements? '}';

paramListDefinition: (
		variableDeclaration (',' variableDeclaration)*
	)?;

paramList: (object ( ',' object)*)?;

/****************************** Statements ******************************/

statement:
	emptyStatement					# StmtEmptyStatement
	| variableDeclarationStatement	# StmtVariableDeclarationStatement
	| variableDefinitionStatement	# StmtVariableDefinitionStatement
	| variableAssignmentStatement	# StmtVariableAssignmentStatement
	| objectStatement				# StmtObjectStatement
	| loopStatement					# StmtLoopStatement
	| conditionStatement			# StmtConditionStatement
	| jumpStatement					# StmtJumpStatement
	| compoundStatement				# StmtCompoundStatement;

statements: statement+;

emptyStatement: ';';
variableDeclarationStatement: variableDeclaration ';';
variableDefinitionStatement: variableDefinition ';';
variableAssignmentStatement: variableAssignment ';';
objectStatement: object ';';

loopStatement:
	While '(' object ')' statement			# WhileLoop
	| For '(' forCondition ')' statement	# ForLoop;
forCondition:
	forCondition1? ';' forCondition2? ';' forCondition3?;
forCondition1:
	variableDeclaration		# ForCdt1VariableDeclaration
	| variableDefinition	# ForCdt1VariableDefinition
	| variableAssignment	# ForCdt1VariableAssignment
	| object				# ForCdt1Object;
forCondition2: object;
forCondition3:
	variableAssignment	# ForCdt3VariableAssignment
	| object			# ForCdt3Object;

conditionStatement:
	If '(' object ')' statement (Else statement)?;

jumpStatement:
	Return object? ';'	# JumpReturn
	| Break ';'			# JumpBreak
	| Continue ';'		# JumpContinue;

compoundStatement: '{' statements? '}';

////////// Assignments //////////

variableDeclaration: type Identifier;

variableDefinition: type Identifier '=' object;

variableAssignment: lvalue '=' object;

////////// Objects //////////

lvalue:
	Identifier				# IdentifierLvalue
	| This '.' Identifier	# MemberLvalue
	| lvalue '.' Identifier	# MemberLvalue
	| lvalue '[' object ']'	# SubscriptLvalue;

object:
	This												# ThisObject
	| Identifier										# IdentifierObject
	| constant											# ConstantObject
	| lvalue											# LvalueObject
	| object '.' Identifier								# MemberObject
	| '(' object ')'									# BracketObject
	| object '(' paramList ')'							# FunctionReturnObject
	| object '[' object ']'								# SubscriptObject
	| lvalue op = ('++' | '--')							# UnaryOperatorObject
	| <assoc = right> op = ('++' | '--') lvalue			# UnaryOperatorObject
	| <assoc = right> op = '~' object					# UnaryOperatorObject
	| <assoc = right> op = '!' object					# UnaryOperatorObject
	| <assoc = right> op = ('+' | '-') object			# UnaryOperatorObject
	| op = New type (('[' object? ']')+ | ('(' ')')?)	# NewObject
	| object op = ('*' | '/' | '%') object				# BinaryOperatorObject
	| object op = ('+' | '-') object					# BinaryOperatorObject
	| object op = ('<<' | '>>') object					# BinaryOperatorObject
	| object op = ('<' | '>' | '<=' | '>=') object		# BinaryOperatorObject
	| object op = ('==' | '!=') object					# BinaryOperatorObject
	| object op = '&' object							# BinaryOperatorObject
	| object op = '^' object							# BinaryOperatorObject
	| object op = '|' object							# BinaryOperatorObject
	| object op = '&&' object							# BinaryOperatorObject
	| object op = '||' object							# BinaryOperatorObject;

type: simpleType | compositeType;
////////// Data Type //////////

simpleType: fundamentalType | customType;
compositeType: simpleType ('[' ']')+;

fundamentalType: Int | Bool | Void | String;
customType: Identifier;

constant:
	Null				# Null
	| LogicalConstant	# LogicalConstant
	| IntegerConstant	# IntegerConstant
	| StringLiteral		# StringLiteral;

/****************************** Tokens ******************************/

New: 'new';
////////// Keywords //////////

Bool: 'bool';
Int: 'int';
Void: 'void';
String: 'string';

Class: 'class';
This: 'this';

If: 'if';
Else: 'else';
For: 'for';
While: 'while';

Return: 'return';
Break: 'break';
Continue: 'continue';

////////// Literals //////////

Null: 'null';

LogicalConstant: True | False;
True: 'true';
False: 'false';

IntegerConstant: DecimalConstant;

StringLiteral: '"' CharacterSequence? '"';

////////// Symbols //////////

// Arithmetic Operators
Add: '+';
Sub: '-';
Mul: '*';
Div: '/';
Mod: '%';

// Relational Operators
Less: '<';
Greater: '>';
Equal: '==';
Unequal: '!=';
LessEqual: '<=';
GreaterEqual: '>=';

// Logical Operators
LogicalNot: '!';
LogicalAnd: '&&';
LogicalOr: '||';

// Bitwise Operators
SHL: '<<';
SHR: '>>';
BitInversion: '~';
BitAnd: '&';
BitOr: '|';
BitXor: '^';

// Assignment Operator
Assign: '=';

// Increment and Decrement
Increment: '++';
Decrement: '--';

// Member Access
MemberAccess: '.';

// Round Brackets
LeftRoundBracket: '(';
RightRoundBracket: ')';

// Square Brackets
LeftSquareBracket: '[';
RightSquareBracket: ']';

// Braces
LeftBrace: '{';
RightBrace: '}';

////////// Identifier //////////

Identifier: IdentifierStartChar IdentifierChar*;

/****************************** Fragments ******************************/

fragment Digit: [0-9];
fragment Zero: '0';
fragment NonzeroDigit: [1-9];
fragment DecimalConstant: Zero | (NonzeroDigit Digit*);

fragment LowercaseLetter: 'a' ..'z';
fragment UppercaseLetter: 'A' ..'Z';
fragment Letter: LowercaseLetter | UppercaseLetter;

fragment IdentifierStartChar: Letter | Underline;
fragment IdentifierChar: Letter | Digit | Underline;

fragment Underline: '_';
fragment PrintableCharacter: '!' .. '[' | ']' ..'~';
fragment EscapeCharacter: '\\n' | '\\\\' | '\\"';
fragment Character:
	PrintableCharacter
	| Whitespace
	| EscapeCharacter;
fragment CharacterSequence: Character+?;

/****************************** Whitespace and Comments ******************************/

Whitespace: [ \t]+ -> skip;
Newline: [\r\n] -> skip;
BlockComment: '/*' .*? '*/' -> skip;
LineComment: '//' .*? [\r\n] -> skip;
