package com.cinebook.service;

import com.cinebook.model.Screen;
import com.cinebook.model.Show;
import com.cinebook.model.ShowSeat;
import com.cinebook.repository.ScreenRepository;
import com.cinebook.repository.ShowRepository;
import com.cinebook.repository.ShowSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private ScreenRepository screenRepository;

    /**
     * Naya show add karne ke saath-saath us show ki saari seats 
     * automatically generate karne ka logic.
     * @Transactional ensures ki agar seats save nahi hui toh show bhi rollback ho jaye.
     */
    @Transactional
    public Show addShow(Show show) {
        // 1. Validate Screen: Pehle check karo ki screen database mein exist karti hai
        Screen screen = screenRepository.findById(show.getScreen().getId())
                .orElseThrow(() -> new RuntimeException("Error: Bhai, is ID (" + show.getScreen().getId() + ") ki koi screen nahi mili!"));

        // 2. Set complete screen object to show and save show
        show.setScreen(screen);
        Show savedShow = showRepository.save(show);

        // 3. Automated Seat Generation Logic
        int totalSeats = screen.getTotalSeats();
        List<ShowSeat> seats = new ArrayList<>();

        // Logic: 1 row mein 10 seats (A1-A10, B1-B10, etc.)
        for (int i = 0; i < totalSeats; i++) {
            char rowChar = (char) ('A' + (i / 10)); // Har 10 seat ke baad alphabetical row change
            int seatNumberInRow = (i % 10) + 1;
            String seatName = String.valueOf(rowChar) + seatNumberInRow;

            // Nayi seat ka object banana aapke ShowSeat model ke mutabiq
            ShowSeat seat = new ShowSeat();
            seat.setSeatNumber(seatName);
            seat.setSeatType("GOLD"); // Default category, can be customized later
            seat.setPrice(savedShow.getBasePrice()); // Initial price taken from show's base price
            seat.setStatus(ShowSeat.SeatStatus.AVAILABLE); // Sabse pehle seats available rahengi
            seat.setShow(savedShow); // Foreign key mapping with saved show

            seats.add(seat);
        }

        // 4. Bulk Save: Saari seats ko ek single batch mein save karna performance ke liye accha hai
        showSeatRepository.saveAll(seats);

        return savedShow;
    }

    /**
     * Kisi specific movie ke liye chal rahe saare shows fetch karna.
     */
    public List<Show> getShowsByMovieId(Long movieId) {
        return showRepository.findByMovieId(movieId);
    }

    /**
     * ID ke basis par show ki detail nikalna.
     */
    public Show getShowById(Long id) {
        return showRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Bhai, is ID ka koi show nahi mila!"));
    }
}