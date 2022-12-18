public class Movie extends RentalItem {
    private String title;
    private String releaseDate;
    private String genre;

    public Movie(double rentalPrice, int rentalDuration, boolean rentalStatus, String title, String releaseDate, String genre) {
        super(rentalPrice, rentalDuration, rentalStatus);
        this.title = title;
        this.releaseDate = releaseDate;
        this.genre = genre;
    }

    public void getDescription() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        return "Movie{" +
                "title='" + title + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
