/* La commande cat en lex */
%x NUM

digit [0-9]
sign [+-]?
exp [Ee]{sign}{digit}+
float1 {digit}+([\.\,]{digit}*)?
float2 \.{digit}+
%%

{sign}({float1}|{float2}) printf("%s", "NOMBRE");

%%
int main()  {return yylex();}
int yywrap() {return 1;}