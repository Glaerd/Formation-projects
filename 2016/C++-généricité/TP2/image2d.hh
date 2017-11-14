#pragma once
#include "box2d.hh"
#include <vector>


template <typename T>
class image2d
{
	public :
		image2d() = default;
		std::vector<T>& vec();
		std::vector<T> vec() const;
		box2d<T>& getBox();
		box2d<T> getBox() const;
	private :
		std::vector<T> data;
		box2d<T> b;
};

#include "image2d.tcc"
