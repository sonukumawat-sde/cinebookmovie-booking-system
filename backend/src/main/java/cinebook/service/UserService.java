package com.cinebook.service;

import com.cinebook.model.User;
import com.cinebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 1. Naya User Register karna
    public User registerUser(User user) {
        // Check karo ki email pehle se toh nahi hai
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Error: Bhai, ye Email pehle se registered hai!");
        }
        
        // Default role set karna
        if (user.getRole() == null) {
            user.setRole("ROLE_USER");
        }
        
        return userRepository.save(user);
    }

    // 2. Login karna (Simple Password Match)
    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Error: User nahi mila!"));

        // Direct password match (College project ke liye fast aur best)
        if (user.getPassword().equals(password)) {
            return user;
        } else {
            throw new RuntimeException("Error: Password galat hai bhai!");
        }
    }
}