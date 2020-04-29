package com.endgame.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.endgame.dao.DaoContract;
import com.endgame.model.User;

@Service
public class UserService {
	
	private DaoContract<User, Integer> loser;
	
	@Autowired
	public void setLoser(DaoContract<User, Integer> us) {
		loser=us;
	}
	
	public User findById(Integer id) {
		return loser.findById(id);
	}
	
	public User insert(User u) {
		loser.insert(u);
		return u;
	}
	
	public User update(User u) {
		loser.update(u);
		return u;
	}
	
	public void deleteById(Integer id) {
		loser.deleteById(id);
	}
	
	public Object authentication(String email,String password) {
		return loser.authentication(email,password);
	}
	
	public List<User> searchByName(String firstName){
	  return loser.searchByName(firstName);
	}

}
