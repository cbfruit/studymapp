package com.studymapp.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studymapp.project.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
	

}
