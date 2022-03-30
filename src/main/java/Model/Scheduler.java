package Model;
import java.util.*;
import View.LiveLogView;
public class Scheduler {
    private List<QueueThread> queues = new ArrayList<QueueThread>();
    private int maxQueues;
    private int maxClients;
    private Strategy strategy;
    public LiveLogView view;

    public Scheduler(LiveLogView view, int maxQueues, int maxClients) {
        this.view = view;
        this.maxQueues = maxQueues;
        this.maxClients = maxClients;
        for(int i = 0; i < maxQueues; i++) {
            QueueThread thread = new QueueThread(view, "Queue " + i, maxClients);
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

    public void dispatchTask(Client client) {
        strategy.addClient(view, queues, client);
    }

    public List<QueueThread> getQueues() {
        return queues;
    }
}
