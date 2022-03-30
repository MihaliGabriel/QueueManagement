package Model;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;
import View.LiveLogView;

public class ConcreteStrategyTime implements Strategy{

    @Override
    public void addClient(FileOutputStream stream, LiveLogView view, List<QueueThread> queues, Client client) throws FileNotFoundException {
        PrintStream printStream = new PrintStream(stream);
        int minimumTime = 100000;
        for(QueueThread i : queues) {
            if(minimumTime > i.getWaitingPeriod())
                minimumTime = i.getWaitingPeriod();
        }
        for(QueueThread j : queues) {
            if(minimumTime == j.getWaitingPeriod()) {
                view.addTextArea("Client " + client.toString() +  " added in " + j.getName());
                printStream.println("Client " + client.toString() +  " added in " + j.getName());
                j.addClient(client);
                break;
            }
        }
    }
}
