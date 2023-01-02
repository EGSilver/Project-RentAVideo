import java.io.*;

public class ItemManager {

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

}

