public class Seat {
    private int row;
    private int number;
    private boolean isBooked;

    public Seat(int row, int number) {
        this.row = row;
        this.number = number;
        this.isBooked = false;
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

    public void book() {
        this.isBooked = true;
    }

    public void cancel() {
        this.isBooked = false;
    }

    @Override
    public String toString() {
        return "Row " + row + ", Seat " + number + (isBooked ? " (BOOKED)" : " (AVAILABLE)");
    }
}
