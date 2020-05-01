package com.test.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.endgame.dao.UserDao;
import com.endgame.model.User;
import com.endgame.service.UserService;
import com.test.TestBeanConfig;

@Service
public class UserServiceTest {

	
	@Mock
	private UserDao ud;
	
	@InjectMocks
	private UserService us;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void findById_test() {
		User user = ud.findById(1);
		verify(ud,times(1)).findById(1);
		
	}
	
	@Test
	public void insertTest() {
		User user = new User();
		User user2 = us.insert(user);
		assertTrue(user.equals(user2));
		verify(ud,times(1)).insert(user);
	}
	
	@Test 
	public void updateTest() {
		User user = new User();
		User user2 = us.update(user);
		assertTrue(user.equals(user2));
		verify(ud,times(1)).update(user);
	}
	
	@Test
	public void findByEmailTest() {
		String email = "email@test.com";
		User user = ud.findByEmail(email);
		verify(ud,times(1)).findByEmail(email);
		
	}
	
	
	@Test
	public void authenticationTest() {
		String email = "email@test.com";
		String password = "pword";
		Object obj = ud.authentication(email, password);
		verify(ud,times(1)).authentication(email,password);
	}
	
	@Test
	public void updatePassTest() {
		String email = "email@test.com";
		String newpass = ud.updatePass(email);
		verify(ud,times(1)).updatePass(email);
	}
	

}
