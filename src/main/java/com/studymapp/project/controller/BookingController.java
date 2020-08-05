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

import com.studymapp.project.model.Booking;
import com.studymapp.project.service.BookingService;

@Controller
public class BookingController {
	
	//Import all services required and add @Autowired
	@Autowired	private BookingService bookingService;
			
	@GetMapping("/booking")
	public String getBooking(Model model) {
		
		//Create object of each List below to call the method in the corresponding classes		
		List<Booking> bookingList = bookingService.getBookings();
				
		//Call the model for each list to display results on booking page		
		model.addAttribute("bookings", bookingList );
				
		//Return booking.html
		return "booking";
	}
	//Use PostMapping annotation to save new Booking and redirect user to updated form on booking.html
	@PostMapping("/booking/addNew")
	public String addNew(Booking booking) {
		
		bookingService.save(booking);
		
		return "redirect:/booking";
	}
	//Call method created in bookingService to return ID from Repository
	//Method specified needs to be optional
	@RequestMapping("booking/findById")	
	//This annotation is necessary to fill the form with the data linked to the ID found in the DB
	@ResponseBody
	public Optional<Booking> findById(int id) {
		
		return bookingService.findById(id);				
	}
	@RequestMapping(value="/booking/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(Booking booking) {
		
		bookingService.save(booking);
		
		return "redirect:/booking";
	}
	
	@RequestMapping(value="/booking/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		
		bookingService.delete(id);
		
		return "redirect:/booking";
	}
}

