package com.cinebook.service;

import com.cinebook.model.Booking;
import com.cinebook.model.Show;
import com.cinebook.model.ShowSeat;
import com.cinebook.model.User;
import com.cinebook.repository.BookingRepository;
import com.cinebook.repository.ShowRepository;
import com.cinebook.repository.ShowSeatRepository;
import com.cinebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    /**
     * CORE ENGINE: Secure Booking Creation
     * @Transactional ensures data safety during this complex process.
     */
    @Transactional
    public Booking createBooking(Long userId, Long showId, List<Long> seatIds) {
        
        // 1. Data Verification: User aur Show exist karte hain ya nahi
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Error: Bhai, is ID ka koi User nahi mila!"));
        
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new RuntimeException("Error: Bhai, is ID ka koi Show nahi mila!"));

        // 2. Fetch Selected Seats
        List<ShowSeat> selectedSeats = showSeatRepository.findAllById(seatIds);
        
        if (selectedSeats.size() != seatIds.size()) {
            throw new RuntimeException("Error: Kuch seats database mein exist hi nahi karti!");
        }

        // 3. Security Check & Total Amount Calculation
        double totalAmount = 0.0;
        
        for (ShowSeat seat : selectedSeats) {
            // STRICT CHECK: Kya ye seat LOCKED hai? Aur kya ye usi user ne lock ki hai jo book kar raha hai?
            if (seat.getStatus() != ShowSeat.SeatStatus.LOCKED || seat.getLockedBy() == null || !seat.getLockedBy().getId().equals(userId)) {
                throw new RuntimeException("Error: Seat " + seat.getSeatNumber() + " aapke naam par locked nahi hai. Kripya dobara select karein.");
            }
            
            // Backend khud price calculate karega (Frontend par bharosa nahi)
            totalAmount += seat.getPrice();
        }

        // 4. Ticket (Booking) Generate Karna
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setSeats(selectedSeats); // Ye automatic 'booking_seats' table mein entry kar dega
        booking.setTotalAmount(totalAmount);
        booking.setStatus(Booking.BookingStatus.PENDING); // Payment baaki hai
        booking.setBookingTime(LocalDateTime.now());

        return bookingRepository.save(booking);
    }

    // User ki purani bookings nikalne ka logic ("My Tickets" page ke liye)
    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    // ID se booking dhundhna
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Bhai, ye booking nahi mili!"));
    }
}