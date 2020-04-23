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
	
	@RequestMapping(method = RequestMethod.POST, value = "/getem.tony/{id}")
	public ResponseEntity<User> getById(@PathVariable String id){
		return new ResponseEntity<User>(us.findById(Integer.parseInt(id)),HttpStatus.OK);
		
	}
	@RequestMapping(method = RequestMethod.POST, value="/in.tony")
	public ResponseEntity<User> insert(@RequestBody User u){
		return new ResponseEntity<User>(us.insert(u),HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/up.tony")
	  public ResponseEntity<User> update(@RequestBody User u){
	    return new ResponseEntity<User>(us.update(u),HttpStatus.OK);
	}
	 
	@RequestMapping(method = RequestMethod.DELETE, value="/gone.tony")
	  public ResponseEntity<String> deleteById(){
	     us.deleteById(3);
	     return new ResponseEntity<String>("should be deleted",HttpStatus.OK);
	}
	 
	 
	
	

}
