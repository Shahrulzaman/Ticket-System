import java.util.ArrayList;
import java.util.List;

public class CinemaHall {
    private int hallNumber;
    private Movie movie;
    private String showtime;
    private List<Seat> seats;
    private int rows;
    private int seatsPerRow;

    public CinemaHall(int hallNumber, Movie movie, String showtime, int rows, int seatsPerRow) {
        this.hallNumber = hallNumber;
        this.movie = movie;
        this.showtime = showtime;
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
        this.seats = new ArrayList<>();
        initializeSeats();
    }

    private void initializeSeats() {
        for (int row = 1; row <= rows; row++) {
            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                seats.add(new Seat(row, seatNum));
            }
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

    public boolean bookSeat(int row, int number) {
        Seat seat = getSeat(row, number);
        if (seat != null && !seat.isBooked()) {
            seat.book();
            return true;
        }
        return false;
    }

    public void displaySeats() {
        System.out.println("\nHall " + hallNumber + " - " + movie.getTitle() + " at " + showtime);
        System.out.println("Screen");
        System.out.println("------");
        for (int row = 1; row <= rows; row++) {
            System.out.print("Row " + row + ": ");
            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                Seat seat = getSeat(row, seatNum);
                System.out.print(seat.isBooked() ? "[X] " : "[" + seatNum + "] ");
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return "Hall " + hallNumber + " - " + movie + " at " + showtime;
    }
}
