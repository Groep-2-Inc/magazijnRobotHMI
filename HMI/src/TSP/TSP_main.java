package TSP;
///---------------------------------------------------------------------CODE GEMAAKT DOOR JASON JOSHUA VAN DER KOLK---------------------------------------------------------------------\\\
public class TSP_main {
    static int routeLength;

    //main functie om het tsp algoritme te laten werken die een array met de statuscodes teruggeeft
    public static int[] main(String[] matrix){
        //stel de route lengte gelijk aan de lengte van de matrix
        routeLength = matrix.length;

        //tijdelijke array waar de x en u coordinaten in komen van de punten
        int[][] xycors;
        xycors = TSP_GetMatrix.getCordinates(matrix);

        //tijdelijke array voor de afstanden van elk punt tot alle andere punten
        double[][] distances = TSP_GetMatrix.getDistances(xycors);

        //roep het algoritme aan met de afstanden
        TSP_Algoritm_2.TSP_Algoritm(distances);

        //zet de afstanden weer in een array met de coordinaten
        String[] endcors = new String[routeLength];
        int i = 0;
        for (int cor: TSP_Algoritm_2.finalPath){
            endcors[i] = matrix[cor];
            i++;
        }

        //return de afstanden in een array met de statuscodes
        return getStatusCodes(endcors);
    }

    //code voor het krijgen van de statuscodes
    public static int[] getStatusCodes(String[] cors){
        //nieuwe tijdelijke intarray voor de codes
        int[] codes = new int[routeLength];
        int i = 0;

        //een for loop die met behulp van de coordinaten de juiste statuscode in de array zet
        for (String s : cors) {
            int code = 400;
            if(s.contains("A")){
                code += 0;
            } else if (s.contains("B")) {
                code += 10;
            }else if (s.contains("C")) {
                code += 20;
            }else if (s.contains("D")) {
                code += 30;
            }else if (s.contains("E")) {
                code += 40;
            }

            if(s.contains("1")){
                code += 1;
            }else if (s.contains("2")){
                code += 2;
            }else if (s.contains("3")){
                code += 3;
            }else if (s.contains("4")){
                code += 4;
            }else if (s.contains("5")){
                code += 5;
            }else if (s.contains("6")){
                code += 6;
            }
            codes[i] = code;
            i++;
        }

        //return vervolgens de statuscodes.
        return codes;
    }
}
