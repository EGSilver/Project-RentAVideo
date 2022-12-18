import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RentalItemLoader {
    //load all movies from the csv file.
    public ArrayList<RentalItem> loadMovies() {
        ArrayList<RentalItem> movies = new ArrayList<>();
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
                RentalItem r = new Movie(rentalPrice, rentalDuration, rentalStatus, stock, title, releaseDate, genre);
                movies.add(r);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return movies;
    }

    //load all games from the csv file.
    public ArrayList<RentalItem> loadGames() {
        ArrayList<RentalItem> games = new ArrayList<>();
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
                RentalItem r = new Game(rentalPrice, rentalDuration, rentalStatus, stock, title, platform, publisher, rating);
                games.add(r);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return games;
    }
}
