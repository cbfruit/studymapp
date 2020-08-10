package com.studymapp.project.controller;

import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomErrorController implements ErrorController{
	
	//add logging for error page
	org.slf4j.Logger logger = LoggerFactory.getLogger(CustomErrorController.class);
	
	//Create controller to allow error page to be recorded in logs
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error"); 
		
		//Custom log msgs
		logger.warn("The error page has been displayed");
		
		return modelAndView;
	}
	
	
	@Override
	public String getErrorPath() { 
		return "/error";
	}

}
