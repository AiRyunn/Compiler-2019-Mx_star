grammar Mx_star;

program:
	(variableDeclaration | classDefinition | functionDefinition)+ EOF;

////////// Variables Declaration //////////

variableDeclaration: declarationStatement;

////////// Classes //////////

classDefinition: Class Identifier '{' classMembers? '}';

classMembers:
	(memberVariable | constructionFunction | memberFunction)+;

memberVariable: type Identifier ';';
constructionFunction: Identifier '(' ')' '{' statements? '}';
memberFunction:
	type Identifier '(' paramListDefinition? ')' '{' statements? '}';

////////// Functions //////////

functionDefinition:
	type Identifier '(' paramListDefinition? ')' '{' statements? '}';

paramListDefinition:
	type Identifier
	| type Identifier ',' paramListDefinition;

paramList: object | object ',' paramList;

////////// Statements //////////

statement:
	emptyStatement
	| assignmentStatement
	| expressionStatement
	| declarationStatement
	| loopStatement
	| conditionStatement
	| jumpStatement
	| compoundStatement;

statements: statement+;

// Empty Statement
emptyStatement: ';';

// Assignment Statement
assignmentStatement: object '=' expression ';';

// Expression Statement
expressionStatement: expression ';';

// Definition Statement
declarationStatement:
	type Identifier ';'
	| type Identifier '=' expression ';';

// Loop Statement
loopStatement:
	While '(' expression ')' statement
	| For '(' forCondition ')' statement;
forCondition: forCondition1? ';' expression? ';' forCondition3?;
forCondition1: type? Identifier '=' expression | expression;
forCondition3: Identifier '=' expression | expression;

// Condition Statement
conditionStatement:
	If '(' expression ')' statement (Else statement)?;

// Jump Statement
jumpStatement: (Return expression | Break | Continue) ';';

// Compound Statement
compoundStatement: '{' statements '}';

////////// Expressions //////////

expression: object;

object:
	Identifier
	| Constant
	| '(' object ')'
	| object '.' object
	| This '.' object
	| object '(' paramList? ')' // _call
	| object '[' object ']' // _subscript
	| object op = (
		'++' // _postfix_increment
		| '--' // _postfix_increment
	)
	| op = ('++' | '--') Identifier
	| op = '~' object
	| op = '!' object
	| op = ('+' | '-') object
	| op = New newObject // TODO
	| object op = ('*' | '/' | '%') object
	| object op = ('+' | '-') object
	| object op = ('<<' | '>>') object
	| object op = ('<' | '>' | '<=' | '>=') object
	| object op = ('==' | '!=') object
	| object op = '&' object
	| object op = '^' object
	| object op = '|' object
	| object op = '&&' object
	| object op = '||' object;

newObject: type ('[' object ']')* ('[' ']')* | type '(' ')';

////////// Data Type //////////

type: simpleType | compositeType;

simpleType: fundamentalType | customType;
compositeType: simpleType ('[' ']')+;

fundamentalType: Int | Bool | Void | String;
customType: Identifier;

/****************************** Tokens ******************************/

////////// Keywords //////////

New: 'new';

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

Constant:
	Null
	| LogicalConstant
	| IntegerConstant
	| StringLiteral;

Null: 'null';

True: 'true';
False: 'false';
LogicalConstant: True | False;

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
ShiftLeft: '<<';
ShiftRight: '>>';
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
fragment PrintableCharacter: '!' .. '~';
fragment EscapeCharacter: '\\n' | '\\\\' | '\\"';
fragment Character:
	PrintableCharacter
	| Whitespace
	| EscapeCharacter;
fragment CharacterSequence: Character+;

/****************************** Whitespace and Comments ******************************/

Whitespace: [ \t]+ -> skip;
Newline: [\r\n] -> skip;
BlockComment: '/*' .*? '*/' -> skip;
LineComment: '//' .*? [\r\n] -> skip;
