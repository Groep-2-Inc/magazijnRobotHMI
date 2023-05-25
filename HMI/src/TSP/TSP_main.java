package TSP;

public class TSP_main {
    static int N = 5;

    public static void main(String[] args){
        String[] matrix = new String[N];
        matrix[0] = "A1";
        matrix[1] = "A5";
        matrix[2] = "A3";
        matrix[3] = "A4";
        matrix[4] = "A6";

        N = matrix.length;


        int[][] xycors;
        xycors = TSP_GetMatrix.getCordinates(matrix);

        for (int[] xy: xycors) {
            for (int xory: xy) {
                System.out.print(xory + ", ");
            }
            System.out.print("\n");
        }

        double[][] distances = TSP_GetMatrix.getDistances(xycors);

        for (double[] xy: distances) {
            for (double distance: xy) {
                System.out.print(distance + ", ");
            }
            System.out.print("\n");
        }

        TSP_Algoritm_2.TSP_Algoritm(distances);
        System.out.println("Path Taken : ");
        for (int i = 0; i <= TSP_Algoritm_2.N; i++)
        {
            System.out.printf("%d ", (TSP_Algoritm_2.final_path[i]+1));
        }

    }
}
