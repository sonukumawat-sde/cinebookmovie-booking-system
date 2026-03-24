package com.cinebook.controller;

import com.cinebook.model.Booking;
import com.cinebook.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // API 1: Nayi ticket (booking) generate karne ke liye (Secure API)
    // URL: POST http://localhost:8080/api/bookings/create
    @PostMapping("/create")
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest request) {
        try {
            // Hum sirf IDs bhej rahe hain, Service baaki sab khud database se nikalegi
            Booking newBooking = bookingService.createBooking(
                    request.getUserId(),
                    request.getShowId(),
                    request.getSeatIds()
            );
            return ResponseEntity.ok(newBooking); // 200 OK ke saath ticket details bhej do
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Booking create nahi ho payi: " + e.getMessage());
        }
    }

    // API 2: Kisi user ki saari bookings dekhne ke liye ("My Tickets" section)
    // URL: GET http://localhost:8080/api/bookings/user/1
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getBookingsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingService.getBookingsByUserId(userId));
    }

    // API 3: Ek specific booking ki details dekhne ke liye
    // URL: GET http://localhost:8080/api/bookings/1
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(bookingService.getBookingById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * DTO (Data Transfer Object) - Security ke liye.
     * Ye class ensure karegi ki frontend se hacker koi galat amount ya status na bhej sake.
     */
    public static class BookingRequest {
        private Long userId;
        private Long showId;
        private List<Long> seatIds;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Long getShowId() {
            return showId;
        }

        public void setShowId(Long showId) {
            this.showId = showId;
        }

        public List<Long> getSeatIds() {
            return seatIds;
        }

        public void setSeatIds(List<Long> seatIds) {
            this.seatIds = seatIds;
        }
    }
}