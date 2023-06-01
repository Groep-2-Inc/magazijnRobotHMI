package classes;
import TSP.*;
// Houd de data van het werkeren bij

public class Verwerken {
    private static int[] TSPRoute;
    private static int[] path;
    private static boolean isVerwerken = false;
//    private static boolean[] open;
    private static int doing;
    private static boolean[] done;
    private static Order order;

    public static void startVerwerken(Order o){
        order = o;
        isVerwerken = true;
        doing = 1;

        TSPRoute = TSP_main.main(getProductCords());
        path = TSP_main.getFinalPath();

//        open = new boolean[order.getProductCount() + 2];
        done = new boolean[order.getProductCount() + 2];

        pickProduct();

    }

    static void nextProduct(){
        done[doing] = true;
        doing++;

        if(doing == TSPRoute.length ){
            System.out.println("klaar");
            Robot.setIsMoving(false);
            Robot.setRobotStatus(201);
        }else{
            pickProduct();
        }
    }

    static void pickProduct() {
//        System.out.println(TSPRoute[doing]);

        Communication.sendComms(TSPRoute[doing]);
    }

    // Returnt de locatie van de producten (door martijn)
    public static String[] getProductCords(){
        // Maakt leeg een lege array aan
        String[] cordsArray = new String[order.getProducts().size() + 2];

        // Zet de eerste positie
        cordsArray[0] = "A1";
        // Loopt alle orders langs
        for(int i = 0; i < order.getProducts().size(); i++){
            // Zet de locatie van de producten in de cordsArray
            cordsArray[i + 1] = order.getProducts().get(i).getBinLocation();
        }
        // Zet de laatste positie in de array
        cordsArray[order.getProducts().size() + 1] = "A6";

        // Stuurt de waarde terug.
        return cordsArray;
    }

}
