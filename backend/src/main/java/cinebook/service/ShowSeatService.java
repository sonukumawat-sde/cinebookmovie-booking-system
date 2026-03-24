package com.cinebook.service;

import com.cinebook.model.ShowSeat;
import com.cinebook.model.User;
import com.cinebook.repository.ShowSeatRepository;
import com.cinebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShowSeatService {

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Kisi specific show ki saari seats fetch karne ka logic.
     */
    public List<ShowSeat> getSeatsByShowId(Long showId) {
        return showSeatRepository.findByShowId(showId);
    }

    /**
     * Seat ka status manually update karne ka logic.
     */
    @Transactional
    public ShowSeat updateSeatStatus(Long seatId, ShowSeat.SeatStatus newStatus) {
        ShowSeat seat = showSeatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Error: Bhai, is ID (" + seatId + ") ki koi seat nahi mili!"));
        
        seat.setStatus(newStatus);
        return showSeatRepository.save(seat);
    }

    /**
     * CORE BOOKING LOGIC: User dwara multiple seats ko ek saath LOCK karna.
     * @Transactional ensures ki either saari seats lock hongi, ya ek bhi nahi hogi.
     */
    @Transactional
    public List<ShowSeat> lockSeats(List<Long> seatIds, Long userId) {
        
        // 1. Verify User: Pehle check karo ki user database mein hai ya nahi
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Error: Bhai, is ID ka koi User nahi mila! Krpya login karein."));

        // 2. Fetch Seats: Un saari seats ko database se nikalo jo user ne select ki hain
        List<ShowSeat> seatsToLock = showSeatRepository.findAllById(seatIds);

        // Validation 2A: Check karo ki user ne jitni ID bheji, utni seats mili ya nahi
        if (seatsToLock.size() != seatIds.size()) {
            throw new RuntimeException("Error: Invalid Seat IDs! Kuch seats database mein nahi mili.");
        }

        // 3. Status Check Validation: Har ek seat ko check karo ki wo AVAILABLE hai ya nahi
        for (ShowSeat seat : seatsToLock) {
            if (seat.getStatus() != ShowSeat.SeatStatus.AVAILABLE) {
                // Agar seat AVAILABLE nahi hai, toh turant transaction rok do aur error throw karo
                throw new RuntimeException("Error: Seat " + seat.getSeatNumber() + " pehle se hi " + seat.getStatus() + " hai!");
            }
        }

        // 4. Locking Process: Agar saari seats AVAILABLE hain, toh unko lock kar do
        LocalDateTime currentTime = LocalDateTime.now();
        
        for (ShowSeat seat : seatsToLock) {
            seat.setStatus(ShowSeat.SeatStatus.LOCKED);
            seat.setLockedAt(currentTime);
            seat.setLockedBy(user); // Seat ko is specific user ke naam par lock kar diya
        }

        // 5. Database Save: Bulk save for better performance
        return showSeatRepository.saveAll(seatsToLock);
    }
}