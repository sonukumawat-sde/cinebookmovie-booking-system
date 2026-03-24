package com.cinebook.repository;

import com.cinebook.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    
    // Yahan hume filhal koi extra code likhne ki zaroorat nahi hai.
    // Spring Boot apne aap samajh jayega ki Movie ko save, update, delete aur find kaise karna hai!

}