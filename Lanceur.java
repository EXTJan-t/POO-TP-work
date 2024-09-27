public class Lanceur{

    public static void main(String[] args) {
        System.out.println("aaaa");

        Plateau P = new Plateau(8, 8, 10);

        P.afficheTout();
        P.afficheCourant();
        P.revelerCase(2, 2);
        P.drapeauxCase(3,3 );
        P.afficheCourant();
    }
}