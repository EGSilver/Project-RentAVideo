public class Game extends RentalItem {
    private String platform;
    private String publisher;
    private double rating;

    public Game(String title, double rentalPrice, int rentalDuration, boolean outOfStock, int stock, String type, String platform, String publisher, double rating) {
        super(title, rentalPrice, rentalDuration, outOfStock, stock, type);
        this.platform = platform;
        this.publisher = publisher;
        this.rating = rating;
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
        return "Game: {" +
                "title='" + super.getTitle() + '\'' +
                ", platform='" + platform + '\'' +
                ", publisher='" + publisher + '\'' +
                ", rating=" + rating +
                '}';
    }
}
