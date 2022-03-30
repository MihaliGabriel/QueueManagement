
package View;
import Model.SelectionPolicy;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class SimulationView {
     JTextField maximumarrivaltimetext;
     JTextField minimumarrivaltimetext;
     JTextField minimumservicetimetext;
     JTextField maximumservicetimetext;
     JLabel minimumarrival;
     JLabel maximumarrival;
     JLabel minimumservice;
     JLabel maximumservice;
     JTextField nrqueuestext;
     JLabel nrqueues;
     JTextField nrsimulationtext;
     JLabel nrmaximsim;
     JComboBox selectionpolicylist;
     JLabel selectionpolicy;
     JTextField nrclientitext;
     JLabel nrclients;
     public JButton start;
     JLabel simulationmanager;
     JFrame frame;

    public int getMaximumarrivaltimetext() {
        return Integer.parseInt(maximumarrivaltimetext.getText());
    }

    public int getMinimumarrivaltimetext() {
        return Integer.parseInt(minimumarrivaltimetext.getText());
    }

    public int getMinimumservicetimetext() {
        return Integer.parseInt(minimumservicetimetext.getText());
    }

    public int getMaximumservicetimetext() {
        return Integer.parseInt(maximumservicetimetext.getText());
    }

    public int  getNrqueuestext() {
        return Integer.parseInt(nrqueuestext.getText());
    }

    public int getNrsimulationtext() {
        return Integer.parseInt(nrsimulationtext.getText());
    }

    public SelectionPolicy getSelectionpolicylist() {
        if(selectionpolicylist.getSelectedItem().toString().equals("SHORTEST_QUEUE"))
            return SelectionPolicy.SHORTEST_QUEUE;
        else return SelectionPolicy.SHORTEST_TIME;

    }

    public int getNrclientitext() {
        if(nrclientitext.getText().length() > 0)
            return Integer.parseInt(nrclientitext.getText());
        else return 0;
    }
    public void closeWindow() {
        frame.setVisible(false);
    }

    public SimulationView() {
        //construct preComponents
        String[] selectionpolicylistItems = {"SHORTEST_QUEUE", "SHORTEST_TIME"};
        frame = new JFrame("SimulationInput");
        //construct components
        maximumarrivaltimetext = new JTextField (5);
        minimumarrivaltimetext = new JTextField (5);
        minimumservicetimetext = new JTextField (5);
        maximumservicetimetext = new JTextField (5);
        minimumarrival = new JLabel ("Minimum arrival time");
        maximumarrival = new JLabel ("Maximum arrival time");
        minimumservice = new JLabel ("Minimum service time");
        maximumservice = new JLabel ("Maximum service time");
        nrqueuestext = new JTextField (5);
        nrqueues = new JLabel ("Numar queues");
        nrsimulationtext = new JTextField (5);
        nrmaximsim = new JLabel ("Numar maxim simulare");
        selectionpolicylist = new JComboBox (selectionpolicylistItems);
        selectionpolicy = new JLabel ("Selection policy");
        nrclientitext = new JTextField (5);
        nrclients = new JLabel ("Numar clienti");
        start = new JButton ("Start");
        simulationmanager = new JLabel ("Simulation manager");

        //adjust size and set layout
        frame.setSize (new Dimension (752, 430));
        frame.setLayout (null);
        frame.setVisible(true);

        //add components
        frame.add(maximumarrivaltimetext);
        frame.add(minimumarrivaltimetext);
        frame.add(minimumservicetimetext);
        frame.add(maximumservicetimetext);
        frame.add(minimumarrival);
        frame.add(maximumarrival);
        frame.add(minimumservice);
        frame.add(maximumservice);
        frame.add(nrqueuestext);
        frame.add(nrqueues);
        frame.add(nrsimulationtext);
        frame.add(nrmaximsim);
        frame.add(selectionpolicylist);
        frame.add(selectionpolicy);
        frame.add(nrclientitext);
        frame.add(nrclients);
        frame.add(start);
        frame.add(simulationmanager);

        //set component bounds (only needed by Absolute Positioning)
        maximumarrivaltimetext.setBounds (90, 170, 125, 25);
        minimumarrivaltimetext.setBounds (90, 80, 125, 25);
        minimumservicetimetext.setBounds (470, 75, 130, 25);
        maximumservicetimetext.setBounds (470, 165, 130, 25);
        minimumarrival.setBounds (90, 55, 120, 30);
        maximumarrival.setBounds (90, 145, 120, 25);
        minimumservice.setBounds (470, 50, 125, 25);
        maximumservice.setBounds (470, 140, 135, 25);
        nrqueuestext.setBounds (150, 270, 100, 25);
        nrqueues.setBounds (150, 245, 100, 25);
        nrsimulationtext.setBounds (405, 270, 140, 25);
        nrmaximsim.setBounds (405, 245, 135, 25);
        selectionpolicylist.setBounds (265, 190, 145, 25);
        selectionpolicy.setBounds (290, 165, 100, 25);
        nrclientitext.setBounds (295, 110, 100, 25);
        nrclients.setBounds (305, 90, 100, 25);
        start.setBounds (280, 345, 100, 25);
        simulationmanager.setBounds (286, 5, 120, 40);

    }
}
