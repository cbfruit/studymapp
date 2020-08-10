package com.studymapp.project.controller;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.studymapp.project.model.User;
import com.studymapp.project.service.UserService;

@Controller
public class RegisterController {
	
	org.slf4j.Logger logger = LoggerFactory.getLogger(RegisterController.class);
	
	@Autowired
	UserService userService;
	

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		//Pass user object to the register view
		modelAndView.addObject("user", user); 
		modelAndView.setViewName("register");
		return modelAndView;
		
		
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView registerUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//create instance of userService FBE
		User userExists = userService.findByEmail(user.getEmail());
		
		System.out.println(userExists);
		
		//Check UserRepo if email already exists
		if (userExists != null) {
			
			logger.error("Unsuccessful registration, email already present in DB");
			
			modelAndView.addObject("successMessage", "Oops!  There is already a user registered with that email address.");
		}		
		//Check the form for errors and return message to user
		else if(bindingResult.hasErrors()){
			modelAndView.addObject("successMessage", "Please correct the errors in the form");
			modelMap.addAttribute("bindingResult", bindingResult);
		}
		//If no errors, a new user will be created in the DB
		else {
			userService.saveUser(user);
			
			logger.info("New account created");
			
			modelAndView.addObject("successMessage", "User account created successfully!");
		}
		modelAndView.addObject("user", new User()); 
		modelAndView.setViewName("register"); 
		return modelAndView;
	}

}
