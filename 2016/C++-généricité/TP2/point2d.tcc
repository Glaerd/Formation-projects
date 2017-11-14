#include "point2d.hh"

template <typename T>
point2d_<T>::point2d_(T i, T j){
	row_ = i;
	col_ = j;
}

template <typename T>
T& point2d_<T>::row(){
	return row_;	
}

template <typename T>
T& point2d_<T>::col(){
	return col_;	
}
