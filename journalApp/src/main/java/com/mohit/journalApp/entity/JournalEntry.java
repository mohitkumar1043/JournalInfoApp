package com.mohit.journalApp.entity;

import java.time.LocalDateTime;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component//Used on a class to make it available for Spring IoC
@NoArgsConstructor
@Document(collection="journal_entries")//  it is tell about  spring hey its a momgodb like doucument
@Data// provides all getter and setters  and required arg constructor ,tostring ,equals and hashcode on compilation time
public class JournalEntry {
	@Id// it is a primary key
private ObjectId id;
private String title;
private String description;
private LocalDateTime date;
}
