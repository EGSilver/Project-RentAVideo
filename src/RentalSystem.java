import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class RentalSystem {
    private ArrayList<Customer> customers;

    private double totalPrice;
    private final double TAX = 1.21;
    StockManager stockManager = new StockManager();
    ItemManager itemManager = new ItemManager();
    RentalItemLoader loader = new RentalItemLoader();


    //TODO Hashmap with day overview, what has been rented / how much money did we make

    public RentalSystem() {
        this.customers = new ArrayList<>();
        this.totalPrice = totalPrice;
    }

    public void checkout(Customer customer, CartManager cartManager, DayOverview overview) throws IOException {
        System.out.println(cartManager.getItemCart());
        String s = customer.getName() + "\n";
        totalPrice = 0;
        for (RentalItem item : cartManager.getItemCart()) {
            if (item instanceof Movie movie) {
                s += movie.getTitle() + "\n";
                stockManager.setStockMinusOne(item);
                overview.setRentals(overview.getRentals()+1);
                if (stockManager.getStockFromCSV(item) <= 1) {
                    item.setOutOfStock(true);
                }
            } else if (item instanceof Game game) {
                stockManager.setStockMinusOne(item);
                overview.setRentals(overview.getRentals()+1);
                s += game.getTitle() + "\n";
                if (stockManager.getStockFromCSV(item) <= 1) {
                    item.setOutOfStock(true);
                }
            }
        }
        for (RentalItem c : cartManager.getItemCart()) {
            totalPrice += c.getRentalPrice();
        }
        s += "\nTotal price to pay: " + (totalPrice * TAX);
        System.out.println(s);
    }


    public void addCustomer(Customer customer, DayOverview overview) {
        overview.setNewMembers(+1);
        customers.add(customer);
    }

    public ArrayList<Customer> getCustomers() {
        System.out.println("Here's a list of all customers: ");
        return customers;
    }

}
