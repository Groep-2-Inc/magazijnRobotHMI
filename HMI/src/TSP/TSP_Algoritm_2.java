package TSP;

public class TSP_Algoritm_2 {
    static int N = TSP_main.N;
    static int[] final_path = new int[N];

    static boolean[] added = new boolean[N];

    public static void TSP_Algoritm(double[][] distances) {
        N -= 1;

        final_path[0] = 0;
        final_path[N] = N;
        added[0] = true;
        added[N] = true;

        if(N == 2){
            final_path[1] = 1;
            added[1] = true;
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
            final_path[1] = min;
            added[min] = true;

            int count = 0;
            for (boolean add: added) {
                if(!add){
                    final_path[2] = count;
                }
                count ++;
            }
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
            final_path[1] = min;
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
            final_path[N-1] = min;
            added[min] = true;

            int count = 0;
            for (boolean add: added) {
                if(!add){
                    final_path[2] = count;
                }
                count ++;
            }
        }

    }
}
