package controller;

import model.Client;
import model.SimulationQueue;

import java.util.List;

public class ShortestQueueStrategy implements Strategy {

    public int addClient(List<SimulationQueue> queues, Client client) {
        SimulationQueue selectedQueue = null;
        int minElements = Integer.MAX_VALUE;

        for (SimulationQueue queue : queues) {
            if (queue.getSize() < minElements) {
                minElements = queue.getSize();
                selectedQueue = queue;
            }
        }

        if (selectedQueue != null) {
            return selectedQueue.addClient(client);
        } else {
            throw new NullPointerException("There are no queues.");
        }
    }
}
