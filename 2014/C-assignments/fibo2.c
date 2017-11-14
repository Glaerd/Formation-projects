#include <stdio.h>


struct vect { // déclaration de la structure "vect" de 1 ligne 2 colonnes
int t[2];
};

struct mat { // déclaration de la structure "mat" de 2 lignes 2 colonnes
int t[2][2];
};

struct vect matvect(struct mat A, struct vect V){   // A*V
int i,j;
struct vect W;
for(i=0; i<2; i++){
W.t[i]=0; // Mise à zéro des cases de la structure W	
for(j=0; j<2; j++)
W.t[i] += A.t[i][j] * V.t[j];
}
return W;
}


struct mat matmat(struct mat A,struct mat B){  //A*B
struct mat C;
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


struct mat powdic(struct mat A, int n){
struct mat P={{{1,0},{0,1}}} ;
while(n!=0){
if((n%2)!=0)
P=matmat(P,A);
A=matmat(A,A);
n=n/2;
}
return P;
}

int fib1(int n) {
struct vect F1={{0,1}};
struct mat M={{{0,1},{1,1}}} ;
struct mat Mn1;
Mn1= powdic(M,n-1);
struct vect Fn;
Fn=matvect(Mn1,F1);	

return Fn.t[1];
}



int main() {
int n;
printf("n=");
scanf("%d",&n);

printf("Fib(%d)=%d\n",n,fib1(n));

return 0;	
}
