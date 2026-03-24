import services.BookingService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Movie Ticket Booking System...\n");
        
        BookingService bookingService = new BookingService();
        Scanner scanner = new Scanner(System.in); 
        boolean isRunning = true; 

        while (isRunning) {
            System.out.println("====== 🎟️ MAIN MENU 🎟️ ======");
            System.out.println("1. View Available Movies");
            System.out.println("2. Book a Ticket");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1-3): ");
            
            int choice = scanner.nextInt(); 

            switch (choice) {
                case 1:
                    bookingService.displayMovies(); 
                    break;
                case 2:
                    // YAHAN HUMNE APNA NAYA LOGIC JOD DIYA HAI!
                    bookingService.bookTicket(scanner);
                    break;
                case 3:
                    System.out.println("\nThank you for using our system! Goodbye. 👋\n");
                    isRunning = false; 
                    break;
                default:
                    System.out.println("\n❌ Invalid choice! Please select 1, 2, or 3.\n");
            }
        }
        
        scanner.close(); 
    }
}