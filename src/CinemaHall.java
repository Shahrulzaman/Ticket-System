import enums.SeatType;
import exceptions.InvalidInputException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a cinema hall with seats, movie screening details, and showtime.
 */
public class CinemaHall {
    private int hallNumber;
    private Movie movie;
    private String showtime;
    private List<Seat> seats;
    private int rows;
    private int seatsPerRow;
    private int totalCapacity;

    public CinemaHall(int hallNumber, Movie movie, String showtime, int rows, int seatsPerRow) throws InvalidInputException {
        if (hallNumber <= 0) {
            throw new InvalidInputException("Hall number must be positive");
        }
        if (movie == null) {
            throw new InvalidInputException("Movie cannot be null");
        }
        if (showtime == null || showtime.trim().isEmpty()) {
            throw new InvalidInputException("Showtime cannot be empty");
        }
        if (rows <= 0 || seatsPerRow <= 0) {
            throw new InvalidInputException("Rows and seats per row must be positive");
        }

        this.hallNumber = hallNumber;
        this.movie = movie;
        this.showtime = showtime;
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
        this.seats = new ArrayList<>();
        this.totalCapacity = rows * seatsPerRow;
        initializeSeats();
    }

    private void initializeSeats() {
        try {
            for (int row = 1; row <= rows; row++) {
                SeatType seatType;
                // Last 2 rows are Premium, next 2 are VIP, rest are Regular
                if (row > rows - 2) {
                    seatType = SeatType.PREMIUM;
                } else if (row > rows - 4) {
                    seatType = SeatType.VIP;
                } else {
                    seatType = SeatType.REGULAR;
                }

                for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                    seats.add(new Seat(row, seatNum, seatType));
                }
            }
        } catch (InvalidInputException e) {
            System.err.println("Error initializing seats: " + e.getMessage());
        }
    }

    public int getHallNumber() {
        return hallNumber;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getShowtime() {
        return showtime;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public Seat getSeat(int row, int number) {
        for (Seat seat : seats) {
            if (seat.getRow() == row && seat.getNumber() == number) {
                return seat;
            }
        }
        return null;
    }

    public boolean bookSeat(int row, int number, String customerName) {
        Seat seat = getSeat(row, number);
        if (seat != null && !seat.isBooked()) {
            seat.book(customerName);
            return true;
        }
        return false;
    }

    public boolean cancelSeat(int row, int number) {
        Seat seat = getSeat(row, number);
        if (seat != null && seat.isBooked()) {
            seat.cancel();
            return true;
        }
        return false;
    }

    public int getAvailableSeatsCount() {
        int count = 0;
        for (Seat seat : seats) {
            if (!seat.isBooked()) {
                count++;
            }
        }
        return count;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void displaySeats() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("  Hall " + hallNumber + " - " + movie.getTitle() + " at " + showtime);
        System.out.println("  Available: " + getAvailableSeatsCount() + "/" + totalCapacity);
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println("\n                      [ SCREEN ]");
        System.out.println("  ═══════════════════════════════════════════════════");

        for (int row = 1; row <= rows; row++) {
            Seat firstSeat = getSeat(row, 1);
            String seatTypeLabel = firstSeat != null ? firstSeat.getSeatType().getDisplayName().substring(0, 1) : " ";
            System.out.print("  Row " + String.format("%2d", row) + " [" + seatTypeLabel + "]: ");

            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                Seat seat = getSeat(row, seatNum);
                if (seat.isBooked()) {
                    System.out.print("[X] ");
                } else {
                    System.out.print("[" + String.format("%2d", seatNum) + "]");
                }
            }
            System.out.println();
        }
        System.out.println("\n  Legend: R=Regular | V=VIP | P=Premium | X=Booked");
    }

    @Override
    public String toString() {
        return "Hall " + hallNumber + " - " + movie + " at " + showtime;
    }
}
