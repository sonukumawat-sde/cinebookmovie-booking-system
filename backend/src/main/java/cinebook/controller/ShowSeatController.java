package com.cinebook.controller;

import com.cinebook.model.ShowSeat;
import com.cinebook.service.ShowSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/show-seats")
@CrossOrigin(origins = "*")
public class ShowSeatController {

    @Autowired
    private ShowSeatService showSeatService;

    /**
     * Kisi specific Show ID ke liye saari seats fetch karna. (Purana logic)
     */
    @GetMapping("/show/{showId}")
    public ResponseEntity<List<ShowSeat>> getSeatsByShowId(@PathVariable Long showId) {
        return ResponseEntity.ok(showSeatService.getSeatsByShowId(showId));
    }

    /**
     * NAYA LOGIC: User dwara seats ko lock karna.
     * URL: POST http://localhost:8080/api/show-seats/lock
     */
    @PostMapping("/lock")
    public ResponseEntity<?> lockSeats(@RequestBody LockSeatRequest request) {
        try {
            // Service ko seat IDs aur User ID bhej rahe hain
            List<ShowSeat> lockedSeats = showSeatService.lockSeats(request.getSeatIds(), request.getUserId());
            return ResponseEntity.ok(lockedSeats);
        } catch (RuntimeException e) {
            // Agar seat pehle se booked/locked hui, toh ye error dega
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Ye ek Data Transfer Object (DTO) hai jo JSON request ko catch karega.
     * Isko ek hi file mein rakhne ke liye static inner class banaya gaya hai.
     */
    public static class LockSeatRequest {
        private List<Long> seatIds;
        private Long userId;

        public List<Long> getSeatIds() {
            return seatIds;
        }

        public void setSeatIds(List<Long> seatIds) {
            this.seatIds = seatIds;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }
    }
}