package com.cinebook.controller;

import com.cinebook.model.Show;
import com.cinebook.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
@CrossOrigin(origins = "*")
public class ShowController {

    @Autowired
    private ShowService showService;

    // API 1: Naya show (timing) add karne ke liye (Admin use karega)
    @PostMapping("/add")
    public ResponseEntity<?> addShow(@RequestBody Show show) {
        try {
            Show savedShow = showService.addShow(show);
            return ResponseEntity.ok(savedShow);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Show add nahi ho paya: " + e.getMessage());
        }
    }

    // API 2: Kisi ek movie ke saare shows nikalne ke liye
    // URL: GET http://localhost:8080/api/shows/movie/1
    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<Show>> getShowsByMovieId(@PathVariable Long movieId) {
        return ResponseEntity.ok(showService.getShowsByMovieId(movieId));
    }

    // API 3: Ek specific show ki details lene ke liye
    @GetMapping("/{id}")
    public ResponseEntity<?> getShowById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(showService.getShowById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}