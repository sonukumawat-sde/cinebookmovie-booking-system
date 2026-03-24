package com.cinebook.model;

import jakarta.persistence.*;

@Entity
@Table(name = "screens")
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Example: "Screen 1" or "Audi A"

    @Column(nullable = false)
    private Integer totalSeats; // Is screen ki total capacity kitni hai

    // Asli Jaadu: Relationship Mapping
    // Many Screens belong to One Theatre
    // Ye 'screens' table mein ek 'theatre_id' naam ka Foreign Key column bana dega
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theatre_id", nullable = false)
    private Theatre theatre;

    // Default Constructor (Required by JPA)
    public Screen() {
    }

    // Constructor with parameters
    public Screen(String name, Integer totalSeats, Theatre theatre) {
        this.name = name;
        this.totalSeats = totalSeats;
        this.theatre = theatre;
    }

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }
}