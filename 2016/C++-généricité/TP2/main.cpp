#include<iostream>
#include "box2d_iterator.hh"
#include "neighb2d_iterator.hh"
#include "image2d.hh"
#include<string>
#include<limits>
#include<queue>

template <typename T>
std::vector<unsigned int> dMap(image2d<T> im){

	std::vector<unsigned int> dmap((int) im.vec().size(), std::numeric_limits<int>::max());
	int k;
	std::queue<point2d_<int>> q;
	point2d_<int> pt;
	std::cout << "Max col and Max row : " << im.getBox().pMax().col() << " - " << im.getBox().pMax().row() << std::endl;
	for(k = 0; k < (int) im.vec().size(); k++){
		point2d_<int> p = point2d_<int>(((int) k/(im.getBox().pMax().row())) + 1, (int)(k%(im.getBox().pMax().col())) + 1);
		if(im.vec()[(int) im.vec().size() - k - 1] == 1){
			dmap[k] = 0;
			std::cout << "Test position : " << ((int) k/(im.getBox().pMax().row())) + 1 << " ----- " << (int)(k%(im.getBox().pMax().col())) + 1 << std::endl;
			neighb2d_iterator<int> neigh(pt ,im.getBox());
			int l;
			for(l = 0; l < (int) neigh.vec_().size(); l++){
				
				if(im.vec()[(int) im.vec().size() - (neigh.vec_()[l].row()-1)*im.getBox().pMax().col()+neigh.vec_()[l].col() - 1 - 1] == 0){
					q.push(p);
					break;
				}
			}
		}
	}
	
	while(!q.empty()){
		pt = q.front();
		q.pop();
		neighb2d_iterator<int> neigh(pt ,im.getBox());
		int l;
		for(l = 0; l < (int) neigh.vec_().size(); l++){
			int n = (neigh.vec_()[l].row()-1)*im.getBox().pMax().col()+neigh.vec_()[l].col() - 1;
			if((int) dmap[n] == std::numeric_limits<int>::max()){
				dmap[n] = dmap[(pt.row() - 1)*im.getBox().pMax().col()+pt.col() - 1] + 1;
				q.push(neigh.vec_()[l]);
			}
		}
	}
	
	return dmap;
	
}

int main ()
{
	int d;
	int i = 0;
	image2d<int> im;
	std::cout << "Numbers of rows on image : ";
	scanf("%d", &d);
	int k,l;
	std::vector<int> im_vec;
	std::vector<int>::iterator it;
	it = im_vec.begin();
	std::string ln;
	int max = 30;
	int col_num = 0;
	for (k = 0; k < d; k ++)
	{
		std::cout << "Write row with '0' and '1' : ";
		std::cin >> ln;
		if(k==0) {
			max = (int) ln.length();
			col_num = (int) ln.length();
		}
		if((int) ln.length() > max) {
			std::cout << "Number of cols too high !" << std::endl;
			k--;
		}
		if((int) ln.length() < col_num) {
			std::cout << "Number of cols too low !" << std::endl;
			k--;
		}
		if((int) ln.length() == col_num) {
			for(l = 0; l < (int) ln.length(); l++){
				int el = ln[l] - '0';
				it = im_vec.insert ( it , el );
			}
		}
	}
	im.vec() = im_vec;
	
	std::cout << (int) im.vec().size()/max << " - " << max << std::endl;
	std::cout << "Test : " << (int) im.vec().size()/max << " - " << max << std::endl;
	box2d<int> b(point2d_<int>(1,1),point2d_<int>((int) im.vec().size()/max,max));
	box2d_iterator<int> b_it(b);
	for(b_it.start(); b.has(b_it.elt()); b_it.next()){
		i++;
	}
	
	im.getBox() = b;
	
	std::vector<unsigned int> dmap = dMap(im);
	int z;
	for(z = 0; z < (int) dmap.size(); z++){
		if(z % im.getBox().pMax().col() == 0) std::cout << std::endl;
		std::cout << dmap[z] << " ";
	}
	std::cout << std::endl << std::endl;
	return 0;
}
