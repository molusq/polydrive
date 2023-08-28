%{
#include "calc.h"	
%}


%%

[0-9]+ {yylval.val = *yytext; return NUMBER;}
[a-zA-Z]+ { yylval.var = strdup(yytext); return IDENT;}
'+' return PLUS;
'-' return MINUS;
'*' return MULT;
'\/' return DIV;
'\(' return OPEN;
'\)' return CLOSE;
\n return EOL;
'=' return EQUAL;

. printf("Unexpexcted character");

%%
int yywrap() { return 1; }