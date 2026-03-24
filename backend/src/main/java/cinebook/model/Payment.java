package com.cinebook.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Jis ticket (booking) ke liye payment ho rahi hai
    private Long bookingId;

    // Razorpay ya bank se aane wali unique transaction ID
    private String transactionId;

    // Kitne paise pay kiye
    private Double amount;

    // Kis waqt payment hui
    private LocalDateTime paymentDate;

    // Payment ka status kya hai (String mein save hoga database mein)
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    // Status ke 3 options
    public enum PaymentStatus {
        PENDING, SUCCESS, FAILED
    }

    // --- GETTERS & SETTERS (Lombok use nahi kar rahe toh ye zaroori hain) ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}