import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class RentalSystem {
    private ArrayList<Customer> customers;
    private ArrayList<RentalItem> itemCart;

    private double totalPrice;
    private final double TAX = 1.21;
    DayOverview overview = new DayOverview(0,0,0,0);
    CheckSetStock checkSetStock = new CheckSetStock();

    //TODO Hashmap with day overview, what has been rented / how much money did we make

    public RentalSystem() {
        this.customers = new ArrayList<>();
        this.itemCart = new ArrayList<>();
        this.totalPrice = totalPrice;
    }

    public ArrayList<RentalItem> addItemToCart(RentalItem item) throws IOException {
        if (checkSetStock.getStockFromCSV(item) <= 0) {
            System.out.println("This item is out of stock");
        } else {
            itemCart.add(item);
            //System.out.println(item.getRentalPrice());
        }
        return itemCart;
    }

    public String viewCart() {
        String s = "";
        for (RentalItem item : itemCart) {
            if (item instanceof Movie) {
                 s += ((Movie) item).getTitle() + "\n";
            } else if (item instanceof Game) {
                 s += ((Game) item).getTitle() + "\n";
            }
        }
        return s;
    }

    public void checkout(Customer customer) throws IOException {
        String s = customer.getName() + "\n";
        totalPrice = 0;
        for (RentalItem item : itemCart) {
            if (item instanceof Movie movie) {
                s += movie.getTitle() + "\n";
                checkSetStock.setStockMinusOne(item);
                overview.setRentals(overview.getRentals()+1);
                if (checkSetStock.getStockFromCSV(item) <= 1) {
                    item.setOutOfStock(true);
                }
            } else if (item instanceof Game game) {
                checkSetStock.setStockMinusOne(item);
                overview.setRentals(overview.getRentals()+1);
                s += game.getTitle() + "\n";
                if (checkSetStock.getStockFromCSV(item) <= 1) {
                    item.setOutOfStock(true);
                }
            }
        }
        for (RentalItem c : itemCart) {
            totalPrice += c.getRentalPrice();
        }
        s += "\nTotal price to pay: " + (totalPrice * TAX);
        System.out.println(s);
    }


    public void addCustomer(Customer customer) {
        overview.setNewMembers(+1);
        customers.add(customer);
    }

    public ArrayList<Customer> getCustomers() {
        System.out.println("Here's a list of all customers: ");
        return customers;
    }

    public void viewOverview(Date date) {
        overview.createOverview(date);
        overview.viewOverview(date);
    }

}
