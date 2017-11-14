#include<stdlib.h>

class matrix{
	
	public:
		int dim() const{
			int np = n;
			return np;
		}
		int dat(int i, int j) const{
			int k = data[j+i*n];
			return k;
		}
		int& dat(int i, int j){
			int& k = data[j+i*n];
			return k;
		}

		matrix() = default;
		matrix(int i){
			n = i;
			data = new int[i*i];
		}

		void print(){
			printf("\nTaille : %d\n", n*n);
			int i,j;
			for(i = 0; i < n; i++){
				for(j = 0; j < n; j++){
					if(j != n-1) printf("%d  ", data[j+i*n]);
					else printf("%d\n", data[j+i*n]);
				}
			}
		}

		void printligne(){
			int i;
			for(i = 0; i < n*n; i++){
				printf("%d ", data[i]);
			}
			printf("\n");
		}

	int operator()(const int& i, const int& j) const{
		return data[j+i*n];
	}
	int& operator()(const int& i, const int& j){
		return data[j+i*n];
	}

	private:
		int n;
		int* data;
};
