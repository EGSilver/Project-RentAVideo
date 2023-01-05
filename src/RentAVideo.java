import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class RentAVideo {
    private JPanel mainPanel;
    private JFrame frame;
    private JButton searchButton;
    private JButton addToCartButton;
    private JButton checkoutButton;
    private JTextField textFieldSearch;
    private JTabbedPane RentScreen;
    private JPanel RentalSystem;
    private JPanel AdminPanel;
    private JList databaseList;
    private JList ShoppingCartList;
    private JList ticketList;
    private JTextField textFieldCustomerName;
    private JTextField textFieldYearsSubscribed;
    private JTextField textFieldCustomerNumber;
    private JLabel yearsSubscribedLabel;
    private JLabel customerNumberLabel;
    private JLabel customerNameLabel;
    private JButton enterButton;
    private final RentalSystem rentalSystem = new RentalSystem();
    private final CartManager cartManager = new CartManager();

    private final DatabaseManager databaseManager = new DatabaseManager();
    private final DayOverview overview = new DayOverview(0, 0, 0, 0);
    private ArrayList<RentalItem> shoppingCart = rentalSystem.getCart(cartManager);
    private ArrayList<RentalItem> searchResults = new ArrayList<>();
    private DefaultListModel<RentalItem> defaultModel = new DefaultListModel<>();
    private Customer klant1 = new Customer(0000001, "Jef", "Vermassen", "Kabouterstraat 8 2800 Mechelen", "2016-02-09", "0499/99/66/33", 0);


    public RentAVideo() {
        textFieldCustomerNumber.setEditable(false);
        textFieldYearsSubscribed.setEditable(false);
        // Add item to shopping cart
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<RentalItem> selectedItems = databaseList.getSelectedValuesList();
                for (RentalItem item : selectedItems) {
                    try {
                        rentalSystem.addItemToCart(item, klant1, cartManager);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(mainPanel, "An error occurred while adding your item to the cart. Please try again later.");
                    }
                }
                for (RentalItem item : selectedItems) {
                    createCartModel().addElement(item);
                }
                // Update the shopping cart list
                ShoppingCartList.setModel(createCartModel());
            }
        });
        // Checkout
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<RentalItem> shoppingCart = rentalSystem.getCart(cartManager);
                if (!(shoppingCart.size() == 0)) {
                    for (RentalItem item : shoppingCart) {
                        try {
                            rentalSystem.checkOut(klant1, cartManager, overview, databaseManager, item);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(mainPanel,"An error occurred while processing the order. Please try again later.");
                        } catch (ParseException ex) {
                            JOptionPane.showMessageDialog(mainPanel, "An error occurred while processing the order. Please try again later.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Your shopping cart is currently empty. Please add items to the cart before checking out.", "Empty Shopping Cart",1);
                }
                // Update the shopping cart list
                rentalSystem.clearShoppingCart();
                clearCartModel();
                ShoppingCartList.setModel(createCartModel());
            }
        });
        // Search for specific item
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel<RentalItem> model = new DefaultListModel<>();
                String searchText = textFieldSearch.getText();
                if (!searchText.equals("")) {
                    clearSearchResultScreen();
                    searchResults = rentalSystem.searchForMovieOrGameInCsv(searchText, databaseManager);
                    for (RentalItem searchResult : searchResults) {
                        model.addElement(searchResult);
                    }
                    databaseList.setModel(model);
                } else {
                    JOptionPane.showMessageDialog(mainPanel,"Please enter a Movie or Game title.");
                    databaseList.setModel(defaultModel);
                }
            }
        });
    }

    // Add items from the Movie/Games Array into the model
    public void createListModel(DatabaseManager databaseManager) {
        ArrayList<RentalItem> rentalMovies = databaseManager.getRentalMovies();
        ArrayList<RentalItem> rentalGames = databaseManager.getRentalGames();
        for (RentalItem movie : rentalMovies) {
            defaultModel.addElement(movie);
        }
        for (RentalItem game : rentalGames) {
            defaultModel.addElement(game);
        }
        databaseList.setModel(defaultModel);
    }

    public void clearCartModel() {
        shoppingCart.clear();
    }

    public void clearSearchResultScreen() {
        searchResults.clear();
    }

    // Add items from the shopping cart to the model
    public DefaultListModel<RentalItem> createCartModel() {
        DefaultListModel<RentalItem> model = new DefaultListModel<>();
        for (RentalItem item : shoppingCart) {
            model.addElement(item);
        }
        ShoppingCartList.setModel(model);
        return model;
    }

    public void loadMovies() {
        rentalSystem.loadMovies(databaseManager);
    }

    public void loadGames() {
        rentalSystem.loadGames(databaseManager);
    }


    public void run(RentalSystem rentalSystem) {
        mainPanel.setPreferredSize(new Dimension(700,600));
        loadGames();
        loadMovies();
        frame = new JFrame();
        frame.setContentPane(mainPanel);
        createListModel(databaseManager);
        //createGameModel(rentalSystem, databaseManager);
        frame.setTitle("RentAVideo");
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
