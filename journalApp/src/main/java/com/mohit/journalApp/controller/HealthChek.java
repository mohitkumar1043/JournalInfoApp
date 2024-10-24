package com.mohit.journalApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController// that is automatically convert return type data in  Jackson format
@RequestMapping("chekapp")
public class HealthChek {
	@GetMapping("/healthchek")
  public String healthChek() {
	  return "ok";
  }
}
