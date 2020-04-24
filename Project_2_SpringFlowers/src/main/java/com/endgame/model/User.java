package com.endgame.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.sun.istack.NotNull;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
			cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Post> posts;
	
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
	
	public static User newInstance(int id, Set<Post> posts, String password, String firstname, String lastname, String birthday,
			String sex, String email) {
		User loser = new User(id, posts, password, firstname, lastname, birthday, sex, email);
		
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
	    Validator v = vf.getValidator();
	    Set<ConstraintViolation<User>> violations = v.validate(loser);
	    if(violations.size()>0) {
	      throw new RuntimeException("Stop doing that");
	    }
	    return loser;
	}
	
	
	


	public User(int id, Set<Post> posts, String password, String firstname, String lastname, String birthday,
			String sex, String email) {
		super();
		this.id = id;
		this.posts = posts;
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


	
	

}
