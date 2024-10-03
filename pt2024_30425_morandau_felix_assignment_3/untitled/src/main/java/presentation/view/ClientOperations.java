package presentation.view;

import model.Client;
import presentation.controller.ClientOperationsController;
import presentation.controller.TableController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the GUI for client operations.
 */
public class ClientOperations extends JFrame {
    private final JButton addClient = new JButton("ADD");
    private final JButton deleteClient = new JButton("DELETE");
    private final JButton editClient = new JButton("EDIT");
    private final JButton back = new JButton("BACK");
    private final JLabel firstNameLabel = new JLabel("First Name:");
    private final JTextField firstNameField = new JTextField();
    private final JLabel lastNameLabel = new JLabel("Last Name:");
    private final JTextField lastNameField = new JTextField();
    private final JLabel phoneNrLabel = new JLabel("Phone Number:");
    private final JTextField phoneNrField = new JTextField();
    private final JLabel emailLabel = new JLabel("Email:");
    private final JTextField emailField = new JTextField();
    private JTable table = new JTable();

    /**
     * Constructs the ClientOperations GUI.
     */
    public ClientOperations() {
        setSize(new Dimension(800, 600));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Initialize UI components and set up controller
        new ClientOperationsController(this);

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
     * Creates the content panel.
     *
     * @return The created content panel.
     */
    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(255, 182, 193));
        return contentPanel;
    }

    /**
     * Creates the top panel.
     *
     * @return The created top panel.
     */
    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Client Operations Menu"));
        topPanel.setBackground(new Color(248, 255, 255));
        return topPanel;
    }

    /**
     * Creates the middle panel.
     *
     * @return The created middle panel.
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
     * Creates the field panel.
     *
     * @return The created field panel.
     */
    private JPanel createFieldPanel() {
        JPanel fieldPanel = new JPanel(new GridLayout(1, 4));
        fieldPanel.setBackground(new Color(255, 182, 193));

        JPanel firstNamePanel = createInputPanel(firstNameLabel, firstNameField);
        JPanel lastNamePanel = createInputPanel(lastNameLabel, lastNameField);
        JPanel phoneNrPanel = createInputPanel(phoneNrLabel, phoneNrField);
        JPanel emailPanel = createInputPanel(emailLabel, emailField);

        fieldPanel.add(firstNamePanel);
        fieldPanel.add(lastNamePanel);
        fieldPanel.add(phoneNrPanel);
        fieldPanel.add(emailPanel);

        return fieldPanel;
    }

    /**
     * Creates an input panel.
     *
     * @param label      The label for the input.
     * @param textField  The text field for input.
     * @return           The created input panel.
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
     * Creates the bottom panel.
     *
     * @return The created bottom panel.
     */
    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        back.setBackground(new Color(255, 182, 193));
        addClient.setBackground(new Color(255, 182, 193));
        editClient.setBackground(new Color(255, 182, 193));
        deleteClient.setBackground(new Color(255, 182, 193));

        bottomPanel.add(back);
        bottomPanel.add(addClient);
        bottomPanel.add(editClient);
        bottomPanel.add(deleteClient);
        bottomPanel.setBackground(new Color(248, 255, 255));

        return bottomPanel;
    }

    public JButton getAddClient() {
        return addClient;
    }

    public JButton getDeleteClient() {
        return deleteClient;
    }

    public JButton getEditClient() {
        return editClient;
    }

    public JButton getBack() {
        return back;
    }

    public JTextField getFirstNameField() {
        return firstNameField;
    }

    public JTextField getLastNameField() {
        return lastNameField;
    }

    public JTextField getPhoneNrField() {
        return phoneNrField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTable getTable() {
        return table;
    }

}
