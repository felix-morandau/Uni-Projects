package view;

import controller.SimulationManager;
import model.StrategyPolicy;

import javax.swing.*;

public class MenuController {
    private final MenuView view = new MenuView();

    public MenuController() {
        view.setVisible(true);
        view.getStart().addActionListener(e -> startButtonFunctionality());
    }

    private void startButtonFunctionality() {

        try {
            int nrOfClients = Integer.parseInt(view.getNrOfClientsField().getText());
            int nrOfQueues = Integer.parseInt(view.getNrOfQueuesField().getText());
            int minArrivalTime = Integer.parseInt(view.getMinArrivalTimeField().getText());
            int maxArrivalTime = Integer.parseInt(view.getMaxArrivalTimeField().getText());
            int minServiceTime = Integer.parseInt(view.getMinServiceTimeField().getText());
            int maxServiceTime = Integer.parseInt(view.getMaxServiceTimeField().getText());
            int maxSimulationTime = Integer.parseInt(view.getMaxSimulationTimeField().getText());
            StrategyPolicy strategy = (StrategyPolicy) view.getSelectedStrategy().getSelectedItem();

            if (minArrivalTime > maxArrivalTime || minServiceTime > maxServiceTime) {
                throw new IllegalStateException();
            }

            view.setVisible(false);

            new SimulationManager(maxSimulationTime, nrOfQueues, nrOfClients, minArrivalTime, maxArrivalTime, minServiceTime, maxServiceTime, strategy);
        } catch (Exception e) {
            if (e instanceof NullPointerException) {
                JOptionPane.showMessageDialog(view, "Empty field");
            } else if (e instanceof IllegalStateException) {
                JOptionPane.showMessageDialog(view, "Max values can not be smaller than min values");
            } else {
                JOptionPane.showMessageDialog(view, "Invalid field");
            }
        }
    }
}
