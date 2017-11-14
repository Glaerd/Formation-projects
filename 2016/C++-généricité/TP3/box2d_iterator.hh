#pragma once
#include "point2d.hh"
#include "box2d.hh"
#include <iostream>

template <typename T>
class box2d_iterator
{
	public :
		box2d_iterator() = default;
		box2d_iterator(box2d<T> b_);
		point2d_<T> elt() const;
		void start();
		void end();
		void next();
	private :
		box2d<T> b;
		point2d_<T> p_it;
};

#include "box2d_iterator.tcc"

