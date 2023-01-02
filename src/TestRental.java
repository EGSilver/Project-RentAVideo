import java.awt.*;
import java.io.IOException;

public class TestRental {
    public static void main(String[] args) throws IOException {
    Panel panel = new Panel();
    DatabaseManager databaseManager = new DatabaseManager();
    CartManager cartManager = new CartManager();
    java.sql.Date currentSystemDate = new java.sql.Date(System.currentTimeMillis());
    DayOverview dayOverview = new DayOverview(0,0,0,0);
    Customer jef = new Customer("Jef","Kabouterstraat 8 2800 Mechelen","2016-02-09","0499/99/66/33",0);
    Game tombRaider = new Game("Tomb Raider",4,3,false,0,"Game","Playstation 1","Eidos Interactive","",9.6);
    RentalItem rentalItem = new RentalItem("",0,0,false, 0, "","");
    Movie theMatrix = new Movie("The Matrix",3.5,3, false,0, "Movie","1999","Action", "","");
    Movie theLionKing = new Movie("The Lion King",3.5,3,false, 0,"Movie","1994","Adventure", "", "");
    RentalSystem rentalSystem = new RentalSystem();
    //m.addGame(tombRaider);

    //***//
    //Load CSV file into Arrays
        databaseManager.loadMovies();
        databaseManager.loadGames();

    //***//
    //Print out loaded Arrays
        //System.out.println(rentalSystem.getRentalMovies(databaseManager));
        //System.out.println(rentalSystem.getRentalGames(databaseManager));
        //System.out.println();
    //***//
    //Add customer to Array
        //rentalSystem.addCustomer(jef, dayOverview);
        //System.out.println(rentalSystem.getCustomers());
        //Add items to cart
        //rentalSystem.addItemToCart(theMatrix, jef, cartManager);
        //rentalSystem.addItemToCart(theLionKing, jef, cartManager);
        //System.out.println(cartManager.viewCart());
        //rentalSystem.checkOut(jef, cartManager, dayOverview, databaseManager);
        //System.out.println(cartManager.getItemCart());
        //System.out.println();
    //***//
    //Adding movies/games to the CSV databases & Testing changes in stock value's
        //rentalSystem.addToDatabse(theLionKing);
        //System.out.println(rentalSystem.getStockFromCSV(theLionKing));
        //rentalSystem.setStockMinusOne(theLionKing, databaseManager);
        //rentalSystem.returnItem(theMatrix, dayOverview);
        //System.out.println(rentalSystem.getStockFromCSV(theMatrix) + "\n");
    //***//
        //System.out.println(currentSystemDate);
        //System.out.println();
        //dayOverview.createOverview(currentSystemDate);
        //dayOverview.viewOverview(currentSystemDate);

    //***/Rental System/***//
        //rentalSystem.movieExists("The Matrix");
        //rentalSystem.setLateReturns(10, dayOverview);
        rentalSystem.addCustomer(jef, dayOverview);
        rentalSystem.addItemToCart(theMatrix, jef, cartManager);
        //rentalSystem.addItemToCart(theLionKing, cartManager);
        rentalSystem.addItemToCart(tombRaider, jef, cartManager);
        rentalSystem.checkOut(jef, cartManager, dayOverview, databaseManager);


        //System.out.println(rentalSystem.getCart(cartManager));
        //System.out.println(rentalSystem.viewCart(cartManager));
        //rentalSystem.returnItem(tombRaider,dayOverview);
        //rentalSystem.returnItem(theLionKing, dayOverview);

        rentalSystem.createOverview(currentSystemDate, dayOverview);
        rentalSystem.viewOverview(currentSystemDate, dayOverview);

        //System.out.println(rentalSystem.getRatingFromCSV(tombRaider));



    }
}