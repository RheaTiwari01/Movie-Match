package com.spring_1.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;


@Entity
@Table(name="user_like")
public class User_like {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;

@ManyToOne
@JoinColumn(name = "user_id", nullable = false) // Foreign key to "user" table
private User user;

@Column(length = 200, nullable = false) // VARCHAR(200) equivalent, NOT NULL constraint
private String movie_id;

@Column(nullable = false) // NOT NULL constraint
private boolean liked;

public User_like() {
	
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}

public String getMovie_id() {
	return movie_id;
}

public void setMovie_id(String movie_id) {
	this.movie_id = movie_id;
}

public boolean isLiked() {
	return liked;
}

public void setLiked(boolean liked) {
	this.liked = liked;
}

public User_like(User user, String movie_id, boolean liked) {
	
	this.user = user;
	this.movie_id = movie_id;
	this.liked = liked;
}


}
