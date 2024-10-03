package controller;

import model.Client;
import model.SimulationQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class QueueManager {
    private List<SimulationQueue> queues;
    private Strategy strategy;
    private double totalWaitTime = 0;

    public QueueManager(int nrOfQueues, Strategy strategy) {
        this.strategy = strategy;
        queues = new ArrayList<>(nrOfQueues);

        for (int i = 0; i < nrOfQueues; i++) {
            queues.add(new SimulationQueue());
        }

        for (SimulationQueue queue : queues) {
            Thread thread = new Thread(queue);

            thread.start();
        }
    }

    public List<Client> enterClient(List<Client> clients, int currentTime) {
        List<Client> remainingClients = new ArrayList<>(clients);
        Iterator<Client> iterator = remainingClients.iterator();

        while (iterator.hasNext()) {
            Client client = iterator.next();
            if (client.getArrivalTime() == currentTime) {
                totalWaitTime += strategy.addClient(queues, client);
                iterator.remove();
            } else {
                break;
            }
        }

        return remainingClients;
    }

    public List<SimulationQueue> getQueues() {
        return queues;
    }

    public void closeAll() {
        for (SimulationQueue queue : queues) {
            queue.close();
        }
    }

    public int getNrOfClientsInQueues() {
        int clientNr = 0;

        for (SimulationQueue queue : queues) {
            clientNr += queue.getClients().size();
        }

        return clientNr;
    }

    public boolean allQueuesEmpty() {
        for (SimulationQueue queue : queues) {
            if (!queue.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    public double getTotalWaitTime() {
        return totalWaitTime;
    }

}
