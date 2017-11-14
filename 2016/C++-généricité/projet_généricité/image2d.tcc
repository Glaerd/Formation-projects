#include "image2d.hh"


template <typename T>
std::vector<T>& image2d<T>::vec(){
	return data;
}

template <typename T>
std::vector<T> image2d<T>::vec() const{
	return data;
}

template <typename T>
box2d<T>& image2d<T>::getBox(){
	return b;
}

template <typename T>
box2d<T> image2d<T>::getBox() const{
	return b;
}
