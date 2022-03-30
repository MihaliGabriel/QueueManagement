package Model;
import java.util.*;
import View.LiveLogView;

public class ConcreteStrategyTime implements Strategy{

    @Override
    public void addClient(LiveLogView view, List<QueueThread> queues, Client client) {
        int minimumTime = 100000;
        for(QueueThread i : queues) {
            if(minimumTime > i.getWaitingPeriod())
                minimumTime = i.getWaitingPeriod();
        }
        for(QueueThread j : queues) {
            if(minimumTime == j.getWaitingPeriod()) {
                view.addTextArea("Client " + client.toString() +  " added in " + j.getName());
                j.addClient(client);
                break;
            }
        }
    }
}
