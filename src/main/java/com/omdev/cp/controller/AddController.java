package com.omdev.cp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddController {
 
	@GetMapping("/add")
	public String add() {
		return "Add method check";
	}
	
}
