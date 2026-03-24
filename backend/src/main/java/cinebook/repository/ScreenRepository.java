package com.cinebook.repository;

import com.cinebook.model.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long> {
    
    // Custom Magic Method: Ek specific theatre ki saari screens nikalne ke liye
    // Query: SELECT * FROM screens WHERE theatre_id = ?
    List<Screen> findByTheatreId(Long theatreId);
    
}