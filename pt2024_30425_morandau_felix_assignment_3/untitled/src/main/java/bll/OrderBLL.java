package bll;

import dao.BillDao;
import dao.Dao;
import model.Bill;
import model.Client;
import model.Orderr;
import model.Product;

import java.util.List;

/**
 * Business logic layer (BLL) class for managing orders.
 * This class provides methods to create orders and bills, and retrieve orders and bills.
 */
public class OrderBLL {
    private List<Orderr> orders;
    private List<Bill> bills;
    private Dao<Orderr> orderDao;

    /**
     * Constructs a new OrderBLL object and initializes the DAO for Order objects.
     * Retrieves the list of orders from the database.
     */
    public OrderBLL() {
        orderDao = new Dao<>(Orderr.class);
        orders = orderDao.retrieveObjects();
        bills = BillDao.retrieveBills();
    }

    /**
     * Creates an order for the selected client and product.
     * Also creates a bill for the order.
     * @param selectedClient The client for whom the order is created.
     * @param selectedProduct The product ordered.
     * @param quantity The quantity of the product ordered.
     */
    public void createOrder(Client selectedClient, Product selectedProduct, int quantity) {
        orderDao.insertObject(new Orderr(selectedClient.getId(), selectedProduct.getId(), quantity));
        orders = orderDao.retrieveObjects();

        createBill(selectedClient, selectedProduct, quantity);
    }

    /**
     * Creates a bill for the order.
     * @param selectedClient The client for whom the order is created.
     * @param selectedProduct The product ordered.
     * @param quantity The quantity of the product ordered.
     */
    private void createBill(Client selectedClient, Product selectedProduct, int quantity) {
        String fullName = selectedClient.getFirst_name() + " " + selectedClient.getLast_name();
        String productName = selectedProduct.getName();
        int price = selectedProduct.getPrice() * quantity;

        BillDao.insertBill(new Bill(orders.get(orders.size() - 1).getId(), fullName, productName, quantity, price));
        bills = BillDao.retrieveBills();
    }

    /**
     * Retrieves the list of orders.
     * @return The list of orders.
     */
    public List<Orderr> getOrders() {
        return orders;
    }

    /**
     * Retrieves the list of bills.
     * @return The list of bills.
     */
    public List<Bill> getBills() {
        return bills;
    }
}
