/* La commande wc en lex */

%{
int nbw = 0, nbc = 0, nbl = 0;
%}

%%

[^ \t\n]+ {nbw++; nbc+=yyleng;}
\n {nbl++; nbc++;}
. {nbc++;}

%%
int main() {
	yylex();
	printf("nbw %d, nbc %d, nbl %d\n", nbw, nbc, nbl);
}
int yywrap() {return 1;}