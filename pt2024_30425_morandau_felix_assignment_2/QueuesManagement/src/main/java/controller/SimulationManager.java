package controller;

import model.Client;
import model.Logs;
import model.SimulationQueue;
import model.StrategyPolicy;
import view.MenuController;
import view.SimulationView;

import javax.swing.*;
import java.util.List;

public class SimulationManager implements Runnable {
    private SimulationView simulationView = new SimulationView();
    private int simulationLimit;
    private int nrOfClients;
    private QueueManager queueManager;
    private List<Client> waitingClients;
    private double avgServiceTime = 0;
    private double avgWaitTime;
    private int peakHour = 0;

    public SimulationManager(int simulationLimit, int nrOfQueues, int nrOfClients, int minArrivalTime, int maxArrivalTime, int minProcessingTime, int maxProcessingTime, StrategyPolicy selectedStrategy) {
        this.simulationLimit = simulationLimit;
        this.nrOfClients = nrOfClients;

        if (selectedStrategy.equals(StrategyPolicy.SHORTEST_TIME)) {
            queueManager = new QueueManager(nrOfQueues, new ShortestTimeStrategy());
        } else {
            queueManager = new QueueManager(nrOfQueues, new ShortestQueueStrategy());
        }

        waitingClients = GenerateRandom.generateRandomClients(nrOfClients, minArrivalTime, maxArrivalTime, minProcessingTime, maxProcessingTime);
        Thread thread = new Thread(this);
        thread.start();
    }

    public void run() {
        int currentTime = 1, peakHourNrOfClients = 0;
        computeAverageServiceTime();

        Logs.clearFile();

        while (currentTime != simulationLimit + 1) {

            if (waitingClients.isEmpty() && queueManager.allQueuesEmpty()) {
                break;
            }
            waitingClients = queueManager.enterClient(waitingClients, currentTime);

            peakHourNrOfClients = computePeakHour(currentTime, peakHourNrOfClients);

            int finalCurrentTime = currentTime;
            SwingUtilities.invokeLater(() -> simulationView.runUI(getMessage(finalCurrentTime, queueManager.getQueues())));

            Logs.addToFile(getMessage(currentTime, queueManager.getQueues()));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            currentTime++;
        }

        avgWaitTime = queueManager.getTotalWaitTime() / (nrOfClients - waitingClients.size());

        SwingUtilities.invokeLater(() -> simulationView.runUI("Simulation stats:\n--Peak hour: " + peakHour + "\n--Average service time:" + avgServiceTime + "\n--Average wait time: " + avgWaitTime));

        Logs.addToFile("Simulation stats:\n--Peak hour: " + peakHour);
        Logs.addToFile("--Average service time: " + avgServiceTime);
        Logs.addToFile("--Average wait time: " + avgWaitTime);

        queueManager.closeAll();
    }

    private int computePeakHour(int currentTime, int peakHourNrOfClients) {
        if (queueManager.getNrOfClientsInQueues() > peakHourNrOfClients) {
            this.peakHour = currentTime;
            peakHourNrOfClients = queueManager.getNrOfClientsInQueues();
        }

        return peakHourNrOfClients;
    }

    private void computeAverageServiceTime() {

        for (Client client : waitingClients) {
            this.avgServiceTime += client.getServiceTime();
        }

        this.avgServiceTime /= waitingClients.size();
    }

    public String getMessage(int currentTime, List<SimulationQueue> queues) {
        StringBuilder message = new StringBuilder();

        message.append("Time:").append(currentTime).append("\n");
        message.append("Waiting clients:\n");
        for (Client client : waitingClients) {
            message.append("(").append(client.getId()).
                    append(", ").
                    append(client.getArrivalTime()).
                    append(", ").
                    append(client.getServiceTime()).
                    append(")");
        }

        int qNr = 1;
        for (SimulationQueue queue : queues) {
            message.append("\nQueue").append(qNr).append(": ");
            for (Client client : queue.getClients()) {
                message.append("(").append(client.getId()).
                        append(", ").
                        append(client.getArrivalTime()).
                        append(", ").
                        append(client.getServiceTime()).
                        append(")").append(", ");
            }

            qNr++;
        }

        return message.append("\n").toString();
    }

    public static void main(String[] args) {
        new MenuController();
    }
}
