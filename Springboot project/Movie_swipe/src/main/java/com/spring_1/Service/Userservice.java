package com.spring_1.Service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.spring_1.Entity.User;
import com.spring_1.Entity.UserRepo;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class Userservice {
    @Autowired
    private UserRepo userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void registerUser(User user) {
        System.out.println("Saving user: " + user.getUsername());
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        System.out.println("User saved successfully!");
    }


    public Optional<User> login(String username, String rawPassword) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && passwordEncoder.matches(rawPassword, user.get().getPassword())) {
            return user; // Return authenticated user
        }
        return Optional.empty(); // Login failed
    }

	public Optional<User> findByUsername(String name) {
		// TODO Auto-generated method stub
		Optional<User> user = userRepository.findByUsername(name);
		if(user.isPresent())
		{
			return user;
		}
		else
			return Optional.empty();
	}
}
