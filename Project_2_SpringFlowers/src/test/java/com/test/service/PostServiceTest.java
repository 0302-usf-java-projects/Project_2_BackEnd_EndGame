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

import com.endgame.dao.PostDao;
import com.endgame.model.Post;

import com.endgame.service.PostService;


@Service
public class PostServiceTest {
	
	@Mock
	private PostDao pd;
	
	@InjectMocks
	private PostService ps;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void findAllTest() {
		List<Post> list = pd.findAll();
		verify(pd,times(1)).findAll();
	}
	
	@Test
	public void findAllById() {
		List<Post> list = ps.findAllById(1);
		verify(pd,times(1)).getAllById(1);
	}
	
	@Test
	public void insertTest() {
		Post po = new Post();
		Post po2 = ps.insert(po);
		assertTrue(po.equals(po2));
		verify(pd,times(1)).insert(po);
	}

}
