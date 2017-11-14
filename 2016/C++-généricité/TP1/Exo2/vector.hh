#include<stdlib.h>

class abstract_vector{

	public:
		int dim() const{
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

		abstract_vector() = default;
		abstract_vector(int i){
			n = i;
			data = new int[i];
		};

		void print(){
			printf("\nTaille : %d\n", n);
			int i;
			printf("[");
			for(i = 0; i < n; i++){
				if(i != n-1) printf("%d  ", data[i]);
				else printf("%d", data[i]);
			}
			printf("]\n");
		}

		int operator[](int element) const{
			return data[element];
		};




	private:
		int n;
		int* data;
};

class one_vector : public abstract_vector{
	public :
		int& operator[](int element){
			return data[element];
		};

		void operator=(const int& element){
			int i;				
			for(i = 0; i < n; i++){
				data[i] = element;
			}
		}
};

class vector : public abstract_vector{
	public:
		int& operator[](int element){
			return data[element];
		};

		void operator=(const vector& element){
			if(n == element.n){
				int i;				
				for(i = 0; i < n; i++){
					data[i] = element.data[i];
				}
			}
		}
};
