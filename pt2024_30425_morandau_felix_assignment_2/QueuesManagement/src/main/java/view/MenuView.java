package view;

import model.StrategyPolicy;

import javax.swing.*;
import java.awt.*;

public class MenuView extends JFrame {

    private final JLabel nrOfClients = new JLabel("Nr of clients");
    private final JLabel nrOfQueues = new JLabel("Nr of queues");
    private final JLabel maxSimulationTime = new JLabel("Max simulation time");
    private final JLabel minArrivalTime = new JLabel("Min arrival time");
    private final JLabel maxArrivalTime = new JLabel("Max arrival time");
    private final JLabel minServiceTime = new JLabel("Min service time");
    private final JLabel maxServiceTime = new JLabel("Max service time");
    private final JLabel strategy = new JLabel("Select strategy");
    private final JTextField nrOfClientsField = new JTextField();
    private final JTextField maxServiceTimeField = new JTextField();
    private final JTextField minServiceTimeField = new JTextField();
    private final JTextField nrOfQueuesField = new JTextField();
    private final JTextField maxSimulationTimeField = new JTextField();
    private final JTextField minArrivalTimeField = new JTextField();
    private final JTextField maxArrivalTimeField = new JTextField();
    private final JComboBox<StrategyPolicy> selectedStrategy = new JComboBox<>(new StrategyPolicy[]{StrategyPolicy.SHORTEST_TIME, StrategyPolicy.SHORTEST_QUEUE});
    private final JButton start = new JButton("Start");

    public MenuView() {
        setSize(new Dimension(400, 400));
        initUI();
    }

    private void initUI() {
        JPanel contentPanel = new JPanel(new BorderLayout());

        JPanel leftPanel = new JPanel(new GridLayout(8, 1));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adding some padding

        leftPanel.add(nrOfClients);
        leftPanel.add(nrOfQueues);
        leftPanel.add(maxSimulationTime);
        leftPanel.add(minArrivalTime);
        leftPanel.add(maxArrivalTime);
        leftPanel.add(minServiceTime);
        leftPanel.add(maxServiceTime);
        leftPanel.add(strategy);


        JPanel rightPanel = new JPanel(new GridLayout(8, 1));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        rightPanel.add(nrOfClientsField);
        rightPanel.add(nrOfQueuesField);
        rightPanel.add(maxSimulationTimeField);
        rightPanel.add(minArrivalTimeField);
        rightPanel.add(maxArrivalTimeField);
        rightPanel.add(minServiceTimeField);
        rightPanel.add(maxServiceTimeField);
        rightPanel.add(selectedStrategy);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(start);

        contentPanel.add(leftPanel, BorderLayout.WEST);
        contentPanel.add(rightPanel, BorderLayout.CENTER);
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(contentPanel);

        setTitle("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JTextField getNrOfClientsField() {
        return nrOfClientsField;
    }

    public JTextField getMaxServiceTimeField() {
        return maxServiceTimeField;
    }

    public JTextField getMinServiceTimeField() {
        return minServiceTimeField;
    }

    public JTextField getNrOfQueuesField() {
        return nrOfQueuesField;
    }

    public JTextField getMaxSimulationTimeField() {
        return maxSimulationTimeField;
    }

    public JTextField getMinArrivalTimeField() {
        return minArrivalTimeField;
    }

    public JTextField getMaxArrivalTimeField() {
        return maxArrivalTimeField;
    }

    public JButton getStart() {
        return start;
    }

    public JComboBox<StrategyPolicy> getSelectedStrategy() {
        return selectedStrategy;
    }
}
