import java.util.ArrayList;
import java.util.List;

public class CinemaSystem {
    private List<CinemaHall> halls;
    private List<Ticket> tickets;
    private double adultPrice;
    private double childPrice;

    public CinemaSystem(double adultPrice, double childPrice) {
        this.halls = new ArrayList<>();
        this.tickets = new ArrayList<>();
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
    }

    public void addHall(CinemaHall hall) {
        halls.add(hall);
    }

    public void displayAllShows() {
        System.out.println("\n===== AVAILABLE SHOWS =====");
        for (CinemaHall hall : halls) {
            System.out.println(hall);
        }
        System.out.println("===========================\n");
    }

    public CinemaHall getHall(int hallNumber) {
        for (CinemaHall hall : halls) {
            if (hall.getHallNumber() == hallNumber) {
                return hall;
            }
        }
        return null;
    }

    public Ticket bookTicket(int hallNumber, int row, int seatNumber, String customerName, String ticketType) {
        CinemaHall hall = getHall(hallNumber);

        if (hall == null) {
            System.out.println("Hall not found!");
            return null;
        }

        double price = ticketType.equalsIgnoreCase("Adult") ? adultPrice : childPrice;

        if (hall.bookSeat(row, seatNumber)) {
            Seat seat = hall.getSeat(row, seatNumber);
            Ticket ticket = new Ticket(hall, seat, customerName, price, ticketType);
            tickets.add(ticket);
            System.out.println("Booking successful!");
            return ticket;
        } else {
            System.out.println("Booking failed! Seat is already booked or doesn't exist.");
            return null;
        }
    }

    public double getAdultPrice() {
        return adultPrice;
    }

    public double getChildPrice() {
        return childPrice;
    }

    public List<CinemaHall> getHalls() {
        return halls;
    }

    public void displayTickets() {
        if (tickets.isEmpty()) {
            System.out.println("No tickets booked yet.");
            return;
        }
        System.out.println("\n===== ALL BOOKED TICKETS =====");
        for (Ticket ticket : tickets) {
            ticket.printTicket();
        }
    }
}
