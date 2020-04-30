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

/**
 * 
 * 
 * @author mohamad
 * UserDao class has the methods that will allow us to access the database.
 *      It will allow us the add, remove and update user data using the methods that
 *          are implemented in this class. 
 *
 */
@Repository
@Transactional
public class UserDao implements DaoContract<User, Integer> {
	

	private SessionFactory sesfact;
	
	@Autowired
	  public void setSesFactory(SessionFactory sse) {
	    sesfact= sse;
	  }
	/**
	 * 
	 * the insert method will let us insert a new user in the database
	 *     when we register on the front end. once a user chooses a password, 
	 *         it will be hashed and stored in the database. 
	 */
	@Override
	public User insert(User t) {
		t.setPassword(securePassword(t.getPassword()));
		sesfact.openSession().save(t);
		return t;
		
	}
	
	/**
	 * 
	 * findById method will allow us to retrieve user information from
	 *     the database using their unique id.
	 */

	@Override
	public User findById(Integer id) {
		return sesfact.openSession().get(User.class, id);
	}
	
	/**
	 * 
	 * findByEmail method was implemented in case a user forgot their password. This
	 *     method will allow us to retrieve the user info by email so we can send them 
	 *         a secured email with a new password associated with their account.
	 */
	
	@Override
	public User findByEmail(String email) {	
		
		//Session session = sesfact.openSession();
		 Session session = sesfact.getCurrentSession();
		 String q = "from User where email ='"+email+"'";
         User user = null;
         try{
        	 user = (User) session.createQuery(q).getSingleResult();
         }
         catch (NoResultException nre){
    		 return user;
         }

         if(user == null){
          return null;
         }
		 return user;
	} 
	
	/**
	 * 
	 * deleteById method will allow us to delete any user from the database using
	 *     their unique id.
	 */

	@Override
	public void deleteById(Integer id) {
		User t = sesfact.getCurrentSession().get(User.class, id);
		sesfact.getCurrentSession().delete(t);		
	}
	
	/**
	 * 
	 * generatePassword is a method that we implemented in order to invoke
	 *     when a user forgets their password and wants to reset it. This method
	 *         is called in the SendEmail class, which returns a randomly generated 
	 *             password that the user will use to log in into their account.
	 */
	
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
	
	/**
	 * 
	 * The updatePass method will update the user's password in the database
	 *     and give it a new value. The new value is the random password that is 
	 *         randomly generated in the generatePassword() method.
	 *         
	 */
		
	public String updatePass(String email) {
						
		User user = findByEmail(email);
		if(user != null) {
									
			String tempPass = generatePassword();									
			user.setPassword(tempPass);
			SendEmail.mail(email, user);
			System.out.println(user.getPassword());
			update(user);
			
			return "OK";
			
		}else {
			return "NO";
		}		
												
	}
	
	/**
	 * 
	 * update method with parameter type User will be invoked when we want to
	 *     update some user information. if a user wants to modify their information,
	 *         the update method will be invoked and the database will be updated.
	 */
	

	@Override
	public User update(User t) {
		t.setPassword(securePassword(t.getPassword()));
		Session session = sesfact.getCurrentSession();
	    //Transaction tx = session.beginTransaction();
	    session.update(t);
	   // tx.commit();
	    return t;
	}
	
	/**
	 * 
	 * authentication method is used when we want to authenticate the 
	 *     credentials of the user that is going to be logged in. when the user 
	 *         enters their information, this method will be called to check if 
	 *             the credentials are correct. 
	 *        
	 */
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
	
	/**
	 * 
	 * securePassword method is used when the user is registering for a new account.
	 *     For security reasons, the password is hashed before it gets stored in the database.
	 *         this method is also called when we resent the password via email, or when the user chooses
	 *             to change their password within their profile.
	 */
	
	
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
	
	/**
	 * 
	 * searchByName method is used when a user wants to search for a friend within their
	 *     friends list. It is set to search via first name, but the wildcard symbol (%) is used.
	 *         Therefore, when a user is searching for their friends, they don't have to type
	 *             the whole firstname. They just have to type the first letter and if the user
	 *                 present, then they will be able to visit  their profile. 
	 */
	
	   public List<User> searchByName(String firstName){
	      Session session = sesfact.getCurrentSession();
	     String q = "from User where firstname LIKE :firstName";
	     List<User> list = session.createQuery(q).setParameter("firstName","%" + firstName + "%").getResultList();
	     return list;
	    }

	
}
