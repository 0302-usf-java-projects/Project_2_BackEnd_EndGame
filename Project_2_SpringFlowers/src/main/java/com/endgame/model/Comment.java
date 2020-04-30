package com.endgame.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
