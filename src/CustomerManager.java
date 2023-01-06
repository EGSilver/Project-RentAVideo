import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CustomerManager {
    private ArrayList<Customer> customers;
    private HashMap<Customer, ArrayList<RentalItem>> rentalHistory = new HashMap<>();

    private HashMap<RentalItem, Date> rentalDates = new HashMap<>();

    public CustomerManager(ArrayList<Customer> customers) {
        this.customers = customers;
    }

     /**
     saveRentalDateInMap is a function that stores the rental date of a rental item in a map.
     @param item The rental item whose rental date is being stored.
     @param rentalDate The rental date of the rental item.
     */
    public void saveRentalDateInMap(RentalItem item, Date rentalDate) {
        rentalDates.put(item, rentalDate);
    }

     /**
     This function returns a string containing information about a rental item and the date it was rented out.
     @param inboundItem The rental item for which the rental date information should be retrieved
     @return A string containing the type of the rental item, its title, and the date it was rented out
     */
    public String viewRentedItemDate(RentalItem inboundItem) {
        String s = "";
        for (Map.Entry<RentalItem, Date> entry : rentalDates.entrySet()) {
            Date rentalDate = entry.getValue();
            s += inboundItem.getType() + ": " + inboundItem.getTitle() + ", Date rented out: " + rentalDate;
        }
        return s;
    }

    public void createRentalHistory(Customer customer, CartManager cartManager) {
        ArrayList<RentalItem> rental = cartManager.getItemCart();
        rentalHistory.put(customer, rental);
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
