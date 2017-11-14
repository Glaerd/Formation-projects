#pragma once
#include "point2d.hh"

template <typename T>
class box2d_iterator;

template <typename T>
class box2d
{
	public :
		box2d() = default;
		box2d(point2d_<T> min, point2d_<T> max);
		bool has(point2d_<T> p);
		point2d_<T> pMin();
		point2d_<T> pMax();
	private :
		point2d_<T> pMin_, pMax_;
};

#include "box2d.tcc"

