package com.endgame.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.endgame.dao.CommentContract;
import com.endgame.model.Comment;

@Service
public class CommentService {

private CommentContract<Comment> commentService;
	
	@Autowired
	public void setCS(CommentContract<Comment> cs) {
		commentService=cs;
	}
	
	
	public Comment insert(Comment c) {	//add a post method 
		return commentService.insert(c);
	}
	
	public List<Comment> getCommentById(int id) {	//add a post method 
		return commentService.getAllByPostId(id);
	}
}
