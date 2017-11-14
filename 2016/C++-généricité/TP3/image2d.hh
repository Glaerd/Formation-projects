#pragma once
#include "box2d.hh"
#include <vector>


template <typename T, typename U>
class image2d
{
	public :
		image2d() = default;
		std::vector<U>& vec();
		std::vector<U> vec() const;
		box2d<T>& getBox();
		box2d<T> getBox() const;
	private :
		std::vector<U> data;
		box2d<T> b;
};

#include "image2d.tcc"
