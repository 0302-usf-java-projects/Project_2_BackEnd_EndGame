package com.endgame.Controller;

import java.util.List;

import org.jboss.logging.Param;
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

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {
	
	@Autowired
	private PostService ps;
	
	@RequestMapping(method = RequestMethod.POST, value ="/addpo.tony")
	public ResponseEntity<Post> insert(@RequestBody Post p){
		return  new ResponseEntity<Post>(ps.insert(p), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/getpo.tony")
	public ResponseEntity<List<Post>> getAllById(@PathVariable("id") int id){
		return new ResponseEntity<List<Post>>(ps.findAllById(id), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/{id}/byepo.tony")
	  public ResponseEntity<String> deleteById(@PathVariable("id") int id){
	     ps.delete(id);
	     return new ResponseEntity<String>("post should be deleted",HttpStatus.OK);
	}
	
	@RequestMapping(method =RequestMethod.POST, value = "/uppo.tony")
	public ResponseEntity<Post> update(@RequestBody Post p){
		return new ResponseEntity<Post>(ps.update(p),HttpStatus.OK);
	}
	
	
	

}
