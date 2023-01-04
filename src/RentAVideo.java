import javax.swing.*;
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
    private JTabbedPane tabbedpane;
    private JPanel RentalSystem;
    private JPanel AdministratorTab;
    private JTextField textField1;
    private JTextField textField2;
    private JList databaseList;
    private JList ShoppingCartList;
    private final RentalSystem rentalSystem = new RentalSystem();
    private final CartManager cartManager = new CartManager();

    private final DatabaseManager databaseManager = new DatabaseManager();
    private final DayOverview overview = new DayOverview(0, 0, 0, 0);
    private ArrayList<RentalItem> shoppingCart = rentalSystem.getCart(cartManager);
    private Customer klant1 = new Customer(0000001, "Jef", "Vermassen", "Kabouterstraat 8 2800 Mechelen", "2016-02-09", "0499/99/66/33", 0);


    public RentAVideo() {
        // Add item to shopping cart
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<RentalItem> selectedItems = databaseList.getSelectedValuesList();
                for (RentalItem item : selectedItems) {
                    try {
                        rentalSystem.addItemToCart(item, klant1, cartManager);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "An error occurred while adding your item to the cart. Please try again later.");
                    }
                }
                for (RentalItem item : selectedItems) {
                    createCartModel().addElement(item);
                    break;
                }
                System.out.println(rentalSystem.viewCart(cartManager));
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
                            throw new RuntimeException(ex);
                        } catch (ParseException ex) {
                            JOptionPane.showMessageDialog(null, "An error occurred while processing the order. Please try again later.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Your shopping cart is currently empty. Please add items to the cart before checking out.", "Empty Shopping Cart",1);
                }
                // Update the shopping cart list
                rentalSystem.clearShoppingCart();
                clearCartModel();
                ShoppingCartList.setModel(createCartModel());
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = textFieldSearch.getText();
                ArrayList<RentalItem> searchResults = rentalSystem.searchForMovieOrGameInCsv(searchText, databaseManager);
                System.out.println(rentalSystem.searchForMovieOrGameInCsv(searchText, databaseManager));
                System.out.println(searchResults);
                DefaultListModel model = new DefaultListModel<>();
                for (RentalItem searchResult : searchResults) {
                    model.addElement(searchResult);
                }
                databaseList.setModel(model);
            }
        });
    }

    // Add items from the Movie Array into the model
    public void createMovieModel(RentalSystem rentalSystem, DatabaseManager databaseManager) {
        ArrayList<RentalItem> rentalMovies = rentalSystem.getRentalMovies(databaseManager);
        DefaultListModel<RentalItem> model = new DefaultListModel<>();
        for (RentalItem movie : rentalMovies) {
            model.addElement(movie);
            databaseList.setModel(model);
        }
    }

    public void clearCartModel() {
        shoppingCart.clear();
    }

    // Add items from the shopping cart to the model
    public DefaultListModel<RentalItem> createCartModel() {

        System.out.println(shoppingCart);
        DefaultListModel<RentalItem> model = new DefaultListModel<>();
        for (RentalItem item : shoppingCart) {
            model.addElement(item);
        }
        ShoppingCartList.setModel(model);
        return model;
    }


    public void run(RentalSystem rentalSystem, DatabaseManager databaseManager) {
        frame = new JFrame();
        frame.setContentPane(mainPanel);
        createMovieModel(rentalSystem, databaseManager);
        frame.setTitle("RentAVideo");
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
