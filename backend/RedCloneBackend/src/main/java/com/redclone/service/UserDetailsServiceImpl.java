package com.redclone.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.redclone.model.User;
import com.redclone.repository.UserRepo;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
   @Autowired
	private UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> userOptional = userRepo.findByUsername(username);
		User user = userOptional.orElseThrow(()->new UsernameNotFoundException("User Not found"));
		return new org.springframework.security.core.userdetails.User(username,user.getPassword(), user.isEnabled(), true, true, true, getAuthorities("USER"));
		
	}
	private Collection<? extends GrantedAuthority> getAuthorities(String role) {
		// TODO Auto-generated method stub
		return Collections.singletonList(new SimpleGrantedAuthority(role));
	}
	
	
}
