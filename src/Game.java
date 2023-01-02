public class Game extends RentalItem {
    private String platform;
    private String publisher;
    private double criticRating;

    public Game(String title, double rentalPrice, int rentalDuration, boolean outOfStock, int stock, String type, String platform, String publisher, String esrbRating, double criticRating) {
        super(title, rentalPrice, rentalDuration, outOfStock, stock, type, esrbRating);
        this.platform = platform;
        this.publisher = publisher;
        this.criticRating = criticRating;
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

    public double getCriticRating() {
        return criticRating;
    }

    public void setCriticRating(double criticRating) {
        this.criticRating = criticRating;
    }

    @Override
    public String toString() {
        return "Game{" +
                "platform='" + platform + '\'' +
                ", publisher='" + publisher + '\'' +
                ", criticRating=" + criticRating +
                '}';
    }
}
