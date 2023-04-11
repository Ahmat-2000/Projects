package modele.player;

import java.util.ArrayList;

import modele.bateau.Bateau;
import modele.bateau.Cellule;

public class Grille {
    private Cellule[][] grille;
    public Grille(int n){
        grille = new Cellule[n][n];
    }
    public void placerBateau(Bateau bateau){
        ArrayList<Cellule> cells = bateau.getCelullesBateau(); 
        int x = 0, y = 0;
        for (Cellule cellule : cells) {
            x = cellule.getligne();
            y = cellule.getcolone();
            grille[x][y] = cellule;
        }
    }
}
