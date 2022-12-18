import java.awt.*;
import java.io.IOException;

public class TestRental {
    public static void main(String[] args) throws IOException {
    Panel panel = new Panel();
    RentalSystem m = new RentalSystem();
    RentalItemLoader itemLoader = new RentalItemLoader();
    CheckSetStock checkSetStock = new CheckSetStock();
    ItemManager itemManager = new ItemManager();
    java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
    DayOverview dayOverview = new DayOverview(0,0,0,0);
    Customer jef = new Customer("Jef","Kabouterstraat 8 2800 Mechelen","19/02/1985","0499/99/66/33",0);
    Game tombRaider = new Game(4.5,4,false, 0,"Tomb Raider","Playstation 1","Eidos Interactive",9.6);
    RentalItem rentalItem = new RentalItem(3.5,14,false, 5);
    Movie theMatrix = new Movie(3.5,3,false, 0,"The Matrix","1999","Action");
    Movie theLionKing = new Movie(3.5,3,false, 0,"The Lion King","1994","Adventure");

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
        m.addCustomer(jef);
        System.out.println(m.getCustomers());
        //Add items to cart
        m.addItemToCart(theMatrix);
        m.addItemToCart(theLionKing);
        System.out.println(m.viewCart());
        m.checkout(jef);
        //System.out.println();
    //***//
    //Adding movies/games to the CSV databases & Testing changes in stock value's
        itemManager.addMovieToMoviesCSV(theLionKing);
        System.out.println(checkSetStock.getStockFromCSV(theLionKing));
        checkSetStock.setStockMinusOne(theLionKing);
        checkSetStock.returnItem(theMatrix);
        //System.out.println(checkSetStock.getStockFromCSV(theMatrix) + "\n");
    //***//
        //System.out.println(date);
        System.out.println();
        dayOverview.createOverview(date);
        m.viewOverview(date);

    }
}