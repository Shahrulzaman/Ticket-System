import enums.PaymentMethod;
import enums.TicketType;
import exceptions.BookingException;
import exceptions.InvalidInputException;
import exceptions.PaymentException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Main system class that manages cinema halls, tickets, and bookings.
 */
public class CinemaSystem {
    private List<CinemaHall> halls;
    private List<Ticket> tickets;
    private Map<TicketType, Double> ticketPrices;

    public CinemaSystem() {
        this.halls = new ArrayList<>();
        this.tickets = new ArrayList<>();
        this.ticketPrices = new HashMap<>();
        initializeDefaultPrices();
    }

    private void initializeDefaultPrices() {
        ticketPrices.put(TicketType.ADULT, 15.00);
        ticketPrices.put(TicketType.CHILD, 10.00);
        ticketPrices.put(TicketType.SENIOR, 12.00);
        ticketPrices.put(TicketType.STUDENT, 12.00);
    }

    public void addHall(CinemaHall hall) throws InvalidInputException {
        if (hall == null) {
            throw new InvalidInputException("Cinema hall cannot be null");
        }
        // Check for duplicate hall numbers
        for (CinemaHall existingHall : halls) {
            if (existingHall.getHallNumber() == hall.getHallNumber()) {
                throw new InvalidInputException("Hall number " + hall.getHallNumber() + " already exists");
            }
        }
        halls.add(hall);
    }

    public void setTicketPrice(TicketType ticketType, double price) throws InvalidInputException {
        if (price <= 0) {
            throw new InvalidInputException("Price must be positive");
        }
        ticketPrices.put(ticketType, price);
    }

    public double getTicketPrice(TicketType ticketType) {
        return ticketPrices.getOrDefault(ticketType, 15.00);
    }

    public void displayAllShows() {
        if (halls.isEmpty()) {
            System.out.println("\nNo shows available at the moment.");
            return;
        }
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                    AVAILABLE SHOWS                         ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        for (CinemaHall hall : halls) {
            System.out.println("  " + hall);
            System.out.println("  Available Seats: " + hall.getAvailableSeatsCount() + "/" + hall.getTotalCapacity());
            System.out.println("  ──────────────────────────────────────────────────────────");
        }
        System.out.println();
    }

    public CinemaHall getHall(int hallNumber) throws BookingException {
        for (CinemaHall hall : halls) {
            if (hall.getHallNumber() == hallNumber) {
                return hall;
            }
        }
        throw new BookingException("Hall number " + hallNumber + " not found");
    }

    public Ticket bookTicket(int hallNumber, int row, int seatNumber, String customerName,
                           TicketType ticketType, PaymentMethod paymentMethod)
            throws BookingException, InvalidInputException, PaymentException {

        CinemaHall hall = getHall(hallNumber);
        Seat seat = hall.getSeat(row, seatNumber);

        if (seat == null) {
            throw new BookingException("Seat at Row " + row + ", Seat " + seatNumber + " does not exist");
        }

        if (seat.isBooked()) {
            throw new BookingException("Seat is already booked");
        }

        // Calculate price
        double basePrice = getTicketPrice(ticketType);
        double finalPrice = basePrice * seat.getSeatType().getPriceMultiplier();

        // Process payment
        Payment payment = new Payment(finalPrice, paymentMethod);
        if (!payment.processPayment()) {
            throw new PaymentException("Payment processing failed");
        }

        // Book the seat
        if (hall.bookSeat(row, seatNumber, customerName)) {
            Ticket ticket = new Ticket(hall, seat, customerName, basePrice, ticketType, payment);
            tickets.add(ticket);
            System.out.println("\n✓ Booking successful!");
            return ticket;
        } else {
            throw new BookingException("Failed to book seat");
        }
    }

    public boolean cancelTicket(int ticketId) throws BookingException {
        for (Ticket ticket : tickets) {
            if (ticket.getTicketId() == ticketId) {
                if (ticket.isCancelled()) {
                    throw new BookingException("Ticket is already cancelled");
                }

                // Cancel the seat in the hall
                CinemaHall hall = ticket.getHall();
                Seat seat = ticket.getSeat();
                hall.cancelSeat(seat.getRow(), seat.getNumber());

                // Mark ticket as cancelled
                ticket.cancel();
                System.out.println("\n✓ Ticket #" + ticketId + " has been cancelled successfully");
                System.out.println("Refund amount: RM" + String.format("%.2f", ticket.getPrice()));
                return true;
            }
        }
        throw new BookingException("Ticket ID " + ticketId + " not found");
    }

    public Ticket getTicket(int ticketId) throws BookingException {
        for (Ticket ticket : tickets) {
            if (ticket.getTicketId() == ticketId) {
                return ticket;
            }
        }
        throw new BookingException("Ticket ID " + ticketId + " not found");
    }

    public List<CinemaHall> getHalls() {
        return halls;
    }

    public void displayTickets() {
        if (tickets.isEmpty()) {
            System.out.println("\nNo tickets booked yet.");
            return;
        }
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                   ALL BOOKED TICKETS                       ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        int activeTickets = 0;
        for (Ticket ticket : tickets) {
            if (!ticket.isCancelled()) {
                ticket.printTicket();
                activeTickets++;
            }
        }
        if (activeTickets == 0) {
            System.out.println("All tickets have been cancelled.");
        }
    }

    public void displayPricing() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                      TICKET PRICING                        ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        for (Map.Entry<TicketType, Double> entry : ticketPrices.entrySet()) {
            System.out.printf("  %-15s : RM %.2f (Base Price)%n",
                entry.getKey().getDisplayName(), entry.getValue());
        }
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("  Seat Type Multipliers:");
        System.out.println("  Regular  : x1.0");
        System.out.println("  VIP      : x1.5");
        System.out.println("  Premium  : x2.0");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
    }

    public double calculateTotalRevenue() {
        double total = 0;
        for (Ticket ticket : tickets) {
            if (!ticket.isCancelled()) {
                total += ticket.getPrice();
            }
        }
        return total;
    }

    public void displayStatistics() {
        int totalBookings = tickets.size();
        int activeBookings = 0;
        int cancelledBookings = 0;

        for (Ticket ticket : tickets) {
            if (ticket.isCancelled()) {
                cancelledBookings++;
            } else {
                activeBookings++;
            }
        }

        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                   SYSTEM STATISTICS                        ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("  Total Halls        : " + halls.size());
        System.out.println("  Total Bookings     : " + totalBookings);
        System.out.println("  Active Bookings    : " + activeBookings);
        System.out.println("  Cancelled Bookings : " + cancelledBookings);
        System.out.println("  Total Revenue      : RM" + String.format("%.2f", calculateTotalRevenue()));
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
    }
}
