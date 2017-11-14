#include<stdlib.h>

class abstract_matrix{

	public:
		
		abstract_matrix() = default;
		abstract_matrix(const int& i,const int& j){
			m = i;
			n = j;
			data = new int[i*j];
		};

		virtual ~abstract_matrix();
		virtual void print() const = 0;
		int operator()(const int& i, const int& j) const{
			return data[j+i*m];
		}

		int dim1() const{
			int np = m;
			return np;
		}
		int dim2() const{
			int np = n;
			return np;
		}
		int dat(int i) const{
			int j = data[i];
			return j;
		}
		int& dat(int i){
			int& j = data[i];
			return j;
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
		};

		void printligne(){
			int i;
			for(i = 0; i < m*n; i++){
				printf("%d ", data[i]);
			}
			printf("\n");
		}


	protected:
		int m,n;
		int* data;
};

class matrix : public abstract_matrix{
	
	public:
		int& operator()(const int& i, const int& j){
			return data[j+i*m];
		}
};

class dia_matrix : public abstract_matrix{
	
	public:
		int& operator()(const int& i){
			return data[i+i*m];
		}
};
