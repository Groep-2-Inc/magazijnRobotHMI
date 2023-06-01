package TSP;

public class TSP_Algoritm_1 {
    static int N = TSP_main.routeLength;
    static int[] final_path = new int[N];

    static boolean[] added = new boolean[N];

    public static void TSP_Algoritm(double[][] distances) {
        N -= 1;

        final_path[0] = 0;
        final_path[N] = N;
        added[0] = true;
        added[N] = true;

        int curchecking = 0;
        for (double[] distance : distances) {
            int min = 0;
            for (int i = 0; i < N; i++) {
                if(min == 0 &&  !added[i]){
                    min = i;
                } else if (min != 0){
                    if (distance[i] < distance[min] && i != curchecking && !added[i]) {
                        min = i;
                    }
                }

            }
            if(curchecking < N - 1){
                if(final_path[curchecking + 1] == 0){
                    System.out.println(min);
                    System.out.println(distance[min]);
                    final_path[curchecking + 1] = min;
                    added[min] = true;
                }

            }
            curchecking++;
        }


    }
}