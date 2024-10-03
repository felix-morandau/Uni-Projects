package controller;

import model.Client;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GenerateRandom {
    private static final Random random = new Random();
    private static int clientNumber = 1;

    public static Client generateRandomClient(int minArrivalTime, int maxArrivalTime, int minProcessingTime, int maxProcessingTime) {
        int id = clientNumber++;
        int arrivalTime = random.nextInt(maxArrivalTime - minArrivalTime) + minArrivalTime;
        int serviceTime = random.nextInt(maxProcessingTime - minProcessingTime) + minProcessingTime;

        return new Client(id, arrivalTime, serviceTime);
    }

    public static List<Client> generateRandomClients(int nrOfClients, int minArrivalTime, int maxArrivalTime, int minProcessingTime, int maxProcessingTime) {
        List<Client> generatedClients = new ArrayList<>();

        for (int i = 0; i < nrOfClients; i++) {
            generatedClients.add(generateRandomClient(minArrivalTime, maxArrivalTime, minProcessingTime, maxProcessingTime));
        }

        Collections.sort(generatedClients);

        return generatedClients;
    }
}
