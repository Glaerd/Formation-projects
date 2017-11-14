#include<stdlib.h>

vector fill(vector& vec, const int el){
	int i;
	for(i = 0; i < vec.dim(); i++){
		vec.dat(i) =  el;
	}
	return vec;
}

matrix fill(matrix& mat, const int el){
	int i,j;
	for(i = 0; i < mat.dim1(); i++){
		for(j = 0; j < mat.dim2(); j++){
			mat.dat(i,j) = el;
		}
	}
	return mat;
}
