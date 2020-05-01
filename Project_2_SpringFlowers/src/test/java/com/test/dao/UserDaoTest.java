package com.test.dao;


import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.nio.channels.SeekableByteChannel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.endgame.dao.UserDao;
import com.endgame.model.User;
import com.test.TestBeanConfig;


@RunWith(MockitoJUnitRunner.class)
public class UserDaoTest {
	
	@Mock
	private SessionFactory sesfact;
	
	@InjectMocks
	private UserDao ud;
	
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	
	
	@Test
	public void findById_test() {
		User user = new User();
		assertNotNull(user);
		
	}
	
	
	
	
	
	
	
	

}
