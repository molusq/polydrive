#include <stdio.h>

typedef unsigned char SMALLSET;

/* rend l'ensemble vide */
SMALLSET init(void){
	return 0;
}

/* rend l'ensemble e auquel on ajoute l'element i */
SMALLSET add(SMALLSET e, int i){
	//int x = ;
	return e|(1<<i);// | x;
	
}

SMALLSET removeSmall(SMALLSET e, int i){	
	return e&(~(1<<i));
}

SMALLSET is_in(SMALLSET e, int i){
	return e&(1<<i);
}

/* affiche les elements de e */
void printSmall(SMALLSET e){
	printf("e = { ");
	for (int i = 0; i<8; i++){
		if(is_in(e,i))
			printf("%d ",i);
	}
	printf("}\n");
}

SMALLSET inter(SMALLSET e, SMALLSET f){
	for (int i = 0; i<8; i++){
		if((f&(1<<i)) && (e&(1<<i)))
			e = add(e,i);
		else (e = e&(0<<i));

	}
	return e;
}

SMALLSET unionSmall(SMALLSET e, SMALLSET f){
	for (int i = 0; i<8; i++){
		if(f&(1<<i))
			e = add(e,i);
	}
	return e;
}

SMALLSET unionSmallV2(SMALLSET e, SMALLSET f){
	return e|f;
}

SMALLSET interSmall(SMALLSET e, SMALLSET f){
	return e&f;
}


int main(){
	SMALLSET e = init();
	SMALLSET f = init();
	f = add(f,6);
	f = add(f,7);
	e = add(e,4);
	printSmall(e);
	e = add(e,3);
	printSmall(e);
	e = add(e,6);
	printSmall(e);
	e = interSmall(e,f);
	e = removeSmall(e,6);
	printSmall(e);
	

}
