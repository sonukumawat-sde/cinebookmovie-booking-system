package com.cinebook.service;

import com.cinebook.model.Screen;
import com.cinebook.repository.ScreenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreenService {

    @Autowired
    private ScreenRepository screenRepository;

    // Nayi screen add karne ka logic (Admin ke liye)
    public Screen addScreen(Screen screen) {
        return screenRepository.save(screen);
    }

    // Kisi ek specific theatre ki saari screens nikalne ka logic
    public List<Screen> getScreensByTheatreId(Long theatreId) {
        // Yahan hum wo custom method use kar rahe hain jo humne ScreenRepository mein banaya tha!
        return screenRepository.findByTheatreId(theatreId);
    }

    // ID se ek specific screen nikalne ka logic
    public Screen getScreenById(Long id) {
        return screenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bhai, is ID ki koi screen nahi mili!"));
    }
}