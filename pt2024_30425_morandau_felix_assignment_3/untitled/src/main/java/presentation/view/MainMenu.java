package presentation.view;

import presentation.controller.MainMenuController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Represents the main menu of the Orders' Management App.
 */
public class MainMenu extends JFrame {

    private final JButton enterClientOperations = new JButton("Client Operations");
    private final JButton enterProductOperations = new JButton("Product Operations");
    private final JButton enterOrderOperations = new JButton("Order Operations");
    private final JLabel title = new JLabel("Orders' Management App");

    /**
     * Constructs the main menu frame.
     */
    public MainMenu() {
        setSize(new Dimension(800, 600));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initUI();

        new MainMenuController(this);
    }

    /**
     * Initializes the user interface.
     */
    private void initUI() {
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 17));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        enterClientOperations.setBackground(new Color(255, 255, 190));
        enterProductOperations.setBackground(new Color(255, 255, 190));
        enterOrderOperations.setBackground(new Color(255, 255, 190));

        JPanel mainPanel = new JPanel(new GridLayout(6, 1, 10, 50));

        mainPanel.setBackground(new Color(255, 182, 193));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setSize(new Dimension(300, 600));

        mainPanel.add(title);
        mainPanel.add(new JLabel());
        mainPanel.add(enterClientOperations);
        mainPanel.add(enterProductOperations);
        mainPanel.add(enterOrderOperations);
        mainPanel.add(new JLabel());

        JPanel contentPanel = new JPanel(new GridLayout(1, 3));
        contentPanel.setBackground(new Color(255, 182, 193));

        contentPanel.add(new JLabel());
        contentPanel.add(mainPanel);
        contentPanel.add(new JLabel());

        setContentPane(contentPanel);
    }

    /**
     * Gets the button for entering client operations.
     *
     * @return The button for client operations.
     */
    public JButton getEnterClientOperations() {
        return enterClientOperations;
    }

    /**
     * Gets the button for entering product operations.
     *
     * @return The button for product operations.
     */
    public JButton getEnterProductOperations() {
        return enterProductOperations;
    }

    /**
     * Gets the button for entering order operations.
     *
     * @return The button for order operations.
     */
    public JButton getEnterOrderOperations() {
        return enterOrderOperations;
    }
}
