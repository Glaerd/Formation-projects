#include<stdio.h>
#include"vector.hh"
#include"matrix.hh"
#include"ops.hh"
#include"fill.hh"

void miseenbouche(){
	printf("Hello World!\n");	
	int i = 50;
	i++;	
	printf("%d\n",i); // << endl;
}

void exo1(){
	printf("\n");
	vector v1(3);
	vector v2(3);

	v1[1] = 3;
	v2 = v1 * 2 + 1;
	v1.print();
	v2.print();
	printf("\n");
	matrix m1(5,5);
	matrix m2(5,5);
	matrix m3(5,5);

	m1(4,1) = 95;
	m1(2,3) = 1;

	fill(m2,1);

	printf("m2(%d,%d) :",m2.dim1(),m2.dim2());
	m2.print();
	m2.printligne();

	vector v3(5);
	fill(v3,1);

	m3 = m1 * m2 + 1;
	printf("m1(%d,%d) :",m1.dim1(),m1.dim2());
	m1.print();
	m3.print();
	m3.printligne();

	v3 = v3 * m3;
	v3.print();
}

int main(){
	miseenbouche();
	exo1();
}
