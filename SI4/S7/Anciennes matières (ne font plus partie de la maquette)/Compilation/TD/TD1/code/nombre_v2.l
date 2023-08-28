/* La commande cat en lex */
%x OCT
%x HEX

%{
	int n = 0;
%}

%%

"0" BEGIN OCT;
<OCT>"x" BEGIN HEX;
<OCT>\n|\t|" " {printf("%d\n", n); n = 0; BEGIN INITIAL;}
<OCT>[0-7] {n += n * 8 + (*yytext - '0');}
<OCT>. BEGIN INITIAL;

<HEX>\n|\t|" " {printf("%d\n", n); n = 0; BEGIN INITIAL;}
<HEX>[0-9] {n += n * 16 + (*yytext - '0');}
<HEX>[A-F] {n += n * 16 + (*yytext - 'A' + 10);}
<HEX>. BEGIN INITIAL;

%%
int main()  {return yylex();}
int yywrap() {return 1;}