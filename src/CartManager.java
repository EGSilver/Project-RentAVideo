import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CartManager {
    private ArrayList<RentalItem> itemCart = new ArrayList<>();
    DatabaseManager databaseManager = new DatabaseManager();
    private Date rentalDate = getDate();
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

    public void checkout(Customer customer, CartManager cartManager, DayOverview overview, DatabaseManager databaseManager) throws IOException, ParseException {
        System.out.println(cartManager.getItemCart());
        String s = "Your Ticket:\n" + customer.getName() + " " + customer.getFirstName() + ", items rented:\n";
        totalPrice = 0;
        for (RentalItem item : cartManager.getItemCart()) {
            if (item.getType().equals("Movie")) {
                s += item.getType() + ": " + item.getTitle() + "\n";
                databaseManager.updateItemStockInCsv(item);
                overview.setRentals(overview.getRentals() + 1);
                if (databaseManager.getStockFromCsv(item) <= 1) {
                    item.setOutOfStock(true);
                }
            } else if (item.getType().equals("Game")) {
                databaseManager.updateItemStockInCsv(item);
                overview.setRentals(overview.getRentals() + 1);
                s += item.getType() + ": " + item.getTitle() + "\n";
                if (databaseManager.getStockFromCsv(item) <= 1) {
                    item.setOutOfStock(true);
                }
            }
        }
        for (RentalItem c : cartManager.getItemCart()) {
            totalPrice += c.getRentalPrice();
        }
        // Make total value only print 2 digits behind the decimal
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String formattedTotalPrice = decimalFormat.format(totalPrice * TAX);
        NumberFormat numberFormat = NumberFormat.getInstance();
        Number number = numberFormat.parse(formattedTotalPrice);
        double income = number.doubleValue();
        if (totalPrice == 0) {
            s += ("Total price to pay: €" + "0");
            System.out.println(s + "\n");
        } else {
            s += "Total price to pay: €" + formattedTotalPrice;
            System.out.println(s + "\n");
            overview.setIncome(income);
        }

    }

    public void returnItemAndCalculateFine(RentalItem item, DayOverview overview) {
        double fine = 0;
        double income = overview.getIncome();
        double fineIncome = 0;
        Date rentalDate = getDate();
        int duration = calcRentalDuration(rentalDate);
        int currentStock = item.getStock();
        item.setStock(currentStock + 1);
        if (duration > 3 && duration < 7) {
            fine = duration * item.getRentalPrice();
            fineIncome = fine;
            System.out.println("This item was returned late by "
                    + (duration - item.getRentalDuration())
                    + " day(s). The calculated fine is = "
                    + fine);
            overview.setIncome(income + fineIncome);
        } else if (duration > 3 && duration < 14) {
            fine = (duration * item.getRentalPrice() * 0.1);
            fineIncome = fine;
            System.out.println("This item was returned late by "
                    + (duration - item.getRentalDuration())
                    + " day(s). The calculated fine is = "
                    + fine);
            overview.setIncome(income + fineIncome);
        } else if (duration > 3 && duration < 21) {
            fine = (duration * (item.getRentalPrice() *2) * 0.05);
            fineIncome = fine;
            System.out.println("This item was returned late by "
                    + (duration - item.getRentalDuration())
                    + " day(s). The calculated fine is = "
                    + fine);
            overview.setIncome(income + fineIncome);
        } else {
            System.out.println("This item was returned on time");
        }
    }

    public ArrayList<RentalItem> getItemCart() {
        return itemCart;
    }

    public int calcRentalDuration(Date rentalDate) {
        Date currentDate = getDate();
        long difference = currentDate.getTime() - rentalDate.getTime();
        int duration = (int) TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
        return duration;
    }

    public Date getDate() {
        return new Date();
    }

    @Override
    public String toString() {
        return "CartManager{" +
                "itemCart=" + itemCart +
                ", databaseManager=" + databaseManager +
                ", totalPrice=" + totalPrice +
                ", TAX=" + TAX +
                ", decimalFormat=" + decimalFormat +
                '}';
    }
}

