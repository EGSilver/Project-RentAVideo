import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class RentalSystem {
    private ArrayList<Customer> customers;
    private CustomerManager customerManager;
    private DatabaseManager databaseManager;
    private CartManager cartManager;
    private DayOverview dayOverview;

    public RentalSystem() {
        this.customers = new ArrayList<>();
        this.databaseManager = new DatabaseManager();
        this.cartManager = new CartManager();
        this.dayOverview = new DayOverview(0,0,0,0);
        this.customerManager = new CustomerManager(customers);
    }

    public void addCustomer(Customer customer, DayOverview overview) {
        overview.setNewMembers(+1);
        customerManager.addCustomer(customer);
        addCustomerToDatabase(customer);
    }

    public ArrayList<Customer> getCustomers() {
        System.out.println("Here's a list of all members: ");
        return (ArrayList<Customer>) customerManager.getCustomers();
    }

    public ArrayList<RentalItem> getRentalMovies(DatabaseManager databaseManager) {
        return databaseManager.getRentalMovies();
    }

    public ArrayList<RentalItem> getRentalGames(DatabaseManager databaseManager) {
        return databaseManager.getRentalGames();
    }

    public boolean movieExists(String title) {
        return databaseManager.movieTitleExists(title);
    }

    public void addItemToDatabase(RentalItem item) {
        if (item.getType().equals("Movie")) {
            databaseManager.addMovieToMoviesCsv((Movie) item);
        } else {
            databaseManager.addGameToGamesCsv((Game) item);
        }
    }

    public int getStockFromCSV(RentalItem item) throws IOException {
        return databaseManager.getStockFromCsv(item);
    }

    public void addGameToCSV(Game game) {
        databaseManager.addGameToGamesCsv(game);
    }

    public void createOverview(Date date, DayOverview day) {
        day.createOverview(date);
    }

    public void viewDayOverview(Date date, DayOverview day) {
        day.viewDayOverview(date);
    }

    public void createIncomeOverview(Date date, DayOverview day) {
        day.createIncomeOverview(date);
    }

    public void viewIncomeOverview(Date date, DayOverview day) {
        day.viewIncomeOverview(date);
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

    public void checkOut(Customer customer, CartManager cartManager, DayOverview overview, DatabaseManager databaseManager) throws IOException, ParseException {
        cartManager.checkout(customer, cartManager, overview, databaseManager);
    }

    public int checkDaysSinceLastRented(RentalItem item, DatabaseManager databaseManager) {
        return databaseManager.checkDaysSinceLastRented(item);
    }

    public void removeItemFromDatabase(RentalItem item, DatabaseManager databaseManager) {
        databaseManager.removeItemFromCsv(item, databaseManager);
    }

    public void setLateReturns(int lateReturns, DayOverview day) {
        day.setLateReturns(lateReturns);
    }

    public String getRatingFromCSV(RentalItem item) throws IOException {
        return databaseManager.getAgeRatingFromCsv(item);
    }

    public void setStockMinusOne(RentalItem item, DatabaseManager databaseManager) throws IOException {
        databaseManager.updateItemStockInCsv(item);
    }

    public void returnItem(RentalItem item, DayOverview overview) throws IOException {
        databaseManager.returnItem(item, overview);
    }

    public void addCustomerToDatabase(Customer customer) {
        databaseManager.addCustomerToDatabase(customer);
    }

    public void removeCustomerFromArray(Customer customer) {
        customerManager.removeCustomer(customer);
    }

    public void removeIncomeOverviewFromMap(Date date, DayOverview dayOverview) {
        dayOverview.removeIncomeOverviewFromMap(date);
    }

    public void removeDayOverviewFromMap(Date date, DayOverview dayOverview) {
        dayOverview.removeDayOverviewFromMap(date);
    }

    public void removeItemFromArraylist(RentalItem item, DatabaseManager databaseManager) {
        databaseManager.removeItemFromArraylist(item, databaseManager);
    }
}
