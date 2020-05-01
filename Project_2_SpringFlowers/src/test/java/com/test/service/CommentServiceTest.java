package com.test.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Service;
import com.endgame.dao.CommentDao;
import com.endgame.model.Comment;
import com.endgame.model.Post;
import com.endgame.service.CommentService;


@Service
public class CommentServiceTest {
	
	@Mock
	private CommentDao cd;
	
	@InjectMocks
	private CommentService cs;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	
	@Test
	public void getCommentTest() {
		List<Comment> list = cs.getCommentById(1);
		verify(cd,times(1)).getAllByPostId(1);
	}
	
	

}
