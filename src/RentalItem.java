public class RentalItem {
    private String title;
    private double rentalPrice;
    private int rentalDuration;
    private boolean outOfStock;
    private int stock;
    private String type;
    private String esrbRating;
    private int daysSinceLastRented;

    public RentalItem(String title, double rentalPrice, int rentalDuration, boolean outOfStock, int stock, String type, String esrbRating, int daysSinceLastRented) {
        this.title = title;
        this.rentalPrice = rentalPrice;
        this.rentalDuration = rentalDuration;
        this.outOfStock = outOfStock;
        this.stock = stock;
        this.type = type;
        this.esrbRating = esrbRating;
        this.daysSinceLastRented = daysSinceLastRented;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }
    public int getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(int rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public String getEsrbRating() {
        return esrbRating;
    }

    public void setEsrbRating(String esrbRating) {
        this.esrbRating = esrbRating;
    }

    public boolean isOutOfStock() {
        return outOfStock;
    }

    public void setRentalStatus() {
        this.outOfStock = outOfStock;
    }

    public void getRentalStatus() {
        this.outOfStock = outOfStock;
    }
    public void setOutOfStock(boolean outOfStock) {
        this.outOfStock = outOfStock;
    }

    public int getDaysSinceLastRented() {
        return daysSinceLastRented;
    }

    public void setDaysSinceLastRented(int daysSinceLastRented) {
        this.daysSinceLastRented = daysSinceLastRented;
    }

    @Override
    public String toString() {
        return "RentalItem{" +
                "title='" + title + '\'' +
                ", rentalPrice=" + rentalPrice +
                ", rentalDuration=" + rentalDuration +
                ", outOfStock=" + outOfStock +
                ", stock=" + stock +
                ", type='" + type + '\'' +
                ", esrbRating='" + esrbRating + '\'' +
                ", daysSinceLastRented=" + daysSinceLastRented +
                '}';
    }
}
