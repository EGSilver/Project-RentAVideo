import java.awt.*;
import java.io.IOException;

public class TestRental {
    public static void main(String[] args) throws IOException {
    Panel panel = new Panel();
    Customer customer = new Customer("Jef","Kabouterstraat 8 2800 Mechelen","19/02/1985","0499/99/66/33",0);
    Game tombRaider = new Game(4.5,4,false, 6,"Tomb Raider","Playstation 1","Eidos Interactive",9.6);
    RentalItem rentalItem = new RentalItem(3.5,14,false, 5);
    Movie theMatrix = new Movie(3.5,3,false, 3,"The Matrix","1999","Action");
    Movie theLionKing = new Movie(3.5,3,false, 3,"The Lion King","1994","Adventure");
    RentalSystem m = new RentalSystem();
    //m.addGame(tombRaider);
    m.loadMovies();
    m.loadGames();
    //System.out.println(m.getRentalMovies());
    //System.out.println(m.getRentalGames());
    //m.addCustomer(customer);
    //System.out.println(m.getCustomers());
    m.addItemToCart(theMatrix);
    m.addItemToCart(theLionKing);
    m.checkout();
    //m.addMovieToMoviesCSV(theLionKing);
    //System.out.println(m.getRentalMovies());
    //m.getStock(theLionKing);
    //m.setStockMinusOne(theLionKing);
        //m.getStock(theMatrix);
        System.out.println(m.getStock(theMatrix));
    }
}