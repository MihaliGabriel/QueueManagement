package Model;
import View.LiveLogView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;

public interface Strategy {
    public void addClient(FileOutputStream stream, LiveLogView view, List<QueueThread> queues, Client client) throws FileNotFoundException;
}

