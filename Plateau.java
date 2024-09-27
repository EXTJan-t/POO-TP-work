import java.util.Random;
import java.util.Set;
import java.util.HashSet;



public class Plateau {
    private int hauteur;
    private int largeur;
    private int nbMines;
    private int nbDrapeaux;

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
        
        while(minePos.size() < nbMines) {
            minePos.add(rd.nextInt(total) - 1);
        }

        for(int e: minePos) {
            mines[(e % (largeur - 2)) + 1][(e / (largeur - 2)) + 2] =  1;
        }
    }


    private void calculeAdjacence() {


        for (int i = 1; i < hauteur; ++i) {
            for (int j = 1; j < largeur; ++i) {
                if (mines[i][j] == 1) {
                    adja[i + 1][j] += 1;
                    adja[i - 1][j] += 1;
                    adja[i][j + 1] += 1;
                    adja[i][j - 1] += 1;
                }
            }

        }

        for (int i = 0; i < hauteur; ++i) {
            adja[i][largeur] = -1;
            adja[i][0] = -1;
        }
        for (int j = 0; j < largeur; ++j) {
            adja[hauteur][j] = -1;
            adja[0][j] = -1;
        }


    }

}
 