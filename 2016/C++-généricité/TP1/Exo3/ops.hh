#include<stdlib.h>

vector operator*(vector vec, const int el){
	vector v(vec.dim());
	v = (vector) vec;
	int i;
	for(i = 0; i < vec.dim(); i++){
		v.dat(i) = el * vec.dat(i);
	}
	return v;
}

/*template <typename T>
T operator+(T tmp,const int el) throws Exception{
	T t(tmp.dim());
	t = (T) tmp;
	int i,j;
	for(i = 0; i < t.dim(); i++){
		matrix* m = NULL;
		try{
			m = dynamic_cast<matrix*>(&tmp);
			if(m){
				for(j = 0; j < t.dim(); j++){
					t.dat(i,j) = el + tmp.dat(i,j);
				}
			}
		}
		catch(Exception e) {
			t.dat(i) = el + tmp.dat(i);
		}
	}
	return t;
}*/

vector operator+(vector tmp,const int el) {
	vector t(tmp.dim());
	t = (vector) tmp;
	int i;
	for(i = 0; i < t.dim(); i++){
		t.dat(i) = el + tmp.dat(i);
	}
	return t;
}

matrix operator+(matrix tmp,const int el) {
	matrix t(tmp.dim());
	t = (matrix) tmp;
	int i,j;
	for(i = 0; i < t.dim(); i++){
		for(j = 0; j < t.dim(); j++){
			t.dat(i,j) = el + tmp.dat(i,j);
		}
	}
	return t;
}

int calcul(const matrix& el, const matrix& mat, int i, int j){
	int k,res = 0;
	for(k = 0; k < el.dim(); k++){
		//printf("(%d,%d) - %d = %d  [%d et %d]\n",i,j,k,el.dat(i,k) * mat.dat(k,j),el.dat(i,k),mat.dat(k,j));
		res += el.dat(i,k) * mat.dat(k,j);
	}
	return res;
}

matrix operator*(const matrix& el, matrix& mat){
	matrix m(mat.dim());
	int i,j;
	for(i = 0; i < mat.dim(); i++){
		for(j = 0; j < mat.dim(); j++){
			m.dat(i,j) = calcul(el, mat, i, j);
			//printf("(%d,%d) = %d \n",i,j,m.dat(i,j));
		}
	}
	printf("\n");
	return m;
}

vector operator*(const vector& el, const matrix& mat){
	vector v(el.dim());
	v = (vector) el;
	int i,j,k;
	for(i = 0; i < mat.dim(); i++){
		k = 0;
		for(j = 0; j < mat.dim(); j++){
			k += el.dat(i) * mat.dat(j,i);
		}
		v.dat(i) = k;
	}
	return v;
}
