package com.mohit.journalApp.entity;



import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;


import lombok.Data;
import lombok.NoArgsConstructor;
@Document(collection = "users")
@Data
@NoArgsConstructor
@Component
public class User {
	@Id
private ObjectId id;
@Indexed(unique = true)

private String username;

private String password;
@DBRef
private List<JournalEntry>entrys=new ArrayList<>();

}
