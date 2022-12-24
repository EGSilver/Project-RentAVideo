public class Movie extends RentalItem {
    private String releaseDate;
    private String genre;
    private String description;

    public Movie(String title, double rentalPrice, int rentalDuration, boolean outOfStock, int stock, String type, String releaseDate, String genre, String description) {
        super(title, rentalPrice, rentalDuration, outOfStock, stock, type);
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.description = description;
    }

    public String getDescription() {
       return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Movie: {" +
                "title='" + super.getTitle() + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                ", type='" + super.getType() + '\'' +
                '}';
    }
}
