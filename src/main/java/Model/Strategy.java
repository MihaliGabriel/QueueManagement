package Model;
import View.LiveLogView;
import java.util.*;

public interface Strategy {
    public void addClient(LiveLogView view, List<QueueThread> queues, Client client);
}

