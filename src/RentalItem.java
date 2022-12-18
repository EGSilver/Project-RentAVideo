public class RentalItem {
    private double rentalPrice;
    private int rentalDuration;
    private boolean outOfStock;
    private int stock;

    public RentalItem(double rentalPrice, int rentalDuration, boolean outOfStock, int stock) {
        this.rentalPrice = rentalPrice;
        this.rentalDuration = rentalDuration;
        this.outOfStock = outOfStock;
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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

    @Override
    public String toString() {
        return "RentalItem{" +
                "rentalPrice=" + rentalPrice +
                ", rentalDuration=" + rentalDuration +
                ", rentalStatus=" + outOfStock +
                ", stock=" + stock +
                '}';
    }
}
