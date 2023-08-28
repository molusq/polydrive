%{
    #include <stdio.h>
    #include <ctype.h>
	int yylex(void);
    void yyerror(char *msg);
%}

%%
G: S '\n' G
	| %empty
;

S: S 'a' {printf("r1\n");}
	| 'x' {printf("r2\n");}
	| 'y' {printf("r3\n");}
;

%%
void yyerror(char *msg) { fprintf(stderr, "ERROR: %s\n", msg); }
int main() { return yyparse(); }

int yylex(void) {
	int c;
	do {
		c = getchar();
	} while (c == ' ');
	return c;
}