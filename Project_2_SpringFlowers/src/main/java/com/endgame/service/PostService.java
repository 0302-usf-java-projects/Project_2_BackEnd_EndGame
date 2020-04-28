package com.endgame.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.endgame.dao.CrudContract;
import com.endgame.dao.DaoContract;
import com.endgame.model.Post;


@Service
public class PostService {
	
	private CrudContract<Post, Integer> po;
	
	@Autowired
	public void setPo(CrudContract<Post, Integer> pd) {
		po=pd;
	}
	
	public Post insert(Post p) {
		po.insert(p);
		return p;
	}
	
	public List<Post> findAll(){
		return po.findAll();
	}
	
	public List<Post> findAllById(int id){
		return po.getAllById(id);
	}
	
	public void  delete(int id) {
		po.delete(id); 
	}
	
	public Post update(Post p) {
		return po.update(p);
	}

}
