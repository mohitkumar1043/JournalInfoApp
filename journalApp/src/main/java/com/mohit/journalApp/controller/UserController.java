package com.mohit.journalApp.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mohit.journalApp.entity.User;
import com.mohit.journalApp.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
private UserService userService;
	private  PasswordEncoder passwordEncode=new  BCryptPasswordEncoder();
 
    @PostMapping("/createuser")
    public ResponseEntity<?> createUser(@RequestBody User u){
    	try {
    	u.setPassword(passwordEncode.encode(u.getPassword()));
    	 userService.saveEntry(u);
    	 return new ResponseEntity<>(u,HttpStatus.OK);
    	}
    	catch(Exception e) {
    		return new ResponseEntity<>(HttpStatus.CONFLICT);
    	}
    }	
    @PutMapping("/changepassword")
    public ResponseEntity<?>  updatePasswrod(@RequestBody User u){
    	//  securitycontext holder will provides us user name 
   String uname=  SecurityContextHolder.getContext().getAuthentication().getName();
   User userInDb = userService.findByUserName(uname)
           .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + uname));
	
   userInDb.setUsername(uname);
   userInDb.setPassword(u.getPassword());
   userInDb.setPassword(passwordEncode.encode(userInDb.getPassword()));
	 userService.saveEntry(userInDb);
    return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("deleteuser")
    public ResponseEntity<?> deleteUser(){
    	String name=  SecurityContextHolder.getContext().getAuthentication().getName();
    	Optional<User> userInDb = userService.findByUserName(name);
    	try {
    	userService.deleteById(userInDb.get().getId());
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	}
    	catch(Exception e) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	 
    }
    
}
