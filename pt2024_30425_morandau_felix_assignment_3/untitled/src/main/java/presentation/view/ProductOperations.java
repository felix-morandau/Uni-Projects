package presentation.view;

import presentation.controller.ProductOperationsController;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the interface for product operations.
 */
public class ProductOperations extends JFrame {
    private final JButton addProduct = new JButton("ADD");
    private final JButton deleteProduct = new JButton("DELETE");
    private final JButton editProduct = new JButton("EDIT");
    private final JButton back = new JButton("BACK");
    private final JLabel nameLabel = new JLabel("Name:");
    private final JTextField nameField = new JTextField();
    private final JLabel priceLabel = new JLabel("Price:");
    private final JTextField priceField = new JTextField();
    private final JLabel stockLabel = new JLabel("Stock:");
    private final JTextField stockField = new JTextField();
    private JTable table = new JTable();

    /**
     * Constructs the ProductOperations frame.
     */
    public ProductOperations() {
        setSize(new Dimension(800, 600));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        new ProductOperationsController(this);

        initUI();
    }

    /**
     * Initializes the user interface.
     */
    private void initUI() {
        JPanel contentPanel = createContentPanel();
        JPanel topPanel = createTopPanel();
        JPanel middlePanel = createMiddlePanel();
        JPanel bottomPanel = createBottomPanel();

        setContentPane(contentPanel);
        contentPanel.add(topPanel, BorderLayout.NORTH);
        contentPanel.add(middlePanel, BorderLayout.CENTER);
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Creates the content panel of the interface.
     *
     * @return The content panel.
     */
    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(255, 182, 193));
        return contentPanel;
    }

    /**
     * Creates the top panel of the interface.
     *
     * @return The top panel.
     */
    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Product Operations Menu"));
        topPanel.setBackground(new Color(248, 255, 255));
        return topPanel;
    }

    /**
     * Creates the middle panel of the interface.
     *
     * @return The middle panel.
     */
    public JPanel createMiddlePanel() {
        JPanel middlePanel = new JPanel(new GridLayout(2, 1));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);

        JPanel fieldPanel = createFieldPanel();

        middlePanel.add(scrollPane);
        middlePanel.add(fieldPanel);

        return middlePanel;
    }

    /**
     * Creates the field panel of the interface.
     *
     * @return The field panel.
     */
    private JPanel createFieldPanel() {
        JPanel fieldPanel = new JPanel(new GridLayout(1, 4));
        fieldPanel.setBackground(new Color(255, 182, 193));

        JPanel namePanel = createInputPanel(nameLabel, nameField);
        JPanel pricePanel = createInputPanel(priceLabel, priceField);
        JPanel stockPanel = createInputPanel(stockLabel, stockField);

        fieldPanel.add(namePanel);
        fieldPanel.add(pricePanel);
        fieldPanel.add(stockPanel);

        return fieldPanel;
    }

    /**
     * Creates an input panel for labels and text fields.
     *
     * @param label      The label.
     * @param textField  The text field.
     * @return The input panel.
     */
    private JPanel createInputPanel(JLabel label, JTextField textField) {
        JPanel inputPanel = new JPanel(new GridLayout(6, 1));
        inputPanel.add(new JLabel());
        inputPanel.add(new JLabel());
        inputPanel.add(label);
        inputPanel.add(textField);
        inputPanel.setBackground(new Color(255, 182, 193));
        return inputPanel;
    }

    /**
     * Creates the bottom panel of the interface.
     *
     * @return The bottom panel.
     */
    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        back.setBackground(new Color(255, 182, 193));
        addProduct.setBackground(new Color(255, 182, 193));
        editProduct.setBackground(new Color(255, 182, 193));
        deleteProduct.setBackground(new Color(255, 182, 193));

        bottomPanel.add(back);
        bottomPanel.add(addProduct);
        bottomPanel.add(editProduct);
        bottomPanel.add(deleteProduct);
        bottomPanel.setBackground(new Color(248, 255, 255));

        return bottomPanel;
    }

    public JButton getAddProduct() {
        return addProduct;
    }

    public JButton getDeleteProduct() {
        return deleteProduct;
    }

    public JButton getEditProduct() {
        return editProduct;
    }

    public JButton getBack() {
        return back;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getPriceField() {
        return priceField;
    }

    public JTextField getStockField() {
        return stockField;
    }

    public JTable getTable() {
        return table;
    }
}
