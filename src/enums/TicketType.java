package enums;

public enum TicketType {
    ADULT("Adult"),
    CHILD("Child"),
    SENIOR("Senior"),
    STUDENT("Student");

    private final String displayName;

    TicketType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
