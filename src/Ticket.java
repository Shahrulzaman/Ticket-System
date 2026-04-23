import enums.TicketType;
import exceptions.InvalidInputException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a ticket for a movie screening with customer details and payment information.
 */
public class Ticket {
    private static int ticketCounter = 1000;
    private int ticketId;
    private CinemaHall hall;
    private Seat seat;
    private String customerName;
    private double price;
    private TicketType ticketType;
    private Payment payment;
    private LocalDateTime bookingDateTime;
    private boolean isCancelled;

    public Ticket(CinemaHall hall, Seat seat, String customerName, double basePrice, TicketType ticketType, Payment payment) throws InvalidInputException {
        if (hall == null) {
            throw new InvalidInputException("Cinema hall cannot be null");
        }
        if (seat == null) {
            throw new InvalidInputException("Seat cannot be null");
        }
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new InvalidInputException("Customer name cannot be empty");
        }
        if (basePrice <= 0) {
            throw new InvalidInputException("Price must be positive");
        }
        if (ticketType == null) {
            throw new InvalidInputException("Ticket type cannot be null");
        }
        if (payment == null) {
            throw new InvalidInputException("Payment cannot be null");
        }

        this.ticketId = ++ticketCounter;
        this.hall = hall;
        this.seat = seat;
        this.customerName = customerName;
        this.ticketType = ticketType;
        this.payment = payment;
        this.bookingDateTime = LocalDateTime.now();
        this.isCancelled = false;

        // Calculate final price based on seat type
        this.price = basePrice * seat.getSeatType().getPriceMultiplier();
    }

    public int getTicketId() {
        return ticketId;
    }

    public CinemaHall getHall() {
        return hall;
    }

    public Seat getSeat() {
        return seat;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getPrice() {
        return price;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public Payment getPayment() {
        return payment;
    }

    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void cancel() {
        this.isCancelled = true;
    }

    public void printTicket() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                      CINEMA TICKET                         ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("  Ticket ID      : #" + ticketId);
        System.out.println("  Customer       : " + customerName);
        System.out.println("  Ticket Type    : " + ticketType.getDisplayName());
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("  Movie          : " + hall.getMovie().getTitle());
        System.out.println("  Rating         : " + hall.getMovie().getRating().getCode());
        System.out.println("  Genre          : " + hall.getMovie().getGenre());
        System.out.println("  Duration       : " + hall.getMovie().getDurationMinutes() + " mins");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("  Hall           : " + hall.getHallNumber());
        System.out.println("  Showtime       : " + hall.getShowtime());
        System.out.println("  Seat           : Row " + seat.getRow() + ", Seat " + seat.getNumber());
        System.out.println("  Seat Type      : " + seat.getSeatType().getDisplayName());
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("  Price          : RM " + String.format("%.2f", price));
        System.out.println("  Payment Method : " + payment.getPaymentMethod().getDisplayName());
        System.out.println("  Transaction ID : " + payment.getTransactionId());
        System.out.println("  Booking Time   : " + bookingDateTime.format(formatter));
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        if (isCancelled) {
            System.out.println("               *** TICKET CANCELLED ***");
        }
        System.out.println();
    }
}
