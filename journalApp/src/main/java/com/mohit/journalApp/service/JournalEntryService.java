package com.mohit.journalApp.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mohit.journalApp.entity.JournalEntry;
import com.mohit.journalApp.repo.JournalEntryRepo;
@Component
public class JournalEntryService {
    
	@Autowired// Used on fields, constructors, or setters to inject dependencies
	private JournalEntryRepo journalentryrepo;
    
	public JournalEntry saveEntry(JournalEntry entry) {
		return journalentryrepo.save(entry);  
	}
	public List<JournalEntry> getAll(){
		
		return journalentryrepo.findAll();
		
		
		
	}
	
	public void deleteAll() {
		journalentryrepo.deleteAll();
		
	}
	
	public Optional<JournalEntry>  findById(ObjectId id) {
		return journalentryrepo.findById(id);
	}
	public void  deleteById(ObjectId id) {
		journalentryrepo.deleteById(id);
	}
	
	
}
