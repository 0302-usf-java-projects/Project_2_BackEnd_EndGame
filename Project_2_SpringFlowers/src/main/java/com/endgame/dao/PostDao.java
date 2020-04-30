package com.endgame.dao;


import java.util.List;


import javax.transaction.Transactional;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.endgame.model.Post;
import com.endgame.model.User;


/**
 * 
 * @author mohamad
 *
 * PostDao class has the methods that will allow us to access the database.
 *      It will allow us the add, remove and update the posts associated with
 *         each unique user.
 */
@Repository
@Transactional
public class PostDao implements CrudContract<Post, Integer > {
	
	private SessionFactory sesfact;
	
	@Autowired
	public void serSesFactory(SessionFactory sse) {
		sesfact= sse;
	}
	/**
	 * findAll method is used to view all posts that are stored in the database.
	 *     The posts will be associated with each user but they will all be listed together on
	 *         one user's feed. 
	 */
	@Override
	public List<Post> findAll() {
		return sesfact.openSession().createQuery("from Post", Post.class).list();
	}
	
	/**
	 * getAllById method is used when a user will view another user's profile. They will be 
	 *     able to see all the posts made by that specific user. We can access those posts from the 
	 *         database using the unique id of each user.
	 */
	@Override
	public List<Post> getAllById(Integer id){
		User u = sesfact.getCurrentSession().get(User.class, id);
		return sesfact.getCurrentSession().createQuery("from Post where user ='" + id + "'",Post.class).list();
	}

	/**
	 * 
	 * insert method is used when the user wants to submit a post. once a user submits their post,
	 *     it will be stored in the database so other users can view it and also so it can be shown 
	 *         in the feed of the users.
	 */
	@Override
	public Post insert(Post p) {
		System.out.println(p);
		sesfact.getCurrentSession().save(p);
		return p;
	}
	
	/**
	 * delete method is used when a user wants to delete a post from their profile. each post has 
	 *     its unique id, whcih helps us delete the post form the database.  
	 */
	@Override
	public void delete(Integer id) {
		Post p = sesfact.getCurrentSession().get(Post.class, id);
		sesfact.getCurrentSession().delete(p);
		
		
	}
	
	/**
	 * The update method is called when we want to update a post. if the user wants to
	 *     change something in their post, a spelling mistake for example, then this method will be
	 *         called to update all the changes in the database.
	 */
	@Override
	public Post update(Post p) {
		sesfact.getCurrentSession().update(p);
		return p;
	}

	
	
	
}

	