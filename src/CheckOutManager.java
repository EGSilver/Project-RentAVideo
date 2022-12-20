import java.io.IOException;
import java.text.DecimalFormat;

public class CheckOutManager {
    private double totalPrice;
    private final double TAX = 1.21;
    public void checkout(Customer customer, CartManager cartManager, DayOverview overview, StockManager stockManager) throws IOException {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
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
        String formattedTotalPrice = decimalFormat.format(totalPrice * TAX);
        s += "\nTotal price to pay: €" + formattedTotalPrice;
        System.out.println(s);
    }
}
