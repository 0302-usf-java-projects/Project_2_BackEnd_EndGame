package com.endgame.dao;

/**
 * 
 * CommentDao class implements the CommentContract interface 
 *      and proivdes implementation for those methods.
 */
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.endgame.model.Comment;


@Repository
@Transactional
public class CommentDao implements CommentContract<Comment> {

	
	private SessionFactory sesfact;
	
	@Autowired
	public void serSesFactory(SessionFactory sse) {
		sesfact= sse;
	}

	/**
	 * insert method is invoked when a user wants to comment on
	 *     one of his friends' posts. 
	 */
	@Override
	public Comment insert(Comment c) {
		System.out.println(c);
		sesfact.getCurrentSession().save(c);
		return c;
	}

	/**
	 * 
	 * The getAllByPostId method is invoked when a user is viewing 
	 *     a post in the feed and he is able to see all the comments made
	 *         on a particular post. Each post has its own id and that is 
	 *             how we can specify how comments and posts are related. 
	 */
	@Override
	public List<Comment> getAllByPostId(int id) {
		Comment u = sesfact.getCurrentSession().get(Comment.class, id);
		return sesfact.getCurrentSession().createQuery("from Comment where post ='" + id + "'",Comment.class).list();
	}

}
