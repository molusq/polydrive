%%

\"([^\\]|\\.|\\\n)*\" {for (int i = 0; i < yyleng; i++) putchar(toupper(yytext[i]));}
.|\n ECHO;

%%
int main()  {return yylex();}
int yywrap() {return 1;}