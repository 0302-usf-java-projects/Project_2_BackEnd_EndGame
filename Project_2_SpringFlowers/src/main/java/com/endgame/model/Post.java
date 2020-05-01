package com.endgame.model;

/**
 * 
 * This is the model class for the post. It has the different fields such as user, 
 *      post, and comments. the user hjas a many-to-one relationship with the user_id,
 *          since a user can make as many posts as he desires. The comments field has a one-to-many 
 *                 relationship  with the post since their can be many comments per post.
 */

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name="post")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	


	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@OneToMany(mappedBy = "post", cascade = { CascadeType.ALL }) 
	private Set<Comment> comments;
	private String title;
	private String content;
	private String created;
	private String updated;
	private String type;
	private String photoKey;
	private int numLikes;
	
	public int getNumLikes() {
		return numLikes;
	}
	




	public void setNumLikes(int numLikes) {
		this.numLikes = numLikes;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getPhotoKey() {
		return photoKey;
	}


	public void setPhotoKey(String photoKey) {
		this.photoKey = photoKey;
	}


	
	  public static Post newInstance(int id, User user,Set<Comment> comments, String title, String
	  content, String created, String updated, String type, int numLikes, String photoKey) { Post po = new Post(id, user,comments,
	  title, content, created, updated, type, numLikes, photoKey);
	  
	  ValidatorFactory vf = Validation.buildDefaultValidatorFactory(); Validator v
	  = vf.getValidator(); Set<ConstraintViolation<Post>> violations =
	  v.validate(po); if(violations.size()>0) { throw new
	  RuntimeException("Stop doing that"); } return po; }
	 

	public Post(int id, User user,Set<Comment> comments, String title, String content, String created, String updated, String type, int numLikes, String photoKey) {
		super();
		this.id = id;
		this.user = user;
		this.title = title;
		this.content = content;
		this.created = created;
		this.updated = updated;
		this.type = type;
		this.numLikes = numLikes;
		this.photoKey = photoKey;
		this.comments = comments;
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

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}



	@Override
	public String toString() {
		return "Post [id=" + id + ", user=" + user + ", title=" + title + ", content=" + content + ", created="
				+ created + ", updated=" + updated + ", type=" + type + ", photoKey=" + photoKey + ", numLikes="
				+ numLikes + "]";
	}

	

}