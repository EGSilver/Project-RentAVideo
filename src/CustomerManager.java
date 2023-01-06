import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CustomerManager {
    private List<Customer> customers;
    private HashMap<Customer, ArrayList<RentalItem>> rentalHistory = new HashMap<>();

    private HashMap<RentalItem, Date> rentalDates = new HashMap<>();

    public CustomerManager(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public ArrayList<Customer> loadCustomersFromCsv() {
        File filepath = new File(".\\data\\customers.csv");
        ArrayList<Customer> customers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int yearsSubscribed = Integer.parseInt(parts[5]);
                Customer customer = new Customer(0, parts[0], parts[1], parts[3], parts[2], parts[4], yearsSubscribed);
                customers.add(customer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return customers;
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
