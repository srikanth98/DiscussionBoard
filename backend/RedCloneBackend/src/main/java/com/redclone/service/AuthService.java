package com.redclone.service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.validator.internal.util.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.redclone.model.NotificationEmail;
import com.redclone.model.User;
import com.redclone.model.VerificationToken;
import com.redclone.objects.AuthenticationToken;
import com.redclone.objects.LoginRequest;
import com.redclone.objects.RefreshTokenRequest;
import com.redclone.objects.RegisterRequest;
import com.redclone.repository.RefreshTokenRepo;
import com.redclone.repository.UserRepo;
import com.redclone.repository.VerificationTokenRepo;
import com.redclone.security.JWTProvider;

@Service
public class AuthService {

	@Autowired
	private  PasswordEncoder passwordEncoder;
	@Autowired
	private  UserRepo userRepository;
	@Autowired
	private RefreshTokenRepo refreshTokenRepo;
	@Autowired
	private  VerificationTokenRepo verificationRepo;
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private JWTProvider jwtProvider;
	@Autowired
	private RefreshTokenService refreshTokenService;
	@Autowired
	private MailService mailService;
	/*
	@Autowired
	private MailService mailService;
	*/
	@Transactional
	public void signup(RegisterRequest request)
	{
		User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setCreatedDate(Instant.now());
        user.setEnabled(true);
        String token=generateVerificationToken(user);
       mailService.sendMail(new NotificationEmail("Account Activation Email",user.getEmail(),
        		"Please click on the following link to activate your account:"+"http://localhost:8080/api/auth/accountVerify/"+token));
      
       System.out.println("Please click on the following link to activate your account:"+"http://localhost:8080/api/auth/accountVerify/"+token);
        
	}
	private String generateVerificationToken(User user) {
		// TODO Auto-generated method stub
		String verificationToken = UUID.randomUUID().toString();
		VerificationToken token = new VerificationToken();
	    token.setToken(verificationToken);
	    token.setUser(user);
	    verificationRepo.save(token);
	    return verificationToken;
	    
	}
	
	public void verifyAccount(String token) throws Exception
	{
		Optional<VerificationToken> verificationToken = verificationRepo.findByToken(token);
		verificationToken.orElseThrow(() -> new Exception("Verification failed with token"));
        enableUser(verificationToken.get());
	}
	@Transactional
	private void enableUser(VerificationToken verificationToken) throws Exception {
		// TODO Auto-generated method stub
	
		String username=verificationToken.getUser().getUsername();
		Optional<User> user = userRepository.findByUsername(username);
		user.orElseThrow(()->new Exception("User for token not found."));
		user.get().setEnabled(true);
		userRepository.save(user.get());
				
				
	}
	public AuthenticationToken login(LoginRequest loginRequest) {
		// TODO Auto-generated method stub
		
		Authentication authenticate = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticate);
	   String token = jwtProvider.generateToken(authenticate);
	   AuthenticationToken authToken = new AuthenticationToken();
	   authToken.setName(loginRequest.getUsername());
	   authToken.setToken(token);
	   authToken.setRefreshToken(refreshTokenService.generateRefreshToken().getToken());
	   authToken.setExpiresAt(Instant.now().plusMillis(jwtProvider.getJWTExpiryTime()));
	   return authToken;
	}
	@Transactional()
	public User getCurrentUser() throws Exception {
		// TODO Auto-generated method stub
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	
		return userRepository.findByUsername(authentication.getName()).orElseThrow(()->new Exception("Unable to find user"));
	}

	
	public AuthenticationToken refreshToken(RefreshTokenRequest requestTokenRequest) throws Exception
	{
		refreshTokenService.validateRefreshToken(requestTokenRequest.getRefreshToken());
		String token = jwtProvider.generateTokenWithUsername(requestTokenRequest.getUsername());
		AuthenticationToken authToken = new AuthenticationToken();
		authToken.setToken(token);
		authToken.setName(requestTokenRequest.getUsername());
		authToken.setRefreshToken(refreshTokenService.generateRefreshToken().getToken());
		authToken.setExpiresAt(Instant.now().plusMillis(jwtProvider.getJWTExpiryTime()));
		return authToken;
	}
}
