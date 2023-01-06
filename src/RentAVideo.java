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
    private JLabel labelPhoneNumber;
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
    private JTextArea textAreaTicketResult;
    private JTextField textFieldTicketResult;
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
    private int clientNumber = 100000;
    private Customer klant1 = new Customer(0000001, "Jef", "Vermassen", "Kabouterstraat 8 2800 Mechelen", "2016-02-09", "0499/99/66/33", 0);


    public RentAVideo() {
        textFieldCustomerNumber.setEditable(false);
        textFieldYearsSubscribed.setEditable(false);
        textAreaTicketResult.setEditable(false);
        databaseList.setEnabled(false);
        /**
         * This method is called when the "Add to Cart" button is clicked.
         * If the customer has not entered their name, an error message is displayed.
         * Otherwise, the selected items are added to the cart and the cart list is updated.
         *
         * @throws IOException  if an error occurs while adding the items to the cart
         */
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String exampleText = "Enter your first and last name";
                if (textFieldCustomerName.getText().equalsIgnoreCase(exampleText) || textFieldCustomerName.getText().equals("")) {
                    JOptionPane.showMessageDialog(mainPanel, "Please provide your first and last name: This information is required before you can continue.");
                } else {
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
            }
        });
        /**
         * This method is called when the "Add to Cart" button is clicked.
         * If the customer has not entered their name, an error message is displayed.
         * Otherwise, the selected items are added to the cart and the cart list is updated.
         *
         * @throws IOException if an error occurs while adding the items to the cart
         */
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer customer = new Customer(0, "", "", "", "", "", 0);
                String s = "";
                ArrayList<RentalItem> shoppingCart = rentalSystem.getCart(cartManager);
                for (int i = 0; i < customers.size(); i++) {
                    String fullName = customers.get(i).getFirstName().toLowerCase() + " " + customers.get(i).getName().toLowerCase();
                    if (textFieldCustomerName.getText().toLowerCase().equals(fullName)) {
                        customer = customers.get(i);
                    }
                }
                if (!(shoppingCart.size() == 0)) {
                    RentalItem rentalItem = new RentalItem("",0,0,false,0,"","",0);
                    //for (RentalItem item : shoppingCart) {
                    for (RentalItem item : shoppingCart) {
                        rentalItem = item;
                    }
                    try {
                        s = rentalSystem.checkOut(customer, cartManager, overview, databaseManager, rentalItem);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(mainPanel, "An error occurred while processing the order. Please try again later.");
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(mainPanel, "An error occurred while processing the order. Please try again later.");
                    }
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Your shopping cart is currently empty. Please add items to the cart before checking out.", "Empty Shopping Cart", 1);
                }
                textAreaTicketResult.setText(s);
                // Update the shopping cart list
                rentalSystem.clearShoppingCart();
                clearCartModel();
                ShoppingCartList.setModel(createCartModel());
            }
        });
        /**
         * This method is called when the "Search" button is clicked.
         * If the search text field is empty, an error message is displayed.
         * Otherwise, the database is searched for movies or games matching the search query,
         * and the search results are displayed in the list.
         */
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
                    JOptionPane.showMessageDialog(mainPanel, "Please enter a Movie or Game title.");
                    databaseList.setModel(defaultModel);
                }
            }
        });
        /**
         * This method is called when the "Submit New Customer" button is clicked.
         * If any of the fields are empty, an error message is displayed.
         * Otherwise, a new Customer object is created and added to the list of customers and the database.
         *
         * @throws IOException if an error occurs while adding the customer to the database
         */
        submitNewCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textFieldNewCustomerFirstName.getText().equals("")
                        || textFieldNewCustomerLastName.getText().equals("")
                        || textFieldNewCustomerAdres.getText().equals("")
                        || textFieldNewCustomerBirthdate.getText().equals("")
                        || labelPhoneNumber.getText().equals("")) {
                    JOptionPane.showMessageDialog(mainPanel, "Please fill out all field.");
                } else {
                    Customer customer = null;
                    try {
                        customer = new Customer(rentalSystem.generateClientNumber(), textFieldNewCustomerFirstName.getText().toLowerCase(),
                                textFieldNewCustomerLastName.getText().toLowerCase(),
                                textFieldNewCustomerAdres.getText().toLowerCase(),
                                textFieldNewCustomerBirthdate.getText(),
                                textFieldNewCustomerPhoneNumber.getText().toLowerCase(),
                                0);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(mainPanel, "An error occurred when generating a new client number.");
                    }
                    String customerInformation = customer.getFirstName() + " " + customer.getName();
                    customerDefaultListModel.addElement(customerInformation);
                    customers.add(customer);
                    JOptionPane.showMessageDialog(mainPanel, "Success: Your information has been added to the system database.");
                    try {
                        rentalSystem.addCustomerToDatabase(customer);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(mainPanel, "An error occurred when trying to add the customer to the database.");
                    }
                    customerListList.setModel(customerDefaultListModel);
                }
            }
        });
        /**
         * This method is called when the "Delete Customer" button is clicked.
         * The selected customer is removed from the list of customers and the database.
         */
        deleteCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Customer c : customers) {
                    if (customerListList.getSelectedValue().equals(c.getFirstName() + " " + c.getName())) {
                        rentalSystem.deleteCustomerFromCsv(c);

                    }
                }
                customers.remove(customerListList.getSelectedIndex());
                customerDefaultListModel.remove(customerListList.getSelectedIndex());
                customerListList.setModel(customerDefaultListModel);
            }
        });
        /**
         * This method is called when the "Search" button is clicked on the admin panel screen.
         * The list is searched for a customer matching the search query, and the search results are displayed.
         * If no match is found, an error message is displayed.
         */
        searchCustomerListSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean found = false;
                DefaultListModel<String> model = new DefaultListModel<>();
                if (!textFieldCustomerListSearch.getText().equals("")) {
                    for (int i = 0; i < customers.size() && !found; i++) {
                        if (textFieldCustomerListSearch.getText().toLowerCase().equals(customers.get(i).getName().toLowerCase()) || textFieldCustomerListSearch.getText().toLowerCase().equals(customers.get(i).getFirstName().toLowerCase())) {
                            model.addElement(customers.get(i).getFirstName() + " " + customers.get(i).getName() + " " + customers.get(i).getPhoneNumber());
                            found = true;
                        }
                    }
                }
                if (!found) {
                    JOptionPane.showMessageDialog(mainPanel, "No results found.");
                    customerListList.setModel(customerDefaultListModel);
                } else {
                    customerListList.setModel(model);
                }
            }
        });
        /**
         * This method is called when the "Enter" button is clicked.
         * It attempts to identify the customer based on the provided name.
         * If a match is not found, an error message is displayed.
         * If a match is found, the customer's name is displayed in the customer name field, and the database list is enabled.
         *
         * @throws ArrayIndexOutOfBoundsException  if an entry for the provided name is not found in the database
         */
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) throws ArrayIndexOutOfBoundsException {
                try {
                    textFieldCustomerName.setText(identifyCustomer());
                } catch (ArrayIndexOutOfBoundsException exception) {
                    JOptionPane.showMessageDialog(mainPanel, "An entry for the provided name was not found in the database. Please register as a new customer before proceeding.");
                }
                if (textFieldCustomerNumber.getText().length() > 0) {
                    databaseList.setEnabled(true);
                }
            }
        });
    }

    /**
     * This method attempts to identify a customer based on their first and last name.
     * If a match is found, the customer's name, years subscribed, and client number are returned.
     *
     * @return the identified customer's name, or an empty string if no match is found
     */
    public String identifyCustomer() {
        String identifiedCustomer = "";
        String[] parts = textFieldCustomerName.getText().toLowerCase().split(" ");
        String firstName = parts[0];
        String name = parts[1];
        for (int i = 0; i < customers.size(); i++) {
            //System.out.println(customers.get(i).getFirstName().toLowerCase());
            if (firstName.toLowerCase().equals(customers.get(i).getFirstName().toLowerCase()) && name.toLowerCase().equals(customers.get(i).getName().toLowerCase())) {
                String yearSubscribed = String.valueOf(customers.get(i).getYearsSubscribed());
                String clientNumber = String.valueOf(customers.get(i).getClientNumber());
                identifiedCustomer = customers.get(i).getFirstName() + " " + customers.get(i).getName();
                textFieldYearsSubscribed.setText(yearSubscribed);
                textFieldCustomerNumber.setText(clientNumber);
                //System.out.println(identifiedCustomer);
            }
        }
        return identifiedCustomer;
    }

    public void createAdminPanelCustomerModel() {
        for (Customer customer : customers) {
            String customerInformation = customer.getFirstName() + " " + customer.getName();
            customerDefaultListModel.addElement(customerInformation);
        }
        customerListList.setModel(customerDefaultListModel);
    }

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

    public ArrayList<Customer> loadCustomersFromCsv() {
        return rentalSystem.loadCustomersFromCsv();
    }


    public void run(RentalSystem rentalSystem) {
        //mainPanel.setPreferredSize(new Dimension(1200,500));
        customers.addAll(loadCustomersFromCsv());
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
