package com.redclone.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.redclone.model.RefreshToken;
import com.redclone.repository.RefreshTokenRepo;

@Service
@Transactional
public class RefreshTokenService {

	@Autowired
	private RefreshTokenRepo refreshTokenRepo;
	
	public RefreshToken generateRefreshToken()
	{
		RefreshToken token=new RefreshToken();
		token.setToken(UUID.randomUUID().toString());
		token.setCreationDate(Instant.now());
		return refreshTokenRepo.save(token);
	}
	
	public void validateRefreshToken(String token) throws Exception
	{
		refreshTokenRepo.findByToken(token).orElseThrow(()->new Exception("Refresh Token not valid"));
	}
	
	public void deleteRefreshToken(String token)
	{
		refreshTokenRepo.deleteByToken(token);
	}
	
}
