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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.sun.istack.NotNull;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
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
	private String birthday; //month-day-year
	
	@Column
	@NotNull
	private String sex; //apparently more than male or female 
	
	@Column
	private String phone; //users phone number 
	
	@Column
	private String work; //users place of employment
	
	@Column
	private String education;
	
	@Column
	private String address; //users current address
	
	@Column(unique = true)
	@NotNull
	private String email;
	

	
	public User() {
		super();
	}
	
	public static User newInstance(int id, Set<Post> posts, String password, String firstname, String lastname, String birthday,
			String sex, String phone, String work, String education, String address, String email) {
		User loser = new User(id, posts, password, firstname, lastname, birthday, sex, phone, work, education, address, email);
		
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
	    Validator v = vf.getValidator();
	    Set<ConstraintViolation<User>> violations = v.validate(loser);
	    if(violations.size()>0) {
	      throw new RuntimeException("Stop doing that");
	    }
	    return loser;
	}
	

	public User(int id, Set<Post> posts, String password, String firstname, String lastname, String birthday,
			String sex, String phone, String work, String education, String address, String email) {
		super();
		this.id = id;
		this.posts = posts;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthday = birthday;
		this.sex = sex;
		this.phone = phone;
		this.work = work;
		this.education = education;
		this.address = address;
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", posts=" + posts + ", password=" + password + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", birthday=" + birthday + ", sex=" + sex + ", phone=" + phone + ", work="
				+ work + ", education=" + education + ", address=" + address + ", email=" + email + "]";
	}


	
	

}