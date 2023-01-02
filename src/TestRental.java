import java.awt.*;
import java.io.IOException;

public class TestRental {
    public static void main(String[] args) throws IOException {
    Panel panel = new Panel();
    DatabaseLoader itemLoader = new DatabaseLoader();
    StockManager stockManager = new StockManager();
    ItemManager itemManager = new ItemManager();
    CartManager cartManager = new CartManager();
    java.sql.Date currentSystemDate = new java.sql.Date(System.currentTimeMillis());
    DayOverview dayOverview = new DayOverview(0,0,0,0);
    Customer jef = new Customer("Jef","Kabouterstraat 8 2800 Mechelen","19/02/1985","0499/99/66/33",0);
    Game tombRaider = new Game("Tomb Raider",4,3,false,0,"Game","Playstation 1","Eidos Interactive",9.6);
    RentalItem rentalItem = new RentalItem("",0,0,false, 0, "");
    Movie theMatrix = new Movie("The Matrix",3.5,3, false,0, "Movie","1999","Action", "");
    Movie theLionKing = new Movie("The Lion King",3.5,3,false, 0,"Movie","1994","Adventure", "");
    RentalSystem rentalSystem = new RentalSystem();
    //m.addGame(tombRaider);

    //***//
    //Load CSV file into Arrays
        itemLoader.loadMovies();
        itemLoader.loadGames();

    //***//
    //Print out loaded Arrays
        //System.out.println(itemLoader.getRentalMovies());
        //System.out.println(itemLoader.getRentalGames());
        //System.out.println();
    //***//
    //Add customer to Array
        //m.addCustomer(jef, dayOverview);
        //System.out.println(m.getCustomers());
        //Add items to cart
        //cartManager.addItemToCart(theMatrix);
        //cartManager.addItemToCart(theLionKing);
        //System.out.println(cartManager.viewCart());
        //checkOutManager.checkout(jef, cartManager, dayOverview, stockManager);
        //System.out.println(cartManager.getItemCart());
        //System.out.println();
    //***//
    //Adding movies/games to the CSV databases & Testing changes in stock value's
        //itemManager.addMovieToMoviesCSV(theLionKing);
        //System.out.println(stockManager.getStockFromCSV(theLionKing));
        //stockManager.setStockMinusOne(theLionKing);
        //stockManager.returnItem(theMatrix, dayOverview);
        //System.out.println(checkSetStock.getStockFromCSV(theMatrix) + "\n");
    //***//
        //System.out.println(date);
        //System.out.println();
        //dayOverview.createOverview(date);
        //dayOverview.viewOverview(date);

    //***/Rental System/***//
        //rentalSystem.movieExists("The Matrix");
        //rentalSystem.setLateReturns(10, dayOverview);
        rentalSystem.addCustomer(jef, dayOverview);
        //rentalSystem.addItemToCart(theMatrix, cartManager);
        //rentalSystem.addItemToCart(theLionKing, cartManager);
        rentalSystem.addItemToCart(tombRaider, cartManager);
        rentalSystem.checkOut(jef,cartManager,dayOverview,stockManager);


        //System.out.println(rentalSystem.getCart(cartManager));
        //System.out.println(rentalSystem.viewCart(cartManager));

        //rentalSystem.returnItem(theLionKing, dayOverview, stockManager);

        rentalSystem.createOverview(currentSystemDate, dayOverview);
        rentalSystem.viewOverview(currentSystemDate, dayOverview);

    }
}