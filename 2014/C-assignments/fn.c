#include<stdio.h>
#include <time.h>
 
struct V{  // déclaration de la structure "vect" de 1 ligne 2 colonnes
	int t[2];
};

struct M{  // déclaration de la structure "mat" de 2 lignes 2 colonnes
	int t[2][2];
};


struct V prodmatvect(struct M A, struct V V1){   // A*V1
	int i,j;
	struct V W;
	for(i=0; i<2; i++){
		W.t[i]=0; // Mise à zéro des cases de la structure W	
		for(j=0; j<2; j++)
			W.t[i] += A.t[i][j] * V1.t[j];
	}
	return W;
}

struct M prodmat(struct M A,struct M B){  //A*B
	struct M C;
	int i,j,k;
	for(i=0; i<2 ;i++){
		for(j=0; j<2 ;j++){
			C.t[i][j]=0; // Mise à zéro des cases de la structure C
			for(k=0; k<2 ;k++){
				C.t[i][j]+= A.t[i][k]*B.t[k][j];
			}
		}
	}
	return C;
}

struct M puissmat(struct M A, int n){
               struct M P={{{1,0},{0,1}}} ;
               while(n!=0){
                            if((n%2)!=0)
                                          P=prodmat(P,A);
                            A=prodmat(A,A);
                            n=n/2;
               }
               return P;
}

int f(int n) {
	struct V F1={{0,1}};
	struct M M1={{{0,1},{1,1}}} ;
	struct M Mn1;
	Mn1= puissmat(M1,n-1);
	struct V Fn;
	Fn=prodmatvect(Mn1,F1);	

	return Fn.t[1];
}


int main() {
	int n=50;
	//printf("n=");
	//scanf("%d",&n);

	printf("F(%d)=%d\n",n,f(n));
	return 0;	
}
