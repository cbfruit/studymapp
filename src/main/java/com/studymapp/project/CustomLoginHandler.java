package com.studymapp.project;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;


@Configuration
public class CustomLoginHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	//Import all methods and classes ensuring correct ones are added to avoid errors
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException{
		
		//To get the target URL, the determineTargetUrl() is required to identify the role of the user logging into the application once the credentials have been authenticated
		String targetUrl = determineTargetUrl(authentication);
		
		//check if the response is already committed
		if(response.isCommitted()) {
			return;
		}
		
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	
	protected String determineTargetUrl(Authentication authentication) {
		String url = "/login?error=true";
		
		//Fetch the roles from the authentication object
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority a : authorities) {
			roles.add(a.getAuthority());
		}
		//Check user roles and return appropriate redirect url
		if(roles.contains("ADMIN_USER")) {
			url = "/admin";
		}
		else if(roles.contains("SITE_USER")) {
			url = "/account";
		}
		return url;
	}

}





















