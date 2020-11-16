package com.redclone.objects;

public class TopicDto {

	private Long id;
	private String name;
	private String description;
	private Integer numOfPosts;
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getNumOfPosts() {
		return numOfPosts;
	}
	public void setNumOfPosts(Integer numOfPosts) {
		this.numOfPosts = numOfPosts;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public TopicDto(Long id, String name, String description, Integer numOfPosts) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.numOfPosts = numOfPosts;
	}
	public TopicDto() {
		super();
	}
	
	
	
}
