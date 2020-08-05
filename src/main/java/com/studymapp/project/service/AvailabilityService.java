package com.studymapp.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studymapp.project.model.Availability;
import com.studymapp.project.repository.AvailabilityRepository;

@Service
public class AvailabilityService {
	
	@Autowired
	private AvailabilityRepository availabilityRepository;
	
	//Find all availabilities in the DB
	public List<Availability> getAvailabilities(){
		return availabilityRepository.findAll();		
	}
	
	//Add new availability
	public void save(Availability availability) {
		availabilityRepository.save(availability);		
	}
	
	//Return availability by ID
	//Method specified needs to be optional in case there is no record present to prevent system crash
	public Optional<Availability> findById(int id) {
		return availabilityRepository.findById(id);
	}
	
	//Write the delete method 
	public void delete(Integer id) {
		availabilityRepository.deleteById(id);		
	}

}
