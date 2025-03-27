package com.spring_1.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity

@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(nullable=false , unique=true)
	private String username;
	@Column(nullable = false)
	private String password;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User(String username, String password,String name) {
		super();
		this.username = username;
		this.password = password;
		this.name=name;
	}
	public User() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
