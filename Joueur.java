import java.util.Scanner;

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


    public boolean veutJouer() {
        System.out.println("Voulez-vous jouer (oui/non) ?");
        String reponse = scanReponse.nextLine().trim().toLowerCase();
        return reponse.equals("oui") || reponse.equals("o");
    }

    public String demanderNom() {
        System.out.println("Quel est votre nom ?");
        String nomInput = scanReponse.nextLine().trim();
        if (!nomInput.isEmpty()) {
            setNom(nomInput);
        }
        return nom;
    }

    public int[] demanderDimensions() {
        int[] dimensions = new int[2];
        System.out.println("Entrez les dimensions du plateau :");
        System.out.print("Hauteur : ");
        dimensions[0] = scanReponse.nextInt();  
        System.out.print("Largeur : ");
        dimensions[1] = scanReponse.nextInt();  
        scanReponse.nextLine();  
        return dimensions;
    }

    public int demanderNbMines() {
        System.out.print("Combien de mines voulez-vous placer ? ");
        int nbMines = scanReponse.nextInt();
        scanReponse.nextLine();  
        return nbMines;
    }

    public char demanderAction() {
        System.out.println("Voulez-vous reveler une case (r) ou placer un drapeau (d) ?");
        char action = scanReponse.nextLine().trim().toLowerCase().charAt(0);
        while (action != 'r' && action != 'd') {
            System.out.println("Entrée invalide. Veuillez entrer 'r' pour reveler ou 'd' pour placer un drapeau.");
            action = scanReponse.nextLine().trim().toLowerCase().charAt(0);
        }
        return action;
    }

    public int[] demanderCoordonnes() {
        int[] coordonnes = new int[2];
        System.out.println("Entrez les coordonnées de la case (format : x y) :");
        coordonnes[0] = scanReponse.nextInt();  
        coordonnes[1] = scanReponse.nextInt();  
        scanReponse.nextLine();  
        return coordonnes;
    }
}
