package TSP;
///---------------------------------------------------------------------CODE GEMAAKT DOOR JASON JOSHUA VAN DER KOLK---------------------------------------------------------------------\\\
public class TSP_Algoritm_2 {
    //int voor de lengte van de route
    static int N = TSP_main.routeLength;

    //int array met het uiteindelijke pad
    static int[] finalPath = new int[N];

    //boolean voor het bijhouden of bepaalde vakjes al toegevoegd zijn in het pad.
    static boolean[] added = new boolean[N];

    //het hardcoded tsp algoritme.
    public static void TSP_Algoritm(double[][] distances) {
        //n naar n-1 maken om goed te werken met de arrays
        N -= 1;

        //zet de eerste en de laatste coordinaat op de eerste en laatste. deze zijn altijd hetzelfde
        finalPath[0] = 0;
        finalPath[N] = N;
        added[0] = true;
        added[N] = true;

        //als er maar 3 records zijn, zet het 2de record dan gelijk aan het 2de record
        if(N == 2){
            finalPath[1] = 1;
            added[1] = true;

            //als er 4 records zijn, kijk dan welke record er het dichtst bij het begin zit, zet deze vervolgens op de tweede plek, en zet de overgebleven record op de laatste plek.
        } else if(N == 3){
            double[] beginning = distances[0];
            int min = 0;
            for (int i = 1; i < N; i++ ) {
                if(beginning[i] > beginning[min]){
                    min = i;
                } else if (min == 0){
                    min = i;
                }
            }
            finalPath[1] = min;
            added[min] = true;

            int count = 0;
            for (boolean add: added) {
                if(!add){
                    finalPath[2] = count;
                }
                count ++;
            }

            //als er 5 records zijn, kijk eerst weer welke er het dichtst bij het begin zit, kijk vervolgens welke er het dichstbij het eind zit en voeg vervolgens op de derde plek het laatste record toe dat nog toegevoegd moet worden.
        } else if(N == 4){
            double[] beginning = distances[0];
            int min = 0;
            for (int i = 1; i < N; i++ ) {
                if(beginning[i] < beginning[min]){
                    min = i;
                } else if (min == 0){
                    min = i;
                }
            }
            finalPath[1] = min;
            added[min] = true;

            double[] ending = distances[N];
            min = 0;
            for (int i = 1; i < N-1; i++ ) {
                if(ending[i] < ending[min]){
                    min = i;
                } else if (min == 0){
                    min = i;
                }
            }
            finalPath[N-1] = min;
            added[min] = true;

            int count = 0;
            for (boolean add: added) {
                if(!add){
                    finalPath[2] = count;
                }
                count ++;
            }
        }

    }
}
