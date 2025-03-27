package com.spring_1.Entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface User_LikeRepo extends JpaRepository<User_like,Integer> {
	


	@Query("SELECT u.movie_id FROM User_like u " +
		       "WHERE u.user.id = :userId1 AND u.liked = true " +
		       "AND u.movie_id IN (SELECT u2.movie_id FROM User_like u2 " +
		       "WHERE u2.user.id = :userId2 AND u2.liked = true " +
		       "GROUP BY u2.movie_id) " +
		       "GROUP BY u.movie_id")
		List<String> findLiked(@Param("userId1") int userId1, @Param("userId2") int userId2);


}
