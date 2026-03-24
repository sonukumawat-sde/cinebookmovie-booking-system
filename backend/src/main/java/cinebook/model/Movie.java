package com.cinebook.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 2000, nullable = false)
    private String description; // Lamba description save karne ke liye length badha di hai

    @Column(nullable = false)
    private Integer durationMinutes; // Example: 165 (for 2h 45m)

    @Column(nullable = false)
    private String language; // Example: "Hindi, Telugu, Tamil"

    @Column(nullable = false)
    private String genre; // Example: "Action, Thriller"

    @Column(nullable = false)
    private String certification; // Example: "UA", "A"

    private Double rating; // Example: 9.5

    @Column(name = "release_date")
    private LocalDate releaseDate;

    // Frontend ke UI ke liye image URLs
    private String posterUrl; 
    private String bannerUrl; 
    private String trailerUrl;

    // Default Constructor (Required by JPA)
    public Movie() {
    }

    // Constructor with parameters
    public Movie(String title, String description, Integer durationMinutes, String language, String genre, String certification, Double rating, LocalDate releaseDate, String posterUrl, String bannerUrl, String trailerUrl) {
        this.title = title;
        this.description = description;
        this.durationMinutes = durationMinutes;
        this.language = language;
        this.genre = genre;
        this.certification = certification;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.posterUrl = posterUrl;
        this.bannerUrl = bannerUrl;
        this.trailerUrl = trailerUrl;
    }

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }
}