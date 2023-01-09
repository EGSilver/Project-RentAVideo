import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class DatabaseManager {
    private ArrayList<RentalItem> games = new ArrayList<>();
    private ArrayList<RentalItem> movies = new ArrayList<>();
    private ArrayList<RentalItem> searchItem = new ArrayList<>();

    // Iterate through Movies & Games array and remove items from arraylist.

    // Loads movie data from a CSV file into an ArrayList of RentalItem objects
    public ArrayList<RentalItem> loadMovies() {
        File movieData = new File((".\\data\\movies.csv"));
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
        File gameData = new File((".\\data\\games.csv"));
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
                int daySinceLastRented = Integer.parseInt(parts[9]);
                RentalItem r = new Game(title, rentalPrice, rentalDuration, rentalStatus, stock, type, platform, publisher, rating, daySinceLastRented);
                games.add(r);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return games;
    }

    public void addCustomerToDatabase(Customer customer) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(".\\data\\customers.csv"));
        String line;
        boolean customerExists = false;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts[0].equals(customer.getFirstName())) {
                customerExists = true;
                break;
            }
        }
        reader.close();
        if (!customerExists) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(".\\data\\customers.csv", true));
            writer.write(customer.getFirstName() +
                    "," + customer.getName() +
                    "," + customer.getBirthdate() +
                    "," + customer.getAddress() +
                    "," + customer.getPhoneNumber() +
                    "," + customer.getYearsSubscribed() +
                    "," + customer.getClientNumber());
            writer.newLine();
            writer.close();
        }
    }

    public void deleteCustomerFromCsv(Customer customer) {
        File filePath = new File(".\\data\\customers.csv");
        ArrayList<Customer> remainingCustomers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int clientNumber = Integer.parseInt(parts[6]);
                if (!(parts[0].equals(customer.getFirstName()) && parts[1].equals(customer.getName()))) {
                    int yearsSubscribed = Integer.parseInt(parts[5]);
                    Customer remainingCustomer = new Customer(clientNumber, parts[0], parts[1], parts[3], parts[2], parts[4], yearsSubscribed);
                    remainingCustomers.add(remainingCustomer);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Customer remainingCustomer : remainingCustomers) {
                writer.write(remainingCustomer.getFirstName() + "," + remainingCustomer.getName() + "," + remainingCustomer.getBirthdate() + "," + remainingCustomer.getAddress() + "," + remainingCustomer.getPhoneNumber() + "," + remainingCustomer.getYearsSubscribed() + "," + remainingCustomer.getClientNumber());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Customer> loadCustomersFromCsv() {
        File filepath = new File(".\\data\\customers.csv");
        ArrayList<Customer> customers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int yearsSubscribed = Integer.parseInt(parts[5]);
                int clientNumber = Integer.parseInt(parts[6]);
                Customer customer = new Customer(clientNumber, parts[0], parts[1], parts[3], parts[2], parts[4], yearsSubscribed);
                System.out.println(customers);
                customers.add(customer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    // Determines if the movie is already present in the CSV file.
    public boolean movieTitleExists(String title) {
        try (BufferedReader reader = new BufferedReader(new FileReader(".\\data\\movies.csv"))) {
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
        try (BufferedReader reader = new BufferedReader(new FileReader(".\\data\\games.csv"))) {
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
        String filePath = ".\\data\\movies.csv";
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
        String filePath = ".\\data\\games.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            String movieData = game.getRentalPrice()
                    + "," + game.getRentalDuration()
                    + "," + game.isOutOfStock()
                    + "," + game.getStock()
                    + "," + game.getTitle()
                    + "," + game.getPublisher()
                    + "," + game.getPlatform()
                    + "," + game.getEsrbRating()
                    + "," + game.getType()
                    + "," + game.getDaysSinceLastRented();
            writer.write(movieData);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getAgeRatingFromCsv(RentalItem item) throws IOException {
        String esrbRating = "";
        if (item.getType().equals("Movie")) {
            File gameData = new File((".\\data\\movies.csv"));
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
            File gameData = new File((".\\data\\games.csv"));
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
            File gameData = new File((".\\data\\movies.csv"));
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
            File gameData = new File((".\\data\\games.csv"));
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

    public void updateItemStockInCsv(RentalItem item) throws IOException {
        String filePath = "";
        if (item.getType().equals("Movie")) {
            filePath = ".\\data\\movies.csv";
        } else {
            filePath = ".\\data\\games.csv";
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

    public RentalItem returnItem(RentalItem item, DayOverview overview) throws IOException {
        String filepath = "";
        if (item.getType().equals("Movie")) {
            filepath = ".\\data\\movies.csv";
        } else {
            filepath = ".\\data\\games.csv";
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

    /**
     This function checks the number of days that have passed since the specified rental item was last rented.
     If the rental item is a movie, it searches the movies list for the item and returns the number of days since it was last rented.
     If the rental item is a game, it searches the games list for the item and returns the number of days since it was last rented.
     */
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
                BufferedReader reader = new BufferedReader(new FileReader(".\\data\\movies.csv"));
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
                File original = new File(".\\data\\movies.csv");
                original.delete();
                File temp = new File("temp.csv");
                temp.renameTo(original);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (item.getType().equals("Game")) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(".\\data\\games.csv"));
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

                File original = new File(".\\data\\games.csv");
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

    public ArrayList<RentalItem> searchForMovieOrGameInCsv(String title, DatabaseManager databaseManager) {
        for (int i = 0; i < databaseManager.movies.size(); i++) {
            if (databaseManager.movies.get(i).getTitle().toLowerCase().contains(title)) {
                searchItem.add(movies.get(i));
            }
        }
        for (int i = 0; i < games.size(); i++) {
            if (databaseManager.games.get(i).getTitle().toLowerCase().contains(title)) {
                searchItem.add(games.get(i));
            }
        }
        return searchItem;
    }

     /**
     Removes a rental item from the database based on its title.
     @param item The rental item to be removed.
     */
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
