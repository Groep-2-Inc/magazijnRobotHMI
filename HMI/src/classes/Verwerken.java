package classes;
// Als de verwerken pagina een keer is geopend

public class Verwerken {
    private static boolean isVerwerken;

    public static boolean isVerwerken() {
        return isVerwerken;
    }
    public static void setIsVerwerken(boolean isVerwerken) {
        Verwerken.isVerwerken = isVerwerken;
    }
}
