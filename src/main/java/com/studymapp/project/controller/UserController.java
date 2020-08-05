package com.studymapp.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studymapp.project.model.User;
import com.studymapp.project.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
			
	@GetMapping("/user")
	public String getUsers(Model model) {
		
		List<User> userList = userService.getUsers();
		
		model.addAttribute("users", userList );
		
		return "user";
	}

}
