%{
  #include <stdio.h>
  #include <ctype.h>
  #include <unistd.h>

  void yyerror(const char* msg);
  int yylex(void);
  void prompt(void);

  #define  YYERROR_VERBOSE 1
  extern int yylineno, yylval;
%}

%token NUMBER

%left '+' '-'
%left '*'
%left '/'
%left '(' ')'

%%

g:      g expr '\n' { printf("= %d\n", $2); prompt(); }
      | /* empty */
      ;

expr:   expr '+' expr { $$ = $1 + $3;                  }
      | expr '-' expr { $$ = $1 - $3;                  }
      | expr '*' expr { $$ = $1 * $3;                  }
      | expr '/' expr { if ($3 != 0) $$ = $1 / $3;
                        else {
                          printf("Division par 0\n"); 
                          return yyerrok;
                        }                              }
      | '(' expr ')'  { $$ = $2;                       }
      | NUMBER        { $$ = $1;                       }
      | '-' NUMBER    { $$ = -$2;                      }
      ;

%%
int main() {
    prompt();
    return yyparse();
}

void yyerror(const char* msg) {
    fprintf(stderr, "\nError:'%s'\n", msg);
    prompt();
}

void prompt(void) {
  printf("[%d] ", yylineno);
  fflush(stdout);
}