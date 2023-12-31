/*
 * Lexical Grammar of calc
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 21-Oct-2015 13:20 (eg)
 * Last file update:  4-Nov-2015 17:09 (eg)
 */

%{
#include <stdlib.h>
#include <strings.h>

#include "calc.h"
#include "syntax.h"

%}

%option yylineno

%%

    /* ---- Read ----*/



	/* ---- Numbers ---- */
[0-9]+\.?[0-9]*(e[+-]?[0-9]+)?	{ yylval.value = atof(yytext); return NUMBER; }
\.[0-9]+(e[+-]?[0-9]+)?		{ yylval.value = atof(yytext); return NUMBER; }

	/* ---- Single char tokens ---- */

[-()<>=+*/;{}.]			{ return *yytext; }

	/* ---- "complex" tokens ---- */
">="				{ return GE; }
"<="            		{ return LE;      }
"=="            		{ return EQ;      }
"!="            		{ return NE;      }
"if"					{ return KIF;     }
"while"                 { return KWHILE;  }
"else"					{ return KELSE;   }
"print"         		{ return KPRINT;  }
"read"                  { return KREAD;   }

	/* ---- Idents ---- */
[a-zA-Z]+			{ yylval.var = strdup(yytext); return IDENT; }


	/* ---- Spaces ----*/
[ \t\n]+        		{ ; }

	/* ---- Comments ---- */
#.*$				{ ; }


	/* ---- Other characters ---- */
.				{ error_msg("unknown character '%c'\n", *yytext); }

%%

