1: bison_1.y
	bison -o bison_1.c bison_1.y; gcc -std=gnu99 -o bison_1 bison_1.c;

2: bison_2.y
	bison -o bison_2.c bison_2.y; gcc -std=gnu99 -o bison_2 bison_2.c;

3: bison_3.y bison_3.l
	bison -g -v -d -o bison_3.c bison_3.y; flex -o bison_3.lex.c bison_3.l; gcc -o bison_3 bison_3.c bison_3.lex.c;

4: bison_4.y bison_4.l
	bison -g -v -d -o bison_4.c bison_4.y; flex -o bison_4.lex.c bison_4.l; gcc -o bison_4 bison_4.c bison_4.lex.c;