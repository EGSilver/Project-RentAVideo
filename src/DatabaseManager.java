import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class DatabaseManager {
    private ArrayList<RentalItem> games = new ArrayList<>();
    private ArrayList<RentalItem> movies = new ArrayList<>();

    // Iterate through Movies & Games array and remove items from arraylist.

    // Loads movie data from a CSV file into an ArrayList of RentalItem objects
    public ArrayList<RentalItem> loadMovies() {
        File movieData = new File(("G:\\Git\\Project-RentAVideo\\data\\movies.csv"));
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
                int daySinceLastRented = Integer.parseInt(parts[10]);
                RentalItem r = new Movie(title, rentalPrice, rentalDuration, rentalStatus, stock, type, releaseDate, genre, discription, esrbRating, daySinceLastRented);
                movies.add(r);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return movies;
    }

    // Loads game data from a CSV file into an ArrayList of RentalItem objects
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
                int daySinceLastRented = Integer.parseInt(parts[10]);
                RentalItem r = new Game(title, rentalPrice, rentalDuration, rentalStatus, stock, type, platform, publisher, rating, criticRating, daySinceLastRented);
                games.add(r);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return games;
    }

    public void addCustomerToDatabase(Customer customer) {
        String filePath = "G:\\Git\\Project-RentAVideo\\data\\customers.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            //When or if the CSV file is empty, we write the customer in the file as the first customer.
            if (reader.readLine() == null) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                    String customerData = customer.getClientNumber() + "," + customer.getFirstName() + "," + customer.getName() + "," + customer.getBirthdate() + "," + customer.getAdres() + "," + customer.getPhoneNumber() + "," + customer.getYearsSubscribed();
                    writer.write(customerData);
                    writer.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                //Check if the customer already exists inside the CSV.
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts[1].equals(customer.getName()) && parts[2].equals(customer.getFirstName())) {
                        System.out.println("Customer: " + customer.getName() + customer.getFirstName() + " already exists inside the database");
                    } else {
                        //If the customer is not located inside the CSV file, write the customer to the CSV file.
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                            String customerData = customer.getFirstName() + "," + customer.getName() + "," + customer.getBirthdate() + "," + customer.getAdres() + "," + customer.getPhoneNumber() + "," + customer.getYearsSubscribed();
                            writer.write(customerData);
                            writer.newLine();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Determines if the movie is already present in the CSV file.
    public boolean movieTitleExists(String title) {
        try (BufferedReader reader = new BufferedReader(new FileReader("G:\\Git\\Project-RentAVideo\\data\\movies.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                // Check if the title of the movie in the file matches the given title
                if (parts[4].equals(title)) {
                    System.out.println(title + " already exists in the database");
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

    // Determines if the game is already present in the CSV file.
    public boolean GameTitleExists(String title) {
        try (BufferedReader reader = new BufferedReader(new FileReader("G:\\Git\\Project-RentAVideo\\data\\games.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                // Check if the title of the movie in the file matches the given title
                if (parts[4].equals(title)) {
                    System.out.println(title + " already exists in the database");
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

    public void addMovieToMoviesCsv(Movie movie) {
        String filePath = "G:\\Git\\Project-RentAVideo\\data\\movies.csv";
        if (!movieTitleExists(movie.getTitle())) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                String movieData = movie.getRentalPrice()
                        + "," + movie.getRentalDuration()
                        + "," + movie.isOutOfStock()
                        + "," + movie.getStock()
                        + "," + movie.getTitle()
                        + "," + movie.getReleaseDate()
                        + "," + movie.getGenre()
                        + "," + movie.getType()
                        + "," + movie.getDescription()
                        + "," + movie.getEsrbRating()
                        + "," + movie.getDaysSinceLastRented();
                writer.write(movieData);
                writer.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void addGameToGamesCsv(Game game) {
        String filePath = "G:\\Git\\Project-RentAVideo\\data\\games.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            String movieData = game.getRentalPrice() + "," + game.getRentalDuration() + "," + game.isOutOfStock() + "," + game.getStock() + "," + game.getTitle() + "," + game.getPublisher() + "," + game.getPlatform() + "," + game.getEsrbRating();
            writer.write(movieData);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getAgeRatingFromCsv(RentalItem item) throws IOException {
        String esrbRating = "";
        if (item.getType().equals("Movie")) {
            File gameData = new File(("G:\\Git\\Project-RentAVideo\\data\\movies.csv"));
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

    public int getStockFromCsv(RentalItem item) throws IOException {
        int stock = 0;
        if (item.getType().equals("Movie")) {
            File gameData = new File(("G:\\Git\\Project-RentAVideo\\data\\movies.csv"));
            try (BufferedReader reader = new BufferedReader(new FileReader(gameData))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (item.getTitle().equals(parts[4])) {
                        stock = Integer.parseInt(parts[3]);
                    }
                }
            }
        } else {
            File gameData = new File(("G:\\Git\\Project-RentAVideo\\data\\games.csv"));
            try (BufferedReader reader = new BufferedReader(new FileReader(gameData))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    stock = Integer.parseInt(parts[3]);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return stock;
    }

    // Function gets called when a customer checks out his cart, works with both types "Movies" & "Games"
    public void updateItemStockInCsv(RentalItem item) throws IOException {
        String filePath = "";
        if (item.getType().equals("Movie")) {
            filePath = "G:\\Git\\Project-RentAVideo\\data\\movies.csv";
        } else {
            filePath = "G:\\Git\\Project-RentAVideo\\data\\games.csv";
        }
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
            if (parts[4].equals(item.getTitle()) && getStockFromCsv(item) > 0) {
                parts[3] = String.valueOf(getStockFromCsv(item) - 1);
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

    // Takes care of being able to return an item to the "store" no matter the type "Movies" & "Games".
    public RentalItem returnItem(RentalItem item, DayOverview overview) throws IOException {
        String filepath = "";
        if (item.getType().equals("Movie")) {
            filepath = "G:\\Git\\Project-RentAVideo\\data\\movies.csv";
        } else {
            filepath = "G:\\Git\\Project-RentAVideo\\data\\games.csv";
        }
        ArrayList<String> arrayLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                arrayLines.add(line);
            }
        }
        for (int i = 0; i < arrayLines.size(); i++) {
            String line = arrayLines.get(i);
            String[] parts = line.split(",");
            if (parts[4].equals(item.getTitle())) {
                parts[3] = String.valueOf(getStockFromCsv(item) + 1);
                arrayLines.set(i, String.join(",", parts));
                System.out.println(item.getType() + ": " + parts[4] + " has been been returned");
                overview.setReturns(overview.getReturns() + 1);
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath, false))) {
            for (String line : arrayLines) {
                writer.write(line);
                writer.newLine();
            }
        }
        return item;
    }

    public int checkDaysSinceLastRented(RentalItem item) {
        int daysSinceLastRented = 0;
        if (item.getType().equals("Movie")) {
            for (RentalItem movie : movies) {
                daysSinceLastRented = movie.getDaysSinceLastRented();
            }
        } else {
            for (RentalItem game : games) {
                daysSinceLastRented = game.getDaysSinceLastRented();
            }
        }
        return daysSinceLastRented;
    }

    public void removeItemFromCsv(RentalItem item, DatabaseManager databaseManager) {
        if (item.getType().equals("Movie") && movieTitleExists(item.getTitle())) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader("G:\\Git\\Project-RentAVideo\\data\\movies.csv"));
                PrintWriter writer = new PrintWriter(new FileWriter("temp.csv"));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (!parts[4].equals(item.getTitle())) {
                        writer.println(line);
                    }
                }
                reader.close();
                writer.close();
                File original = new File("G:\\Git\\Project-RentAVideo\\data\\movies.csv");
                original.delete();
                File temp = new File("temp.csv");
                temp.renameTo(original);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (item.getType().equals("Game")) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader("G:\\Git\\Project-RentAVideo\\data\\games.csv"));
                PrintWriter writer = new PrintWriter(new FileWriter("temp.csv"));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (!parts[4].equals(item.getTitle())) {
                        writer.println(line);
                    }
                }
                reader.close();
                writer.close();

                File original = new File("G:\\Git\\Project-RentAVideo\\data\\games.csv");
                original.delete();

                File temp = new File("temp.csv");
                temp.renameTo(original);
            } catch (IOException e) {
                e.printStackTrace();
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

    public void removeItemFromArraylist(RentalItem item, DatabaseManager databaseManager) {
        String title = item.getTitle();
        Iterator<RentalItem> itMovie = databaseManager.movies.iterator();
        Iterator<RentalItem> itGame = databaseManager.games.iterator();
        if (item.getType().equals("Movie")) {
            while (itMovie.hasNext()) {
                RentalItem i = itMovie.next();
                if (i.getTitle().equals(title)) {
                    itMovie.remove();
                }
            }
        } else {
            while (itGame.hasNext()) {
                RentalItem i = itGame.next();
                if (i.getTitle().equals(title)) {
                    itGame.remove();
                }
            }
        }
    }

    @Override
    public String toString() {
        return "DatabaseManager{" + "games=" + games + ", movies=" + movies + '}';
    }
}
