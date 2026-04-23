import enums.MovieRating;
import exceptions.InvalidInputException;

/**
 * Represents a movie with details like title, genre, duration, and rating.
 */
public class Movie {
    private String title;
    private String genre;
    private int durationMinutes;
    private MovieRating rating;
    private String director;
    private String description;

    public Movie(String title, String genre, int durationMinutes, MovieRating rating) throws InvalidInputException {
        validateInputs(title, genre, durationMinutes);
        this.title = title;
        this.genre = genre;
        this.durationMinutes = durationMinutes;
        this.rating = rating;
        this.director = "";
        this.description = "";
    }

    public Movie(String title, String genre, int durationMinutes, MovieRating rating, String director, String description) throws InvalidInputException {
        validateInputs(title, genre, durationMinutes);
        this.title = title;
        this.genre = genre;
        this.durationMinutes = durationMinutes;
        this.rating = rating;
        this.director = director != null ? director : "";
        this.description = description != null ? description : "";
    }

    private void validateInputs(String title, String genre, int durationMinutes) throws InvalidInputException {
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidInputException("Movie title cannot be empty");
        }
        if (genre == null || genre.trim().isEmpty()) {
            throw new InvalidInputException("Movie genre cannot be empty");
        }
        if (durationMinutes <= 0) {
            throw new InvalidInputException("Movie duration must be positive");
        }
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public MovieRating getRating() {
        return rating;
    }

    public String getDirector() {
        return director;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return title + " (" + genre + ", " + durationMinutes + " mins) [" + rating.getCode() + "]";
    }
}
