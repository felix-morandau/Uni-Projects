package controller;

import model.Client;
import model.SimulationQueue;

import java.util.List;

public class ShortestTimeStrategy implements Strategy {

    public int addClient(List<SimulationQueue> queues, Client client) throws NullPointerException {
        SimulationQueue selectedQueue = null;
        int shortestTime = Integer.MAX_VALUE;

        for (SimulationQueue queue : queues) {
            if (queue.getWaitTime().intValue() < shortestTime) {
                shortestTime = queue.getWaitTime().intValue();
                selectedQueue = queue;
            }
        }

        if (selectedQueue == null) {
            throw new NullPointerException("There are no queues.");
        }

        return selectedQueue.addClient(client);
    }
}
