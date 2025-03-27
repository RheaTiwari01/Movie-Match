package com.spring_1.Entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
Optional<User> findByUsername(String username);
}
