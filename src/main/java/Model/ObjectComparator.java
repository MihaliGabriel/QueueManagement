package Model;

import java.util.Comparator;

public class ObjectComparator implements Comparator {
    @Override
    public int compare(Object client1, Object client2) {
        Client myclient1 = (Client)client1;
        Client myclient2 = (Client)client2;
        int value = 0;
        if(myclient1.getT_arrival() == myclient2.getT_arrival())
            value = 0;
        if(myclient1.getT_arrival() < myclient2.getT_arrival())
            value = -1;
        if(myclient1.getT_arrival() > myclient2.getT_arrival())
            value = 1;
        return value;
    }
}
