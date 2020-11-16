package com.redclone.security;

import java.nio.charset.Charset;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.time.Instant;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Service
public class JWTProvider {
	/* Todo later for jks
	private Keystore keystore;
	@PostConstruct
	public void init()
	{
		try
		{
			keystore=KeyStore.getInstance("JKS");
			
		}
		catch(Exception ex)
		{
			throw new Ex
		}
	}*/
	//Below is not recommended. TODO Change to use file.
	@Value("${jwt.key}")
	private String key;
	
	@Value("${jwt.expiration}")
	private Long jwtExpiryTime;//Milli secs.
	public String generateToken(Authentication authentication )
	{
		User principal= (org.springframework.security.core.userdetails.User)authentication.getPrincipal();
		return Jwts.builder().setSubject(principal.getUsername()).signWith(getPrivateKey()).setExpiration(Date.from(Instant.now().plusMillis(jwtExpiryTime))).compact();
	}
	public String generateTokenWithUsername(String username )
	{
		
		return Jwts.builder().setSubject(username).signWith(getPrivateKey()).setExpiration(Date.from(Instant.now().plusMillis(jwtExpiryTime))).compact();
	}
	private Key getPrivateKey() {
		// TODO Next step: Use JKS for additional security
		return Keys.hmacShaKeyFor(key.getBytes());
		
	}
	
	public long getJWTExpiryTime()
	{
		return jwtExpiryTime;
	}
	public boolean validateToken(String token)
	{
		
		Jwts.parser().setSigningKey(key.getBytes(Charset.forName("UTF-8"))).parseClaimsJws(token);
		return true;
	}
	
	
	public String getUserNameFromToken(String token)
	{
		Claims claims = Jwts.parser().setSigningKey(key.getBytes(Charset.forName("UTF-8"))).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

}
