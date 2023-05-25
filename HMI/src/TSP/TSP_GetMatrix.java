package TSP;

public class TSP_GetMatrix {
    public static int[][] getCordinates(String[] Cors) {
        //maak nieuwe array aan voor alle x en y posities
        int[][] pointxy = new int[TSP_main.N][2];
        int i = 0;
//        for (int i = 0; i < TSP_main.N; i++) {
            for (String line : Cors) {
                if(line.contains("A")){
                    pointxy[i][0] = 1;
                } else if (line.contains("B")) {
                    pointxy[i][0] = 2;
                }else if (line.contains("C")) {
                    pointxy[i][0] = 3;
                }else if (line.contains("D")) {
                    pointxy[i][0] = 4;
                }else if (line.contains("E")) {
                    pointxy[i][0] = 5;
                }

                if(line.contains("1")){
                    pointxy[i][1] = 1;
                }else if (line.contains("2")){
                    pointxy[i][1] = 2;
                }else if (line.contains("3")){
                    pointxy[i][1] = 3;
                }else if (line.contains("4")){
                    pointxy[i][1] = 4;
                }else if (line.contains("5")){
                    pointxy[i][1] = 5;
                }else if (line.contains("6")){
                    pointxy[i][1] = 6;
                }
                i++;
            }
//        }
        //return een matrix met alle x en y punten
        return pointxy;
    }

    public static double[][] getDistances(int[][] cordinates){
        //int om het huidige punt bij te houden
        int current = 0;
        //een nieuwe matrix maken voor de afstanden
        double[][] distances = new double[TSP_main.N][TSP_main.N];

        //for loop voor het huidige punt
        for (int[] points: cordinates) {
            //krijg de x en y positie van het huidig punt
            int x1 = points[0];
            int y1 = points[1];

            //for loop voor het berekenen van de afstand tot de andere punten.
            for (int i = 0; i < TSP_main.N; i++) {
                //x en y coordinaten van het andere punt ophalen
                int x2 = cordinates[i][0];
                int y2 = cordinates[i][1];
                //bereken de afstand
                double distance = pytagoras(((x1-x2)*7), ((y1 - y2)*5));
                //voeg de afstand toe aan de matrix in de array van het huidige punt
                distances[current][i] = distance;
            }
            //verhoog het huidige punt
            current++;
        }
        //return de matrix
        return distances;
    }

    public static double pytagoras (double x, double y){
        //stelling van pytagoras.
        return Math.round(Math.sqrt((x * x + y * y)) * 100.0) / 100.0;
    }
}
