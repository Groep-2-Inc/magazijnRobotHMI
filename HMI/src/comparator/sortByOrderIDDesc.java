package comparator;
// Door Martjn

import classes.Order;
import java.util.Comparator;

public class sortByOrderIDDesc implements Comparator<Order> {
    public int compare(Order a, Order b) {
        return b.getOrderID() - a.getOrderID();
    }
}
