public class UnderAgedCustomer extends Customer {
    private boolean underAged;

    public UnderAgedCustomer(String name, String adres, String birthdate, String phoneNumber, int yearsSubscribed, boolean underAged) {
        super(name, adres, birthdate, phoneNumber, yearsSubscribed);
        this.underAged = underAged;
    }

    public void rent() {
    }

    @Override
    public String toString() {
        return "UnderAgedCustomer{" +
                "underAged=" + underAged +
                '}';
    }
}
