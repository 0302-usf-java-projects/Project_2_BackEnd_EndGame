package com.endgame.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.endgame.model.User;
import com.endgame.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService us;
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/getem.tony")
	public ResponseEntity<User> getById(@PathVariable("id") int id){
		return new ResponseEntity<User>(us.findById(id),HttpStatus.OK);
		
	}
	@RequestMapping(method = RequestMethod.POST, value="/in.tony")
	public ResponseEntity<User> insert(@RequestBody User u){
		return new ResponseEntity<User>(us.insert(u),HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/up.tony")
	  public ResponseEntity<User> update(@RequestBody User u){
	    return new ResponseEntity<User>(us.update(u),HttpStatus.OK);
	}
	 
	@RequestMapping(method = RequestMethod.DELETE, value="/{id}/gone.tony")
	  public ResponseEntity<String> deleteById(@PathVariable("id") int id){
	     us.deleteById(id);
	     return new ResponseEntity<String>("should be deleted",HttpStatus.OK);
	}
	 
	 
	
	

}
