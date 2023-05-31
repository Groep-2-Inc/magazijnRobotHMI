package classes;
import TSP.*;
// Houd de data van het werkeren bij

public class Verwerken {
    static int[] TSPRoute;
    static int[] path;
    static boolean isVerwerken = false;
    static int[] open;
    static int doing;
    static int[] done;
    static Order order;

    static void startVerwerken(Order o){
        order = o;
        isVerwerken = true;

        open = new int[order.getProductCount() + 2];
        done = new int[order.getProductCount() + 2];
    }

}
