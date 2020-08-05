package com.studymapp.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studymapp.project.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	//Method to search the Role repo for the user permissions
	public Role findByRole(String role);

}
