package bll;

import dao.Dao;
import model.Client;

import java.util.List;

/**
 * Business logic layer (BLL) class for managing clients.
 */
public class ClientBLL {
    private List<Client> clients;
    private Dao<Client> clientDao;

    /**
     * Constructs a new ClientBLL object and initializes the DAO for Client objects.
     * Retrieves the list of clients from the database.
     */
    public ClientBLL() {
        clientDao = new Dao<>(Client.class);
        clients = clientDao.retrieveObjects();
    }

    /**
     * Updates the information of a client.
     * @param client The client object to be updated.
     */
    public void updateClient(Client client) {
        clientDao.updateObject(client);
        clients = clientDao.retrieveObjects();
    }

    /**
     * Inserts a new client into the database.
     * @param client The client object to be inserted.
     */
    public void insertClient(Client client) {
        clientDao.insertObject(client);
        clients = clientDao.retrieveObjects();
    }

    /**
     * Deletes a client from the database.
     * @param client The client object to be deleted.
     */
    public void deleteClient(Client client) {
        clientDao.deleteObject(client);
        clients = clientDao.retrieveObjects();
    }

    /**
     * Retrieves the list of clients.
     * @return The list of clients.
     */
    public List<Client> getClients() {
        return clients;
    }
}
