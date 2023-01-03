import java.awt.*;
import java.io.IOException;

public class TestRental {
    public static void main(String[] args) throws IOException {
    Panel panel = new Panel();
    DatabaseManager databaseManager = new DatabaseManager();
    CartManager cartManager = new CartManager();
    java.sql.Date currentSystemDate = new java.sql.Date(System.currentTimeMillis());
    DayOverview dayOverview = new DayOverview(0,0,0,0);
    Customer klant1 = new Customer(0000001,"Jef","Vermassen","Kabouterstraat 8 2800 Mechelen","2016-02-09","0499/99/66/33",0);
    Game tombRaider = new Game("Tomb Raider",4,3,false,0,"Game","Playstation 1","Eidos Interactive","",9.6, 1);
    RentalItem rentalItem = new RentalItem("",0,0,false, 0, "","",0);
    Movie theMatrix = new Movie("The Matrix",3.5,3, false,0, "Movie","1999","Action", "","", 1);
    Movie theLionKing = new Movie("The Lion King",3.5,3,false, 0,"Movie","1994","Adventure", "", "", 1);
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
        //rentalSystem.addCustomer(klant1, dayOverview);
        //System.out.println(rentalSystem.getCustomers());
        //Add items to cart
        //rentalSystem.addItemToCart(theMatrix, klant1, cartManager);
        //rentalSystem.addItemToCart(theLionKing, klant1, cartManager);
        //System.out.println(cartManager.viewCart());
        //rentalSystem.checkOut(klant1, cartManager, dayOverview, databaseManager);
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

    //***/Rental System Testing/***//
        //rentalSystem.movieExists("The Matrix");
        //rentalSystem.setLateReturns(10, dayOverview);
        rentalSystem.addCustomer(klant1, dayOverview);
        //rentalSystem.addItemToCart(theMatrix, klant1, cartManager);
        rentalSystem.addItemToCart(theLionKing, klant1, cartManager);
        rentalSystem.addItemToCart(tombRaider, klant1, cartManager);
        //rentalSystem.viewCart(cartManager);
        rentalSystem.checkOut(klant1, cartManager, dayOverview, databaseManager);

        //System.out.println(rentalSystem.getCart(cartManager));
        //System.out.println(rentalSystem.viewCart(cartManager));

    //***/DayOverview Class/***//
        //rentalSystem.returnItem(tombRaider,dayOverview);
        //rentalSystem.returnItem(theLionKing, dayOverview);
        //rentalSystem.returnItem(tombRaider, dayOverview);
        //rentalSystem.returnItem(theLionKing, dayOverview);
        rentalSystem.createOverview(currentSystemDate, dayOverview);
        rentalSystem.viewDayOverview(currentSystemDate, dayOverview);
        rentalSystem.createIncomeOverview(currentSystemDate, dayOverview);
        rentalSystem.viewIncomeOverview(currentSystemDate, dayOverview);

        //System.out.println(rentalSystem.getRatingFromCSV(tombRaider));



    }
}