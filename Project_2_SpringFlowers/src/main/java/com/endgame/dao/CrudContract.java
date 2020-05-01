package com.endgame.dao;

/**
 * 
 * CrudContract interface has the methods that will be implemented 
 *  in the PostDao class.
 */
import java.util.List;



public interface CrudContract<P,I> {
	P insert(P p);
	List<P> findAll();
	List<P> getAllById(I id);
	void delete(I id);
	P update(P p);

}
