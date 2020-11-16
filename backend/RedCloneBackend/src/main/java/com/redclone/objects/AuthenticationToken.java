package com.redclone.objects;

import java.time.Instant;

public class AuthenticationToken {

	private String name;
	private String token;
	private String refreshToken;
	private Instant expiresAt;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	public AuthenticationToken(String name, String token, String refreshToken, Instant expiresAt) {
		super();
		this.name = name;
		this.token = token;
		this.refreshToken = refreshToken;
		this.expiresAt = expiresAt;
	}
	public Instant getExpiresAt() {
		return expiresAt;
	}
	public void setExpiresAt(Instant expiresAt) {
		this.expiresAt = expiresAt;
	}
	public AuthenticationToken(String name, String token) {
		super();
		this.name = name;
		this.token = token;
		
	}
	public AuthenticationToken() {
		// TODO Auto-generated constructor stub
	}
	
}
