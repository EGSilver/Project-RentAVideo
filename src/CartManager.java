import java.io.IOException;
import java.util.ArrayList;

public class CartManager {
    private ArrayList<RentalItem> itemCart = new ArrayList<>();
    StockManager stockManager = new StockManager();

    public ArrayList<RentalItem> addItemToCart(RentalItem item) throws IOException {
        if (stockManager.getStockFromCSV(item) <= 0) {
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
                s +=  item.getTitle() + "\n";
        }
        return s;
    }

    public ArrayList<RentalItem> getItemCart() {
        return itemCart;
    }
}

