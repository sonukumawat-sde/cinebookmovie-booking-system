package com.cinebook.service;

import com.cinebook.model.Theatre;
import com.cinebook.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;

    // Naya theatre add karne ka logic (Admin ke liye)
    public Theatre addTheatre(Theatre theatre) {
        return theatreRepository.save(theatre);
    }

    // Saare theatres nikalne ka logic (App mein list dikhane ke liye)
    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }

    // ID se ek specific theatre nikalne ka logic
    public Theatre getTheatreById(Long id) {
        return theatreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bhai, is ID ka koi theatre nahi mila!"));
    }
}