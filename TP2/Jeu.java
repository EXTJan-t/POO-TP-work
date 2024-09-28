public class Jeu {
    private Joueur joueur;
    private Plateau plateau;

    public Jeu(Joueur joueur, Plateau plateau) {
        this.joueur = joueur;
        this.plateau = plateau;
    }

    public void jouer() {
        boolean partieTerminee = false;

    
        while (!partieTerminee) {

            plateau.afficheCourant();

            char action = joueur.demanderAction();

            int[] coordonnes = joueur.demanderCoordonnes();


            if (action == 'r') {

                plateau.revelerCase(coordonnes[0], coordonnes[1]);
            } else if (action == 'd') {

                plateau.drapeauxCase(coordonnes[0], coordonnes[1]);
            }
            partieTerminee = plateau.jeuGagne() || plateau.jeuPerdu();
        }

        System.out.println("La partie est terminée.");
        plateau.afficheTout();
        plateau.afficheCourant();
        joueur.finir(); 
    }
}
