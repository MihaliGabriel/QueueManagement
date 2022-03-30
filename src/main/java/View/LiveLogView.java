package View;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class LiveLogView {
    private JTextArea textAreaLive;
    private JLabel liveLog;
    private JTextField secondsText;
    private JLabel seconds;
    private JTextField averageText;
    private JLabel average;
    private JFrame frame;
    private JScrollPane scrollPane;
    public LiveLogView() {
        //construct components
        textAreaLive = new JTextArea (1000, 5);
        liveLog = new JLabel ("Live log");
        secondsText = new JTextField (5);
        seconds = new JLabel ("Seconds");
        averageText = new JTextField (5);
        average = new JLabel ("Average waiting time");
        frame = new JFrame("Live");
        textAreaLive.setEditable(false);
        scrollPane = new JScrollPane(textAreaLive);
        scrollPane.setBounds(60, 50, 310, 375);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        //adjust size and set layout
        frame.setSize (new Dimension (752, 430));
        frame.setLayout (null);
        frame.setVisible(true);

        //add components
        frame.add(scrollPane);
        frame.add(liveLog);
        frame.add(secondsText);
        frame.add(seconds);
        frame.add(averageText);
        frame.add(average);

        //set component bounds (only needed by Absolute Positioning)
        textAreaLive.setBounds (60, 50, 330, 375);
        liveLog.setBounds (170, 20, 140, 30);
        secondsText.setBounds (510, 75, 115, 25);
        seconds.setBounds (510, 50, 100, 25);
        averageText.setBounds (510, 170, 120, 25);
        average.setBounds (510, 145, 130, 25);
    }
    public synchronized void setSeconds(String seconds) {
        secondsText.setText(seconds);
    }
    public synchronized void setAverage(String average) {
        averageText.setText(average);
    }
    public synchronized void addTextArea(String text) {
        textAreaLive.append(text + "\n");
    }
}
