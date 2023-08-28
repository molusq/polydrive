%x STR


%%

"\"" BEGIN STR;
<STR>\" BEGIN INITIAL;
<STR>\. ECHO;
<STR>.|\n putchar(toupper(*yytext));

%%
int main()  {return yylex();}
int yywrap() {return 1;}