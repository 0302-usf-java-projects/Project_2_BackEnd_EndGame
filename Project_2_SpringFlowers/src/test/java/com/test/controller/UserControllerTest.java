package com.test.controller;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.endgame.Controller.UserController;
import com.endgame.model.User;
import com.test.TestBeanConfig;


public class UserControllerTest {
	
	
	@Autowired
	private UserController uc;
	
	@Test
	public void authentication_Test() {
		String email = "endgame.tony777@gmail.com";
		String password ="password";
		User user = new User();
		user.setEmail(email);
		
		assertEquals(user.getEmail(), email);
	}

}
