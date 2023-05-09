package comparator;
// Door Martjn

import classes.Order;
import java.util.Comparator;

public class sortByOrderIDAsc implements Comparator<Order> {
    public int compare(Order a, Order b) {
        return a.getOrderID() - b.getOrderID();
    }
}
