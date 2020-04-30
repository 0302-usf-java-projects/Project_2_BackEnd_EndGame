package com.test.dao;


import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.endgame.dao.UserDao;
import com.endgame.model.User;
import com.test.TestBeanConfig;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestBeanConfig.class})
public class UserDaoTest {
	
	@Autowired
	private UserDao ud;
	
	@BeforeClass
	public static void setup() {
		UserDao ud = new UserDao();
	}
	
	@Test
	public void findById_test() {
		User results = ud.findById(1);
		assertTrue(!(results==null));
		
	}
	
	
	
	
	
	
	
	

}
