import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class RentalSystem {
    private ArrayList<RentalItem> movies;
    private ArrayList<RentalItem> games;
    private ArrayList<Customer> customers;
    private ArrayList<RentalItem> itemCart;

    private double totalPrice;
    private final double TAX = 1.21;
    DayOverview overview = new DayOverview(0,0,0,0);

    //TODO Hashmap with day overview, what has been rented / how much money did we make

    public RentalSystem() {
        this.movies = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.games = new ArrayList<>();
        this.itemCart = new ArrayList<>();
        this.totalPrice = totalPrice;
    }

    public void addGame(Game x) {
        games.add(x);
    }

    public ArrayList<RentalItem> addItemToCart(RentalItem item) throws IOException {
        if (getStockFromCSV(item) <= 0) {
            System.out.println("This item is out of stock");
        } else {
            itemCart.add(item);
            //System.out.println(item.getRentalPrice());
        }
        return itemCart;
    }

    public String viewCart() {
        String s = "";
        for (RentalItem item : itemCart) {
            if (item instanceof Movie) {
                 s += ((Movie) item).getTitle() + "\n";
            } else if (item instanceof Game) {
                 s += ((Game) item).getTitle() + "\n";
            }
        }
        return s;
    }

    public void checkout(Customer customer) throws IOException {
        String s = customer.getName() + "\n";
        totalPrice = 0;
        for (RentalItem item : itemCart) {
            if (item instanceof Movie movie) {
                s += movie.getTitle() + "\n";
                setStockMinusOne(item);
                overview.setRentals(overview.getRentals()+1);
                if (getStockFromCSV(item) <= 1) {
                    item.setOutOfStock(true);
                }
            } else if (item instanceof Game game) {
                setStockMinusOne(item);
                overview.setRentals(overview.getRentals()+1);
                s += game.getTitle() + "\n";
                if (getStockFromCSV(item) <= 1) {
                    item.setOutOfStock(true);
                }
            }
        }
        for (RentalItem c : itemCart) {
            totalPrice += c.getRentalPrice();
        }
        s += "\nTotal price to pay: " + (totalPrice * TAX);
        System.out.println(s);
    }

    public ArrayList<RentalItem> getRentalMovies() {
        System.out.println("Here's a list of all movies: ");
        return movies;
    }

    public ArrayList<RentalItem> getRentalGames() {
        System.out.println("Here's a list of all games: ");
        return games;
    }

    public void addCustomer(Customer customer) {
        overview.setNewMembers(+1);
        customers.add(customer);
    }

    public ArrayList<Customer> getCustomers() {
        System.out.println("Here's a list of all customers: ");
        return customers;
    }

    public boolean movieExists(String title) {
        try (BufferedReader reader = new BufferedReader(new FileReader("G:\\Git\\Project-RentAVideo\\data\\test.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                // Check if the title of the movie in the file matches the given title
                if (parts[4].equals(title)) {
                    System.out.println(title + " already in the database");
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

    public int getStockFromCSV(RentalItem item) throws IOException {
        int stock = 0;
        if (item instanceof Movie) {
            File gameData = new File(("G:\\Git\\Project-RentAVideo\\data\\test.csv"));
            try (BufferedReader reader = new BufferedReader(new FileReader(gameData))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (((Movie) item).getTitle().equals(parts[4])) {
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

    public void setStockMinusOne(RentalItem item) throws IOException {
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
            if (item instanceof Movie && parts[4].equals(((Movie) item).getTitle()) && getStockFromCSV(item) > 0
                    || item instanceof Game && parts[4].equals(((Game) item).getTitle()) && getStockFromCSV(item) > 0) {
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

    public void returnItem(RentalItem item) throws IOException {
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
            if (item instanceof Movie && parts[4].equals(((Movie) item).getTitle()) && getStockFromCSV(item) > 0
                    || item instanceof Game && parts[4].equals(((Game) item).getTitle()) && getStockFromCSV(item) > 0) {
                parts[3] = String.valueOf(getStockFromCSV(item)+1);
                arrayLines.set(i, String.join(",", parts));
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
                    + game.getRating();
            writer.write(movieData);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void viewOverview(Date date) {
        overview.createOverview(date);
        overview.viewOverview(date);
    }

}
