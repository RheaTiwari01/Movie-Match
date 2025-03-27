package com.spring_1.Entity;

import org.springframework.stereotype.*;

import jakarta.persistence.*;

@Entity
public class TrendsRepo {
@Id  
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
private String movieId;
private String title;
public TrendsRepo(String movieId, String title) {
	this.movieId = movieId;
	this.title = title;
}
public TrendsRepo() {
	
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getMovieId() {
	return movieId;
}
public void setMovieId(String movieId) {
	this.movieId = movieId;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}





}
