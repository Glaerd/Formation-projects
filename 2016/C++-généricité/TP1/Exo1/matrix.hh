#include<stdlib.h>

class matrix{
	
	public:
		int dim1() const{
			int np = m;
			return np;
		}
		int dim2() const{
			int np = n;
			return np;
		}
		int dat(int i, int j) const{
			int k = data[j+i*m];
			return k;
		}
		int& dat(int i, int j){
			int& k = data[j+i*m];
			return k;
		}

		matrix() = default;
		matrix(int i, int j){
			m = i;
			n = j;
			data = new int[i*j];
		}

		void print(){
			printf("\nTaille : %d\n", n*m);
			int i,j;
			for(i = 0; i < m; i++){
				for(j = 0; j < n; j++){
					if(j != n-1) printf("%d  ", data[j+i*m]);
					else printf("%d\n", data[j+i*m]);
				}
			}
		}

		void printligne(){
			int i;
			for(i = 0; i < m*n; i++){
				printf("%d ", data[i]);
			}
			printf("\n");
		}

	int operator()(const int& i, const int& j) const{
		return data[j+i*m];
	}
	int& operator()(const int& i, const int& j){
		return data[j+i*m];
	}

	private:
		int m,n;
		int* data;
};
