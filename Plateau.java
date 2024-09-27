import java.util.Random;
import java.util.Set;
import java.util.HashSet;



public class Plateau {
    private int hauteur;
    private int largeur;
    private int nbMines;
    private int nbDrapeaux = 0;

    private int[][] mines;
    private int[][] etats;
    private int[][] adja;

    public Plateau(int ha, int la, int mi) {
        this.hauteur = ha + 2;
        this.largeur = la + 2;
        this.nbMines = mi;

        mines = new int[hauteur][largeur];
        etats = new int[hauteur][largeur];
        adja = new int[hauteur][largeur];

        ajouteMinesAlea();
        calculeAdjacence();
    }

    private void ajouteMinesAlea() {
        int total = (hauteur - 2) * (largeur - 2);
        Set<Integer> minePos = new HashSet<Integer>();
    
        Random rd = new Random();
        
        while (minePos.size() < nbMines) {
            minePos.add(rd.nextInt(total));
        }
    
        for (int e : minePos) {
            mines[(e / (largeur - 2)) + 1][(e % (largeur - 2)) + 1] = 1;
        }
    }
    
    private void calculeAdjacence() {
        for (int i = 1; i < hauteur - 1; ++i) {
            for (int j = 1; j < largeur - 1; ++j) {
                if (mines[i][j] == 1) {
                    if (i + 1 < hauteur) adja[i + 1][j] += 1;
                    if (i - 1 > 0) adja[i - 1][j] += 1;
                    if (j + 1 < largeur) adja[i][j + 1] += 1;
                    if (j - 1 > 0) adja[i][j - 1] += 1;
                }
            }
        }
    
        for (int i = 0; i < hauteur; ++i) {
            adja[i][largeur - 1] = -1;
            adja[i][0] = -1;
        }
        for (int j = 0; j < largeur; ++j) {
            adja[hauteur - 1][j] = -1;
            adja[0][j] = -1;
        }
    }
    
    public void afficheTout() {
        System.out.println("**********************");
        System.out.println("* Mines / Drapeaux   *");
        System.out.println("*  " + this.nbMines + "    /    "+ this.nbDrapeaux +"       *");
        System.out.println("**********************");
    
        System.out.print("    ");
        for (int i = 1; i < adja[0].length - 1; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("  --------------------");
    
        char[] rowLabels = {'-','A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K'};
        for (int i = 1; i < adja.length - 1; i++) {
            System.out.print(rowLabels[i] + " | ");
            for (int j = 1; j < adja[i].length - 1; j++) {
                if (adja[i][j] == -1) {
                    System.out.print("* ");
                } else {
                    System.out.print(adja[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public void revelerCase() {


    }
    

}
 