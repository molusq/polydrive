#include <stdio.h>

void insertion(int t[],int n, int v){
	int i=n;
	while(i>=0 && v<t[i]){
		t[i+1]=t[i];
		i--;
	}
	t[i+1]=v;
}

int main(){
	int n = 100;
	int t[n];
	for(int i = 0;i<n;i++)
		t[i]=0;
	int v;
	while(scanf("%d",&v) != EOF){
		insertion(t,n,v);
		n++;
		if(v==0)break;
	}
	
	for(int i=0;i<n;i++){
		if(t[i]!=0)printf("%d \n",t[i]);
	}
	return 0;
}
