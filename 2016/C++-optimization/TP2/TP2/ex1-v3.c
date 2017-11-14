#include<stdio.h>
#include<stdlib.h>
#include <string.h>

int main()
{
	int n = 1000;
	int* a = (int*) calloc(n*n,sizeof(int));
	int* b = (int*) calloc(n*n,sizeof(int));
	
	memcpy(b, a, n*n*sizeof(int));
	return 0;
}
