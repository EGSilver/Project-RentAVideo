import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DatabaseLoader {
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
                String discription = parts[8];
                String esrbRating = parts[9];
                RentalItem r = new Movie(title, rentalPrice, rentalDuration, rentalStatus, stock, type, releaseDate, genre, discription, esrbRating);
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
                String rating = parts[7];
                String type = parts[8];
                double criticRating = Double.parseDouble(parts[9]);
                RentalItem r = new Game(title, rentalPrice, rentalDuration, rentalStatus, stock, type, platform, publisher, rating, criticRating);
                games.add(r);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return games;
    }

    public String getRatingfromCSV(RentalItem item) throws IOException {
        String esrbRating = "";
        if (item.getType().equals("Movie")) {
            File gameData = new File(("G:\\Git\\Project-RentAVideo\\data\\test.csv"));
            try (BufferedReader reader = new BufferedReader(new FileReader(gameData))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (item.getTitle().equals(parts[4])) {
                        esrbRating = (parts[9]);
                    }
                }
            }
        } else {
            File gameData = new File(("G:\\Git\\Project-RentAVideo\\data\\games.csv"));
            try (BufferedReader reader = new BufferedReader(new FileReader(gameData))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    esrbRating = (parts[7]);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return esrbRating;
    }

    public ArrayList<RentalItem> getRentalMovies() {
        System.out.println("Here's a list of all movies: ");
        return movies;
    }

    public ArrayList<RentalItem> getRentalGames() {
        System.out.println("Here's a list of all games: ");
        return games;
    }

}
