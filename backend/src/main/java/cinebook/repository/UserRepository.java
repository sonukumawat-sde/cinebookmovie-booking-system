package com.cinebook.repository;

import com.cinebook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Login ke waqt email se user dhoondhne ke liye
    Optional<User> findByEmail(String email);
}