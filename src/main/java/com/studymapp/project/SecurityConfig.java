package com.studymapp.project;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomLoginHandler customLoginHandler;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private DataSource dataSource;
	
	//Query for authentication in app.props
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	//Query for authorisation in app.props
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;
	
		
	
	//Provide the user query & role query result, along with datasource and password encoder to be executed on login
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);
	}
	
	
	//Define access controls and required permissions for each URL path
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//This line forces HTTPS in Spring Boot app
		http.requiresChannel().anyRequest().requiresSecure();
		
		//Enables SameSite and XXS protection and adds these to response header
		http.headers()
			.frameOptions().sameOrigin()
			.httpStrictTransportSecurity().disable()
			.xssProtection().block(true);

		http.authorizeRequests()
				// URLs matching for access rights
				.antMatchers("/").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/register").permitAll()
				.antMatchers("/index/**").permitAll()
				.antMatchers("/contact/**").permitAll()
				.antMatchers("/info/**").permitAll()
				.antMatchers("/account/**").hasAnyAuthority("ADMIN_USER", "SITE_USER")
				.antMatchers("/showAvailability/**").hasAnyAuthority("SITE_USER")
				.antMatchers("/availability/**").hasAnyAuthority("ADMIN_USER")
				.antMatchers("/user/**").hasAnyAuthority("ADMIN_USER")
				.antMatchers("/booking/**").hasAnyAuthority("ADMIN_USER")
				.antMatchers("/admin/**").hasAnyAuthority("ADMIN_USER")
				.anyRequest().authenticated()
				.and()
				// form login
				.csrf().disable().formLogin()
				.loginPage("/login")
				.failureUrl("/login?error=true")
				
				
				//CustomLoginHandler
				.successHandler(customLoginHandler)
				
				
				
				.usernameParameter("email")
				.passwordParameter("password")
				.and()
				// logout
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/index").and()
				.exceptionHandling()
				.accessDeniedPage("/access-denied");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

}
