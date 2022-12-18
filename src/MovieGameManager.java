public class MovieGameManager {
    RentalItemLoader loader = new RentalItemLoader();

    //load all games from the csv file.
    public void loadGames() {
        loader.loadGames();
    }

    //load all movies from the csv file.
    public void loadMovies() {
        loader.loadMovies();
    }

}
