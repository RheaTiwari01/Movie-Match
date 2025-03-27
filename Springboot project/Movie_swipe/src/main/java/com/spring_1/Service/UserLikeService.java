package com.spring_1.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring_1.Entity.User;
import com.spring_1.Entity.UserRepo;
import com.spring_1.Entity.User_LikeRepo;
import com.spring_1.Entity.User_like;

@Service 
public class UserLikeService {
@Autowired
private User_LikeRepo ulr;
@Autowired
		public  UserRepo userRepository;

		@Autowired User_LikeRepo userLikeRepository;
public void saveUserLike(User_like us) {
	ulr.save(us);
	
}
public Optional<User_like> getUserLikes(int userId)
{
	return ulr.findById(userId);
	
}
public List<String> findlikes(int userId1, int userId2)
{
	return ulr.findLiked(userId1, userId2);
	
}
public void saveUserLike(int userId, String movieId, boolean liked) {
    // Fetch user by ID
    User user = userRepository.findById(userId)
                              .orElseThrow(() -> new RuntimeException("User not found"));

    // Create a User_like object
    User_like userLike = new User_like(user, movieId, liked);

    // Save the User_like object
    userLikeRepository.save(userLike);
}
}
