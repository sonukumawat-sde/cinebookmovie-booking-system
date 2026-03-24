package services;

import models.Movie;
import models.Ticket;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookingService {
    // 1. Data Structures (Using ArrayList - Core DSA)
    private List<Movie> movieList;
    private List<Ticket> bookedTickets;

    // 2. Constructor: Movies data initialize karne ke liye
    public BookingService() {
        movieList = new ArrayList<>();
        bookedTickets = new ArrayList<>();

        // Industry standard: Adding default movies
        movieList.add(new Movie(1, "Kalki 2898 AD", "Hindi", 3.0, 250.0));
        movieList.add(new Movie(2, "Oppenheimer", "English", 3.0, 400.0));
        movieList.add(new Movie(3, "Pushpa 2", "Telugu", 2.5, 200.0));
        movieList.add(new Movie(4, "Spider-Man", "English", 2.2, 300.0));
    }

    // 3. Method: Movies ki list print karne ke liye
    public void displayMovies() {
        System.out.println("\n=================================");
        System.out.println("      🎬 AVAILABLE MOVIES 🎬      ");
        System.out.println("=================================");
        for (Movie movie : movieList) {
            System.out.println(movie.toString());
        }
        System.out.println("=================================\n");
    }

    // 4. Method: Ticket Book karne ka Main Logic
    public void bookTicket(Scanner scanner) {
        System.out.println("\n--- 🎟️ BOOK YOUR TICKET ---");
        displayMovies(); 

        System.out.print("Enter the Movie ID you want to book (1-4): ");
        int movieId = scanner.nextInt();
        scanner.nextLine(); // Buffer clear (Very important for String inputs)

        // Movie find karna list mein se
        Movie selectedMovie = null;
        for (Movie movie : movieList) {
            if (movie.getMovieId() == movieId) {
                selectedMovie = movie;
                break;
            }
        }

        // Error Handling: Agar galat ID dali toh
        if (selectedMovie == null) {
            System.out.println("❌ Invalid Movie ID! Booking failed.\n");
            return;
        }

        // User Details lena
        System.out.print("Enter your Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Seat Number (e.g., A1, B5): ");
        String seatNumber = scanner.nextLine();

        // Unique Ticket ID Generate karna
        String ticketId = "TKT" + (bookedTickets.size() + 101); 
        
        // Ticket Object Create karna
        Ticket newTicket = new Ticket(ticketId, name, selectedMovie, seatNumber, selectedMovie.getTicketPrice());
        
        // Step 1: Memory (ArrayList) mein save karna
        bookedTickets.add(newTicket); 

        // Step 2: Permanent File (bookings.txt) mein save karna
        saveTicketToFile(newTicket);

        System.out.println("\n✅ BOOKING SUCCESSFUL! Here is your ticket:\n");
        System.out.println(newTicket.toString()); 
    }

    // 5. Method: File Handling (Private for Security/Encapsulation)
    private void saveTicketToFile(Ticket ticket) {
        // Path fix: "bookings.txt" direct project folder mein banegi
        try (FileWriter fw = new FileWriter("bookings.txt", true);
             PrintWriter pw = new PrintWriter(fw)) {
            
            pw.println(ticket.toString());
            pw.println("------------------------------------"); 
            
            System.out.println("💾 Data secured! Ticket saved in 'bookings.txt'");
            
        } catch (IOException e) {
            System.out.println("❌ System Error: Could not save ticket to file.");
        }
    }
}