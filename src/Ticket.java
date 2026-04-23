public class Ticket {
    private static int ticketCounter = 1000;
    private int ticketId;
    private CinemaHall hall;
    private Seat seat;
    private String customerName;
    private double price;
    private String ticketType; // "Adult" or "Child"

    public Ticket(CinemaHall hall, Seat seat, String customerName, double price, String ticketType) {
        this.ticketId = ++ticketCounter;
        this.hall = hall;
        this.seat = seat;
        this.customerName = customerName;
        this.price = price;
        this.ticketType = ticketType;
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

    public void printTicket() {
        System.out.println("\n========== TICKET ==========");
        System.out.println("Ticket ID: " + ticketId);
        System.out.println("Customer: " + customerName);
        System.out.println("Ticket Type: " + ticketType);
        System.out.println("Movie: " + hall.getMovie().getTitle());
        System.out.println("Hall: " + hall.getHallNumber());
        System.out.println("Showtime: " + hall.getShowtime());
        System.out.println("Seat: Row " + seat.getRow() + ", Seat " + seat.getNumber());
        System.out.println("Price: RM" + price);
        System.out.println("============================\n");
    }
}
