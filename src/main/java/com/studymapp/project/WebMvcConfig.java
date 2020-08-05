package com.studymapp.project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Set as configuration class
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	//Define bean for password encoder
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		
		//Create new instance of password encoder
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

}





