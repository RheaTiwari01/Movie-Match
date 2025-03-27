package com.spring_1.controller;



import java.security.Principal;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring_1.Entity.LoginReq;
import com.spring_1.Entity.TrendingMovieRepo;
import com.spring_1.Entity.User;
import com.spring_1.Entity.UserRepo;
import com.spring_1.Entity.User_like;
import com.spring_1.Service.LikeRequest;
import com.spring_1.Service.TrendingMoviesService;
import com.spring_1.Service.UserLikeService;
import com.spring_1.Service.Userservice;

import jakarta.servlet.http.HttpSession;

@Controller

public class UserController {
	 @Autowired
	 
	    private Userservice userservice; 
	 @Autowired
	 private TrendingMovieRepo tmr;
	 @Autowired
	 private UserRepo ur;
	 @Autowired
	 private UserLikeService usr;
	 @Autowired 
	 private Userservice us;
	 @Autowired
	 private TrendingMoviesService tms;
	 

@GetMapping("/trend")
public String   trending(Model model) {
     List<Map<String, Object>> trends =tms.trends(); // No casting needed
     model.addAttribute("tr",trends);
     return "trending";
}

@GetMapping("/like")
public String likeMovie(Model model) {
	model.addAttribute("user_like", new User_like());
   List<Map<String,Object>> movie=tms.trends();
   model.addAttribute("tr", movie);
     //ResponseEntity.ok("Movies liked successfully!");
     return "movieLike";
}
@PostMapping("/liked")
public String likeMovie(@RequestParam List<String> movieIds, Principal principal,Model model) {
    System.out.print("here");
    
    // Fetch the logged-in user
    User logUser = ur.findByUsername(principal.getName())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    // Iterate through the list of movie IDs to save the liked movies
    for (String movieId : movieIds) {
        // Create a new User_like instance with the movie ID and liked status set to true
        User_like userLike = new User_like(logUser, movieId, true); // Set liked as true
        usr.saveUserLike(userLike);  // Save the liked movie to the database
        System.out.println("Saved liked movie ID: " + movieId);
    }

    // Debugging: Check if movie_id is received correctly
    System.out.println("Logged-in user ID: " + logUser.getId());
    User user=ur.findByUsername(principal.getName()).orElseThrow(()->new UsernameNotFoundException("User not found"));
	model.addAttribute("userId1", user.getId() );
	
	return "CommonLike";
	

  //  return ResponseEntity.ok("Movies liked successfully!");
}


@GetMapping("/")
public String adduser(Model model)
{
	model.addAttribute("user", new User());
	return "home";
}
@GetMapping("/log")
public String Loginuser(@RequestParam(value = "error", required = false) String error, Model model)
{
	
	 if (error != null) {
         model.addAttribute("errorMessage", "Invalid username or password. Please try again.");
     }
     return "login";
}
@PostMapping("/add")
public String addUser(@ModelAttribute User user, Model model) {
    try {
        us.registerUser(user);  // Attempt to save the user
    } catch (DataIntegrityViolationException e) {
        // Catching the exception if there's a violation (like duplicate username)
        model.addAttribute("error", "Username already exists. Please choose a different username.");
        return "home";// Returning to the same page with the error message
    }
    List<Map<String, Object>> trends =tms.trends(); // No casting needed
    model.addAttribute("tr",trends);
    return "trending";
    // If successful, return the next page (e.g., a success or trending page)
    
}

/*@PostMapping("/log")
public String loginUser(@ModelAttribute LoginReq lr, Model model) {
    Optional<User> user = us.login(lr.getUsername(), lr.getPassword());

    if (user.isPresent()) {
        // Login successful, redirect to a home or dashboard page
        model.addAttribute("user", user.get());
        model.addAttribute("user_like", new User_like());
       
        List<Map<String, Object>> trends =tms.trends(); // No casting needed
        model.addAttribute("tr",trends);

        return "redirect:like";
        //return "trending";  // Replace with the view name of your dashboard
    } else {
        // Login failed, show an error message
        model.addAttribute("errorMessage", "Invalid username or password. Please try again.");
        return "login";  // Stay on the login page
    }
}*/
@GetMapping("/users")
public String getUsers(Model model, Principal principal)
{
	User user=ur.findByUsername(principal.getName()).orElseThrow(()->new UsernameNotFoundException("User not found"));
	model.addAttribute("userId1", user.getId() );
	
	return "CommonLike";
	
	
	
}
@GetMapping("/compare")
public String compare(@RequestParam int userId1, @RequestParam int userId2, Model model) {
    List<String> commonLikes = usr.findlikes(userId1, userId2);
    model.addAttribute("commonMovies", commonLikes);
    return "CommonLike";  // Ensure it returns the same Thymeleaf template
}

}
