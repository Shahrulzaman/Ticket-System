import enums.MovieRating;
import enums.PaymentMethod;
import enums.TicketType;
import exceptions.BookingException;
import exceptions.InvalidInputException;
import exceptions.PaymentException;
import java.util.Scanner;

/**
 * Main class demonstrating the Cinema Ticket Booking System.
 * Features: Multiple seat types, payment processing, booking cancellation, and statistics.
 */
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static CinemaSystem cinema = new CinemaSystem();

    public static void main(String[] args) {
        try {
            initializeSystem();
            displayWelcome();
            runMainMenu();
        } catch (Exception e) {
            System.err.println("Fatal error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void initializeSystem() throws InvalidInputException {
        // Create movies with ratings
        Movie movie1 = new Movie("The Dark Knight", "Action", 152, MovieRating.PG18,
                                "Christopher Nolan", "When the menace known as the Joker wreaks havoc...");
        Movie movie2 = new Movie("Inception", "Sci-Fi", 148, MovieRating.P13,
                                "Christopher Nolan", "A thief who steals corporate secrets through dream-sharing...");
        Movie movie3 = new Movie("Interstellar", "Sci-Fi", 169, MovieRating.P13,
                                "Christopher Nolan", "A team of explorers travel through a wormhole in space...");

        // Create cinema halls with different showtimes
        CinemaHall hall1 = new CinemaHall(1, movie1, "18:00", 5, 8);
        CinemaHall hall2 = new CinemaHall(2, movie2, "20:30", 6, 10);
        CinemaHall hall3 = new CinemaHall(3, movie3, "19:00", 5, 8);

        // Add halls to cinema system
        cinema.addHall(hall1);
        cinema.addHall(hall2);
        cinema.addHall(hall3);
    }

    private static void displayWelcome() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║          WELCOME TO CINEMA TICKET BOOKING SYSTEM           ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        cinema.displayPricing();
    }

    private static void runMainMenu() {

        boolean running = true;

        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    viewShows();
                    break;
                case 2:
                    bookTicket();
                    break;
                case 3:
                    cancelTicket();
                    break;
                case 4:
                    viewAllTickets();
                    break;
                case 5:
                    viewStatistics();
                    break;
                case 6:
                    cinema.displayPricing();
                    break;
                case 0:
                    running = false;
                    displayGoodbye();
                    break;
                default:
                    System.out.println("✗ Invalid choice! Please try again.\n");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                        MAIN MENU                           ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("  1. View Available Shows");
        System.out.println("  2. Book Ticket");
        System.out.println("  3. Cancel Ticket");
        System.out.println("  4. View All Booked Tickets");
        System.out.println("  5. View Statistics");
        System.out.println("  6. View Pricing");
        System.out.println("  0. Exit");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }

    private static void viewShows() {
        cinema.displayAllShows();
        System.out.print("Enter hall number to view seats (or 0 to go back): ");
        int hallChoice = getIntInput();

        if (hallChoice == 0) return;

        try {
            CinemaHall hall = cinema.getHall(hallChoice);
            hall.displaySeats();
        } catch (BookingException e) {
            System.out.println("✗ " + e.getMessage());
        }
    }

    private static void bookTicket() {
        cinema.displayAllShows();

        try {
            // Get hall selection
            System.out.print("Enter hall number: ");
            int hallNumber = getIntInput();
            CinemaHall hall = cinema.getHall(hallNumber);

            // Display seats
            hall.displaySeats();

            // Get customer details
            System.out.print("\nEnter your name: ");
            scanner.nextLine(); // consume newline
            String customerName = scanner.nextLine();

            // Get ticket type
            System.out.println("\nTicket Types:");
            System.out.println("1. Adult (RM" + cinema.getTicketPrice(TicketType.ADULT) + ")");
            System.out.println("2. Child (RM" + cinema.getTicketPrice(TicketType.CHILD) + ")");
            System.out.println("3. Senior (RM" + cinema.getTicketPrice(TicketType.SENIOR) + ")");
            System.out.println("4. Student (RM" + cinema.getTicketPrice(TicketType.STUDENT) + ")");
            System.out.print("Select ticket type (1-4): ");
            int ticketChoice = getIntInput();

            TicketType ticketType;
            switch (ticketChoice) {
                case 1: ticketType = TicketType.ADULT; break;
                case 2: ticketType = TicketType.CHILD; break;
                case 3: ticketType = TicketType.SENIOR; break;
                case 4: ticketType = TicketType.STUDENT; break;
                default:
                    System.out.println("✗ Invalid ticket type!");
                    return;
            }

            // Get seat selection
            System.out.print("\nEnter row number: ");
            int row = getIntInput();
            System.out.print("Enter seat number: ");
            int seatNumber = getIntInput();

            // Get payment method
            System.out.println("\nPayment Methods:");
            System.out.println("1. Cash");
            System.out.println("2. Credit Card");
            System.out.println("3. Debit Card");
            System.out.println("4. E-Wallet");
            System.out.print("Select payment method (1-4): ");
            int paymentChoice = getIntInput();

            PaymentMethod paymentMethod;
            switch (paymentChoice) {
                case 1: paymentMethod = PaymentMethod.CASH; break;
                case 2: paymentMethod = PaymentMethod.CREDIT_CARD; break;
                case 3: paymentMethod = PaymentMethod.DEBIT_CARD; break;
                case 4: paymentMethod = PaymentMethod.E_WALLET; break;
                default:
                    System.out.println("✗ Invalid payment method!");
                    return;
            }

            // Book the ticket
            Ticket ticket = cinema.bookTicket(hallNumber, row, seatNumber, customerName,
                                            ticketType, paymentMethod);
            ticket.printTicket();

        } catch (BookingException | InvalidInputException | PaymentException e) {
            System.out.println("✗ Booking failed: " + e.getMessage());
        }
    }

    private static void cancelTicket() {
        System.out.print("\nEnter ticket ID to cancel: ");
        int ticketId = getIntInput();

        try {
            cinema.cancelTicket(ticketId);
        } catch (BookingException e) {
            System.out.println("✗ Cancellation failed: " + e.getMessage());
        }
    }

    private static void viewAllTickets() {
        cinema.displayTickets();
    }

    private static void viewStatistics() {
        cinema.displayStatistics();
    }

    private static void displayGoodbye() {
        cinema.displayStatistics();
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║       Thank you for using Cinema Booking System!          ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
    }

    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input! Please enter a number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}