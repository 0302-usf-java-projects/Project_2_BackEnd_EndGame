package com.endgame.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.endgame.dao.UserDao;
import com.endgame.helpers.SendEmail;
import com.endgame.model.User;
import com.endgame.service.UserService;


@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	
	@Autowired
	private UserService us;
	
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "/{email}/{password}/authentication.tony")
	public ResponseEntity<Object> authentication(@PathVariable("email") String email,@PathVariable("password") String password){
		return new ResponseEntity<Object>(us.authentication(email,password),HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/getem.tony")
	public ResponseEntity<User> getById(@PathVariable("id") int id){
		return new ResponseEntity<User>(us.findById(id),HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{email}/getemail.tony")
	public ResponseEntity<User> getByEmail(@PathVariable("email") String email){
		return new ResponseEntity<User>(us.findByEmail(email),HttpStatus.OK);
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
	

	@RequestMapping(method = RequestMethod.GET, value = "{firstName}/search.tony")
	public ResponseEntity<List<User>> searchByName(@PathVariable("firstName") String firstName) {
	    return new ResponseEntity<List<User>>(us.searchByName(firstName), HttpStatus.OK);
	}
	 

//	@RequestMapping(method = RequestMethod.GET, value="/{email}/email.tony")
//		public ResponseEntity<String> sendEmail(@PathVariable("email") String email){		
//					us.sendMail(email);
//			return new ResponseEntity<String>("email sent", HttpStatus.OK);
//	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{email}/updatepass.tony")
	public ResponseEntity<String> updatepass(@PathVariable("email") String email){		
				us.updatePass(email);
		return new ResponseEntity<String>("pass updated", HttpStatus.OK);
}
	 
	
}	
	


