package com.cinebook.service;

import com.cinebook.model.Movie;
import com.cinebook.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    // Nayi movie add karne ka logic (Admin ke liye)
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    // Saari movies nikalne ka logic (Homepage par dikhane ke liye)
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    // ID se ek specific movie nikalne ka logic (Movie details page ke liye)
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bhai, is ID ki koi movie nahi mili!"));
    }
}