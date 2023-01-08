import java.io.*;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;

public class RentalSystem {
    private ArrayList<Customer> customers;
    private CustomerManager customerManager;
    private CartManager cartManager;
    private DayOverview dayOverview;
    private DatabaseManager databaseManager;

    public RentalSystem() {
        this.customers = new ArrayList<>();
        this.databaseManager = new DatabaseManager();
        this.cartManager = new CartManager();
        this.dayOverview = new DayOverview(0, 0, 0, 0);
        this.customerManager = new CustomerManager(customers);
    }

    public String returnItem(RentalItem item, DayOverview overview, CartManager cartManager) throws IOException, ParseException {
        databaseManager.returnItem(item, overview);
        return cartManager.returnItemAndCalculateFine(item, overview);
    }

    public void testHashMap() {
        dayOverview.testHashMap();
    }

    public void loadMovies(DatabaseManager databaseManager) {
        databaseManager.loadMovies();
    }

    public void loadGames(DatabaseManager databaseManager) {
        databaseManager.loadGames();
    }

    public String checkOut(Customer customer, CartManager cartManager, DayOverview overview, DatabaseManager databaseManager, RentalItem item) throws IOException, ParseException {
        saveRentalDate(item);
        return cartManager.checkout(customer, cartManager, overview, databaseManager);
    }

    public void addCustomer(Customer customer, DayOverview overview) throws IOException {
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

    public DayOverview createOverview(String date, DayOverview overview) {
        return dayOverview.createOverview(date, overview);
    }

    public Object viewDayOverview(String date) {
        return dayOverview.viewDayOverview(date);

    }

    public void createIncomeOverview(String date) {
        dayOverview.createIncomeOverview(date);
    }

    public Object viewIncomeOverview(String date) {
        return dayOverview.viewIncomeOverview(date);

    }

    public String addItemToCart(RentalItem item, Customer customer, CartManager cart) throws IOException {
        return cart.addItemToCart(item, customer);
    }

    public String viewCart(CartManager cart) {
        return cart.viewCart();
    }

    public ArrayList<RentalItem> getCart(CartManager cart) {
        return cart.getItemCart();
    }

    public void clearShoppingCart() {
        cartManager.clearShoppingCart();
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

    public void addCustomerToDatabase(Customer customer) throws IOException {
        databaseManager.addCustomerToDatabase(customer);
    }

    public void deleteCustomerFromCsv(Customer customer) {
        databaseManager.deleteCustomerFromCsv(customer);
    }

    public void removeCustomerFromArray(Customer customer) {
        customerManager.removeCustomer(customer);
    }

    public void removeIncomeOverviewFromMap(String date, DayOverview dayOverview) {
        dayOverview.removeIncomeOverviewFromMap(date);
    }

    public void removeDayOverviewFromMap(String date, DayOverview dayOverview) {
        dayOverview.removeDayOverviewFromMap(date);
    }

    public void removeItemFromArraylist(RentalItem item, DatabaseManager databaseManager) {
        databaseManager.removeItemFromArraylist(item, databaseManager);
    }

    public void createRentalHistory(Customer customer, CartManager cartManager) {
        customerManager.createRentalHistory(customer, cartManager);
    }

    public ArrayList<RentalItem> viewRentalHistory(Customer customer) {
        return customerManager.viewRentalHistory(customer);
    }

    public ArrayList<Customer> loadCustomersFromCsv() {
        return databaseManager.loadCustomersFromCsv();
    }

    public void saveRentalDate(RentalItem item) {
        customerManager.saveRentalDateInMap(item, cartManager.getDate());
    }

    public String viewRentedItemDate(RentalItem item) {
        return customerManager.viewRentedItemDate(item);
    }

    public ArrayList<RentalItem> searchForMovieOrGameInCsv(String title, DatabaseManager databaseManager) {
        return databaseManager.searchForMovieOrGameInCsv(title, databaseManager);
    }
    public int generateClientNumber() throws IOException {
      return customerManager.generateClientNumber();
    }

    public double getIncome() {
        return dayOverview.getIncome();
    }

    public String viewIncomeOverviewTest(Date currentSystemDate, DayOverview dayOverview) {
        return dayOverview.viewIncomeOverviewTest(currentSystemDate, dayOverview);
    }

    public void createIncomeOverviewTest(Date currentSystemDate, DayOverview dayOverview) {
        dayOverview.createIncomeOverviewTest(currentSystemDate, dayOverview);
    }
}
