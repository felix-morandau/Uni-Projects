package presentation.controller;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import model.Bill;
import model.Client;
import model.Product;
import presentation.view.MainMenu;
import presentation.view.OrderOperations;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Controller for managing order operations.
 * Communicates directly with the operation view and handles all the operations for the view.
 */
public class OrderOperationsController {
    private OrderOperations view;
    private TableController<Bill> tableController = new TableController<>();
    private OrderBLL orderBLL;
    private ClientBLL clientBLL;
    private ProductBLL productBLL;

    /**
     * Constructs an OrderOperationsController with the specified view.
     * Initializes the necessary BLL objects and sets up the initial state of the view.
     * @param view The OrderOperations view.
     */
    public OrderOperationsController(OrderOperations view) {
        this.view = view;
        view.setVisible(true);

        orderBLL = new OrderBLL();
        clientBLL = new ClientBLL();
        productBLL = new ProductBLL();

        updateOptions();

        view.getCreateOrder().addActionListener(e -> createOrderAction());
        view.getBack().addActionListener(e -> backAction());
        view.getViewBills().addActionListener(e -> showBill());
    }

    /**
     * Updates the client and product options in the view.
     */
    private void updateOptions() {
        view.getClientOptions().removeAllItems();
        view.getProductOptions().removeAllItems();

        List<Client> clients = clientBLL.getClients();
        List<Product> products = productBLL.getProducts();

        for (Client client : clients) {
            view.getClientOptions().addItem(client);
        }

        for (Product product : products) {
            view.getProductOptions().addItem(product);
        }
    }

    /**
     * Handles the action to navigate back to the main menu.
     */
    private void backAction() {
        view.setVisible(false);
        new MainMenu();
    }

    /**
     * Handles the action to create a new order.
     */
    private void createOrderAction() {
        Client selectedClient = (Client) view.getClientOptions().getSelectedItem();
        Product selectedProduct = (Product) view.getProductOptions().getSelectedItem();
        String quantityString = view.getQuantityField().getText();

        if (selectedProduct == null || selectedClient == null) {
            JOptionPane.showMessageDialog(view, "Please select a client and a product.");
        } else if (!quantityString.isEmpty()) {
            try {
                int quantity = Integer.parseInt(quantityString);

                if (quantity > selectedProduct.getStock()) {
                    JOptionPane.showMessageDialog(view, "Quantity is higher than the available stock.");
                } else if (quantity > 0) {
                    orderBLL.createOrder(selectedClient, selectedProduct, quantity);
                    selectedProduct.setStock(selectedProduct.getStock() - quantity);
                    productBLL.updateProduct(selectedProduct);
                } else {
                    JOptionPane.showMessageDialog(view, "Quantity has to be greater than 0");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(view, "Enter a valid number.");
            }
        }

        updateOptions();
    }

    /**
     * Displays the bills in the view's table.
     */
    private void showBill() {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableController.fillTable(orderBLL.getBills(), tableModel);
        view.getTable().setModel(tableModel);
        view.updateBillPanel();
    }
}
