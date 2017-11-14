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
	v2 = v1 * 2;
	v2 = v2 + 1;
	v1.print();
	v2.print();
	printf("\n");
	matrix m1(5);
	matrix m2(5);
	matrix m3(5);

	m1(4,1) = 95;
	m1(2,3) = 1;

	fill(m2,1);

	printf("m2(%d) :",m2.dim());
	m2.print();
	m2.printligne();

	vector v3(5);
	fill(v3,1);

	m3 = m1 * m2;
	m3 = m3;
	printf("m1(%d) :",m1.dim());
	m1.print();
	m2.print();
	m3.print();

	v3.print();

	v3 = v3 * m3;
	v3.print();
}

int main(){
	miseenbouche();
	exo1();
}
