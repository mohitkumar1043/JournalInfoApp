package com.mohit.journalApp.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mohit.journalApp.entity.JournalEntry;
import com.mohit.journalApp.service.JournalEntryService;
import com.mohit.journalApp.service.UserService;

@RestController
@RequestMapping("/journalentry")
public   class JournalEntryController {
	@Autowired
   private    JournalEntryService  journalentryservice; 
	@Autowired
	private UserService userService;
   @GetMapping("/getallentrysofuser")// this way is batter for us thats take less time because do not searching  all gone on 
  // @GetMapping                  direct target
   public ResponseEntity<?>  getAll() {
	   String uname=  SecurityContextHolder.getContext().getAuthentication().getName();
	   Optional<com.mohit.journalApp.entity.User> userInDb=userService.findByUserName(uname);
	   List<JournalEntry> entrys=userInDb.get().getEntrys();
	   if(entrys!=null&&entrys.size()!=0) {
		   return new ResponseEntity<>(entrys,HttpStatus.OK);
	   }
	   else {
		   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	   
   }
  @PostMapping("/createentry")
  // @PostMapping
  // requestbody is saying spring hey please take the data and give me its object 
  public ResponseEntity<?> createEntry(@RequestBody JournalEntry entry) {
	    try {
		   String uname=  SecurityContextHolder.getContext().getAuthentication().getName();
		   Optional<com.mohit.journalApp.entity.User> userInDb=userService.findByUserName(uname);
		  
			  entry.setDate(LocalDateTime.now());
				 JournalEntry saved=  journalentryservice.saveEntry(entry);
				 userInDb.get().getEntrys().add(saved);
				 userService.saveEntry(userInDb.get());
				
				      return new  ResponseEntity<>(HttpStatus.CREATED);
	    }
		catch(Exception e) {	
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		 
		}
	 
  }
	
	
	@DeleteMapping("/deleteuserentry")
	public ResponseEntity<?> deleteEntry(ObjectId id) {
		try {
			// String uname=  SecurityContextHolder.getContext().getAuthentication().getName();
			 //  Optional<com.mohit.journalApp.entity.User> userInDb=userService.findByUserName(uname);
			//List<JournalEntry> entrys =  userInDb.get().getEntrys();
			// full implimation of this function is reanining
			    journalentryservice.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);	
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
