package com.endgame.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.endgame.model.Post;
import com.endgame.service.PostService;

import org.apache.log4j.Logger;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {
	
	final static Logger lg = Logger.getLogger(PostController.class);
	
	@Autowired
	private PostService ps;
		
	
	/** 
	 * Gets all Posts
	 * @return List of all Posts 
	 */	
	@RequestMapping(method = RequestMethod.GET, value = "/allposts.tony")
	//response entity is a type that lets you build an entire http response
	public ResponseEntity<List<Post>> findAll() {
		lg.info("all post requested");
		return new ResponseEntity<List<Post>>(ps.findAll(), HttpStatus.OK);
	}
		
	/**
	 * Inserts Post 
	 * @return Post object
	 */	
	@RequestMapping(method = RequestMethod.POST, value ="/addpo.tony")
	public ResponseEntity<Post> insert(@RequestBody Post p){
		lg.info("New Post was created.");
		return  new ResponseEntity<Post>(ps.insert(p), HttpStatus.OK);
	}
		
	/**
	 * Gets all Posts by User id 
	 * @return List of Posts
	 * @param id
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/getpo.tony")
	public ResponseEntity<List<Post>> getAllById(@PathVariable("id") int id){
		return new ResponseEntity<List<Post>>(ps.findAllById(id), HttpStatus.OK);
	}
	
	/**
	 * Deletes Post by id 
	 * @param id
	 */
	@RequestMapping(method = RequestMethod.DELETE, value="/{id}/byepo.tony")
	  public ResponseEntity<String> deleteById(@PathVariable("id") int id){
		 lg.info("Post was deleted.");
	     ps.delete(id);
	     return new ResponseEntity<String>("post should be deleted",HttpStatus.OK);
	}
	
	/**
	 * Updates Post
	 * @return Post object  
	 */
	@RequestMapping(method =RequestMethod.POST, value = "/uppo.tony")
	public ResponseEntity<Post> update(@RequestBody Post p){
		lg.info("Post id: " + p.getId() +" " + p.getTitle() + " updated.");
		return new ResponseEntity<Post>(ps.update(p),HttpStatus.OK);
	}
		

}
