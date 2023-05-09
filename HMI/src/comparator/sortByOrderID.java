package comparator;
// Door Martjn

import classes.Order;
import java.util.Comparator;

public class sortByOrderID implements Comparator<Order> {
    public int compare(Order a, Order b) {
//        System.out.println("Sorted");

        return a.getOrderID() - b.getOrderID();
    }
}
