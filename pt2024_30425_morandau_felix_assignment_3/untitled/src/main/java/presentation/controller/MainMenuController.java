package presentation.controller;

import presentation.view.ClientOperations;
import presentation.view.MainMenu;
import presentation.view.OrderOperations;
import presentation.view.ProductOperations;

/**
 * Controller for managing actions on the main menu.
 */
public class MainMenuController {
    private final MainMenu view;

    /**
     * Constructs a MainMenuController with the specified MainMenu view.
     *
     * @param view The MainMenu view associated with this controller.
     */
    public MainMenuController(MainMenu view) {
        this.view = view;
        view.setVisible(true);

        view.getEnterClientOperations().addActionListener(e -> clientOperationButton());
        view.getEnterProductOperations().addActionListener(e -> productOperationsButton());
        view.getEnterOrderOperations().addActionListener(e -> orderOperationsButton());
    }

    /**
     * Switches to the client operations view when the client operations button is clicked.
     */
    private void clientOperationButton() {
        view.setVisible(false);
        new ClientOperations();
    }

    /**
     * Switches to the product operations view when the product operations button is clicked.
     */
    public void productOperationsButton() {
        view.setVisible(false);
        new ProductOperations();
    }

    /**
     * Switches to the order operations view when the order operations button is clicked.
     */
    public void orderOperationsButton() {
        view.setVisible(false);
        new OrderOperations();
    }
}
