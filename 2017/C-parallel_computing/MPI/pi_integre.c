#include "mpi.h"
#include "math.h"
#include <stdio.h>
#include <stdlib.h>


int main( int argc, char *argv[] )
{
	int rank, size, n;
	double final_res;
	MPI_Init( &argc, &argv );
	MPI_Comm_rank( MPI_COMM_WORLD, &rank );
	MPI_Comm_size( MPI_COMM_WORLD, &size );
	if(rank==0) {
		if(argc>1) n = atoi(argv[1]);
	}
	MPI_Bcast(&n,1,MPI_INT,0,MPI_COMM_WORLD);
	if(n != 0){
		int local_size = n / size;
		double res = 0;
		double h1,h2,hm;
		for(int i = 0; i < local_size; i++){
			h1 = ((float)1)/(1+pow(((float)i+rank*local_size)/n,2));
			h2 = ((float)1)/(1+pow(((float)i+rank*local_size+1)/n,2));
			hm = (h1+h2)/2;
			res += hm*((float)1)/n;
		}
		MPI_Reduce(&res,&final_res,1,MPI_DOUBLE,MPI_SUM,0,MPI_COMM_WORLD);
		MPI_Finalize();
		if(rank==0) printf("%lf\n",final_res*4);
	}
	else printf("BCast doesn't work ! \n");
	return 0;
}
