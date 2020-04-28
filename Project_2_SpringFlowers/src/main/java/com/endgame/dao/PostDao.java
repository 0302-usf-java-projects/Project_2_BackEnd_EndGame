package com.endgame.dao;


import java.util.List;


import javax.transaction.Transactional;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.endgame.model.Post;
import com.endgame.model.User;

@Repository
@Transactional
public class PostDao implements CrudContract<Post, Integer > {
	
	private SessionFactory sesfact;
	
	@Autowired
	public void serSesFactory(SessionFactory sse) {
		sesfact= sse;
	}
	
	@Override
	public List<Post> findAll() {
		return sesfact.openSession().createQuery("from Post", Post.class).list();
	}
	
	@Override
	public List<Post> getAllById(Integer id){
		User u = sesfact.getCurrentSession().get(User.class, id);
		return sesfact.getCurrentSession().createQuery("from Post where user ='" + id + "'",Post.class).list();
	}

	@Override
	public Post insert(Post p) {
		System.out.println(p);
		sesfact.getCurrentSession().save(p);
		return p;
	}
	@Override
	public void delete(Integer id) {
		Post p = sesfact.getCurrentSession().get(Post.class, id);
		sesfact.getCurrentSession().delete(p);
		
		
	}
	@Override
	public Post update(Post p) {
		sesfact.getCurrentSession().update(p);
		return p;
	}

	
	
	
}

	