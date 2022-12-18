public class RentalItem {
    private double rentalPrice;
    private int rentalDuration;
    private boolean rentalStatus;

    public RentalItem(double rentalPrice, int rentalDuration, boolean rentalStatus) {
        this.rentalPrice = rentalPrice;
        this.rentalDuration = rentalDuration;
        this.rentalStatus = rentalStatus;
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

    public boolean isRentalStatus() {
        return rentalStatus;
    }

    public void setRentalStatus() {
        this.rentalStatus = rentalStatus;
    }

    public void getRentalStatus() {
        this.rentalStatus = rentalStatus;
    }

    public void setRentalStatus(boolean rentalStatus) {
        this.rentalStatus = rentalStatus;
    }
    @Override
    public String toString() {
        return "RentalItem{" +
                "rentalPrice=" + rentalPrice +
                ", rentalDuration=" + rentalDuration +
                ", rentalStatus=" + rentalStatus +
                '}';
    }
}
