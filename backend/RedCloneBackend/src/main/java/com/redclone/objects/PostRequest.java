package com.redclone.objects;

public class PostRequest {

	private Long postId;
	private String topicName;
	private String postName;
	private String url;
	private String description;
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
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
	public PostRequest(Long postId, String topicName, String postName, String url, String description) {
		super();
		this.postId = postId;
		this.topicName = topicName;
		this.postName = postName;
		this.url = url;
		this.description = description;
	}
	
	public PostRequest()
	{
		
	}
	
	
	
}
