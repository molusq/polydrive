/*
 *  lex.l                       -- Un lexical simple pour implémenter ETF
 *
 *            Author: Erick Gallesio [eg@unice.fr]
 *     Creation date:  7-Oct-2016 13:19 (eg)
 *  Last file update:  5-Oct-2017 12:29 (eg)
*/

%{

#include <stdio.h>
#include "analyseur-ll.h"
extern int yylval;

%}

%option noyywrap

%%

[0-9]+                  { yylval = atoi(yytext); return INT; }
"+"                     { return PLUS;  }
"-"                     { return MINUS; }
"*"                     { return MULT;  }
"/"                     { return DIV;   }
"("                     { return OPEN;  }
")"                     { return CLOSE; }
\n                      { return EOL; }
<<EOF>>                 { return EOF; }

[ \t]                   { }
.                       { fprintf(stderr, "Unexpected character %c (%d)\n",
                                          *yytext, *yytext); }
%%
