package com.endgame.dao;

import java.util.List;

/**
 * 
 * @author mohamad
 *
 * @param <C>
 * 
 * CommentContract interface has the methods that will be implemented 
 *  in the CommentDao class.
 */

public interface CommentContract<C> {
	
	C insert(C c);
	List<C> getAllByPostId(int id);

}
