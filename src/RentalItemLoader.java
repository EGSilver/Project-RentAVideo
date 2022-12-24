import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RentalItemLoader {
    private ArrayList<RentalItem> games = new ArrayList<>();
    private ArrayList<RentalItem> movies = new ArrayList<>();

    //load all movies from the csv file.
    public ArrayList<RentalItem> loadMovies() {
        File movieData = new File(("G:\\Git\\Project-RentAVideo\\data\\test.csv"));
        try (BufferedReader reader = new BufferedReader(new FileReader(movieData))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                double rentalPrice = Double.parseDouble(parts[0]);
                int rentalDuration = Integer.parseInt(parts[1]);
                boolean rentalStatus = Boolean.parseBoolean(parts[2]);
                int stock = Integer.parseInt(parts[3]);
                String title = parts[4];
                String releaseDate = parts[5];
                String genre = parts[6];
                String type = parts[7];
                String discription = parts[7];
                RentalItem r = new Movie(title, rentalPrice, rentalDuration, rentalStatus, stock, type, releaseDate, genre, discription);
                movies.add(r);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return movies;
    }

    //load all games from the csv file.
    public ArrayList<RentalItem> loadGames() {
        File gameData = new File(("G:\\Git\\Project-RentAVideo\\data\\games.csv"));
        try (BufferedReader reader = new BufferedReader(new FileReader(gameData))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                double rentalPrice = Double.parseDouble(parts[0]);
                int rentalDuration = Integer.parseInt(parts[1]);
                boolean rentalStatus = Boolean.parseBoolean(parts[2]);
                int stock = Integer.parseInt(parts[3]);
                String title = parts[4];
                String platform = parts[5];
                String publisher = parts[6];
                double rating = Double.parseDouble(parts[7]);
                String type = parts[8];
                RentalItem r = new Game(title, rentalPrice, rentalDuration, rentalStatus, stock, type, platform, publisher, rating);
                games.add(r);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return games;
    }

    //get a list of all movies loaded into the arraylist
    public ArrayList<RentalItem> getRentalMovies() {
        System.out.println("Here's a list of all movies: ");
        return movies;
    }

    //get a list of all games loaded into the arraylist
    public ArrayList<RentalItem> getRentalGames() {
        System.out.println("Here's a list of all games: ");
        return games;
    }

}
