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
    private JPanel rentalSystemPanel;
    private JPanel adminPanel;
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
    private JPanel newCustomerPanel;
    private JTextField textFieldNewCustomerAdres;
    private JTextField textFieldNewCustomerFirstName;
    private JTextField textFieldNewCustomerBirthdate;
    private JTextField textFieldNewCustomerLastName;
    private JTextField textFieldNewCustomerPhoneNumber;
    private JTextField textFieldNewCustomerEmail;
    private JButton submitNewCustomerButton;
    private JButton submitDateButton1;
    private JButton submitDateButton;
    private JTextField textFieldDayRapportDateInput;
    private JTextField textFieldEarningsRapportDateInput;
    private JButton searchCustomerListSearch;
    private JTextField textFieldCustomerListSearch;
    private JButton deleteCustomerButton;
    private JTextField TextFieldMovieTitle;
    private JTextField textFieldMovieRentalPrice;
    private JTextField textFieldMovieRentalDuration;
    private JTextField textFieldMovieStock;
    private JTextField textFieldMovieType;
    private JTextField textFieldMovieReleaseDate;
    private JTextField textFieldMovieDescription;
    private JTextField textFieldMovieEsrbRating;
    private JList earningsRapportList;
    private JButton addMovieButton;
    private JList customerListList;
    private JLabel labelFirstName;
    private JLabel labelLastName;
    private JLabel labelAdres;
    private JLabel labelBirthdate;
    private JLabel textfieldPhoneNumber;
    private JLabel labelEmail;
    private JLabel labelMovieRentalPrice;
    private JLabel labelMovieRentalDuration;
    private JLabel labelMovieStock;
    private JLabel labelMovieType;
    private JLabel labelMovieReleaseDate;
    private JLabel labelMovieDescription;
    private JLabel labelMovieEsrbRating;
    private JLabel labelMovieTitle;
    private JLabel labelGameTitle;
    private JLabel labelGameRentalPrice;
    private JLabel labelGameRentalDuration;
    private JLabel labelGameStock;
    private JLabel labelGameType;
    private JLabel labelGamePlatform;
    private JLabel labelGamePublisher;
    private JLabel labelGameEsrbRating;
    private JTextField textFieldGameTitle;
    private JTextField textFieldGameRentalPrice;
    private JTextField textFieldGameRentalDuration;
    private JTextField textFieldGameStock;
    private JTextField textFieldGameType;
    private JTextField textFieldGamePlatform;
    private JTextField textFieldGamePublisher;
    private JTextField textFieldGameEsrbRating;
    private JButton addGameButton;
    private JLabel labelDayRapport;
    private JLabel labelEarningsRapport;
    private JLabel labelCustomerList;
    private JPanel customerListPanel;
    private JScrollPane earningsScrollRapportPane;
    private JPanel moviePanel;
    private JPanel gamePanel;
    private JScrollPane customerListScrollPane;
    private JPanel overviewTicketPanel;
    private final RentalSystem rentalSystem = new RentalSystem();
    private final CartManager cartManager = new CartManager();
    private ArrayList<Customer> customers = new ArrayList<>();

    private final CustomerManager customerManager = new CustomerManager(customers);
    private final DatabaseManager databaseManager = new DatabaseManager();
    private final DayOverview overview = new DayOverview(0, 0, 0, 0);
    private ArrayList<RentalItem> shoppingCart = rentalSystem.getCart(cartManager);
    private ArrayList<RentalItem> searchResults = new ArrayList<>();

    private DefaultListModel<String> customerDefaultListModel = new DefaultListModel<String>();
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
        submitNewCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer customer = new Customer(0,textFieldNewCustomerFirstName.getText().toLowerCase(),
                        textFieldNewCustomerLastName.getText().toLowerCase(),
                        textFieldNewCustomerAdres.getText().toLowerCase(),
                        textFieldNewCustomerBirthdate.getText(),
                        textfieldPhoneNumber.getText().toLowerCase(),
                        0);
                customers.add(customer);
                rentalSystem.addCustomerToDatabase(customer);
            }
        });
        deleteCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customers.remove(customerListList.getSelectedIndex());
                customerDefaultListModel.remove(customerListList.getSelectedIndex());
                createAdminPanelCustomerModel();
                customerListList.setModel(customerDefaultListModel);
            }
        });
    }

    public void createAdminPanelCustomerModel() {
        for (Customer customer : customers) {
            String customerInformation = customer.getFirstName() + " " + customer.getName() + " " + customer.getPhoneNumber();
            customerDefaultListModel.addElement(customerInformation);
        }
        customerListList.setModel(customerDefaultListModel);
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
        customers.add(klant1);
        //mainPanel.setPreferredSize(new Dimension(1200,500));
        loadGames();
        loadMovies();
        createAdminPanelCustomerModel();
        frame = new JFrame();
        frame.setContentPane(mainPanel);
        createListModel(databaseManager);
        //createGameModel(rentalSystem, databaseManager);
        frame.setTitle("RentAVideo");
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
