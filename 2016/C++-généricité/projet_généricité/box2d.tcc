#include "box2d.hh"

template <typename T>
box2d<T>::box2d(point2d_<T> min, point2d_<T> max){
	pMin_ = min;
	pMax_ = max;
}

template <typename T>
bool box2d<T>::has(point2d_<T> p)
{
	if(p.col() <= pMax_.col() && 
	   p.col() >= pMin_.col() && 
	   p.row() <= pMax_.row() && 
	   p.row() >= pMin_.row()) 
		return true;
	else return false;
}
template <typename T>
point2d_<T> box2d<T>::pMin(){
	return pMin_;
}
template <typename T>
point2d_<T> box2d<T>::pMax(){
	return pMax_;
}

