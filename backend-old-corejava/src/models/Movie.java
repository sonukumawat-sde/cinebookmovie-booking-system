package models;

public class Movie {
    // 1. Private Variables (Encapsulation - data safe rakhne ke liye)
    private int movieId;
    private String title;
    private String language;
    private double durationInHours;
    private double ticketPrice;

    // 2. Constructor (Jab nayi movie add karenge, tab ye function chalega)
    public Movie(int movieId, String title, String language, double durationInHours, double ticketPrice) {
        this.movieId = movieId;
        this.title = title;
        this.language = language;
        this.durationInHours = durationInHours;
        this.ticketPrice = ticketPrice;
    }

    // 3. Getters (Kyunki variables private hain, toh unhe read karne ke liye function chahiye)
    public int getMovieId() { return movieId; }
    public String getTitle() { return title; }
    public String getLanguage() { return language; }
    public double getDurationInHours() { return durationInHours; }
    public double getTicketPrice() { return ticketPrice; }

    // 4. toString Method (Taki jab hum movie ko screen par print karein, toh kachra na dikhe, proper text dikhe)
    @Override
    public String toString() {
        return movieId + ". " + title + " (" + language + ") - " + durationInHours + " hrs | Price: ₹" + ticketPrice;
    }
}