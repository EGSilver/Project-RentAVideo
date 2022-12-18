import java.awt.image.AreaAveragingScaleFilter;
import java.io.*;
import java.util.ArrayList;

public class RentalSystem {
    private ArrayList<RentalItem> movies;
    private ArrayList<RentalItem> games;
    private ArrayList<Customer> customers;
    private ArrayList<RentalItem> itemCart;
    private double totalPrice;

    public RentalSystem() {
        this.movies = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.games = new ArrayList<>();
        this.itemCart = new ArrayList<>();
        this.totalPrice = totalPrice;
    }

    //load all movies from the csv file.
    public void loadMovies() {
        File movieData = new File(("G:\\Git\\Project-RentAVideo\\data\\test.csv"));
        try (BufferedReader reader = new BufferedReader(new FileReader(movieData))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                double rentalPrice = Double.parseDouble(parts[0]);
                int rentalDuration = Integer.parseInt(parts[1]);
                boolean rentalStatus = Boolean.parseBoolean(parts[2]);
                String title = parts[3];
                String releaseDate = parts[4];
                String genre = parts[5];
                RentalItem r = new Movie(rentalPrice, rentalDuration, rentalStatus, title, releaseDate, genre);
                movies.add(r);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addGame(Game x) {
        games.add(x);
    }

    //load all games from the csv file.
    public void loadGames() {
        File gameData = new File(("G:\\Git\\Project-RentAVideo\\data\\games.csv"));
        try (BufferedReader reader = new BufferedReader(new FileReader(gameData))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                double rentalPrice = Double.parseDouble(parts[0]);
                int rentalDuration = Integer.parseInt(parts[1]);
                boolean rentalStatus = Boolean.parseBoolean(parts[2]);
                String title = parts[3];
                String platform = parts[4];
                String publisher = parts[5];
                double rating = Double.parseDouble(parts[6]);
                RentalItem r = new Game(rentalPrice, rentalDuration, rentalStatus, title, platform, publisher, rating);
                games.add(r);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<RentalItem> addItemToCart(RentalItem item) {
        itemCart.add(item);
        //System.out.println(item.getRentalPrice());
        return itemCart;
    }

    public void checkout() {
        String s = "";
        totalPrice = 0;
        for (RentalItem item : itemCart) {
            if (item instanceof Movie) {
                Movie movie = (Movie) item;
                item.setRentalStatus(true);
                s += movie.getTitle() + "\n";
            } else if (item instanceof Game) {
                Game game = (Game) item;
                item.setRentalStatus(true);
                s += game.getTitle() + "\n";
            }
        }
        for (RentalItem c : itemCart) {
            totalPrice += c.getRentalPrice();
        }
        s += "\nTotal price to pay: " + totalPrice;
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
        customers.add(customer);
    }

    public ArrayList<Customer> getCustomers() {
        System.out.println("Here's a list of all customers: ");
        return customers;
    }

    public boolean movieExists(String title) {
        try (BufferedReader reader = new BufferedReader(new FileReader("movies.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                // Check if the title of the movie in the file matches the given title
                if (parts[3].equals(title)) {
                    System.out.println("HALLONEGERO");
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
        if (movieExists(movie.getTitle())) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                String movieData = movie.getRentalPrice() + ","
                        + movie.getRentalDuration() + ","
                        + movie.isRentalStatus() + ","
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
                    + game.isRentalStatus() + ","
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

}
