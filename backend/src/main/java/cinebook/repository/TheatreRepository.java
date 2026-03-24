package com.cinebook.repository;

import com.cinebook.model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long> {
    // Basic operations (save, delete, findById) Spring Boot khud handle kar lega
}