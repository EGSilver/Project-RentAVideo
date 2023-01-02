import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartManager {
    private ArrayList<RentalItem> itemCart = new ArrayList<>();
    StockManager stockManager = new StockManager();
    DatabaseManager loader = new DatabaseManager();

    private double totalPrice;
    private final double TAX = 1.21;
    private DecimalFormat decimalFormat = new DecimalFormat("#.00");

    public ArrayList<RentalItem> addItemToCart(RentalItem item, Customer customer) throws IOException {
        if (stockManager.getStockFromCSV(item) <= 0) {
            System.out.println("This item is out of stock");
        } else if (loader.getRatingfromCSV(item).equals("A") && customer.checkUnderaged()) {
            System.out.println("You are to young to be able to rent this item. Please contact support for more information.\n");
        } else {
            itemCart.add(item);
        }
        return itemCart;
    }

    public String viewCart() {
        String s = "";
        for (RentalItem item : itemCart) {
            s += item.getTitle() + "\n";
        }
        return s;
    }

    public void checkout(Customer customer, CartManager cartManager, DayOverview overview, StockManager stockManager) throws IOException {
        System.out.println(cartManager.getItemCart());
        String s = customer.getName() + "\n";
        totalPrice = 0;
        for (RentalItem item : cartManager.getItemCart()) {
            if (item.getType().equals("Movie")) {
                s += item.getTitle() + "\n";
                stockManager.setMovieStockMinusOne(item);
                overview.setRentals(overview.getRentals() + 1);
                if (stockManager.getStockFromCSV(item) <= 1) {
                    item.setOutOfStock(true);
                }
            } else if (item.getType().equals("Game")) {
                stockManager.setGameStockMinusOne(item);
                overview.setRentals(overview.getRentals() + 1);
                s += item.getTitle() + "\n";
                if (stockManager.getStockFromCSV(item) <= 1) {
                    item.setOutOfStock(true);
                }
            }
        }
        for (RentalItem c : cartManager.getItemCart()) {
            totalPrice += c.getRentalPrice();
            //todo rentalDays & lateReturns
        }
        String formattedTotalPrice = decimalFormat.format(totalPrice * TAX);
        if (totalPrice == 0) {
            s += ("Total price to pay: €" + "0");
            System.out.println(s + "\n");
        } else {
            s += "Total price to pay: €" + formattedTotalPrice;
            System.out.println(s + "\n");
        }
    }

    public ArrayList<RentalItem> getItemCart() {
        return itemCart;
    }
}

