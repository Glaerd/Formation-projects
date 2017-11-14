#include<stdio.h>
#include<stdlib.h>

int main()
{
	int n = 1000;
	int* a = (int*) malloc(n*n*sizeof(int));
	int* b = (int*) malloc(n*n*sizeof(int));
	
	int i,j;	
		
	for(i = 0; i<n; i++){
		for(j = 0; j<n; j++){
			a[i*n + j] = 0;
			b[i*n + j] = a[i*n + j];
		}
	}
	return 0;
}
