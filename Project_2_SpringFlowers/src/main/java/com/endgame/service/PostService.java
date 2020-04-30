package com.endgame.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.endgame.dao.CrudContract;
import com.endgame.model.Post;


@Service
public class PostService {
	
	private CrudContract<Post, Integer> po;
	
	@Autowired
	public void setPo(CrudContract<Post, Integer> pd) {
		po=pd;
	}
	
	public Post insert(Post p) {	//add a post method 
		po.insert(p);
		return p;
	}
	
	public List<Post> findAll(){ //returns list of all post 
		return po.findAll();
	}
	
	public List<Post> findAllById(int id){ //returns list of all post by a user
		return po.getAllById(id);
	}
	
	public void  delete(int id) { //deletes post
		po.delete(id); 
	}
	
	public Post update(Post p) {	//update post based on received post object
		return po.update(p);
	}

}