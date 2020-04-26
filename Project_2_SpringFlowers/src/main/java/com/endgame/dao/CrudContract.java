package com.endgame.dao;

import java.util.List;

public interface CrudContract<P,I> {
	P insert(P p);
	List<P> getAllById(I id);
	void delete(I id);
	P update(P p);

}
