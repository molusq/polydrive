/* La commande toupper en lex */

%{
#include <ctype.h>
%}

letter [a-z]

%%

{letter} putchar(toupper(yytext[0]));
.|\n ECHO;

%%
int main()  { return yylex(); }
int yywrap() { return 1; }