package comparator;
// Door Martjn

import classes.Order;
import java.util.Comparator;

public class sortByOrderCompletedTrue implements Comparator<Order> {
    public int compare(Order a, Order b) {
        boolean orderCompletedA = a.isOrderCompleted();
        boolean orderCompletedB = b.isOrderCompleted();

        if (orderCompletedA == orderCompletedB) {
            return 0;
        } else if (!orderCompletedA) {
            return 1;
        } else {
            return -1;
        }
    }
}
