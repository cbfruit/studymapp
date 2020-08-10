package com.studymapp.project.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.studymapp.project.model.Role;
import com.studymapp.project.model.User;
import com.studymapp.project.repository.RoleRepository;
import com.studymapp.project.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	BCryptPasswordEncoder encoder;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	
	//Call findByEmail method to check if email exists in DB already
    public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
    
    public List<User> getUsers(){
		return userRepository.findAll();		
	}
        
	@Override
	public void saveUser(User user) {
		
		//convert the password from plain text to BCrypt
		user.setPassword(encoder.encode(user.getPassword()));		
		//Set the user status to verified
		user.setStatus("VERIFIED");
		//Use findByRole method to locate least privileged user
		Role userRole = roleRepository.findByRole("SITE_USER");
		//Set user role to the above by default
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		
		userRepository.save(user);

	}
}


