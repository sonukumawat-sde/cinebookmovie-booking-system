package com.cinebook.controller;

import com.cinebook.model.Screen;
import com.cinebook.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/screens")
@CrossOrigin(origins = "*")
public class ScreenController {

    @Autowired
    private ScreenService screenService;

    // API 1: Nayi screen add karne ke liye
    @PostMapping("/add")
    public ResponseEntity<?> addScreen(@RequestBody Screen screen) {
        try {
            Screen savedScreen = screenService.addScreen(screen);
            return ResponseEntity.ok(savedScreen);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Screen add nahi ho payi: " + e.getMessage());
        }
    }

    // API 2: Kisi theatre ki saari screens nikalne ke liye (Jaise Inox ki saari screens)
    // URL: GET http://localhost:8080/api/screens/theatre/1
    @GetMapping("/theatre/{theatreId}")
    public ResponseEntity<List<Screen>> getScreensByTheatreId(@PathVariable Long theatreId) {
        return ResponseEntity.ok(screenService.getScreensByTheatreId(theatreId));
    }

    // API 3: Ek specific screen ki details lene ke liye
    @GetMapping("/{id}")
    public ResponseEntity<?> getScreenById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(screenService.getScreenById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}