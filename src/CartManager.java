import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartManager {
    private ArrayList<RentalItem> itemCart = new ArrayList<>();
    DatabaseManager databaseManager = new DatabaseManager();

    private double totalPrice;
    private final double TAX = 1.21;
    private DecimalFormat decimalFormat = new DecimalFormat("#.00");

    public ArrayList<RentalItem> addItemToCart(RentalItem item, Customer customer) throws IOException {
        if (databaseManager.getStockFromCsv(item) <= 0) {
            System.out.println("This item is out of stock");
        } else if (databaseManager.getAgeRatingFromCsv(item).equals("A") && customer.checkUnderaged()) {
            System.out.println(item.getTitle() + " has been rated " + databaseManager.getAgeRatingFromCsv(item) + ". You must be age 18 or higher to be able to rent this item.\n");
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

    public void checkout(Customer customer, CartManager cartManager, DayOverview overview, DatabaseManager databaseManager) throws IOException {
        System.out.println(cartManager.getItemCart());
        String s = customer.getName() + "\n";
        totalPrice = 0;
        for (RentalItem item : cartManager.getItemCart()) {
            if (item.getType().equals("Movie")) {
                s += item.getTitle() + "\n";
                databaseManager.updateItemStockInCsv(item);
                overview.setRentals(overview.getRentals() + 1);
                if (databaseManager.getStockFromCsv(item) <= 1) {
                    item.setOutOfStock(true);
                }
            } else if (item.getType().equals("Game")) {
                databaseManager.updateItemStockInCsv(item);
                overview.setRentals(overview.getRentals() + 1);
                s += item.getTitle() + "\n";
                if (databaseManager.getStockFromCsv(item) <= 1) {
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
            //TODO Re-evaluate
            overview.setIncome(totalPrice * TAX);
        }

    }

    public ArrayList<RentalItem> getItemCart() {
        return itemCart;
    }
}

