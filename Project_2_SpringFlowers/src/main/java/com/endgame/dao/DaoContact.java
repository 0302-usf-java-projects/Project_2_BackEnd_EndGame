package com.endgame.dao;



public interface DaoContact<T,I> {
	T insert(T t);
	T findById(I id);
	void deleteById(I id);
	T update(T t);

	
	

}
