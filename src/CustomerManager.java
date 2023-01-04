import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

//TODO Hashmap? for when customer checks out so we can track what the customer has rented.

public class CustomerManager {
    private List<Customer> customers;
    private HashMap<Customer, ArrayList<RentalItem>> rentalHistory = new HashMap<>();

    public CustomerManager(ArrayList<Customer> customers) {
        this.customers = customers;
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
