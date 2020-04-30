package com.endgame.model;

import java.util.Set;

import javax.persistence.Entity;
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
@Table(name="comment")
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JoinColumn(name="post_id", nullable=false)
	@ManyToOne
	private Post post;
	private String content;
	private String created;
	private int user_id;
	private String firstname;
	private String lastname;

	
	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
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


	public Comment(int id, Post post, String content, String created, int user_id, String firstname, String lastname) {
		super();
		this.id = id;
		this.post = post;
		this.content = content;
		this.created = created;
		this.user_id = user_id;
		this.firstname = firstname;
		this.lastname = lastname;
	}


	@Override
	public String toString() {
		return "Comment [id=" + id + ", post=" + post + ", content=" + content + ", created=" + created + "]";
	}
	
	
	  public static Comment newInstance(int id, Post post,String content,String created) { Comment co = new Comment(id, post,content,created);
			  
			  ValidatorFactory vf = Validation.buildDefaultValidatorFactory(); Validator v
			  = vf.getValidator(); Set<ConstraintViolation<Comment>> violations =
			  v.validate(co); if(violations.size()>0) { throw new
			  RuntimeException("Stop doing that"); } return co; }


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Post getPost() {
		return post;
	}


	public void setPost(Post post) {
		this.post = post;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getCreated() {
		return created;
	}


	public void setCreated(String created) {
		this.created = created;
	}


	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Comment(int id, Post post, String content, String created) {
		super();
		this.id = id;
		this.post = post;
		this.content = content;
		this.created = created;
	}
	
	
}
