package com.studymapp.project.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.studymapp.project.model.Availability;
import com.studymapp.project.service.AvailabilityService;

@Controller
public class ShowAvailabilityController {
	
	org.slf4j.Logger logger = LoggerFactory.getLogger(ShowAvailabilityController.class);
	
	@Autowired	private AvailabilityService availabilityService;
			
	@GetMapping("/showAvailability")
	public String getAvailabilies(Model model) {
		
		List<Availability> availabilityList = availabilityService.getAvailabilities();
		
		model.addAttribute("availabilities", availabilityList );
		
		logger.info("Availability list displayed to user");
		
		return "showAvailability";
	}
}

