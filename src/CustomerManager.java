import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomerManager {
    private List<Customer> customers;

    public CustomerManager(ArrayList<Customer> customers) {
        this.customers = customers;
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
                '}';
    }
}
