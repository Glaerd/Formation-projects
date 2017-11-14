#include<stdlib.h>

vector operator*(vector& vec, const int el){
	vector v(vec.dim());
	v = (vector) vec;
	int i;
	for(i = 0; i < vec.dim(); i++){
		v.dat(i) = el * vec.dat(i);
	}
	return v;
}

vector operator+(vector vec,const int el){
	vector v(vec.dim());
	v = (vector) vec;
	int i;
	for(i = 0; i < vec.dim(); i++){
		v.dat(i) = el + vec.dat(i);
	}
	return v;
}

matrix operator+(matrix mat,const int el){
	matrix m(mat.dim1(),mat.dim2());
	m = (matrix) mat;
	int i,j;
	for(i = 0; i < mat.dim1(); i++){
		for(j = 0; j < mat.dim2(); j++){
			m.dat(i,j) = el + mat.dat(i,j);
		}
	}
	return m;
}

int calcul(const matrix& el, const matrix& mat, int i, int j){
	int k,res = 0;
	for(k = 0; k < el.dim1(); k++){
		printf("(%d,%d) - %d = %d  [%d et %d]\n",i,j,k,el.dat(i,k) * mat.dat(k,j),el.dat(i,k),mat.dat(k,j));
		res += el.dat(i,k) * mat.dat(k,j);
	}
	return res;
}

matrix operator*(const matrix& el, matrix& mat){
	matrix m(mat.dim1(),mat.dim2());
	int i,j;
	for(i = 0; i < mat.dim1(); i++){
		for(j = 0; j < mat.dim2(); j++){
			m.dat(i,j) = calcul(el, mat, i, j);
			printf("(%d,%d) = %d \n",i,j,m.dat(i,j));
		}
	}
	printf("\n");
	return m;
}

vector operator*(const vector& el, const matrix& mat){
	vector v(el.dim());
	v = (vector) el;
	int i,j,k;
	for(i = 0; i < mat.dim1(); i++){
		k = 0;
		for(j = 0; j < mat.dim2(); j++){
			k += el.dat(i) * mat.dat(j,i);
		}
		v.dat(i) = k;
	}
	return v;
}
