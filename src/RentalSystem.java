import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class RentalSystem {
    private ArrayList<Customer> customers;
    private DatabaseManager databaseManager;
    private CartManager cartManager;
    private DayOverview dayOverview;

    public RentalSystem() {
        this.customers = new ArrayList<>();
        this.databaseManager = new DatabaseManager();
        this.cartManager = new CartManager();
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

    public ArrayList<RentalItem> getRentalMovies(DatabaseManager databaseManager) {
        return databaseManager.getRentalMovies();
    }

    public ArrayList<RentalItem> getRentalGames(DatabaseManager databaseManager) {
        return databaseManager.getRentalGames();
    }

    public boolean movieExists(String title) {
        return databaseManager.movieExists(title);
    }

    public void addToDatabse(RentalItem item) {
        if (item.getType().equals("Movie")) {
            databaseManager.addMovieToMoviesCSV((Movie) item);
        } else {
            databaseManager.addGameToGamesCSV((Game) item);
        }

    }

    public int getStockFromCSV(RentalItem item) throws IOException {
        return databaseManager.getStockFromCSV(item);
    }

    public void addGameToCSV(Game game) {
        databaseManager.addGameToGamesCSV(game);
    }

    public void returnItem(RentalItem item, DayOverview overview) throws IOException {
        if (item.getType().equals("Movie")) {
            databaseManager.returnMovieItem(item, overview);
        } else {
            databaseManager.returnGameItem(item, overview);
        }
    }

    public void createOverview(Date date, DayOverview day) {
        day.createOverview(date);
    }

    public void viewOverview(Date date, DayOverview day) {
        day.viewOverview(date);
    }

    public void addItemToCart(RentalItem item, Customer customer, CartManager cart) throws IOException {
        cart.addItemToCart(item, customer);
    }

    public String viewCart(CartManager cart) {
        return cart.viewCart();
    }

    public ArrayList<RentalItem> getCart (CartManager cart) {
        return cart.getItemCart();
    }

    public void checkOut(Customer customer, CartManager cartManager, DayOverview overview, DatabaseManager databaseManager) throws IOException {
        cartManager.checkout(customer, cartManager, overview, databaseManager);
    }

    public void setLateReturns(int lateReturns, DayOverview day) {
        day.setLateReturns(lateReturns);
    }

    public String getRatingFromCSV(RentalItem item) throws IOException {
        return databaseManager.getRatinFromCSV(item);
    }

    public void setStockMinusOne(RentalItem item, DatabaseManager databaseManager) throws IOException {
        if (item.getType().equals("Movie")) {
            databaseManager.setMovieStockMinusOne(item);
        } else {
            databaseManager.setGameStockMinusOne(item);
        }
    }

}
