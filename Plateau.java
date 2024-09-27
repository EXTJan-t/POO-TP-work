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

    public void revelerCase(int x, int y) {
        
        if (x <= 0 || x >= hauteur || y <= 0 || y >= largeur) {
            return;
        }
    
        
        if (etats[x][y] != 0) {
            System.out.println("invalid operation");
            return;
        }
    
        
        etats[x][y] = 2; 
    
        if (adja[x][y] > 0) {
            return;
        }
    
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx != 0 || dy != 0) { 
                    revelerCase(x + dx, y + dy);
                }
            }
        }
    }
    

    public void drapeauxCase(int x, int y) {


        if (etats[x + 1][y + 1] != 0) {
            System.out.println("invalid operation");
            return;
        }

        etats[x + 1][y + 1] = 1;
        this.nbDrapeaux += 1;
    }

    public void afficheCourant() {
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
        for (int i = 1; i < hauteur - 1; i++) {
            System.out.print(rowLabels[i] + " | ");
            for (int j = 1; j < largeur - 1; j++) {
                if (adja[i][j] == -1) {
                    System.out.print("* ");
                } else {

                    if (etats[i][j] == 1) {
                        System.out.print("x ");
                    } else if(etats[i][j] == 2) {
                        System.out.print(adja[i][j] + " ");
                    } else {
                        System.out.print(". ");
                    }
                    
                }
            }
            System.out.println();
        }
    }

    public boolean jeuPerdu() {
        for (int i = 1; i < hauteur - 1; i++) {
            for (int j = 1; j < largeur - 1; j++) {
                if (mines[i][j] == 1 && etats[i][j] == 2) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean jeuGagne() {
        
        for (int i = 1; i < hauteur - 1; i++) {
            for (int j = 1; j < largeur - 1; j++) {
                if (mines[i][j] == 0 && etats[i][j] == 2) {
                    continue;
                } else {
                    return false;
                }
            }
        }

        return true;

    }



}
 