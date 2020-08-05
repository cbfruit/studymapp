package com.studymapp.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studymapp.project.model.Availability;
import com.studymapp.project.service.AvailabilityService;

@Controller
public class AvailabilityController {
	
	@Autowired	private AvailabilityService availabilityService;
			
	@GetMapping("/availability")
	public String getBooking(Model model) {
		
		List<Availability> availabilityList = availabilityService.getAvailabilities();
		
		model.addAttribute("availabilities", availabilityList );
				
		return "availability";
	}
	
	
	//Use PostMapping annotation to save new Availability and redirect user to updated form on availability.html
	@PostMapping("/availability/addNew")
	public String addNew(Availability availability) {
		
		availabilityService.save(availability);
		
		return "redirect:/availability";
	}
	//Call method created in availabilityService to return ID from Repository
	//Method specified needs to be optional
	@RequestMapping("availability/findById")	
	//This annotation is necessary to fill the form with the data linked to the ID found in the DB
	@ResponseBody
	public Optional<Availability> findById(int id) {
		
		return availabilityService.findById(id);				
	}
	@RequestMapping(value="/availability/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(Availability availability) {
		
		availabilityService.save(availability);
		
		return "redirect:/availability";
	}
	
	@RequestMapping(value="/availability/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		
		availabilityService.delete(id);
		
		return "redirect:/availability";
	}
}

