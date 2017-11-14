#include<stdio.h>
#include"vector.hh"
#include"matrix.hh"

void exo2(){
	printf("\n");
	vector v1(3);

	v1[1] = 3;
	v1.print();
	printf("\n");

	matrix m1(5,5);

	m1(4,1) = 95;
	m1(2,3) = 1;

	printf("m1(%d,%d) :",m1.dim1(),m1.dim2());
	m1.print();
}

int main(){
	exo2();
}
