package com.endgame.dao;

import java.util.List;

public interface DaoContract<T,I> {
	T insert(T t);
	T findById(I id);
	T findByEmail(String e);
	String updatePass(String email);
	void deleteById(I id);
	T update(T t);
	Object authentication(String email, String password);
	List<T> searchByName(String firstName); 

}
