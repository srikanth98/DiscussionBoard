package com.redclone.model;

import java.time.Instant;



import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

@Entity
@Table(name="Posts")
public class Post {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long postId;
	@NotBlank(message="Post Name cannot be empty")
	private String postName;
	@Nullable
	private String url;
	@Nullable
	@Lob
	private String description;
	private Integer voteCount;
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userId",referencedColumnName="userId")
	private User user;
	private Instant createdDate;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="topicId",referencedColumnName="id")
	private Topic topic;
	

	public Integer getVoteCount() {
		return voteCount;
	}

	public Post(Long postId, @NotBlank(message = "Post Name cannot be empty") String postName, String url,
			String description, Integer voteCount, @NotNull User user, Instant createdDate, @NotNull Topic topic) {
		super();
		this.postId = postId;
		this.postName = postName;
		this.url = url;
		this.description = description;
		this.voteCount = voteCount;
		this.user = user;
		this.createdDate = createdDate;
		this.topic = topic;
	}

	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}


	
	

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public Post() {
		super();
	}


	
	
}
