package com.endgame.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


@Entity
@Table
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", nullable = false)
	private User user;
	private String title;
	private String content;
	private Timestamp created;
	private Timestamp updated;
	
	
	public static Post newInstance(int id, User user, String title, String content, Timestamp created, Timestamp updated) {
		Post po = new Post(id, user, title, content, created, updated);
		
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
	    Validator v = vf.getValidator();
	    Set<ConstraintViolation<Post>> violations = v.validate(po);
	    if(violations.size()>0) {
	      throw new RuntimeException("Stop doing that");
	    }
	    return po;
	}
	
	public Post(int id, User user, String title, String content, Timestamp created, Timestamp updated) {
		super();
		this.id = id;
		this.user = user;
		this.title = title;
		this.content = content;
		this.created = created;
		this.updated = updated;
	}




	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public User getUser() {
		return user;
	}




	public void setUser(User user) {
		this.user = user;
	}




	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public String getContent() {
		return content;
	}




	public void setContent(String content) {
		this.content = content;
	}




	public Timestamp getCreated() {
		return created;
	}




	public void setCreated(Timestamp created) {
		this.created = created;
	}




	public Timestamp getUpdated() {
		return updated;
	}




	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}




	@Override
	public String toString() {
		return "Post [id=" + id + ", user=" + user + ", title=" + title + ", content=" + content + ", created="
				+ created + ", updated=" + updated + "]";
	}
	
	
	
	
	

}
