package com.spring_1.Entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TrendingMovieRepo extends JpaRepository<TrendsRepo,Integer> {
	 void deleteAll(); // Clear old trending movies
}
