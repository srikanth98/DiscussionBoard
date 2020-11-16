package com.redclone.model;

import java.time.Instant;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import javax.persistence.Entity;
@Entity
public class RefreshToken {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String token;
	private Instant creationDate;
	
	public RefreshToken() {
		super();
	}
	public RefreshToken(Long id, String token, Instant creationDate) {
		super();
		this.id = id;
		this.token = token;
		this.creationDate = creationDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Instant getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Instant creationDate) {
		this.creationDate = creationDate;
	}
	
	
	
}
