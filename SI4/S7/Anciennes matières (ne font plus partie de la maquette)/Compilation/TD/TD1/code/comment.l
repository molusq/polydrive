%x STR COMMENT
%{
int count = 0;
%}
%%

"\"" {putchar('"'); BEGIN STR;}
<STR>\" {putchar('"'); BEGIN INITIAL;}
<STR>\. ECHO;
<STR>.|\n putchar(toupper(*yytext));
"//".* {}
"/*" {count++; BEGIN COMMENT;}
<COMMENT>"*/" {count--; if(count == 0) BEGIN INITIAL;}
<COMMENT>.|\n {}
<COMMENT>"/*" count++;
<COMMENT><<EOF>> {printf("%s\n", "Error, non ending comment"); BEGIN INITIAL;}


%%
int main()  { return yylex(); }
int yywrap() { return 1; }