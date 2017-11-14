#include <vector>
#include "neighb2d_iterator.hh"


template <typename T>
neighb2d_iterator<T>::neighb2d_iterator(point2d_<T> p, box2d<T> b){
		if(p.col() > 1) vec.insert(vec.begin(),point2d_<T>(p.row(),p.col()-1));
		if(p.col() <= b.pMax().col() - 1) vec.insert(vec.begin(),point2d_<T>(p.row(),p.col()+1));
		if(p.row() > 1) vec.insert(vec.begin(),point2d_<T>(p.row()-1,p.col()));
		if(p.row() <= b.pMax().row() - 1) vec.insert(vec.begin(),point2d_<T>(p.row()+1,p.col()));
}

template <typename T>
std::vector<point2d_<T>> neighb2d_iterator<T>::vec_(){
	return vec;
}
