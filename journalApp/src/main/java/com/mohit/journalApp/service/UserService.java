package com.mohit.journalApp.service;

import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mohit.journalApp.entity.User;
import com.mohit.journalApp.repo.UserRepo;
@Component
public class UserService {
	@Autowired
private UserRepo userRepo;
	
	public void saveEntry(User u) {
		userRepo.save(u);  
	}
	public List<User>getAll(){
		return userRepo.findAll();
	}
	
	public void deleteAll() {
		userRepo.deleteAll();
		
	}
	
	public Optional<User>  findById(ObjectId id) {
		return userRepo.findById(id);
	}
	public void  deleteById(ObjectId id) {
		userRepo.deleteById(id);
	}
	public Optional<User> findByUserName (String username) {
		return userRepo.findByUsername(username);
	}
	
	
}
