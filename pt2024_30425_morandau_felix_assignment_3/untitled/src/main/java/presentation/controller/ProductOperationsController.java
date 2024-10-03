package presentation.controller;

import bll.ProductBLL;
import model.Product;
import presentation.view.MainMenu;
import presentation.view.ProductOperations;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Controller for managing product operations.
 * Communicates directly with the product view and handles all the operations for the view.
 */
public class ProductOperationsController {
    private ProductOperations view;
    private ProductBLL productBLL;
    private Product selectedProduct = new Product();
    private TableController<Product> tableController = new TableController<>();

    /**
     * Constructs a ProductOperationsController with the specified view.
     * Initializes the necessary BLL object and sets up the initial state of the view.
     * @param view The ProductOperations view.
     */
    public ProductOperationsController(ProductOperations view) {
        productBLL = new ProductBLL();

        this.view = view;
        view.setVisible(true);

        DefaultTableModel model = new DefaultTableModel();
        tableController.fillTable(productBLL.getProducts(), model);
        view.getTable().setModel(model);

        view.getBack().addActionListener(e -> backAction());
        view.getAddProduct().addActionListener(e -> addProductAction());
        view.getEditProduct().addActionListener(e -> editProductAction());
        view.getDeleteProduct().addActionListener(e -> deleteProductAction());
        setupMouseAction();
    }

    /**
     * Handles the action to navigate back to the main menu.
     */
    private void backAction() {
        view.setVisible(false);
        new MainMenu();
    }

    /**
     * Handles the action to add a new product.
     */
    private void addProductAction() {
        try {
            Product newProduct = getNewProduct();
            if (newProduct != null) {
                productBLL.insertProduct(newProduct);
                refreshTable();
                JOptionPane.showMessageDialog(view, "Product added successfully.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Please enter valid numeric values for price and stock.");
        }
    }

    /**
     * Sets up the mouse action for the table in the view.
     */
    private void setupMouseAction() {
        view.getTable().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                mouseEvent(e);
            }
        });
    }

    /**
     * Handles the mouse event when a table row is clicked.
     * Retrieves the selected product's information.
     * @param event The mouse event.
     */
    private void mouseEvent(java.awt.event.MouseEvent event) {
        List<String> rowData = new ArrayList<>();

        int rowNr = view.getTable().getSelectedRow();

        DefaultTableModel tableModel = (DefaultTableModel) view.getTable().getModel();

        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            rowData.add(tableModel.getValueAt(rowNr, i).toString());
        }

        getSelectedProduct(rowData);
    }

    /**
     * Handles the action to edit a product.
     */
    private void editProductAction() {
        try {
            modifySelectedProduct();
            productBLL.updateProduct(selectedProduct);
            refreshTable();
            JOptionPane.showMessageDialog(view, "Product updated successfully");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Please enter valid numeric values for price and stock.");
        }
    }

    /**
     * Handles the action to delete a product.
     */
    private void deleteProductAction() {
        productBLL.deleteProduct(selectedProduct);
        refreshTable();
        JOptionPane.showMessageDialog(view, "Product deleted successfully.");
    }

    /**
     * Modifies the selected product based on the input fields in the view.
     */
    private void modifySelectedProduct() {
        String name = view.getNameField().getText();
        String priceText = view.getPriceField().getText();
        String stockText = view.getStockField().getText();

        if (!Objects.equals(name, "")) {
            selectedProduct.setName(name);
        }

        if (!priceText.isEmpty()) {
            try {
                int price = Integer.parseInt(priceText);
                selectedProduct.setPrice(price);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(view, "Please enter a valid numeric value for price.");
            }
        }

        if (!stockText.isEmpty()) {
            try {
                int stock = Integer.parseInt(stockText);
                selectedProduct.setStock(stock);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(view, "Please enter a valid numeric value for stock.");
            }
        }
    }

    /**
     * Retrieves the selected product's information from the table data.
     * @param productData The data of the selected product.
     */
    private void getSelectedProduct(List<String> productData) {
        int iterator = 0;

        selectedProduct.setId(Integer.parseInt(productData.get(iterator++)));
        selectedProduct.setName(productData.get(iterator++));
        selectedProduct.setPrice(Integer.parseInt(productData.get(iterator++)));
        selectedProduct.setStock(Integer.parseInt(productData.get(iterator)));
    }

    /**
     * Retrieves a new product from the input fields in the view.
     * @return The new product.
     */
    private Product getNewProduct() {
        try {
            String name = view.getNameField().getText();
            int price = Integer.parseInt(view.getPriceField().getText());
            int stock = Integer.parseInt(view.getStockField().getText());
            return new Product(name, price, stock);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Incorrect data entered.");
            return null;
        }
    }

    /**
     * Refreshes the table in the view with updated product data.
     */
    private void refreshTable() {
        DefaultTableModel model = new DefaultTableModel();
        tableController.fillTable(productBLL.getProducts(), model);
        view.getTable().setModel(model);
    }
}
