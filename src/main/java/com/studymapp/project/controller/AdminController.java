package com.studymapp.project.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.studymapp.project.model.User;
import com.studymapp.project.service.UserService;

@Controller
public class AdminController {
	
	org.slf4j.Logger logger = LoggerFactory.getLogger(AvailabilityController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView home(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		
		//return list of registered user to the admin panel page
		List<User> userList = userService.getUsers();
		
		model.addAttribute("users", userList );
		
		modelAndView.setViewName("admin"); 
		
		//log msgs
		logger.info("Display user list on admin panel");
		
		return modelAndView;
	}
}
