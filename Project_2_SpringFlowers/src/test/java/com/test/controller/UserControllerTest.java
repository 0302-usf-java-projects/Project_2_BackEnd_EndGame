package com.test.controller;



import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.endgame.Controller.UserController;
import com.test.TestBeanConfig;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestBeanConfig.class})
public class UserControllerTest {
	
	
	@Autowired
	private UserController uc;
	
	@Test
	public void authentication_Test() {
		String email = "endgame.tony777@gmail.com";
		String password ="password";
		ResponseEntity<Object> results = uc.authentication(email, password);
		assertTrue(!(results ==null));
	}

}
