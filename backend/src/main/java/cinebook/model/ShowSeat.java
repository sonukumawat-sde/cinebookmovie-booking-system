package com.cinebook.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "show_seats")
public class ShowSeat {

    // Ye enum decide karega ki seat ki current halat kya hai
    public enum SeatStatus {
        AVAILABLE,
        LOCKED,
        BOOKED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String seatNumber; // Example: "A1", "D5"

    @Column(nullable = false)
    private String seatType; // Example: "VIP", "GOLD", "SILVER"

    @Column(nullable = false)
    private Double price; // Is specific seat ki final price

    // @Enumerated(EnumType.STRING) batata hai ki database mein ise text ("LOCKED", "BOOKED") ki tarah save karna hai
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatStatus status;

    // Jab koi user seat par click karega, toh yahan time save ho jayega. 10 minute baad timeout hoga.
    private LocalDateTime lockedAt;

    // Kis show ki seat hai?
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    // Kis user ne isko lock kiya hai? (Payment ke time pe kaam aayega)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locked_by_user_id") // Null ho sakta hai jab seat Available ho
    private User lockedBy;

    // Default Constructor (Required by JPA)
    public ShowSeat() {
    }

    // Constructor with parameters
    public ShowSeat(String seatNumber, String seatType, Double price, SeatStatus status, Show show) {
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.price = price;
        this.status = status;
        this.show = show;
    }

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }

    public LocalDateTime getLockedAt() {
        return lockedAt;
    }

    public void setLockedAt(LocalDateTime lockedAt) {
        this.lockedAt = lockedAt;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public User getLockedBy() {
        return lockedBy;
    }

    public void setLockedBy(User lockedBy) {
        this.lockedBy = lockedBy;
    }
}