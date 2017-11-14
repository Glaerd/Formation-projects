#include "mpi.h"
#include "math.h"
#include <stdio.h>
#include <stdlib.h>


void set_tab(double** tab, int rank, int n, int np, MPI_Status status){
	int send_number1 = rank + 1;
	int send_number2 = rank - 1;
	if(send_number2 < 0) send_number2 = np-1;
	if(send_number1 >= np) send_number1 = 0;
	if(rank != np-1) {
		printf("%d to %d\n",rank,send_number1);
		MPI_Send(tab[(n/np)],n,MPI_DOUBLE,send_number1,send_number1,MPI_COMM_WORLD);
	}
	if(rank != 0) {
		printf("%d to %d\n",rank,send_number2);
		MPI_Send(tab[1],n,MPI_DOUBLE,send_number2,send_number2,MPI_COMM_WORLD);
	}
	if(rank != np-1) {
		printf("%d from %d\n",rank,send_number1);
		MPI_Recv(tab[(n/np)+1],n,MPI_DOUBLE,send_number1,rank,MPI_COMM_WORLD,&status);
	}
	if(rank != 0) {
		printf("%d from %d\n",rank,send_number2);
		MPI_Recv(tab[0],n,MPI_DOUBLE,send_number2,rank,MPI_COMM_WORLD,&status);
	}
	printf("%d Fini\n",rank);
}

int main( int argc, char *argv[] )
{
	int rank, size, np=1, n=0;
	if(argc>1) n = atoi(argv[1]);
	MPI_Status status;
	MPI_Init( &argc, &argv );
	MPI_Comm_rank( MPI_COMM_WORLD, &rank );
	MPI_Comm_size( MPI_COMM_WORLD, &size );
	np = size;
	if(n == 0){
		printf("n non initialisés\n");
		exit(1);
	}
	if(n%np != 0){
		printf("n non divisible par np\n");
		exit(1);
	}
	double final_res;
	double** tab;
	int i,j,k;
	double default_value = -1, proc_number = (double) rank;
	tab = malloc((n/np+2) * sizeof(double*));
	for(i = 0; i < n/np + 2; i++){
		tab[i] = malloc(n * sizeof(double));
		for(j = 0; j < n; j++){
			if(i == 0 || i == n/np + 1) tab[i][j] = default_value;
			else tab[i][j] = proc_number;
		}
	}
	
	set_tab(tab,rank,n,np,status);
	fflush(0);
	
	for(k = 0; k < np; k++){
		MPI_Barrier(MPI_COMM_WORLD);
		if(rank == k){
			for(i = 0; i < n/np + 2; i++){
				for(j = 0; j < n; j++){
					printf("%1.1f\t", tab[i][j]);
					fflush(0);
				}
				printf("\n");
				fflush(0);
			}
			printf("\n");
			fflush(0);
		}
	}
	
	MPI_Finalize();
	return 0;
}
