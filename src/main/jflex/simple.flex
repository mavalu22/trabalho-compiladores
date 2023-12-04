/* this is the scanner example from the JLex website
   (with small modifications to make it more readable) */

package com.ufes.compilador.JFlex;

%%

%public

%{
  private int comment_count = 0;
%}

%line
%char
%state COMMENT
%unicode
%caseless
%ignorecase

%debug

LineTerminator=\r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace={LineTerminator} | [ \t\f]
Comment={TraditionalComment} | {EndOfLineComment} | {DocumentationComment}
TraditionalComment="(*" [^*] ~"*)" | "(*" "*"+ ")"
EndOfLineComment="//"{InputCharacter}* {LineTerminator}?
DocumentationComment="{"{CommentContent} "}"
CommentContent=( [^*] | \*+ [^/*] )*

ALPHA=[A-Za-z]
DIGIT=[0-9]
NONNEWLINE_WHITE_SPACE_CHAR=[\ \t\b\012]
NEWLINE=\r|\n|\r\n
WHITE_SPACE_CHAR=[\n\r\ \t\b\012]
STRING_TEXT=(\\\"|[^\n\r\"\\]|\\{WHITE_SPACE_CHAR}+\\)*
COMMENT_TEXT=([^*/\n]|[^*\n]"/"[^*\n]|[^/\n]"*"[^/\n]|"*"[^/\n]|"/"[^*\n])+
Ident = {ALPHA}({ALPHA}|{DIGIT}|_)*

espaco={NONNEWLINE_WHITE_SPACE_CHAR}|{NONNEWLINE_WHITE_SPACE_CHAR}+|{WHITE_SPACE_CHAR}|{WhiteSpace}
ID=[_|a-z|A-Z][a-z|A-Z|0-9|_|$|%|@|#|!|?]*
INTEIRO=0|[1-9][0-9]*
REAL={INTEIRO}.{INTEIRO}|{INTEIRO},{INTEIRO}

tipo=inteiro|real|caracteres|caracter|booleano
%%

<YYINITIAL> {
  "," { return (new Yytoken(yytext(),yyline,"TKN_virgula")); }
  ":" { return (new Yytoken(yytext(),yyline,"TKN_doisPontos")); }
  ";" { return (new Yytoken(yytext(),yyline,"TKN_pontoEvirgula")); }
  "(" { return (new Yytoken(yytext(),yyline,"TKN_abreParenteses")); }
  ")" { return (new Yytoken(yytext(),yyline,"TKN_fechaParenteses")); }
  "[" { return (new Yytoken(yytext(),yyline,"TKN_abreColchetes")); }
  "]" { return (new Yytoken(yytext(),yyline,"TKN_fechaColchetes")); }
  "." { return (new Yytoken(yytext(),yyline,"TKN_ponto")); }
  "+" { return (new Yytoken(yytext(),yyline,"TKN_mais")); }
  "-" { return (new Yytoken(yytext(),yyline,"TKN_hifen")); }
  "*" { return (new Yytoken(yytext(),yyline,"TKN_asterisco")); }
  "/" { return (new Yytoken(yytext(),yyline,"TKN_barra")); }
  "=" { return (new Yytoken(yytext(),yyline,"TKN_igual")); }
  "<>" { return (new Yytoken(yytext(),yyline,"TKN_diferente")); }
  "<"  { return (new Yytoken(yytext(),yyline,"TKN_menor")); }
  "<=" { return (new Yytoken(yytext(),yyline,"TKN_menorOuIgual")); }
  ">"  { return (new Yytoken(yytext(),yyline,"TKN_maior")); }
  ">=" { return (new Yytoken(yytext(),yyline,"TKN_maiorOuIgual")); }
  "&"  { return (new Yytoken(yytext(),yyline,"TKN_eComercial")); }
  "|"  { return (new Yytoken(yytext(),yyline,"TKN_barraVertical")); }
  {espaco} { }
  {Comment} { }
}

<YYINITIAL> "true"        { return (new Yytoken(yytext(), yyline, "TKN_true")); }
<YYINITIAL> "false"       { return (new Yytoken(yytext(), yyline, "TKN_false")); }
<YYINITIAL> "abstract"    { return (new Yytoken(yytext(), yyline, "TKN_abstract")); }
<YYINITIAL> "boolean"     { return (new Yytoken(yytext(), yyline, "TKN_boolean")); }
<YYINITIAL> "break"       { return (new Yytoken(yytext(), yyline, "TKN_break")); }
<YYINITIAL> "byte"        { return (new Yytoken(yytext(), yyline, "TKN_byte")); }
<YYINITIAL> "case"        { return (new Yytoken(yytext(), yyline, "TKN_case")); }
<YYINITIAL> "catch"       { return (new Yytoken(yytext(), yyline, "TKN_catch")); }
<YYINITIAL> "char"        { return (new Yytoken(yytext(), yyline, "TKN_char")); }
<YYINITIAL> "class"       { return (new Yytoken(yytext(), yyline, "TKN_class")); }
<YYINITIAL> "const"       { return (new Yytoken(yytext(), yyline, "TKN_const")); }
<YYINITIAL> "continue"    { return (new Yytoken(yytext(), yyline, "TKN_continue")); }
<YYINITIAL> "default"     { return (new Yytoken(yytext(), yyline, "TKN_default")); }
<YYINITIAL> "do"          { return (new Yytoken(yytext(), yyline, "TKN_do")); }
<YYINITIAL> "double"      { return (new Yytoken(yytext(), yyline, "TKN_double")); }
<YYINITIAL> "else"        { return (new Yytoken(yytext(), yyline, "TKN_else")); }
<YYINITIAL> "extends"     { return (new Yytoken(yytext(), yyline, "TKN_extends")); }
<YYINITIAL> "final"       { return (new Yytoken(yytext(), yyline, "TKN_final")); }
<YYINITIAL> "finally"     { return (new Yytoken(yytext(), yyline, "TKN_finally")); }
<YYINITIAL> "float"       { return (new Yytoken(yytext(), yyline, "TKN_float")); }
<YYINITIAL> "for"         { return (new Yytoken(yytext(), yyline, "TKN_for")); }
<YYINITIAL> "goto"        { return (new Yytoken(yytext(), yyline, "TKN_goto")); }
<YYINITIAL> "if"          { return (new Yytoken(yytext(), yyline, "TKN_if")); }
<YYINITIAL> "implements"  { return (new Yytoken(yytext(), yyline, "TKN_implements")); }
<YYINITIAL> "import"      { return (new Yytoken(yytext(), yyline, "TKN_import")); }
<YYINITIAL> "instanceof"  { return (new Yytoken(yytext(), yyline, "TKN_instanceof")); }
<YYINITIAL> "int"         { return (new Yytoken(yytext(), yyline, "TKN_int")); }
<YYINITIAL> "interface"   { return (new Yytoken(yytext(), yyline, "TKN_interface")); }
<YYINITIAL> "long"        { return (new Yytoken(yytext(), yyline, "TKN_long")); }
<YYINITIAL> "native"      { return (new Yytoken(yytext(), yyline, "TKN_native")); }
<YYINITIAL> "new"         { return (new Yytoken(yytext(), yyline, "TKN_new")); }
<YYINITIAL> "package"     { return (new Yytoken(yytext(), yyline, "TKN_package")); }
<YYINITIAL> "private"     { return (new Yytoken(yytext(), yyline, "TKN_private")); }
<YYINITIAL> "protected"   { return (new Yytoken(yytext(), yyline, "TKN_protected")); }
<YYINITIAL> "public"      { return (new Yytoken(yytext(), yyline, "TKN_public")); }
<YYINITIAL> "return"      { return (new Yytoken(yytext(), yyline, "TKN_return")); }
<YYINITIAL> "short"       { return (new Yytoken(yytext(), yyline, "TKN_short")); }
<YYINITIAL> "static"      { return (new Yytoken(yytext(), yyline, "TKN_static")); }
<YYINITIAL> "super"       { return (new Yytoken(yytext(), yyline, "TKN_super")); }
<YYINITIAL> "switch"      { return (new Yytoken(yytext(), yyline, "TKN_switch")); }
<YYINITIAL> "synchronized" { return (new Yytoken(yytext(), yyline, "TKN_synchronized")); }
<YYINITIAL> "this"        { return (new Yytoken(yytext(), yyline, "TKN_this")); }
<YYINITIAL> "throw"       { return (new Yytoken(yytext(), yyline, "TKN_throw")); }
<YYINITIAL> "throws"      { return (new Yytoken(yytext(), yyline, "TKN_throws")); }
<YYINITIAL> "transient"   { return (new Yytoken(yytext(), yyline, "TKN_transient")); }
<YYINITIAL> "try"         { return (new Yytoken(yytext(), yyline, "TKN_try")); }
<YYINITIAL> "void"        { return (new Yytoken(yytext(), yyline, "TKN_void")); }
<YYINITIAL> "volatile"    { return (new Yytoken(yytext(), yyline, "TKN_volatile")); }
<YYINITIAL> "while"       { return (new Yytoken(yytext(), yyline, "TKN_while")); }

<YYINITIAL> "programa" { return (new Yytoken(yytext(),yyline,"TKN_nomeiaPrograma")); }
<YYINITIAL> {tipo} { return (new Yytoken(yytext(),yyline,"TKN_tipoVariavel")); }
<YYINITIAL> {ID} { return (new Yytoken(yytext(),yyline,"TKN_identificador")); }
<YYINITIAL> {INTEIRO} { return (new Yytoken(yytext(),yyline,"TKN_tipoInteiro")); }
<YYINITIAL> {ALPHA} { return (new Yytoken(yytext(),yyline,"TKN_tipoCaractere")); }
<YYINITIAL> {REAL} { return (new Yytoken(yytext(),yyline,"TKN_tipoReal")); }



<COMMENT> {
  "/*" { comment_count++; }
  "*/" { if (--comment_count == 0) yybegin(YYINITIAL); }
  {COMMENT_TEXT} { }
}

"//"     { yybegin(COMMENT); }
<COMMENT>{
  [^\n]*  { /* Ignore characters within a line comment. */ }
  \n      { yybegin(YYINITIAL); }
}

"/*"     { yybegin(COMMENT); }
<COMMENT>{
  [^*]*   { /* Ignore characters within a block comment. */ }
  "*"     { /* Possible end of comment */ }
  "*"+"/" { yybegin(YYINITIAL); }
}


{NEWLINE} { }