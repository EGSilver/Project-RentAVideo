public class Game extends RentalItem {
    private String title;
    private String platform;
    private String publisher;
    private double rating;

    public Game(double rentalPrice, int rentalDuration, boolean rentalStatus, int stock, String title, String platform, String publisher, double rating) {
        super(rentalPrice, rentalDuration, rentalStatus, stock);
        this.title = title;
        this.platform = platform;
        this.publisher = publisher;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Game{" +
                "title='" + title + '\'' +
                ", platform='" + platform + '\'' +
                ", publisher='" + publisher + '\'' +
                ", rating=" + rating +
                '}';
    }
}
