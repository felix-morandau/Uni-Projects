package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The ConnectionFactory class provides a method for obtaining database connections.
 */
public class ConnectionFactory {
    private static final String url = "jdbc:postgresql://localhost:5432/ordersmanagement";
    private static final String user = "postgres";
    private static final String password = "51362912a";

    /**
     * Gets a connection to the database.
     *
     * @return a database connection
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new SQLException("Error connecting to the database: " + e.getMessage());
        }
    }
}
