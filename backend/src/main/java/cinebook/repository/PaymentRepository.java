package com.cinebook.repository;

import com.cinebook.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // Kisi specific booking ID ki payment details nikalne ke liye
    Optional<Payment> findByBookingId(Long bookingId);

    // Razorpay/Stripe se aane wali Transaction ID se payment dhundhne ke liye (Refund mein kaam aayega)
    Optional<Payment> findByTransactionId(String transactionId);
}