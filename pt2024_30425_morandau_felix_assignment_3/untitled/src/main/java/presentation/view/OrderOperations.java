package presentation.view;

import model.Client;
import model.Product;
import presentation.controller.OrderOperationsController;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the interface for order operations.
 */
public class OrderOperations extends JFrame {
    private final JButton back = new JButton("BACK");
    private final JButton createOrder = new JButton("CREATE");
    private final JButton viewBills = new JButton("BILLS");
    private JComboBox<Client> clientOptions = new JComboBox<>();
    private JComboBox<Product> productOptions = new JComboBox<>();
    private final JLabel quantityLabel = new JLabel("Quantity:");
    private final JTextField quantityField = new JTextField();
    private JPanel billPanel = new JPanel();
    private JTable table = new JTable();

    /**
     * Constructs the OrderOperations frame.
     */
    public OrderOperations() {
        initializeUI();

        new OrderOperationsController(this);
    }

    /**
     * Initializes the user interface.
     */
    private void initializeUI() {
        setTitle("Order Operations");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(createMainPanel(), BorderLayout.CENTER);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Creates the main panel of the interface.
     *
     * @return The main panel.
     */
    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(createTopPanel(), BorderLayout.NORTH);
        mainPanel.add(createMiddlePanel(), BorderLayout.CENTER);
        mainPanel.add(createBottomPanel(), BorderLayout.SOUTH);

        return mainPanel;
    }

    /**
     * Creates the top panel of the interface.
     *
     * @return The top panel.
     */
    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel();

        topPanel.add(new JLabel("Order Menu"));

        return topPanel;
    }

    /**
     * Creates the middle panel of the interface.
     *
     * @return The middle panel.
     */
    private JPanel createMiddlePanel() {
        JPanel middlePanel = new JPanel(new GridLayout(1, 2, 10, 10));

        JPanel middleLeftPanel = new JPanel(new GridLayout(10, 1, 10, 10));

        middleLeftPanel.add(new JLabel());
        middleLeftPanel.add(new JLabel("Clients:"));
        middleLeftPanel.add(clientOptions);
        middleLeftPanel.add(new JLabel("Products:"));
        middleLeftPanel.add(productOptions);
        middleLeftPanel.add(quantityLabel);
        middleLeftPanel.add(quantityField);

        middlePanel.add(middleLeftPanel);
        middlePanel.add(billPanel);

        return middlePanel;
    }

    /**
     * Creates the bottom panel of the interface.
     *
     * @return The bottom panel.
     */
    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new FlowLayout(10, 10, 10));

        bottomPanel.add(back);
        bottomPanel.add(createOrder);
        bottomPanel.add(viewBills);

        return bottomPanel;
    }

    /**
     * Updates the bill panel with the table of bills.
     */
    public void updateBillPanel() {
        billPanel.add(table);
    }

    /**
     * Retrieves the back button.
     *
     * @return The back button.
     */
    public JButton getBack() {
        return back;
    }

    /**
     * Retrieves the create order button.
     *
     * @return The create order button.
     */
    public JButton getCreateOrder() {
        return createOrder;
    }

    /**
     * Retrieves the view bills button.
     *
     * @return The view bills button.
     */
    public JButton getViewBills() {
        return viewBills;
    }

    /**
     * Retrieves the client options combo box.
     *
     * @return The client options combo box.
     */
    public JComboBox<Client> getClientOptions() {
        return clientOptions;
    }

    /**
     * Retrieves the product options combo box.
     *
     * @return The product options combo box.
     */
    public JComboBox<Product> getProductOptions() {
        return productOptions;
    }

    /**
     * Retrieves the quantity text field.
     *
     * @return The quantity text field.
     */
    public JTextField getQuantityField() {
        return quantityField;
    }

    /**
     * Retrieves the table component.
     *
     * @return The table component.
     */
    public JTable getTable() {
        return table;
    }
}
