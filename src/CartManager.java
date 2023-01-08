import javax.swing.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CartManager {
    private ArrayList<RentalItem> itemCart = new ArrayList<>();
    private DatabaseManager databaseManager = new DatabaseManager();

    private Date rentalDate;
    private double totalPrice;
    private final double TAX = 1.21;

    private DecimalFormat decimalFormat = new DecimalFormat("#.00");
    private Date currentSystemDate = new java.sql.Date(System.currentTimeMillis());

    public String addItemToCart(RentalItem item, Customer customer) throws IOException {
        String outOfStockMessage = "";
        if (databaseManager.getStockFromCsv(item) <= 0) {
            outOfStockMessage += "This item is out of stock";
        } else if (databaseManager.getAgeRatingFromCsv(item).equals("A") && customer.checkUnderaged()) {
            outOfStockMessage += item.getTitle() + " has been rated " + databaseManager.getAgeRatingFromCsv(item) + ". You must be age 18 or higher to be able to rent this item.\n";
        } else {
            itemCart.add(item);
        }
        return outOfStockMessage;
    }

    public String viewCart() {
        String s = "";
        for (RentalItem item : itemCart) {
            s += item.getTitle() + "\n";
        }
        return s;
    }

    /**
     The checkout function processes the rental transaction for a customer and updates the day's overview with relevant information.
     It also updates the stock of the rented items in the database and sets them as out of stock if necessary.
     */
    public String checkout(Customer customer, CartManager cartManager, DayOverview overview, DatabaseManager databaseManager) throws IOException, ParseException {
        rentalDate = getDate();
        double getIncome = overview.getIncome();
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
        // Making sure total value only print 2 digits after the decimal
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
            overview.setIncome(getIncome + income);
            overview.updateIncomeOverview(String.valueOf(currentSystemDate));
        }
        return s;
    }

    /**
    The returnItemAndCalculateFine function processes the return of a rental item and calculates any applicable fines.
    It also updates the day's overview with relevant information and updates the stock of the returned item.
     */
    public String returnItemAndCalculateFine(RentalItem item, DayOverview overview) throws ParseException {
        // For testing late returns.
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date pastDate = dateFormat.parse("2022-12-04");
        // End
        String stringOutput = "";
        double fine = 0;
        double income = overview.getIncome();
        double fineIncome = 0;
        int returns = overview.getLateReturns();
        int duration = calcRentalDuration(pastDate);
        //int duration = calcRentalDuration(rentalDate);
        int currentStock = item.getStock();
        //item.setStock(currentStock + 1);
        if (duration > 3 && duration > 7) {
            fine = duration * item.getRentalPrice();
            fineIncome = fine;
            stringOutput = "This item was returned late by "
                    + (duration - item.getRentalDuration())
                    + " day(s). The calculated fine is = €"
                    + fine;
            overview.setLateReturns((returns) +1);
            overview.setIncome((income) + fineIncome);
        } else if (duration > 3 && duration > 14) {
            fine = (duration * item.getRentalPrice() * 0.1);
            fineIncome = fine;
            stringOutput = "This item was returned late by "
                    + (duration - item.getRentalDuration())
                    + " day(s). The calculated fine is = €"
                    + fine;
            overview.setLateReturns((returns) +1);
            overview.setIncome((income) + fineIncome);
        } else if (duration > 3 && duration > 21) {
            fine = (duration * (item.getRentalPrice() * 2 ) * 0.05);
            fineIncome = fine;
            stringOutput = "This item was returned late by "
                    + (duration - item.getRentalDuration())
                    + " day(s). The calculated fine is = €"
                    + fine;
            overview.setLateReturns((returns) +1);
            overview.setIncome((income) + fineIncome);
        } else {
            stringOutput = "This item was returned on time";
        }
        return stringOutput;
    }

    public ArrayList<RentalItem> getItemCart() {
        return itemCart;
    }

    /**
     calcRentalDuration calculates the duration of a rental by calculating the difference between the current date and the rental date.
     @param rentalDate the date when the rental started
     */
    public int calcRentalDuration(Date rentalDate) {
        Date currentDate = getDate();
        long difference = currentDate.getTime() - rentalDate.getTime();
        int duration = (int) TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
        return duration;
    }

    public Date getDate() {
        return new Date();
    }

    public void clearShoppingCart() {
        itemCart.clear();
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

