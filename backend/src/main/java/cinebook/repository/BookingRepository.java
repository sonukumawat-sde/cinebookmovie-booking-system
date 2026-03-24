package com.cinebook.repository;

import com.cinebook.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Ek specific user ki saari bookings nikalne ke liye ("My Tickets" page ke liye)
    // Query: SELECT * FROM bookings WHERE user_id = ?
    List<Booking> findByUserId(Long userId);

    // Ticket check karte waqt, unique PNR / Booking Reference se ticket dhundhne ke liye
    // Query: SELECT * FROM bookings WHERE booking_reference = ?
    Optional<Booking> findByBookingReference(String bookingReference);
}