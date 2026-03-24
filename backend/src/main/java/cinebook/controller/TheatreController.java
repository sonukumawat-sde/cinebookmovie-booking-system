package com.cinebook.controller;

import com.cinebook.model.Theatre;
import com.cinebook.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theatres")
@CrossOrigin(origins = "*")
public class TheatreController {

    @Autowired
    private TheatreService theatreService;

    // API 1: Naya theatre add karne ke liye (Admin use karega)
    @PostMapping("/add")
    public ResponseEntity<?> addTheatre(@RequestBody Theatre theatre) {
        try {
            Theatre savedTheatre = theatreService.addTheatre(theatre);
            return ResponseEntity.ok(savedTheatre);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Theatre add nahi ho paya: " + e.getMessage());
        }
    }

    // API 2: Saare theatres ki list lene ke liye
    @GetMapping("/all")
    public ResponseEntity<List<Theatre>> getAllTheatres() {
        return ResponseEntity.ok(theatreService.getAllTheatres());
    }

    // API 3: Ek specific theatre ki details lene ke liye
    @GetMapping("/{id}")
    public ResponseEntity<?> getTheatreById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(theatreService.getTheatreById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}