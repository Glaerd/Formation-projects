#include <vector>
#include "neighb2d_iterator.hh"


template <typename T,typename U>
neighb2d_iterator<T>::neighb2d_iterator(point2d_<T> p, box2d<T> b,image2d<U,T> im){
		if(p.col() > 1 && im.vec()[(p.row()-1)*b.pMax().col()+p.col()-2] != " ") 
			vec.insert(vec.begin(),point2d_<T>(p.row(),p.col()-1));
		if(p.col() <= b.pMax().col() - 1 && im.vec()[(p.row()-1)*b.pMax().col()+p.col()] != " ") 
			vec.insert(vec.begin(),point2d_<T>(p.row(),p.col()+1));
		if(p.row() > 1 && im.vec()[(p.row()-2)*b.pMax().col()+p.col()-1] != " ") 
			vec.insert(vec.begin(),point2d_<T>(p.row()-1,p.col()));
		if(p.row() <= b.pMax().row() - 1 && im.vec()[(p.row())*b.pMax().col()+p.col()-1] != " ") 
			vec.insert(vec.begin(),point2d_<T>(p.row()+1,p.col()));
}

template <typename T>
std::vector<point2d_<T>> neighb2d_iterator<T>::vec_(){
	return vec;
}
