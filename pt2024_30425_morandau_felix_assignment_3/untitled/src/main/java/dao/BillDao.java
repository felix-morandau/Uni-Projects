package dao;

import connection.ConnectionFactory;
import model.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The BillDao class provides methods for interacting with the bill table in the database.
 */
public class BillDao {

    /**
     * Inserts a bill into the bill table.
     *
     * @param bill the bill to be inserted
     * @throws RuntimeException if an SQL exception occurs during execution
     */
    public static void insertBill(Bill bill) {
        String query = "INSERT INTO bill (order_id, name, product, price, quantity) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, bill.order_id());
            statement.setString(2, bill.clientName());
            statement.setString(3, bill.productName());
            statement.setInt(4, bill.price());
            statement.setDouble(5, bill.quantity());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error when inserting bill.", e);
        }
    }

    /**
     * Retrieves a list of bills from the bill table.
     *
     * @return a list of retrieved bills
     * @throws RuntimeException if an SQL exception occurs during execution
     */
    public static List<Bill> retrieveBills() {
        String query = "SELECT * FROM bill";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            return createBills(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Error when retrieving bills.", e);
        }
    }

    /**
     * Creates a list of Bill objects from the given ResultSet.
     *
     * @param resultSet the ResultSet containing data from the database
     * @return a list of created Bill objects
     * @throws SQLException if an SQL exception occurs
     */
    private static List<Bill> createBills(ResultSet resultSet) throws SQLException {
        List<Bill> bills = new ArrayList<>();
        while (resultSet.next()) {
            int orderId = resultSet.getInt("order_id");
            String clientName = resultSet.getString("name");
            String productName = resultSet.getString("product");
            int quantity = resultSet.getInt("quantity");
            int price = resultSet.getInt("price");

            Bill bill = new Bill(orderId, clientName, productName, quantity, price);
            bills.add(bill);
        }
        return bills;
    }
}
