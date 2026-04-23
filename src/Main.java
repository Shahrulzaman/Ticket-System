import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create cinema system with adult and child pricing
        CinemaSystem cinema = new CinemaSystem(25.50, 10.00);

        // Create movies
        Movie movie1 = new Movie("The Dark Knight", "Action", 152);
        Movie movie2 = new Movie("Inception", "Sci-Fi", 148);
        Movie movie3 = new Movie("Interstellar", "Sci-Fi", 169);

        // Create cinema halls with different showtimes
        CinemaHall hall1 = new CinemaHall(1, movie1, "18:00", 5, 8);
        CinemaHall hall2 = new CinemaHall(2, movie2, "20:30", 6, 10);
        CinemaHall hall3 = new CinemaHall(3, movie3, "19:00", 5, 8);

        // Add halls to cinema system
        cinema.addHall(hall1);
        cinema.addHall(hall2);
        cinema.addHall(hall3);

        System.out.println("====================================");
        System.out.println("  WELCOME TO CINEMA BOOKING SYSTEM  ");
        System.out.println("====================================");
        System.out.println("Adult Ticket: RM" + cinema.getAdultPrice());
        System.out.println("Child Ticket: RM" + cinema.getChildPrice());

        boolean continueBooking = true;

        while (continueBooking) {
            // Display all available shows
            cinema.displayAllShows();

            // Ask user to select a hall
            System.out.print("Enter hall number to view seats (or 0 to exit): ");
            int hallChoice = scanner.nextInt();

            if (hallChoice == 0) {
                continueBooking = false;
                continue;
            }

            CinemaHall selectedHall = cinema.getHall(hallChoice);
            if (selectedHall == null) {
                System.out.println("Invalid hall number! Please try again.\n");
                continue;
            }

            // Display seats for selected hall
            selectedHall.displaySeats();

            // Ask if user wants to book
            System.out.print("\nDo you want to book a ticket? (yes/no): ");
            String bookChoice = scanner.next();

            if (!bookChoice.equalsIgnoreCase("yes")) {
                continue;
            }

            // Get customer name
            scanner.nextLine(); // consume newline
            System.out.print("Enter your name: ");
            String customerName = scanner.nextLine();

            // Get ticket type
            System.out.print("Ticket type (Adult/Child): ");
            String ticketType = scanner.next();

            while (!ticketType.equalsIgnoreCase("Adult") && !ticketType.equalsIgnoreCase("Child")) {
                System.out.print("Invalid type! Enter Adult or Child: ");
                ticketType = scanner.next();
            }

            // Get seat selection
            System.out.print("Enter row number: ");
            int row = scanner.nextInt();

            System.out.print("Enter seat number: ");
            int seatNumber = scanner.nextInt();

            // Book the ticket
            Ticket ticket = cinema.bookTicket(hallChoice, row, seatNumber, customerName, ticketType);
            if (ticket != null) {
                ticket.printTicket();
            }

            // Ask if user wants to continue
            System.out.print("\nBook another ticket? (yes/no): ");
            String continueChoice = scanner.next();
            continueBooking = continueChoice.equalsIgnoreCase("yes");
        }

        // Display all booked tickets before exit
        System.out.println("\n====================================");
        cinema.displayTickets();
        System.out.println("Thank you for using our cinema booking system!");
        System.out.println("====================================");

        scanner.close();
    }
}