import java.io.*;
import java.util.ArrayList;

public class DatabaseManager {
    private ArrayList<RentalItem> games = new ArrayList<>();
    private ArrayList<RentalItem> movies = new ArrayList<>();
    
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

    public boolean movieExists(String title) {
        try (BufferedReader reader = new BufferedReader(new FileReader("G:\\Git\\Project-RentAVideo\\data\\test.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                // Check if the title of the movie in the file matches the given title
                if (parts[4].equals(title)) {
                    System.out.println(title + " exists in the database");
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            // The file doesn't exist yet, so the movie definitely doesn't exist
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void addMovieToMoviesCSV(Movie movie) {
        String filePath = "G:\\Git\\Project-RentAVideo\\data\\test.csv";
        if (!movieExists(movie.getTitle())) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                String movieData = movie.getRentalPrice() + ","
                        + movie.getRentalDuration() + ","
                        + movie.isOutOfStock() + ","
                        + movie.getStock() + ","
                        + movie.getTitle() + ","
                        + movie.getReleaseDate() + ","
                        + movie.getGenre();
                writer.write(movieData);
                writer.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void addGameToGamesCSV(Game game) {
        String filePath = "G:\\Git\\Project-RentAVideo\\data\\games.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            String movieData = game.getRentalPrice() + ","
                    + game.getRentalDuration() + ","
                    + game.isOutOfStock() + ","
                    + game.getStock() + ","
                    + game.getTitle() + ","
                    + game.getPublisher() + ","
                    + game.getPlatform() + ","
                    + game.getEsrbRating();
            writer.write(movieData);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public String getRatinFromCSV(RentalItem item) throws IOException {
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

    public int getStockFromCSV(RentalItem item) throws IOException {
        int stock = 0;
        if (item.getType().equals("Movie")) {
            File gameData = new File(("G:\\Git\\Project-RentAVideo\\data\\test.csv"));
            try (BufferedReader reader = new BufferedReader(new FileReader(gameData))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (item.getTitle().equals(parts[4])) {
                        stock = Integer.parseInt(parts[3]);
                    }
                }
            }
            //System.out.print("Amount of " + ((Movie) item).getTitle() + " in stock: ");
        } else {
            File gameData = new File(("G:\\Git\\Project-RentAVideo\\data\\games.csv"));
            try (BufferedReader reader = new BufferedReader(new FileReader(gameData))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    stock = Integer.parseInt(parts[3]);
                }
                //System.out.print("Amount of " + ((Game) item).getTitle() + " in stock: ");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return stock;
    }
    //function gets called when a customer checks out a movie
    public void setMovieStockMinusOne(RentalItem item) throws IOException {
        String filePath = "G:\\Git\\Project-RentAVideo\\data\\test.csv";
        ArrayList<String> arrayLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                arrayLines.add(line);
            }
        }
        for (int i = 0; i < arrayLines.size(); i++) {
            String line = arrayLines.get(i);
            String[] parts = line.split(",");
            if (item.getType().equals("Movie") && parts[4].equals(item.getTitle()) && getStockFromCSV(item) > 0) {
                parts[3] = String.valueOf(getStockFromCSV(item)-1);
                arrayLines.set(i, String.join(",", parts));
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (String line : arrayLines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    public void setGameStockMinusOne(RentalItem item) throws IOException {
        String  filePath = "G:\\Git\\Project-RentAVideo\\data\\games.csv";
        ArrayList<String> arrayLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                arrayLines.add(line);
            }
        }
        for (int i = 0; i < arrayLines.size(); i++) {
            String line = arrayLines.get(i);
            String[] parts = line.split(",");
            if (item.getType().equals("Game") && parts[4].equals(item.getTitle()) && getStockFromCSV(item) > 0) {
                parts[3] = String.valueOf(getStockFromCSV(item)-1);
                arrayLines.set(i, String.join(",", parts));
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (String line : arrayLines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    //customer can return a rented item, updates the stock field in the CSV file
    public void returnItem(RentalItem item, DayOverview overview) throws IOException {
        String filePath = "G:\\Git\\Project-RentAVideo\\data\\test.csv";
        ArrayList<String> arrayLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                arrayLines.add(line);
            }
        }
        for (int i = 0; i < arrayLines.size(); i++) {
            String line = arrayLines.get(i);
            String[] parts = line.split(",");
            if (parts[7].equals(item.getType()) && parts[4].equals(item.getTitle()) && getStockFromCSV(item) > 0
                    || item.getType().equals("Game") && parts[4].equals(item.getTitle()) && getStockFromCSV(item) > 0) {
                parts[3] = String.valueOf(getStockFromCSV(item)+1);
                arrayLines.set(i, String.join(",", parts));
                System.out.println("Movie: " + parts[4] + " has been been returned");
                overview.setReturns(overview.getReturns()+1);
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (String line : arrayLines) {
                writer.write(line);
                writer.newLine();
            }
        }
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
