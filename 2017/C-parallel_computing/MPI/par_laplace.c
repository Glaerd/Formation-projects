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
		MPI_Send(tab[(n/np)],n,MPI_DOUBLE,send_number1,send_number1,MPI_COMM_WORLD);
	}
	if(rank != 0) {
		MPI_Send(tab[1],n,MPI_DOUBLE,send_number2,send_number2,MPI_COMM_WORLD);
	}
	if(rank != np-1) {
		MPI_Recv(tab[(n/np)+1],n,MPI_DOUBLE,send_number1,rank,MPI_COMM_WORLD,&status);
	}
	if(rank != 0) {
		MPI_Recv(tab[0],n,MPI_DOUBLE,send_number2,rank,MPI_COMM_WORLD,&status);
	}
}

void laplace(double** tab, int rank, int n, int np, MPI_Status status){
	int i,j;
	double** tmp_tab;
	double err = 0;
	int converged = 0;
	tmp_tab = malloc((n/np+2) * sizeof(double*));
	for(i = 1; i < n/np+1;i++){
		tmp_tab[i] = malloc(n * sizeof(double));
	}
	
	while(!converged){
		err = 0;
		if(rank == 0) {printf(" - In Loop\n"); fflush(0);}
		for(i = 1; i < n/np+1;i++){
			for(j = 0; j < n; j++){
				tmp_tab[i][j] = 0.25*(tab[i+1][j]+tab[i-1][j]+tab[i][j+1]+tab[i][j-1]);
			}
		}
		
		for(i = 1; i < n/np+1;i++){
			for(j = 0; j < n; j++){
				err += pow(tmp_tab[i][j] - tab[i][j],2);
				tab[i][j] = tmp_tab[i][j];
			}
		}
		set_tab(tab,rank,n,np,status);
		double rec_err = 0;
		if(rank == 0) rec_err = err;
		for(i = 1; i < np; i++){
			if(rank == i) MPI_Send(&err,1,MPI_DOUBLE,0,rank,MPI_COMM_WORLD);
			else if(rank == 0) MPI_Recv(&err,1,MPI_DOUBLE,i,i,MPI_COMM_WORLD,&status);
			rec_err += err;
		}
		rec_err = sqrt(rec_err);
		if(rank == 0) {printf("%lf",rec_err);fflush(0);}
		if(rec_err < 1.0e-11) {
			converged = 1;
		}
		for(i = 1; i < np; i++){
			if(rank == 0) MPI_Send(&converged,1,MPI_INT,i,i,MPI_COMM_WORLD);
			else if(rank == i) MPI_Recv(&converged,1,MPI_DOUBLE,0,rank,MPI_COMM_WORLD,&status);
		}
	}
	printf("\n"); fflush(0);
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
	
	laplace(tab,rank,n,np,status);
	FILE* f;
	if(rank == 0){
		if ((f = fopen ("/user/magnanc/pvm3/MPI/result.laplace", "ab+")) == NULL) { perror ("saving matrice : fopen "); }
	}
	double* tmp_line = malloc(n * sizeof(double));
	for(k = 0; k < np; k++){
		MPI_Barrier(MPI_COMM_WORLD);
		for(i = 1; i < n/np + 1; i++){
			MPI_Barrier(MPI_COMM_WORLD);
			if(rank == 0){
				if(k == 0){
					for(j = 0; j < n; j++){
						fprintf(f,"%2.4f\t", tab[i][j]);
						fflush(0);
					}
					fprintf(f,"\n");
					fflush(0);
				}
				else{
					printf("Recv from %d - with tag %d\n",k,k*(n/np)+i);fflush(0);
					MPI_Recv(tmp_line,n,MPI_DOUBLE,k,k*(n/np)+i,MPI_COMM_WORLD,&status);
					for(j = 0; j < n; j++){
						fprintf(f,"%2.4f\t", tmp_line[j]);
						fflush(0);
					}
					fprintf(f,"\n");
					fflush(0);
				}
			}
			if(rank == k && k != 0){
				printf("Send : to 0 - with tag %d\n",rank*(n/np)+i);fflush(0);
				MPI_Send(tab[i],n,MPI_DOUBLE,0,rank*(n/np)+i,MPI_COMM_WORLD);
			}
		}
	}
	
	MPI_Finalize();
	return 0;
}
