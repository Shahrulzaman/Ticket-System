package enums;

public enum MovieRating {
    U("U", 0, "Universal - Suitable for all ages"),
    P13("P13", 13, "Parental guidance for children under 13"),
    PG18("18", 18, "Suitable only for persons 18 and above");

    private final String code;
    private final int minimumAge;
    private final String description;

    MovieRating(String code, int minimumAge, String description) {
        this.code = code;
        this.minimumAge = minimumAge;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public int getMinimumAge() {
        return minimumAge;
    }

    public String getDescription() {
        return description;
    }
}
