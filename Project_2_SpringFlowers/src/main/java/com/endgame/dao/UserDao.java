package com.endgame.dao;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import com.endgame.model.User;

@Repository
@Transactional
public class UserDao implements DaoContract<User, Integer> {
	

	private SessionFactory sesfact;
	
	@Autowired
	  public void setSesFactory(SessionFactory sse) {
	    sesfact= sse;
	  }
	
	@Override
	public User insert(User t) {
		t.setPassword(securePassword(t.getPassword()));
		sesfact.openSession().save(t);
		return t;
		
	}

	@Override
	public User findById(Integer id) {
		return sesfact.openSession().get(User.class, id);
	}

	@Override
	public void deleteById(Integer id) {
		User t = sesfact.getCurrentSession().get(User.class, id);
		sesfact.getCurrentSession().delete(t);

		
	}

	@Override
	public User update(User t) {
		t.setPassword(securePassword(t.getPassword()));
		Session session = sesfact.openSession();
	    Transaction tx = session.beginTransaction();
	    session.update(t);
	    tx.commit();
	    return t;
	}

	@Override
	public Object authentication(String email, String password) {
		Session session = sesfact.getCurrentSession();
		Object user = null;
		try{
			user = session.createSQLQuery("select * from \"user\" where email='"+email+"' and password='"+securePassword(password)+"'").getSingleResult();
			return user;
		}catch (NoResultException nre){
		//Ignore this because as per your logic this is ok!
		}
		if(user == null){
		 return null;
		}
		return null;


	}
	
	
	public String securePassword(String passwordToHash){
	    String generatedPassword = null;
	    try {
	        MessageDigest md = MessageDigest.getInstance("SHA-512");
	        md.update("kamal".getBytes(StandardCharsets.UTF_8));
	        byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
	        StringBuilder sb = new StringBuilder();
	        for(int i=0; i< bytes.length ;i++){
	            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        generatedPassword = sb.toString();
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    return generatedPassword;
	}

	
}
