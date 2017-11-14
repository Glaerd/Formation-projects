#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<time.h>

int main()
{
	int n = 4;
	int* a = (int*) calloc(n*n,sizeof(int));
	int* b = (int*) calloc(n*n,sizeof(int));

	int i,j;
	srand(time(NULL));

	for(i = 0; i<n; i++){
		for(j = 0; j<n; j++){
			a[i*n + j] = rand()%100;
		}
	}

	for(i = 1; i<n-1; i++){
		for(j = 1; j<n-1; j++){
			
			b[i*n + j] = (a[(i-1)*n + j-1]+a[(i-1)*n + j]+a[(i-1)*n + j+1]+
						a[i*n + j-1]+a[i*n + j]+a[i*n + j+1]+a[(i+1)*n + j-1]+
						a[(i+1)*n + j]+a[(i+1)*n + j+1])/9;
		}
	}
	for(i = 0; i<n; i++){
		printf("\n");
		for(j = 0; j<n; j++){
			printf("%d ", a[i*n + j]);
		}
	}
	printf("\n");
	for(i = 0; i<n; i++){
		printf("\n");
		for(j = 0; j<n; j++){
			printf("%d ", b[i*n + j]);
		}
	}
	printf("\n");
	return 0;
}
