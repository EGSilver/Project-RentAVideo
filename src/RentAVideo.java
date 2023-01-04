import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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


    public RentAVideo() {
        databaseList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //TODO update list ie. Refresh list
            }
        });
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer klant1 = new Customer(0000001,"Jef","Vermassen","Kabouterstraat 8 2800 Mechelen","2016-02-09","0499/99/66/33",0);
                List<RentalItem> selectedItems = databaseList.getSelectedValuesList();
                for (RentalItem item : selectedItems) {
                    try {
                        rentalSystem.addItemToCart(item, klant1, cartManager);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
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
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void createMovieModel(RentalSystem rentalSystem, DatabaseManager databaseManager) {
        ArrayList<RentalItem> rentalMovies = rentalSystem.getRentalMovies(databaseManager);
        DefaultListModel<RentalItem> model = new DefaultListModel<>();
        for (RentalItem movie : rentalMovies) {
            model.addElement(movie);
            databaseList.setModel(model);
        }
    }

    public DefaultListModel<RentalItem> createCartModel() {
        ArrayList<RentalItem> shoppingCart = rentalSystem.getCart(cartManager);
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
        createMovieModel(rentalSystem ,databaseManager);
        frame.setTitle("RentAVideo");
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
