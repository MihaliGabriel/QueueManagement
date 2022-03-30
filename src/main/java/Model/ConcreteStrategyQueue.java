package Model;

import View.LiveLogView;
import java.util.List;

public class ConcreteStrategyQueue implements Strategy{

    @Override
    public void addClient(LiveLogView view, List<QueueThread> queues, Client client) {
        int minimumSize = 100000;
        for(QueueThread i : queues) {
            if(minimumSize > i.getClients().size())
                minimumSize = i.getClients().size();
        }
        for(QueueThread j : queues) {
            if(minimumSize == j.getClients().size()) {
                view.addTextArea("Client " + client.toString() +  " added in " + j.getName());
                j.addClient(client);
                break;
            }
        }
    }

}

