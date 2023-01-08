import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;

public class RentAVideo {
    private JPanel mainPanel;
    private JFrame frame;
    private JButton searchButton;
    private JButton addToCartButton;
    private JButton checkoutButton;
    private JTextField textFieldSearch;
    private JPanel rentalSystemPanel;
    private JPanel adminPanel;
    private JList<RentalItem> databaseList;
    private JList<RentalItem> shoppingCartList;
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
    private JButton submitDateButtonEarningsReport;
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
    private JButton addMovieButton;
    private JList<String> customerListList;
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
    private JTextField textFieldCustomerNameReturnScreen;
    private JTextField textFieldCustomerYearsSubscribedReturnScreen;
    private JTextField textFieldCustomerNumberReturnScreen;
    private JTextArea textAreaReturnScreen;
    private JButton submitButtonReturnScreen;
    private JList returnScreenJlist;
    private JButton returnMovieButton;
    private JTextArea dailyReportTextArea;
    private JTextArea earningsRapportListtextArea;
    private JButton deleteDailyFromHistoryButton;
    private JButton deleteEarningsFromHistoryButton;
    private JButton removeItemButton;
    private JTextArea textAreaChristmasTree;
    private JTextField textFieldTicketResult;
    private final RentalSystem rentalSystem = new RentalSystem();
    private CartManager cartManager = new CartManager();
    private ArrayList<Customer> customers = new ArrayList<>();

    private final CustomerManager customerManager = new CustomerManager(customers);
    private final DatabaseManager databaseManager = new DatabaseManager();
    private DayOverview overview = new DayOverview(0, 0, 0, 0);
    private ArrayList<RentalItem> shoppingCart = rentalSystem.getCart(cartManager);
    private ArrayList<RentalItem> searchResults = new ArrayList<>();
    private ArrayList<RentalItem> rentalMovies = databaseManager.getRentalMovies();
    private ArrayList<RentalItem> rentalGames = databaseManager.getRentalGames();
    private DefaultListModel<String> customerDefaultListModel = new DefaultListModel<String>();
    private DefaultListModel<RentalItem> defaultModel = new DefaultListModel<>();
    private DefaultListModel rentalHistory = new DefaultListModel<>();

    private HashMap<String, Double> dayEarningsReportMap = new HashMap<>();

    DefaultListModel<String> dayEarningReport = new DefaultListModel<>();
    private boolean submitButtonReturnScreenFired = false;
    private boolean enterButtonRentScreenFired = false;
    private Date currentSystemDate = new java.sql.Date(System.currentTimeMillis());
    private Customer customer;


    public RentAVideo() {
        textAreaReturnScreen.setEditable(false);
        textFieldCustomerNumber.setEditable(false);
        textFieldYearsSubscribed.setEditable(false);
        textAreaTicketResult.setEditable(false);
        databaseList.setEnabled(false);
        textFieldCustomerNumberReturnScreen.setEditable(false);
        textFieldCustomerYearsSubscribedReturnScreen.setEditable(false);
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
                            String underAgedAndOutOfStockMessage = rentalSystem.addItemToCart(item, customer, cartManager);
                            if (underAgedAndOutOfStockMessage.equalsIgnoreCase("This item is out of stock")) {
                                JOptionPane.showMessageDialog(mainPanel, "This item is out of stock");
                            } else if (underAgedAndOutOfStockMessage.contains("18")) {
                                JOptionPane.showMessageDialog(mainPanel, underAgedAndOutOfStockMessage);
                            }
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(mainPanel, "An error occurred while adding your item to the cart. Please try again later.");
                        }
                    }
                    for (RentalItem item : selectedItems) {
                        createCartModel().addElement(item);
                    }
                    shoppingCartList.setModel(createCartModel());
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
                overview.createIncomeOverview(String.valueOf(currentSystemDate));
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
                    RentalItem rentalItem = new RentalItem("", 0, 0, false, 0, "", "", 0);
                    for (RentalItem item : shoppingCart) {
                        rentalItem = item;
                    }
                    try {
                        s = rentalSystem.checkOut(customer, cartManager, overview, databaseManager, rentalItem);
                        rentalSystem.saveRentalDate(rentalItem);
                        rentalSystem.createRentalHistory(customer, cartManager);
                        rentalHistory.addAll(rentalSystem.viewRentalHistory(customer));
                        //dayEarningReport.addElement(rentalSystem.viewIncomeOverviewTest((java.sql.Date) currentSystemDate, overview));
                        String incomeReportString = rentalSystem.viewIncomeOverviewTest((java.sql.Date) currentSystemDate, overview);
                        String[] parts = incomeReportString.split(":");
                        Double income = Double.valueOf(parts[1].replace("€", ""));
                        dayEarningsReportMap.put(String.valueOf(currentSystemDate), income);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(mainPanel, "An error occurred while processing the order. Please try again later.");
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(mainPanel, "An error occurred while processing the order. Please try again later.");
                    }
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Your shopping cart is currently empty. Please add items to the cart before checking out.", "Empty Shopping Cart", 1);
                }
                textAreaTicketResult.setText(s);
                rentalSystem.clearShoppingCart();
                clearCartModel();
                shoppingCartList.setModel(createCartModel());

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
                String searchText = textFieldSearch.getText().toLowerCase();
                if (!searchText.equals("")) {
                    clearSearchResultScreen();
                    searchResults = rentalSystem.searchForMovieOrGameInCsv(searchText, databaseManager);
                    for (RentalItem searchResult : searchResults) {
                        out.println(searchResult);
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
                        // Making sure a new customer can enter a lastname with various spaces.
                        String[] lastNameParts = textFieldNewCustomerLastName.getText().split(" ");
                        String firstName = textFieldNewCustomerFirstName.getText();
                        String lastName = lastNameParts[0];
                        for (int i = 1; i < lastNameParts.length; i++) {
                            lastName += " " + lastNameParts[i];
                        }
                        customer = new Customer(rentalSystem.generateClientNumber(), firstName,
                                lastName,
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
                    textFieldNewCustomerFirstName.setText("");
                    textFieldNewCustomerLastName.setText("");
                    textFieldNewCustomerAdres.setText("");
                    textFieldNewCustomerBirthdate.setText("");
                    textFieldNewCustomerPhoneNumber.setText("");
                    textFieldNewCustomerEmail.setText("");
                    overview.setNewMembers(overview.getNewMembers() + 1);
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
                int j = 1;
                if (!textFieldCustomerListSearch.getText().equals("")) {
                    String[] parts = textFieldCustomerListSearch.getText().split(" ");
                    for (int i = 0; i < customers.size() && !found; i++) {
                        if (parts.length == 1) {
                            if (parts[0].equalsIgnoreCase(customers.get(i).getName().toLowerCase())
                                    || parts[0].equalsIgnoreCase(customers.get(i).getFirstName())) {
                                model.addElement(customers.get(i).getFirstName() + " " + customers.get(i).getName() + " " + customers.get(i).getPhoneNumber());
                                found = true;
                            }
                        } else if (parts.length == 2) {
                            if (parts[0].equalsIgnoreCase(customers.get(i).getName()) && parts[1].equalsIgnoreCase(customers.get(i).getFirstName())
                                    || parts[1].equalsIgnoreCase(customers.get(i).getName()) && parts[0].equalsIgnoreCase(customers.get(i).getFirstName())) {
                                model.addElement(customers.get(i).getFirstName() + " " + customers.get(i).getName() + " " + customers.get(i).getPhoneNumber());
                                found = true;
                            }
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
        addMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = TextFieldMovieTitle.getText();
                Double rentalPrice = Double.valueOf(textFieldMovieRentalPrice.getText());
                int rentalDuration = Integer.parseInt(textFieldMovieRentalDuration.getText());
                boolean inStock = Boolean.parseBoolean(textFieldMovieStock.getText());
                int stock = Integer.parseInt(textFieldMovieStock.getText());
                Movie newMovie = new Movie(title, rentalPrice, rentalDuration, inStock, stock, "Movie", textFieldMovieReleaseDate.getText(), "placeholder", "", textFieldMovieEsrbRating.getText(), 0);
                rentalSystem.addItemToDatabase(newMovie);
                JOptionPane.showMessageDialog(mainPanel, "Movie " + newMovie.getTitle() + " has been added to the database");
                rentalMovies.add(newMovie);
                createListModel(databaseManager);
                rentalMovies.clear();
            }
        });
        addGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = textFieldGameTitle.getText();
                Double rentalPrice = Double.valueOf(textFieldGameRentalPrice.getText());
                int rentalDuration = Integer.parseInt(textFieldGameRentalDuration.getText());
                boolean inStock = true;
                int stock = Integer.parseInt(textFieldGameStock.getText());
                Game newGame = new Game(title, rentalPrice, rentalDuration, inStock, stock, "Game", textFieldGamePlatform.getText(), textFieldGamePublisher.getText(), textFieldGameEsrbRating.getText(), 0);
                rentalSystem.addItemToDatabase(newGame);
                JOptionPane.showMessageDialog(mainPanel, "Game " + newGame.getTitle() + " has been added to the database");
                rentalGames.add(newGame);
                createListModel(databaseManager);
                rentalGames.clear();
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
                enterButtonRentScreenFired = true;
                try {
                    textFieldCustomerName.setText(identifyCustomer());
                } catch (ArrayIndexOutOfBoundsException exception) {
                    JOptionPane.showMessageDialog(mainPanel, "An entry for the provided name was not found in the database. Please enter your full name or register as a new customer before proceeding.");
                }
                if (textFieldCustomerNumber.getText().length() > 0) {
                    databaseList.setEnabled(true);
                }
            }
        });
        /**
         * This method handles the action performed event when the submit button is clicked on the rent screen.
         * It identifies the customer by calling the identifyCustomer method and sets the customer name in the return screen text field.
         */
        submitButtonReturnScreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel emptyReplacement = new DefaultListModel<>();
                returnScreenJlist.setModel(emptyReplacement);
                submitButtonReturnScreenFired = true;
                String[] parts = identifyCustomer().split(" ");
                try {
                    if (parts.length > 2) {
                        textFieldCustomerNameReturnScreen.setText(parts[0] + " " + parts[1] + " " + parts[2]);
                    } else {
                        textFieldCustomerNameReturnScreen.setText(parts[0] + " " + parts[1]);
                    }
                } catch (ArrayIndexOutOfBoundsException exception) {
                    JOptionPane.showMessageDialog(mainPanel, "An entry for the provided name was not found in the database. Please enter your full name or register as a new customer before proceeding.");

                }
                try {
                    String firstName = parts[0];
                    String[] lastNameParts = Arrays.copyOfRange(parts, 1, parts.length);
                    String lastName = lastNameParts[0];
                    for (int i = 1; i < lastNameParts.length; i++) {
                        lastName += " " + lastNameParts[i];
                    }
                    for (Customer customer : customers) {
                        if (customer.getFirstName().equalsIgnoreCase(firstName) && customer.getName().equalsIgnoreCase(lastName)) {
                            createRentalItemReturnList(customer);
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException q) {
                }
                //returnScreenJlist.setModel(rentalHistory);
            }
        });
        /**
         * This method handles the action performed event when the submit date button is clicked.
         * It fetches the date entered in the text field, formats it and checks if it matches the pattern.
         * If it matches, it fetches the day overview for that date and displays it in the daily report list.
         */
        submitDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) throws NullPointerException {
                rentalSystem.testHashMap();
                DefaultListModel<Object> dayReportModel = new DefaultListModel<>();
                rentalSystem.createOverview(String.valueOf(currentSystemDate), overview);
                String date = textFieldDayRapportDateInput.getText();
                String replaceDate = date.replace("/", "-");
                if (textFieldDayRapportDateInput.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(mainPanel, "Please enter a date.");
                } else if (matchDatePattern(replaceDate)) {
                    try {
                        dayReportModel.addElement(rentalSystem.viewDayOverview(replaceDate));
                        dailyReportTextArea.setText(String.valueOf(dayReportModel));
                    } catch (NullPointerException q) {
                        JOptionPane.showMessageDialog(mainPanel, "No data found for the specified date.");
                    }

                }
                textFieldDayRapportDateInput.setText("");
            }
        });
        returnMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RentalItem item = (RentalItem) returnScreenJlist.getSelectedValue();
                try {
                    textAreaReturnScreen.setText(rentalSystem.returnItem(item, overview, cartManager));
                } catch (IOException | ParseException ex) {
                    JOptionPane.showMessageDialog(mainPanel, "There was a problem while processing your order, please try again later.");
                } catch (NullPointerException np) {

                }
                try {
                    rentalHistory.remove(returnScreenJlist.getSelectedIndex());
                } catch (ArrayIndexOutOfBoundsException ob) {
                    JOptionPane.showMessageDialog(mainPanel, "Please select an item first.");
                }
                returnScreenJlist.setModel(rentalHistory);
            }
        });
        submitDateButtonEarningsReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                earningsRapportListtextArea.setText("");
                String date = textFieldEarningsRapportDateInput.getText();
                String replaceDate = date.replace("/", "-");
                String income = String.valueOf(dayEarningsReportMap.get(replaceDate));
                if (textFieldEarningsRapportDateInput.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(mainPanel, "Please enter a date.");
                } else if (matchDatePattern(replaceDate)) {
                    if (dayEarningsReportMap.isEmpty() || income.equals("null")) {
                        JOptionPane.showMessageDialog(mainPanel, "No data found for the specified date.");
                    } else {
                        earningsRapportListtextArea.setText("Total income\nof the day:\n" + replaceDate + "\n" + income);
                    }
                }

            }
        });
        removeItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeSelectionFromCart(shoppingCartList.getSelectedValue());
                createCartModel();
            }
        });
        deleteEarningsFromHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeSelectionFromHistory(textFieldEarningsRapportDateInput.getText());
                earningsRapportListtextArea.setText("");
            }
        });
    }

    public void removeSelectionFromHistory(String specificDate) {
        Iterator iterator = dayEarningsReportMap.entrySet().iterator();
        String reformattedDate = specificDate.replaceAll("/","-");
        while (iterator.hasNext()) {
            Map.Entry<String, Double> entry = (Map.Entry<String, Double>) iterator.next();
            if (entry.getKey().equals(reformattedDate)) {
                iterator.remove();
            }
        }
    }

    public void removeSelectionFromCart(RentalItem item) {
        Iterator iterator = shoppingCart.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(item)) {
                iterator.remove();
            }
        }
    }

    public void createRentalItemReturnList(Customer customer) throws NullPointerException {
        try {
            rentalHistory.addAll(rentalSystem.viewRentalHistory(customer));
            returnScreenJlist.setModel(rentalHistory);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(mainPanel, "You have not rented any items yet.");
        }
    }

    public boolean matchDatePattern(String date) {
        String incomingDate = date;
        String pattern = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";
        Pattern r = Pattern.compile(pattern);
        Matcher matcher = r.matcher(incomingDate);
        if (matcher.find()) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * This method attempts to identify a customer based on their first and last name.
     * If a match is found, the customer's name, years subscribed, and client number are returned.
     *
     * @return the identified customer's name, or an empty string if no match is found
     */
    public String identifyCustomer() {
        String[] parts = {};
        String identifiedCustomer = "";
        if (submitButtonReturnScreenFired) {
            parts = textFieldCustomerNameReturnScreen.getText().toLowerCase().split(" ");
        } else if (enterButtonRentScreenFired) {
            parts = textFieldCustomerName.getText().toLowerCase().split(" ");
        }
        String firstName = parts[0];
        String[] lastNameParts = Arrays.copyOfRange(parts, 1, parts.length);
        String lastName = lastNameParts[0];
        for (int i = 1; i < lastNameParts.length; i++) {
            lastName += " " + lastNameParts[i];
        }
        for (int i = 0; i < customers.size(); i++) {
            if (firstName.toLowerCase().equals(customers.get(i).getFirstName().toLowerCase()) && lastName.toLowerCase().equals(customers.get(i).getName().toLowerCase())) {
                String yearSubscribed = String.valueOf(customers.get(i).getYearsSubscribed());
                String clientNumber = String.valueOf(customers.get(i).getClientNumber());
                identifiedCustomer = customers.get(i).getFirstName() + " " + customers.get(i).getName();
                if (enterButtonRentScreenFired) {
                    enterButtonRentScreenFired = false;
                    textFieldYearsSubscribed.setText(yearSubscribed);
                    textFieldCustomerNumber.setText(clientNumber);
                } else if (submitButtonReturnScreenFired) {
                    submitButtonReturnScreenFired = false;
                    textFieldCustomerYearsSubscribedReturnScreen.setText(yearSubscribed);
                    textFieldCustomerNumberReturnScreen.setText(clientNumber);
                }
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
        defaultModel.clear();
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

    public Customer getCustomer() {
        for (int i = 0; i < customers.size(); i++) {
            String fullName = customers.get(i).getFirstName().toLowerCase() + " " + customers.get(i).getName().toLowerCase();
            if (textFieldCustomerName.getText().toLowerCase().equals(fullName)) {
                customer = customers.get(i);
            }
        }
        return customer;
    }

    public DefaultListModel<RentalItem> createCartModel() {
        DefaultListModel<RentalItem> model = new DefaultListModel<>();
        for (RentalItem item : shoppingCart) {
            model.addElement(item);
        }
        shoppingCartList.setModel(model);
        return model;
    }

    public void loadMovies() {
        rentalSystem.loadMovies(databaseManager);
    }

    public void loadGames() {
        rentalSystem.loadGames(databaseManager);
    }

    public String christmasTree() {
        int size = 5;
        StringBuilder tree = new StringBuilder();
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size - i; j++) {
                tree.append(" ");
            }
            for (int j = 1; j <= 2 * i - 1; j++) {
                tree.append("*");
            }
            tree.append("\n");
        }
        for (int i = 1; i <= size - 1; i++) {
            tree.append(" ");
        }
        tree.append("*\n");
        return tree.toString();
    }


    public ArrayList<Customer> loadCustomersFromCsv() {
        return rentalSystem.loadCustomersFromCsv();
    }

    public void run(RentalSystem rentalSystem) {
        out.println(christmasTree());
        //mainPanel.setPreferredSize(new Dimension(1200,500));
        customers.addAll(loadCustomersFromCsv());
        loadMovies();
        loadGames();
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
