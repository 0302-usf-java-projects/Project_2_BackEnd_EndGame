package com.endgame.dao;

import java.util.List;

public interface CommentContract<C> {
	
	C insert(C c);
	List<C> getAllByPostId(int id);

}
