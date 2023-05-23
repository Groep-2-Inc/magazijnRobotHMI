package TSP;

public class TSP_GetMatrix {
    public static int[][] getCordinates(int[][] matrix) {
        //maak nieuwe array aan voor alle x en y posities
        int[][] pointxy = new int[TSP_main.N][2];
        //initialiseer de y en x
        int y = 0;
        int x = 0;

        //for loop die in de matrix zoekt naar een bepaald aantal punten (N)
        for (int i = 0; i < TSP_main.N; i++) {
            //for loop die door de lijnen heen kijkt (y coordinaat)
            for (int[] line : matrix) {
                //for loop die door de velden heen kijkt (x coordinaat)
                for (int field: line){
                    //verhoog de X coordinaat
                    x++;
                    //kijk of het veld gelijk is aan i
                    if(field == i+1){
                        //vul de X en Y coordinaat in
                        pointxy[i][0] = x;
                        pointxy[i][1] = (TSP_main.matrixsize - y);
                        //reset de x en y coordinaat;
                        x = 0;
                        y = 0;
                    }
                }
                //verhoog de y coordinaat en reset de x coordinaat
                y++;
                x=0;
            }
            //reset de y coordinaat
            y = 0;
        }
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
                double distance = pytagoras((x1-x2), (y1 - y2));
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
