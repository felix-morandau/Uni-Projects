package controller;

import model.Client;
import model.SimulationQueue;

import java.util.List;

public interface Strategy {
    int addClient(List<SimulationQueue> queues, Client client);
}
