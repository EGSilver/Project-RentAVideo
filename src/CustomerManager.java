import java.util.*;

public class CustomerManager {
    private List<Customer> customers;
    private HashMap<Customer, ArrayList<RentalItem>> rentalHistory = new HashMap<>();

    private HashMap<RentalItem, Date> rentalDates = new HashMap<>();

    public CustomerManager(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public void saveRentalDateInMap(RentalItem item, Date rentalDate) {
        rentalDates.put(item, rentalDate);
    }

    public String viewRentedItemDate(RentalItem inboundItem) {
        String s = "";
        for (Map.Entry<RentalItem, Date> entry : rentalDates.entrySet()) {
            Date rentalDate = entry.getValue();
            s += inboundItem.getType() + ": " + inboundItem.getTitle() + ", Date rented out: " + rentalDate;
        }
        return s;
    }

    public void createRentalHistory(Customer customer, CartManager cartManager) {
        ArrayList<RentalItem> rental  = cartManager.getItemCart();
           rentalHistory.put(customer,rental);
    }

    public ArrayList<RentalItem> viewRentalHistory(Customer customer) {
        return rentalHistory.get(customer);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void removeCustomer(Customer customer) {
        //Iterate through Array and remove customer
        Iterator<Customer> it = customers.iterator();
        while (it.hasNext()) {
            if (it.next().equals(customer)) {
                it.remove();
            }
        }
    }

    @Override
    public String toString() {
        return "CustomerManager{" +
                "customers=" + customers +
                ", rentalHistory=" + rentalHistory +
                '}';
    }
}
