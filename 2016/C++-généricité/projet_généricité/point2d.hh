#pragma once

template <typename T>
class point2d_
{
	public :
		point2d_() = default;
		point2d_<T>(T i, T j);
		T& row();
		T& col();
	private :
		T row_, col_;
};

#include "point2d.tcc"
