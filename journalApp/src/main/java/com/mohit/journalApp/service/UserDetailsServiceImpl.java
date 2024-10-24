package com.mohit.journalApp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.mohit.journalApp.entity.User;
import com.mohit.journalApp.repo.UserRepo;
@Component
public class UserDetailsServiceImpl  implements UserDetailsService{
  @Autowired
   public UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User u = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
		
		return org.springframework.security.core.userdetails.User.withUsername(u.getUsername())
                .password(u.getPassword()) 
                .roles()
                .build();
		
	}

}
