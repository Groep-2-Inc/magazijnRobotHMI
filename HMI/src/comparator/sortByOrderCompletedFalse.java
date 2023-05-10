package comparator;
// Door Martjn

import classes.Order;
import java.util.Comparator;

public class sortByOrderCompletedFalse implements Comparator<Order> {
    public int compare(Order a, Order b) {
        int orderCompletedA = a.isOrderCompleted();
        int orderCompletedB = b.isOrderCompleted();

        if (orderCompletedA == orderCompletedB) {
            return 0;
        } else if (orderCompletedA == 0) {
            return -1;
        } else {
            return 1;
        }
    }
}
