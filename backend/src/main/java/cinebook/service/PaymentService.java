package com.cinebook.service;

import com.cinebook.model.Booking;
import com.cinebook.model.Payment;
import com.cinebook.model.ShowSeat;
import com.cinebook.repository.BookingRepository;
import com.cinebook.repository.PaymentRepository;
import com.cinebook.repository.ShowSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    /**
     * CORE LOGIC: Payment process karna aur Ticket & Seats ka status change karna.
     * @Transactional guarantees ki agar payment save hui, toh seats bhi pakka update hongi.
     */
    @Transactional
    public Payment processPayment(Long bookingId, String transactionId, boolean isSuccess) {
        
        // 1. Database se Pending Booking (Ticket) nikalna
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Error: Bhai, aisi koi booking (Ticket) nahi mili!"));

        // Check karna ki payment pehle hi toh nahi ho chuki
        if (booking.getStatus() != Booking.BookingStatus.PENDING) {
            throw new RuntimeException("Error: Is ticket ki payment pehle hi process ho chuki hai. Status: " + booking.getStatus());
        }

        // 2. Naya Payment Record banana
        Payment payment = new Payment();
        payment.setBookingId(bookingId);
        // Agar bank se transactionId nahi aayi, toh ek mock (fake) ID bana lo (testing ke liye)
        payment.setTransactionId(transactionId != null ? transactionId : "TXN-" + UUID.randomUUID().toString().substring(0, 10).toUpperCase());
        payment.setAmount(booking.getTotalAmount()); // Ticket ka total amount
        payment.setPaymentDate(LocalDateTime.now());

        // 3. SUCCESS ya FAILURE ka Asli Logic
        if (isSuccess) {
            payment.setStatus(Payment.PaymentStatus.SUCCESS);
            booking.setStatus(Booking.BookingStatus.CONFIRMED); // Ticket Confirm ho gayi

            // Saari seats ko LOCKED se hamesha ke liye BOOKED kar do
            List<ShowSeat> seats = booking.getSeats();
            for (ShowSeat seat : seats) {
                seat.setStatus(ShowSeat.SeatStatus.BOOKED);
            }
            showSeatRepository.saveAll(seats); // Seats save kar di
            
        } else {
            payment.setStatus(Payment.PaymentStatus.FAILED);
            booking.setStatus(Booking.BookingStatus.CANCELLED); // Ticket Cancel ho gayi

            // Seats ko wapas AVAILABLE kar do taaki dusre users book kar sakein
            List<ShowSeat> seats = booking.getSeats();
            for (ShowSeat seat : seats) {
                seat.setStatus(ShowSeat.SeatStatus.AVAILABLE);
                seat.setLockedBy(null); // User ka naam hata do
                seat.setLockedAt(null); // Lock time hata do
            }
            showSeatRepository.saveAll(seats); // Seats save kar di
        }

        // 4. Final Updates Database mein save karna
        bookingRepository.save(booking);
        return paymentRepository.save(payment);
    }
}