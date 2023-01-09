import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class TestRental {
    public static void main(String[] args) throws IOException, ParseException {
    RentAVideo rentAVideoPanel = new RentAVideo();
    //DatabaseManager databaseManager = new DatabaseManager();
    RentalSystem rentalSystem = new RentalSystem();
    //CartManager cartManager = new CartManager();
    //java.sql.Date currentSystemDate = new java.sql.Date(System.currentTimeMillis());
    //DayOverview dayOverview = new DayOverview(0,0,0,0);
    //Customer klant2 = new Customer(0,"Swa","Backeljau","Onbekend", "1962", "xxxxx",0);
    //Customer klant1 = new Customer(0000001,"Jef","Vermassen","Kabouterstraat 8 2800 Mechelen","2016-02-09","0499/99/66/33",0);
    //Game tombRaider = new Game("Tomb Raider",3.5,3,false,0,"Game","Playstation 1","Eidos Interactive","",0);
    //RentalItem rentalItem = new RentalItem("",0,0,false, 0, "","",0);
    //Movie theMatrix = new Movie("The Matrix",0,3, false,0, "Movie","1999","Action", "","", 1);
    //Movie theLionKing = new Movie("The Lion King",3.5,3,false, 0,"Movie","1994","Adventure", "", "", 1);
    //Movie SnowWhtie = new Movie("Snow White",0,0,false,0,"Movie","","","","",1);




    //rentalSystem.addCustomerToDatabase(klant2);

        //rentalSystem.loadMovies(databaseManager);
        //rentalSystem.loadGames(databaseManager);
        //System.out.println(rentalSystem.searchForMovieOrGameInCsv("The Matrix", databaseManager));
        //System.out.println(rentalSystem.searchForMovieOrGameInCsv("Tomb Raider", databaseManager));

    //***//
    //Print out loaded Arrays
        //System.out.println(rentalSystem.getRentalMovies(databaseManager));
        //System.out.println(rentalSystem.getRentalGames(databaseManager));
    //***//
    //Add member to Array
        //rentalSystem.addCustomer(klant1, dayOverview);
        //System.out.println(rentalSystem.getCustomers());
        //Add items to cart
        //rentalSystem.addItemToCart(theMatrix, klant1, cartManager);
        //rentalSystem.addItemToCart(theLionKing, klant1, cartManager);
        //System.out.println(cartManager.viewRentalCart());
        //rentalSystem.checkOut(klant1, cartManager, dayOverview, databaseManager, rentalItem);
        //rentalSystem.clearShoppingCart(cartManager);
        //System.out.println(cartManager.getItemCart());
        //System.out.println();
    /**
     *Adding movies/games to the CSV databases & Testing changes in stock value's
     */
        //rentalSystem.addItemToDatabase(theLionKing);
        //System.out.println(rentalSystem.getStockFromCSV(theLionKing));
        //rentalSystem.setStockMinusOne(theLionKing, databaseManager);
    /**
     * Testing Stock
     */
        //rentalSystem.returnItem(theMatrix, dayOverview, cartManager, currentSystemDate);
        //System.out.println(rentalSystem.getStockFromCSV(theMatrix) + "\n");
    //***//
        //System.out.println(currentSystemDate);
        //System.out.println();
        //dayOverview.createOverview(currentSystemDate);
        //dayOverview.viewOverview(currentSystemDate);
    /**
    * Rental System Testing
    */
        //rentalSystem.movieExists("The Matrix");
        //rentalSystem.setLateReturns(10, dayOverview);
        //rentalSystem.addCustomer(klant1, dayOverview);
        //rentalSystem.addItemToCart(theMatrix, klant1, cartManager);
        //rentalSystem.addItemToCart(theLionKing, klant1, cartManager);
        //rentalSystem.addItemToCart(tombRaider, klant1, cartManager);
        //rentalSystem.viewCart(cartManager);
        //rentalSystem.checkOut(klant1, cartManager, dayOverview, databaseManager, rentalItem);


        //System.out.println(rentalSystem.getCart(cartManager));
        //System.out.println(rentalSystem.viewCart(cartManager));

    /**
    Testing if we can return Items
     */
        //rentalSystem.returnItem(tombRaider,dayOverview, cartManager, currentSystemDate);
        //rentalSystem.returnItem(theLionKing, dayOverview, cartManager, currentSystemDate);
        //rentalSystem.returnItem(tombRaider, dayOverview, cartManager, currentSystemDate);
        //rentalSystem.returnItem(theLionKing, dayOverview, cartManager, currentSystemDate);

    /**
    DayOverview Class
     * Test this as a block*
     */
        //rentalSystem.addItemToCart(theLionKing, klant1, cartManager);
        //rentalSystem.checkOut(klant1, cartManager, dayOverview, databaseManager, rentalItem);
        //rentalSystem.createOverview(String.valueOf(currentSystemDate), dayOverview);
        //System.out.println(rentalSystem.viewDayOverview(String.valueOf(currentSystemDate)));
        //System.out.println();
        //System.out.println(rentalSystem.viewIncomeOverviewTest(currentSystemDate, dayOverview));

    /**
     * Remove overview from hashmap
     * Test this as a block*
     */
        //System.out.println();
        //rentalSystem.createOverview(String.valueOf(currentSystemDate), dayOverview);
        //rentalSystem.createIncomeOverviewTest(currentSystemDate, dayOverview);
        //rentalSystem.removeDayOverviewFromMap(String.valueOf(currentSystemDate), dayOverview);
        //rentalSystem.removeIncomeOverviewFromMap(String.valueOf(currentSystemDate), dayOverview);
        //System.out.println(rentalSystem.viewDayOverview(String.valueOf(currentSystemDate)));
        //System.out.println(rentalSystem.viewIncomeOverviewTest(currentSystemDate, dayOverview));

        //System.out.println(rentalSystem.getRatingFromCSV(tombRaider));

     /**
      * Test DaysSinceLastRented
      */
        //System.out.println();
        //System.out.println(rentalSystem.checkDaysSinceLastRented(theMatrix, databaseManager));
        //rentalSystem.removeItemFromDatabase(theMatrix, databaseManager);

    /**
    * Test Remove members (customers)
     * Test this as a block*
     */
        //System.out.println();
        //rentalSystem.addCustomer(klant1, dayOverview);
        //System.out.println(rentalSystem.getCustomers());
        //rentalSystem.removeCustomerFromArray(klant1);
        //System.out.println(rentalSystem.getCustomers());

    /**
    * Remove item (Movie or Game) from arraylist
     * Test this as a block*
     */
        //System.out.println(rentalSystem.getRentalMovies(databaseManager));
        //rentalSystem.removeItemFromArraylist(theLionKing, databaseManager);
        //System.out.println(rentalSystem.getRentalMovies(databaseManager));

    /**
    * Testing the Hashmap in dayOverview that keeps track of customer rental items
     */
        //rentalSystem.addItemToCart(theLionKing, klant1, cartManager);
        //rentalSystem.checkOut(klant1, cartManager, dayOverview, databaseManager, rentalItem);
        //rentalSystem.createRentalHistory(klant1, cartManager);
        //System.out.println(rentalSystem.viewRentalHistory(klant1));

    /**
    * Checking when the movie was rented out
     */
        //System.out.println(rentalSystem.viewRentedItemDate(theMatrix));

    /**
     * Run GUI.
      */
        rentAVideoPanel.run(rentalSystem);
    }
}
