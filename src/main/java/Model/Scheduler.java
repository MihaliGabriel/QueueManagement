package Model;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;
import View.LiveLogView;
public class Scheduler {
    private List<QueueThread> queues = new ArrayList<QueueThread>();
    private int maxQueues;
    private int maxClients;
    private Strategy strategy;
    public LiveLogView view;
    private FileOutputStream stream;

    public Scheduler(FileOutputStream stream, LiveLogView view, int maxQueues, int maxClients) {
        this.stream = stream;
        this.view = view;
        this.maxQueues = maxQueues;
        this.maxClients = maxClients;
        for(int i = 0; i < maxQueues; i++) {
            QueueThread thread = new QueueThread(stream, view, "Queue " + i, maxClients);
            queues.add(thread);
            Thread t = new Thread(thread);
            t.start();
        }
    }

    public void changeStrategy(SelectionPolicy policy) {
        if (policy == SelectionPolicy.SHORTEST_QUEUE)
            strategy = new ConcreteStrategyQueue();
        if(policy == SelectionPolicy.SHORTEST_TIME)
            strategy = new ConcreteStrategyTime();
    }

    public void dispatchTask(Client client) throws FileNotFoundException {
        strategy.addClient(stream, view, queues, client);
    }

    public List<QueueThread> getQueues() {
        return queues;
    }
}
