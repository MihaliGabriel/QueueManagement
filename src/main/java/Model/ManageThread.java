package Model;
import View.LiveLogView;
import View.SimulationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;


public class ManageThread implements Runnable {
    public int nr_clients;
    public int nr_queues;
    public int t_simulation_max;
    public SelectionPolicy selectionPolicy;
    public int min_service;
    public int max_service;
    public int min_arrival;
    public int max_arrival;

    private Scheduler scheduler;
    private List<Client> generatedClients;
    public float averageWaiting;
    private LiveLogView liveLog;
    private static FileOutputStream stream;

    public ManageThread(LiveLogView liveLog, int nr_clients, int nr_queues, int t_simulation_max, SelectionPolicy selectionPolicy, int min_service, int max_service, int min_arrival, int max_arrival) throws FileNotFoundException {
        stream = new FileOutputStream("TP2Results.txt");
        this.liveLog = liveLog;
        this.nr_clients = nr_clients;
        this.nr_queues = nr_queues;
        this.t_simulation_max = t_simulation_max;
        this.selectionPolicy = selectionPolicy;
        this.min_service = min_service;
        this.max_service = max_service;
        this.min_arrival = min_arrival;
        this.max_arrival = max_arrival;
        scheduler = new Scheduler(stream, liveLog, nr_queues, nr_clients);
        generatedClients = Collections.synchronizedList(new ArrayList<Client>());
        generateRandomClients();
        scheduler.changeStrategy(selectionPolicy);
    }

    public void generateRandomClients() {
        Random rand = new Random();
        for (int i = 0; i < nr_clients; i++) {
            int rand_1 = rand.nextInt(10000);
            int rand_2 = rand.nextInt(10000);
            rand_1 = (rand_1 % (max_arrival - min_arrival)) + (min_arrival + 1);
            rand_2 = (rand_2 % (max_service - min_service)) + (min_service + 1);
            Client clientRandom = new Client(i, rand_1, rand_2);
            generatedClients.add(clientRandom);
        }
        Collections.sort(generatedClients, new ObjectComparator());
        System.out.println("Clienti generati random:");
        for(Client c : generatedClients) {
            System.out.println(c.getId() +" "+c.getT_arrival() +" "+ c.getT_service());
        }
    }

    public synchronized void run() {
        int currentTime = 0, count = 0, sum = 0;
        for(Client c : generatedClients) {
            sum = sum + c.getT_service();
            count++;
        }
        averageWaiting = (float)sum / count;
        liveLog.setAverage(String.format("%.2g%n", averageWaiting));
        while (currentTime < t_simulation_max) {
            ArrayList<Client> wantedClients = new ArrayList<Client>();
            System.out.println(currentTime + "\n");
            liveLog.setSeconds(Integer.toString(currentTime));
            for (Client c : generatedClients) {
                if (c.getT_arrival() == currentTime) {
                    try {
                        scheduler.dispatchTask(c);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    wantedClients.add(c);
                }
            }
            for(Client x : wantedClients) {
                generatedClients.remove(x);
            }
            currentTime++;
            try {
                Thread.sleep(1000);
            }catch(InterruptedException e) {};
        }
    }
    public static void main(String[] args) throws IOException {
        SimulationView view = new SimulationView();
        view.start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LiveLogView viewLive = new LiveLogView();
                System.out.println(view.getNrclientitext() + " " +  view.getNrqueuestext() +" " + view.getNrsimulationtext() +" "+ view.getMinimumservicetimetext() +" "+ view.getMaximumservicetimetext() + " "+ view.getMinimumarrivaltimetext() +" "+ view.getMaximumarrivaltimetext());
                ManageThread management = null;
                try {
                    management = new ManageThread(viewLive, view.getNrclientitext(), view.getNrqueuestext(), view.getNrsimulationtext(), view.getSelectionpolicylist(), view.getMinimumservicetimetext(), view.getMaximumservicetimetext(), view.getMinimumarrivaltimetext(), view.getMaximumarrivaltimetext());
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                Thread t = new Thread(management);
                t.start();
                view.closeWindow();
            }
        });
        if(stream != null) {
            stream.close();
        }
    }
}

