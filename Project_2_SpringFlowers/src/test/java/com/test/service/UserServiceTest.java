package com.test.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.endgame.model.User;
import com.endgame.service.UserService;
import com.test.TestBeanConfig;

@Service
public class UserServiceTest {
	private UserService us;
	
	@Before
	public void setup() {
		us = new UserService();
	}
	
	@Test
	public void findById_test() {
		User result = us.findById(1);
		assertNotNull(result);
	}
	

}
