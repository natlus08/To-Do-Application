package com.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author 387090
 *
 */
@Controller
@RequestMapping("/app")
public class ToDoLandingController {
	
	@GetMapping
	String getIndexPage() {
		return "todo/home";
	}
	
}
