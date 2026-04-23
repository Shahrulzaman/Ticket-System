import enums.SeatType;
import exceptions.InvalidInputException;

/**
 * Represents a seat in a cinema hall with row, number, type, and booking status.
 */
public class Seat {
    private int row;
    private int number;
    private boolean isBooked;
    private SeatType seatType;
    private String bookedBy;

    public Seat(int row, int number, SeatType seatType) throws InvalidInputException {
        if (row <= 0) {
            throw new InvalidInputException("Row number must be positive");
        }
        if (number <= 0) {
            throw new InvalidInputException("Seat number must be positive");
        }
        if (seatType == null) {
            throw new InvalidInputException("Seat type cannot be null");
        }
        this.row = row;
        this.number = number;
        this.seatType = seatType;
        this.isBooked = false;
        this.bookedBy = null;
    }

    public int getRow() {
        return row;
    }

    public int getNumber() {
        return number;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public String getBookedBy() {
        return bookedBy;
    }

    public void book(String customerName) {
        this.isBooked = true;
        this.bookedBy = customerName;
    }

    public void cancel() {
        this.isBooked = false;
        this.bookedBy = null;
    }

    @Override
    public String toString() {
        return "Row " + row + ", Seat " + number + " [" + seatType.getDisplayName() + "]" +
               (isBooked ? " (BOOKED)" : " (AVAILABLE)");
    }
}
