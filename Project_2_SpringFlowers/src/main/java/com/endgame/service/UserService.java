package com.endgame.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.endgame.dao.DaoContract;
import com.endgame.model.User;
import com.endgame.helpers.SendEmail;

@Service
public class UserService {
	
	
	
	private DaoContract<User, Integer> loser;
	
	@Autowired
	public void setLoser(DaoContract<User, Integer> us) {
		loser=us;
	}
	
	public User findById(Integer id) { //returns User object based on ID
		return loser.findById(id);
	}
	
	public User findByEmail(String email) { //returns User based on Email
		return loser.findByEmail(email);
	}
	
	public User insert(User u) { //add new User to database
		loser.insert(u);
		return u;
	}
	
	public User update(User u) { //updates User based on received User object
		loser.update(u);
		return u;
	}
	
	public String updatePass(String email) { //update password by email address
		return loser.updatePass(email);
	}
	
	public void deleteById(Integer id) { //delete a User by ID
		loser.deleteById(id);
	}
	
	public Object authentication(String email,String password) { //authentication method 
		return loser.authentication(email,password);
	}
	

	public List<User> searchByName(String firstName){ //returns list of Users by first name 
	  return loser.searchByName(firstName);
	}

}