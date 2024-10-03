package presentation.controller;

import bll.ClientBLL;
import model.Client;
import presentation.view.ClientOperations;
import presentation.view.MainMenu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Controller for managing client operations.
 * Directly communicates with the client view and handles the operations for the view.
 */
public class ClientOperationsController {
    private ClientOperations view;
    private ClientBLL clientBLL;
    private Client selectedClient = new Client();
    private TableController<Client> tableController = new TableController<>();

    /**
     * Constructs a ClientOperationsController with the specified view.
     * Initializes the ClientBLL and sets up the initial state of the view.
     * @param view The ClientOperations view.
     */
    public ClientOperationsController(ClientOperations view){
        clientBLL = new ClientBLL();

        this.view = view;
        view.setVisible(true);

        DefaultTableModel model = new DefaultTableModel();
        tableController.fillTable(clientBLL.getClients(), model);
        view.getTable().setModel(model);

        view.getBack().addActionListener(e -> backAction());
        view.getAddClient().addActionListener(e -> addClientAction());
        view.getEditClient().addActionListener(e -> editClientAction());
        view.getDeleteClient().addActionListener(e -> deleteClientAction());
        setupMouseAction();
    }

    /**
     * Handles the action to navigate back to the main menu.
     */
    private void backAction(){
        view.setVisible(false);
        new MainMenu();
    }

    /**
     * Handles the action to add a new client.
     */
    private void addClientAction(){
        try {
            Client newClient = getNewClient();
            clientBLL.insertClient(newClient);
        } catch (IllegalStateException e){
            JOptionPane.showMessageDialog(view, "Fields must not be empty.");
        }

        refreshTable();
        JOptionPane.showMessageDialog(view, "Client added successfully.");
    }

    /**
     * Sets up mouse action listener for the table.
     */
    private void setupMouseAction(){
        view.getTable().addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                mouseEvent(e);
            }
        });
    }

    /**
     * Handles mouse click event on the table.
     * @param event The mouse event.
     */
    private void mouseEvent(java.awt.event.MouseEvent event){
        List<String> rowData = new ArrayList<>();
        int rowNr = view.getTable().getSelectedRow();
        DefaultTableModel tableModel = (DefaultTableModel) view.getTable().getModel();

        for(int i=0 ; i<tableModel.getColumnCount() ; i++){
            rowData.add(tableModel.getValueAt(rowNr, i).toString());
        }

        getSelectedClient(rowData);
    }

    /**
     * Handles the action to edit a client.
     */
    private void editClientAction(){
        modifySelectedClient();
        clientBLL.updateClient(selectedClient);
        refreshTable();
        JOptionPane.showMessageDialog(view, "Client updated successfully");
    }

    /**
     * Handles the action to delete a client.
     */
    private void deleteClientAction(){
        clientBLL.deleteClient(selectedClient);
        refreshTable();
        JOptionPane.showMessageDialog(view, "Client deleted successfully.");
    }

    /**
     * Modifies the selected client based on the input fields.
     */
    private void modifySelectedClient(){
        String firstName = view.getFirstNameField().getText();
        String lastName = view.getLastNameField().getText();
        String phoneNr = view.getPhoneNrField().getText();
        String email = view.getEmailField().getText();

        if(!Objects.equals(firstName, "")){
            selectedClient.setFirst_name(firstName);
        }

        if(!lastName.isEmpty()){
            selectedClient.setLast_name(lastName);
        }

        if(!Objects.equals(phoneNr, "")){
            selectedClient.setPhone_nr(phoneNr);
        }

        if(!email.isEmpty()){
            selectedClient.setEmail(email);
        }
    }

    /**
     * Retrieves the selected client from the table data.
     * @param clientData The data of the selected client.
     */
    private void getSelectedClient(List<String> clientData){
        int iterator = 0;

        selectedClient.setId(Integer.parseInt(clientData.get(iterator++)));
        selectedClient.setFirst_name(clientData.get(iterator++));
        selectedClient.setLast_name(clientData.get(iterator++));
        selectedClient.setPhone_nr(clientData.get(iterator++));
        selectedClient.setEmail(clientData.get(iterator));
    }

    /**
     * Retrieves a new client from the input fields.
     * @return The new client object.
     * @throws IllegalStateException If any of the required fields is empty.
     */
    private Client getNewClient(){
        String firstName = view.getFirstNameField().getText();
        String lastName = view.getLastNameField().getText();
        String phoneNr = view.getPhoneNrField().getText();
        String email = view.getEmailField().getText();

        if(firstName.isEmpty() || lastName.isEmpty() || phoneNr.isEmpty() || email.isEmpty()){
            throw new IllegalStateException();
        }

        return new Client(firstName, lastName, phoneNr, email);
    }

    /**
     * Refreshes the table with updated data.
     */
    private void refreshTable(){
        DefaultTableModel model = new DefaultTableModel();
        tableController.fillTable(clientBLL.getClients(), model);
        view.getTable().setModel(model);
    }
}
