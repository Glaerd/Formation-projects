#include "image2d.hh"


template <typename T,typename U>
std::vector<T>& image2d<T,U>::vec(){
	return data;
}

template <typename T,typename U>
std::vector<T> image2d<T,U>::vec() const{
	return data;
}

template <typename T,typename U>
box2d<T>& image2d<T,U>::getBox(){
	return b;
}

template <typename T,typename U>
box2d<T> image2d<T,U>::getBox() const{
	return b;
}
