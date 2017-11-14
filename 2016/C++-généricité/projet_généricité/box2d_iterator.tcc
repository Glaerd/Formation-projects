#include "box2d_iterator.hh"

template <typename T>
box2d_iterator<T>::box2d_iterator(box2d<T> b_)
{
	b = b_;
}

template <typename T>
point2d_<T> box2d_iterator<T>::elt() const
{
	return p_it;
}

template <typename T>
void box2d_iterator<T>::start()
{
	p_it = b.pMin();
}

template <typename T>
void box2d_iterator<T>::end()
{
	p_it = b.pMax();
}

template <typename T>
void box2d_iterator<T>::next()
{
	point2d_<T> p_temp;
	if(p_it.col() <= b.pMax().col() || p_it.row() <= b.pMax().row())
	{
		if(p_it.col() >= b.pMax().col()) 
		{
			p_temp.col() = b.pMin().col();
			p_temp.row() = p_it.row() + 1;
			p_it = p_temp;
		}
		else {
			p_temp.row() = p_it.row();
			p_temp.col() = p_it.col() + 1;
			p_it = p_temp;
		}
	}
}
