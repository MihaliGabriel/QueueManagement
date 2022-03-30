package Model;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import View.LiveLogView;

public class QueueThread implements Runnable {
    private BlockingQueue<Client> clients;
    private AtomicInteger waitingPeriod;
    private int max_clients;
    private String name;
    public LiveLogView logView;
    private FileOutputStream stream;

    QueueThread(FileOutputStream stream, LiveLogView logView, String name, int max_clients) {
        this.stream = stream;
        this.logView = logView;
        waitingPeriod = new AtomicInteger(0);
        clients = new ArrayBlockingQueue<Client>(max_clients);
        this.name = name;
    }

    public void addClient(Client client) {
        clients.add(client);
        waitingPeriod.addAndGet(client.getT_service());
    }

    public synchronized void run() {
        PrintStream print = new PrintStream(stream);
        while(true) {
            if (clients.size() > 0) {
                Client client = clients.element();
                try {
                    Thread.sleep(1000 * client.getT_service());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                clients.remove(client);
                logView.addTextArea("Client " + client.toString() + "removed from " + name);
                print.println("Client " + client.toString() + "removed from " + name);

            }
        }
    }

    public BlockingQueue<Client> getClients() {
        return clients;
    }

    public int getWaitingPeriod() {
        return waitingPeriod.get();
    }
    public String getName() {
        return name;
    }
}

