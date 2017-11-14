#include <stdio.h>


int main(){
	int tab[]={0,2,3,4,5,6,7,11,12,14,17};
	int n = sizeof(tab)/sizeof(tab[0]);
	int v=14;
	printf("Somme : %d\n",v);
	int i=0, j=n-1, c=0 ;
	while(i!=n && j !=-1){
		if(tab[i]+tab[j]==v){
			printf("(%d,%d)\n",tab[i],tab[j]);
			
			i++;
			j--;
			c++;
		}
		else if(tab[i]+tab[j]<v)
			i++;
		else
			j--;
	}
	printf("Nombre de couples : %d\n",c);
	return 0;
}
