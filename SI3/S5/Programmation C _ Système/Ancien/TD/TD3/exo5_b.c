#include <stdio.h>

#define CHAR_SIZE  8                        /* nombre de bits dans un char */
#define MAX_BIGSET 125                      /* nombre de cellules dans un ensemble */
#define MAX_VAL    (CHAR_SIZE * MAX_BIGSET)
/* 8x125 = 1000 bits: 0 à 999*/
typedef unsigned char BIGSET[MAX_BIGSET];

void BIGSET_init(BIGSET e){
	for (int i = 0; i < 125; i++){
		e[i] = 0;
	}
}


void BIGSET_add(BIGSET e, int i){
	int numcase = i/8;
	int numbit = i%8;
	e[numcase] |= 1<<numbit;
}

int BIGSET_is_in(BIGSET e, int i){
	int numcase = i/8;
	int numbit = i%8;
	return e[numcase]&(1<<numbit);
}

void BIGSET_print(BIGSET e){
	int printB = 1;
	for(int i = 0; i < 125; i++){
		if(BIGSET_is_in(e,i)){
			if(printB){
				printf("%d",i);
				printB = 0;
			}
			else printf(" %d",i);
		}
	}
}

void BIGSET_inter(BIGSET e1, BIGSET e2, BIGSET res){
	for(int i = 0; i < 125; i++){
		res[i] = (e1[i]&e2[i]);
	}
}

void BIGSET_union(BIGSET e1, BIGSET e2, BIGSET res){
	for(int i = 0; i < 125; i++){
		res[i] = (e1[i]|e2[i]);
	}
}

int main()
{
	BIGSET e1, e2, e3;
  	int i;
  	BIGSET_init(e1); BIGSET_init(e2);
  	for (i = 0; i < 40; i += 5) BIGSET_add(e2, i);
  	for (i = 0; i < 40; i += 3) BIGSET_add(e1, i);
  	BIGSET_inter(e1, e2, e3);
    printf("e1 = {"); BIGSET_print(e1); printf("}\n"); /* => e1 = {0 3 6 9 12 15 18 21 24 27 30 33 36 39} */
  	printf("e2 = {"); BIGSET_print(e2); printf("}\n"); /* => e2 = {0 5 10 15 20 25 30 35} */
  	printf("e3 = {"); BIGSET_print(e3); printf("}\n"); /* => e3 = {0 15 30} */
  	BIGSET_union(e1,e2,e3);
  	printf("e3 = {"); BIGSET_print(e3); printf("}\n");  	

	return 0;
}