#include <sdtio.h>

int iterate(int (*f)(int), int t[], int n){
	int res = t[0];
	for(int i = 0; i < n; i++){
		res = f(res, t[i]);
	}
	return res;
}
