package bll;

import model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientBLLTest {
    private ClientBLL clientBLL;

    @BeforeEach
    void setUp() {
        clientBLL = new ClientBLL();
    }

    @Test
    void testInsertClient() {
        List<Client> initialClients = clientBLL.getClients();
        int initialSize = initialClients.size();

        Client newClient = new Client("John", "Doe", "123456789", "john@example.com");

        clientBLL.insertClient(newClient);

        List<Client> updatedClients = clientBLL.getClients();
        int updatedSize = updatedClients.size();

        assertEquals(initialSize + 1, updatedSize);

        assertEquals(newClient.getFirst_name() + newClient.getLast_name(), updatedClients.get(updatedSize - 1).getFirst_name() + updatedClients.get(updatedSize - 1).getLast_name());

        clientBLL.deleteClient(newClient);
    }

    @Test
    void testUpdateClient() {
        Client newClient = new Client("John", "Doe", "123456789", "john@example.com");

        clientBLL.insertClient(newClient);

        List<Client> updatedClients = clientBLL.getClients();

        Client clientToUpdate = updatedClients.get(updatedClients.size() - 1);
        clientToUpdate.setFirst_name("Jane");
        clientToUpdate.setLast_name("Smith");

        clientBLL.updateClient(clientToUpdate);

        List<Client> updatedClientsAfterUpdate = clientBLL.getClients();

        assertEquals("Jane", updatedClientsAfterUpdate.get(updatedClientsAfterUpdate.size() - 1).getFirst_name());
        assertEquals("Smith", updatedClientsAfterUpdate.get(updatedClientsAfterUpdate.size() - 1).getLast_name());

        clientBLL.deleteClient(clientToUpdate);
    }

    @Test
    void testDeleteClient() {
        // Create a new client
        Client newClient = new Client("John", "Doe", "123456789", "john@example.com");

        // Insert the new client
        clientBLL.insertClient(newClient);

        // Retrieve the updated list of clients
        List<Client> updatedClients = clientBLL.getClients();
        int initialSize = updatedClients.size();

        // Delete the last inserted client
        clientBLL.deleteClient(updatedClients.get(initialSize - 1));

        // Retrieve the list of clients after deletion
        List<Client> updatedClientsAfterDeletion = clientBLL.getClients();
        int updatedSize = updatedClientsAfterDeletion.size();

        // Assert that the size of the list has decreased by 1 after deletion
        assertEquals(initialSize - 1, updatedSize);
    }

    @Test
    void testGetClients() {
        // Retrieve the list of clients
        List<Client> clients = clientBLL.getClients();

        // Assert that the retrieved list is not null
        // and contains at least one client (if any exists in the database)
        assertEquals(false, clients.isEmpty());
    }
}
