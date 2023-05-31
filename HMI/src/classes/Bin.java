package classes;

import java.util.ArrayList;

public class Bin {
    public int capacity; // capaciteit van de bin (Daan en Sarah)
    public ArrayList<Product> producten = new ArrayList<>(); //lijst van objecten die in de bin zitten (Daan en Sarah)
    public static ArrayList<Bin> binsFirstFit = new ArrayList<>(); // lijst van de bins die aangemaakt zijn voor het first fit algoritme (Daan en Sarah)
    public static int indexBinsFirstFit; // initialisatie van attribuut dat de index van de first fit bins bijhoudt, om erbij te kunnen vanaf Main.java (Daan en Sarah)

    // Onderstaand: constructor van Bin die de capaciteit standaard op 100 zet (Daan en Sarah)
    public Bin() {
        this.capacity = 10;
    }

    // Onderstaand: method voor first fit algoritme die objecten in de bins stopt en zo nodig nieuwe bins aanmaakt (Daan en Sarah)
    public void objectInBinFirstFit(Product product) {
        indexBinsFirstFit = binsFirstFit.size() - 1; //bepaald de index aan de hand van de grootte van de arrayList 'bins' (-1, want index start vanaf 0) (Daan en Sarah)
        for (int i = 0; i <= indexBinsFirstFit; i++) { // loopt door het aantal bins heen (Daan en Sarah)
            if (product.getWeight() <= binsFirstFit.get(i).capacity) { // er wordt gekeken of het object in de bin past (Daan en Sarah)
                binsFirstFit.get(i).producten.add(product); // object wordt aan de bin toegevoegd (Daan en Sarah)
                binsFirstFit.get(i).capacity -= product.getWeight(); // capaciteit van de bin wordt geüpdatet (Daan en Sarah)
                return; //>>> functie wordt verlaten <<<//
            }
        }
        Bin newBin = new Bin(); //we zitten nog steeds in de functie, dus blijkbaar past object in geen van de bins, er wordt een nieuwe bin aangemaakt (Daan en Sarah)
        binsFirstFit.add(newBin); // de nieuwe bin wordt toegevoegd aan de lijst van bins (Daan en Sarah)
        newBin.producten.add(product); // object wordt toegevoegd aan de bin (Daan en Sarah)
        newBin.capacity -= product.getWeight(); //capaciteit van de bin wordt geüpdatet (Daan en Sarah)
    }


    //Onderstaand: method die per doos de producten print (Daan en Sarah)
    public static ArrayList<Bin> getBins(String type) {
//        for(int i = 0; i< binsFirstFit.size(); i++){
//            System.out.println(Bin.class + ": doos: " + (i + 1) + " ");
//            for(int j = 0; j< binsFirstFit.get(i).producten.size(); j++){
//                System.out.println("Gewicht: " + binsFirstFit.get(i).producten.get(j).getWeight() + " - " + binsFirstFit.get(i).producten.get(j).getProductName() + " - " + binsFirstFit.get(i).producten.get(j).getProductID() );
//            }
//        }
        return binsFirstFit;
    }
    public static void reset(){
        binsFirstFit.clear();
    }

}
