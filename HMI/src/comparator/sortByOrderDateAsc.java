package comparator;
// Door Martjn

import classes.Order;
import java.util.Comparator;

public class sortByOrderDateAsc implements Comparator<Order> {
    public int compare(Order a, Order b) {
        return a.getDate().compareTo(b.getDate());
    }
}
