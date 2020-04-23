package com.endgame.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.sun.istack.NotNull;

@Entity
@Table
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
	
	@Column(unique = true)
	@NotNull
	private String username;
	
	@Column
	@NotNull
	private String password;
	
	@Column
	@NotNull
	private String firstname;
	
	@Column
	@NotNull
	private String lastname;
	
	@Column
	@NotNull
	private String birthday;
	
	@Column
	@NotNull
	private String sex;
	
	@Column(unique = true)
	@NotNull
	private String email;
	
	
	
	
	public User() {
		super();
	}
	
	public static User newInstance(int id, String username, String password, String firstname, String lastname, String birthday,
			String sex, String email) {
		User loser = new User(id, username, password, firstname, lastname, birthday, sex, email);
		
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
	    Validator v = vf.getValidator();
	    Set<ConstraintViolation<User>> violations = v.validate(loser);
	    if(violations.size()>0) {
	      throw new RuntimeException("Stop doing that");
	    }
	    return loser;
	}
	
	


	private User(int id, String username, String password, String firstname, String lastname, String birthday,
			String sex, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthday = birthday;
		this.sex = sex;
		this.email = email;
	}





	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getBirthday() {
		return birthday;
	}


	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", birthday=" + birthday + ", sex=" + sex + ", email=" + email + "]";
	}
	
	
	
	
	
	
	

}
