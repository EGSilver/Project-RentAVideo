import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class RentalSystem {
    private ArrayList<Customer> customers;
    private RentalItemLoader loader;
    private ItemManager itemManager;
    private CartManager cartManager;
    private CheckOutManager checkOutManager;
    private DayOverview dayOverview;

    public RentalSystem() {
        this.customers = new ArrayList<>();
        this.loader = new RentalItemLoader();
        this.itemManager = new ItemManager();
        this.cartManager = new CartManager();
        this.checkOutManager = new CheckOutManager();
        this.dayOverview = new DayOverview(0,0,0,0);
    }

    public void addCustomer(Customer customer, DayOverview overview) {
        overview.setNewMembers(+1);
        customers.add(customer);
    }

    public ArrayList<Customer> getCustomers() {
        System.out.println("Here's a list of all customers: ");
        return customers;
    }

    public ArrayList<RentalItem> getRentalMovies() {
        return loader.getRentalMovies();
    }

    public ArrayList<RentalItem> getRentalGames() {
        return loader.getRentalGames();
    }

    public boolean movieExists(String title) {
        return itemManager.movieExists(title);
    }

    public void addMovieToCSV(Movie movie) {
        itemManager.addMovieToMoviesCSV(movie);
    }

    public void addGameToCSV(Game game) {
        itemManager.addGameToGamesCSV(game);
    }

    public void returnItem(RentalItem item, DayOverview overview, StockManager stockManager) throws IOException {
        stockManager.returnItem(item, overview);
    }

    public void createOverview(Date date, DayOverview day) {
        day.createOverview(date);
    }

    public void viewOverview(Date date, DayOverview day) {
        day.viewOverview(date);
    }

    public void addItemToCart(RentalItem item, CartManager cart) throws IOException {
        cart.addItemToCart(item);
    }

    public String viewCart(CartManager cart) {
        return cart.viewCart();
    }

    public ArrayList<RentalItem> getCart (CartManager cart) {
        return cart.getItemCart();
    }

    public void checkOut(Customer customer, CartManager cartManager, DayOverview overview, StockManager stockManager) throws IOException {
        checkOutManager.checkout(customer, cartManager, overview, stockManager);
    }

    public void setLateReturns(int lateReturns, DayOverview day) {
        day.setLateReturns(lateReturns);
    }

}
