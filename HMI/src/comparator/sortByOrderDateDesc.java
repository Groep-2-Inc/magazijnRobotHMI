package comparator;
// Door Martjn

import classes.Order;
import java.util.Comparator;

public class sortByOrderDateDesc implements Comparator<Order> {
    public int compare(Order a, Order b) {
        return b.getDate().compareTo(a.getDate());
    }
}
