public class Game extends RentalItem {
    private String platform;
    private String publisher;

    public Game(String title, double rentalPrice, int rentalDuration, boolean outOfStock, int stock, String type, String platform, String publisher, String esrbRating, int daysSinceLastRented) {
        super(title, rentalPrice, rentalDuration, outOfStock, stock, type, esrbRating, daysSinceLastRented);
        this.platform = platform;
        this.publisher = publisher;
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

/*
    @Override
    public String toString() {
        return "Game{" +
                "title='" + super.getTitle() + '\'' +
                "platform='" + platform + '\'' +
                ", publisher='" + publisher + '\'' +
                '}';
    }
*/

   @Override
   public String toString() {
        return super.getTitle() + " - " +
                super.getType();


    }
}
