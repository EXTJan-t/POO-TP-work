public class Lanceur{

    public static void main(String[] args) {
        Joueur joueur = new Joueur();
        
        
        joueur.demanderNom();
        
        if (joueur.veutJouer()) {
            int[] dimensions = joueur.demanderDimensions();
            
            int nbMines = joueur.demanderNbMines();
            
            Plateau plateau = new Plateau(dimensions[0], dimensions[1], nbMines);
            
            Jeu jeu = new Jeu(joueur, plateau);
            jeu.jouer();  
        } else {
            joueur.finir();  
        }
    }
}