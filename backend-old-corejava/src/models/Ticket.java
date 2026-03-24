package models;

public class Ticket {
    // 1. Private Variables 
    private String ticketId;
    private String customerName;
    private Movie movie; // Yahan humne purani Movie class ko use kiya hai!
    private String seatNumber;
    private double finalPrice;

    // 2. Constructor
    public Ticket(String ticketId, String customerName, Movie movie, String seatNumber, double finalPrice) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.movie = movie;
        this.seatNumber = seatNumber;
        this.finalPrice = finalPrice;
    }

    // 3. Getters
    public String getTicketId() { return ticketId; }
    public String getCustomerName() { return customerName; }
    public Movie getMovie() { return movie; }
    public String getSeatNumber() { return seatNumber; }
    public double getFinalPrice() { return finalPrice; }

    // 4. toString Method (Ticket print karne ka sundar format)
    @Override
    public String toString() {
        return "====================================\n" +
               "          🎟️ MOVIE TICKET 🎟️          \n" +
               "====================================\n" +
               "Ticket ID : " + ticketId + "\n" +
               "Name      : " + customerName + "\n" +
               "Movie     : " + movie.getTitle() + " (" + movie.getLanguage() + ")\n" +
               "Seat No   : " + seatNumber + "\n" +
               "Amount    : ₹" + finalPrice + "\n" +
               "====================================";
    }
}