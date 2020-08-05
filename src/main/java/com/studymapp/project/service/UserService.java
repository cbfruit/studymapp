package com.studymapp.project.service;

import java.util.List;

import com.studymapp.project.model.User;

public interface UserService {
	
	public void saveUser(User user);
	
	public User findByEmail(String email);
	
	public List<User> getUsers();
	
}







