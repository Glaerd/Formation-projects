#pragma once
#include <vector>
#include "point2d.hh"
#include "box2d.hh"
#include "image2d.hh"


template <typename T, typename U>
class neighb2d_iterator
{
	public :
		neighb2d_iterator() = default;
		neighb2d_iterator(point2d_<T> p, box2d<T> b, image2d<U,T> im);
		std::vector<point2d_<T>> vec_();
	private :
		std::vector<point2d_<T>> vec;
};

#include "neighb2d_iterator.tcc"
