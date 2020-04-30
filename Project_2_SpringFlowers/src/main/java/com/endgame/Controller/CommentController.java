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

import com.endgame.model.Comment;
import com.endgame.model.Post;
import com.endgame.service.CommentService;
import com.endgame.service.PostService;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommentController {

	@Autowired
	private CommentService cs;
	
	@RequestMapping(method = RequestMethod.POST, value = "/insertComment.tony")
	//response entity is a type that lets you build an entire http response
	public ResponseEntity<Comment> insert(@RequestBody Comment c) {
		return new ResponseEntity<Comment>(cs.insert(c), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/getComment.tony")
	//response entity is a type that lets you build an entire http response
	public ResponseEntity<List<Comment>> findAll(@PathVariable("id") int id) {
		return new ResponseEntity<List<Comment>>(cs.getCommentById(id), HttpStatus.OK);
	}

	

	
}
