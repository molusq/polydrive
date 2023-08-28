%{
	#include <stdio.h>
	#include "bison_3.h"
%}

%option noyywrap
%option yylineno

%%
[ \t]           { /* skip  */ }
"\n"            { return '\n'; }
[aA]            { return TOK_A;}
[xX]            { return TOK_X;}
[yY]            { return TOK_Y;}
. 				{printf("Unexpected character");}

%%