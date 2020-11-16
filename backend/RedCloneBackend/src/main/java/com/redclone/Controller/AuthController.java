package com.redclone.Controller;

import java.util.Optional;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redclone.objects.AuthenticationToken;
import com.redclone.objects.LoginRequest;
import com.redclone.objects.RegisterRequest;
import com.redclone.service.AuthService;
import com.redclone.service.RefreshTokenService;
import com.redclone.objects.RefreshTokenRequest;
import com.redclone.model.RefreshToken;
import com.redclone.model.VerificationToken;
import com.sun.mail.iap.Response;
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@Autowired
	private RefreshTokenService refreshTokenService;
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody RegisterRequest request)
	{
		authService.signup(request);
		return new ResponseEntity<>("Registration successful!",HttpStatus.OK);
	}
	
	@GetMapping("/accountVerify/{token}")
	public  ResponseEntity<String> accountVerification(@PathVariable String token) throws Exception
	{
		authService.verifyAccount(token);
		return new ResponseEntity<String>("Account enabled",HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationToken> login(@RequestBody LoginRequest loginRequest )
	{
		AuthenticationToken token = authService.login(loginRequest);
		return new ResponseEntity<AuthenticationToken>(token,HttpStatus.OK);
		
	}
	
	@PostMapping("/refresh/token")
	public AuthenticationToken refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshRequest)
	{
		try {
		return authService.refreshToken(refreshRequest);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	
	@PostMapping("/logout")
	public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest)
	{
		refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
		return new ResponseEntity<String>("Refresh token logout done.",HttpStatus.OK);
	}
}
