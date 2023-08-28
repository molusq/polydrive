%{
	#include <stdio.h>
	#include "bison_4.h"
%}

%option noyywrap
%option yylineno

%%
[ \t' ']        { /* skip  */ }
"\n"            { return '\n'; }
[0-9]+			{ yylval = atoi(yytext); return NUMBER; }
[*/()+-]		{ return yytext[0]; }
. 				{ printf("Unexpected character\n"); }

%%