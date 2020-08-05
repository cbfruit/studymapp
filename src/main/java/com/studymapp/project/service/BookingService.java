package com.studymapp.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studymapp.project.model.Booking;
import com.studymapp.project.repository.BookingRepository;

@Service
public class BookingService {
	
	@Autowired
	private BookingRepository bookingRepository;
	
	//Find all availabilities in the DB
	public List<Booking> getBookings(){
		return bookingRepository.findAll();		
	}
	
	//Add new booking
	public void save(Booking booking) {
		bookingRepository.save(booking);		
	}
	
	//Return booking by ID
	//Method specified needs to be optional in case there is no record present to prevent system crash
	public Optional<Booking> findById(int id) {
		return bookingRepository.findById(id);
	}
	
	//Write the delete method 
	public void delete(Integer id) {
		bookingRepository.deleteById(id);		
	}

}
