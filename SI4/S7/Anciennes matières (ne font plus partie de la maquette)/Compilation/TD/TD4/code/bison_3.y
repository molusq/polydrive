%{
    #include <stdio.h>
    #include <ctype.h>
    #define YYERROR_VERBOSE 1
	
	int yylex(void);
    void yyerror (char const *msg);
    void show_prompt(void);

    int line_count = 0;

%}

%locations
%token TOK_A TOK_X TOK_Y

%%
G: G S '\n' {printf("Analysis OK\n"); show_prompt();}
	| error '\n' {yyerrok;}
	| %empty {printf("Bye\n");}
	;

S:		S TOK_A {printf("r1\n");}
	|	TOK_X {printf("r2\n");}
	|	TOK_Y {printf("r3\n");}
	;


%%
void yyerror (char const *msg) { fprintf(stderr, "ERROR: line %d, %s\n", line_count, msg); }

void show_prompt() {
	printf("Line %d > ", ++line_count);
	fflush(stdout);
}

int main() {
	show_prompt();
	return yyparse(); 
}