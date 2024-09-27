import java.util.Scanner;;

public class Joueur {
    public String nom;
    private Scanner scanReponse;

    public Joueur(){
        this.nom = "Anonym";
        this.scanReponse = new Scanner(System.in);
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void finir() {
        scanReponse.close();
    }

}