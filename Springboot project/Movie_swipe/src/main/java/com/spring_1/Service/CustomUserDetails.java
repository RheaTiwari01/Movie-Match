package com.spring_1.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring_1.Entity.User;
import com.spring_1.Entity.UserRepo;

@Service
public class CustomUserDetails implements UserDetailsService{

	@Autowired
	UserRepo ur;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = ur.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
		
		return org.springframework.security.core.userdetails.User.withUsername(user.getUsername()).password(user.getPassword()).authorities("USER").build();
	}

}
