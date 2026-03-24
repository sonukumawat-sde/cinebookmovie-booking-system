package com.cinebook.controller;

import com.cinebook.model.Payment;
import com.cinebook.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    /**
     * API: Payment process karne ke liye aur Ticket ko CONFIRM karne ke liye.
     * URL: POST http://localhost:8080/api/payments/process
     */
    @PostMapping("/process")
    public ResponseEntity<?> processPayment(@RequestBody PaymentRequest request) {
        try {
            Payment payment = paymentService.processPayment(
                    request.getBookingId(),
                    request.getTransactionId(),
                    request.isSuccess()
            );
            return ResponseEntity.ok(payment); // 200 OK agar payment record save ho gaya
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Payment process fail ho gayi: " + e.getMessage());
        }
    }

    /**
     * DTO (Data Transfer Object) - Security aur clean data aane ke liye.
     * Frontend se sirf Booking ID, Transaction ID, aur Success (true/false) aayega.
     */
    public static class PaymentRequest {
        private Long bookingId;
        private String transactionId;
        private boolean success; // true for SUCCESS, false for FAILED

        public Long getBookingId() {
            return bookingId;
        }

        public void setBookingId(Long bookingId) {
            this.bookingId = bookingId;
        }

        public String getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(String transactionId) {
            this.transactionId = transactionId;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }
    }
}