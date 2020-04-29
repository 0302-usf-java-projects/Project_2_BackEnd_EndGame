package com.endgame.dao;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import java.util.Random;


import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import com.endgame.helpers.SendEmail;
import com.endgame.model.User;
import org.hibernate.Query;

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
	public User findByEmail(String email) {	
		
		//Session session = sesfact.openSession();
		 Session session = sesfact.getCurrentSession();
		 String q = "from User where email ='"+email+"'";
         User user = (User) session.createQuery(q).getSingleResult();
		 return user;	
	} 

	@Override
	public void deleteById(Integer id) {
		User t = sesfact.getCurrentSession().get(User.class, id);
		sesfact.getCurrentSession().delete(t);		
	}
	
	
	public String generatePassword() {
		
		 int leftLimit = 97; // letter 'a'
		    int rightLimit = 122; // letter 'z'
		    int targetStringLength = 10;
		    Random random = new Random();
		    StringBuilder buffer = new StringBuilder(targetStringLength);
		    for (int i = 0; i < targetStringLength; i++) {
		        int randomLimitedInt = leftLimit + (int) 
		          (random.nextFloat() * (rightLimit - leftLimit + 1));
		        buffer.append((char) randomLimitedInt);
		    }
		    String generatedString = buffer.toString();
		 
		    //System.out.println(generatedString);			    
		    
		return generatedString;
	}
	
		
	public String updatePass(String email) {
						
		User user = findByEmail(email);
		if(user != null) {
									
			String tempPass = generatePassword();									
			user.setPassword(tempPass);
			SendEmail.mail(email, user);
			System.out.println(user.getPassword());
			update(user);
			
			return "user found";
			
		}else {
			return "user is not found";
		}		
												
	}
	

	@Override
	public User update(User t) {
		t.setPassword(securePassword(t.getPassword()));
		Session session = sesfact.getCurrentSession();
	    //Transaction tx = session.beginTransaction();
	    session.update(t);
	   // tx.commit();
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
	
//	public List<User> searchByName(String firstName){
//	  return sesfact.openSession().createQuery("From User where" +  firstName + " like '%?%'", User.class).list();
//	}
	
	/**this is tested and returns the user with the specified first name*/
//	public List<User> searchByName(String firstName){
//	  Session session = sesfact.getCurrentSession();
//	 String q = "from User where firstname ='"+firstName+"'";
//	 List<User> list = session.createQuery(q).list();
//	 return list;
//	}
	
	   public List<User> searchByName(String firstName){
	      Session session = sesfact.getCurrentSession();
	     String q = "from User where firstname LIKE :firstName";
	     List<User> list = session.createQuery(q).setParameter("firstName","%" + firstName + "%").getResultList();
	     return list;
	    }

	
}
