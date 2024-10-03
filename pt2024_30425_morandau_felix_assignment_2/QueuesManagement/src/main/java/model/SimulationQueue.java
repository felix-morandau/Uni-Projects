package model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class SimulationQueue implements Runnable {
    private final BlockingQueue<Client> clients = new LinkedBlockingQueue<>();
    private final AtomicInteger waitTime = new AtomicInteger(0);
    private boolean closed = false;

    public void run() {
        while (!closed) {

            if (!clients.isEmpty()) {
                Client currentClient = clients.peek();

                if (waitTime.intValue() > 0) {
                    waitTime.set(waitTime.intValue() - 1);
                }
                currentClient.setServiceTime(currentClient.getServiceTime() - 1);

                if (currentClient.getServiceTime() == 0) {
                    clients.remove();
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int addClient(Client client) {
        int waits = waitTime.intValue();

        clients.add(client);
        waitTime.set(waitTime.intValue() + client.getServiceTime());

        return waits;
    }

    public AtomicInteger getWaitTime() {
        return this.waitTime;
    }

    public int getSize() {
        return clients.size();
    }

    public boolean isEmpty() {
        return clients.isEmpty();
    }

    public BlockingQueue<Client> getClients() {
        return clients;
    }

    public void close() {
        this.closed = true;
    }
}
