package com.spring_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.spring_1.Service.CustomUserDetails;

@Configuration
public class SecurityConfig {
	@Autowired
    private CustomUserDetails customUserDetails;
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


	@Bean
	public SecurityFilterChain securitychain(HttpSecurity http) throws Exception
	{
		http.authorizeHttpRequests(configurer -> configurer
			    .requestMatchers("/", "/log","/add").permitAll()  // Correct usage of requestMatchers()
			    .anyRequest().authenticated()
			)
			.formLogin(form -> form 
			    .loginPage("/log")
			    .defaultSuccessUrl("/like", true)  // Note the method spelling here
			    .permitAll()
			    )
		
			.logout( logout ->logout
			    .logoutSuccessUrl("/log")  // Correct spelling of logoutSuccessUrl()
			    .permitAll()
);
			return http.build();

	}

	// Inject your custom UserDetailsService

	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
	    AuthenticationManagerBuilder authenticationManagerBuilder = 
	            http.getSharedObject(AuthenticationManagerBuilder.class);
	    
	    authenticationManagerBuilder
	        .userDetailsService(customUserDetails)  // Set your custom UserDetailsService
	      ;  // Set the PasswordEncoder (e.g., BCrypt)

	    return authenticationManagerBuilder.build();  // Build and return the AuthenticationManager
	}


}
