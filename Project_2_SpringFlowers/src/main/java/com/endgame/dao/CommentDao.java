package com.endgame.dao;

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

	@Override
	public Comment insert(Comment c) {
		System.out.println(c);
		sesfact.getCurrentSession().save(c);
		return c;
	}

	@Override
	public List<Comment> getAllByPostId(int id) {
		Comment u = sesfact.getCurrentSession().get(Comment.class, id);
		return sesfact.getCurrentSession().createQuery("from Comment where post ='" + id + "'",Comment.class).list();
	}

}
