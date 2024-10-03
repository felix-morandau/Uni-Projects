package bll;

import model.Orderr;
import model.Bill;
import model.Client;
import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderBLLTest {
    private OrderBLL orderBLL;

    @BeforeEach
    void setUp() {
        orderBLL = new OrderBLL();
    }

    @Test
    void testCreateOrder() {
        Client client = new Client("Mara", "Oprisor", "0742563438", "mara@gmail.com");
        Product product = new Product("Laptop", 1000, 10);

        ProductBLL productBLL = new ProductBLL();
        productBLL.insertProduct(product);

        List<Bill> initialBills = orderBLL.getBills();
        int initialBillsSize = initialBills.size();

        orderBLL.createOrder(client, product, 2);

        List<Bill> updatedBills = orderBLL.getBills();
        int updatedBillsSize = updatedBills.size();

        assertEquals(initialBillsSize + 1, updatedBillsSize);
        assertEquals("John Doe", updatedBills.get(updatedBillsSize - 1).clientName());
        assertEquals("Laptop", updatedBills.get(updatedBillsSize - 1).productName());
        assertEquals(2000, updatedBills.get(updatedBillsSize - 1).price());

        productBLL.deleteProduct(product);
    }

    @Test
    void testGetOrders() {
        List<Orderr> orders = orderBLL.getOrders();

        assertEquals(false, orders.isEmpty());
    }

    @Test
    void testGetBills() {
        List<Bill> bills = orderBLL.getBills();

        assertEquals(false, bills.isEmpty());
    }
}
