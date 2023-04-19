package modele.bateau ;
import java.util.ArrayList;
public class Bateau implements IBateau{
    protected int tailleBateau ;
    protected Cellule boatHead ;
    protected Cellule boatTail ;
    protected ArrayList<Cellule> celullesBateau ;
    
    public Bateau(int tailleBateau){
        this.tailleBateau = tailleBateau;
        this.celullesBateau = new ArrayList<>() ;
    }
    /*public void ajouterCellule(Cellule c){
        this.celullesBateau.add(c);
    }*/
    /**
     * Creation des Bateaux verticaux
     * @param x
     * @param y
     */
    public void CreationBateauVertical(int x,int y){
        Cellule tmp = null;
        boatHead = new Cellule(x, y);
        this.celullesBateau.add(boatHead);
        for (int i = 1; i < tailleBateau; i++) {
            tmp = new Cellule(x+1, y);
            this.celullesBateau.add(tmp);
        }
        boatTail = celullesBateau.get(tailleBateau -1);
    }

    /**
     * Creation de bateaux horizentaux
     * @param x
     * @param y
     */
    public void CreationBateauHorizontal(int x,int y){
        Cellule tmp = null;
        boatHead = new Cellule(x, y);
        this.celullesBateau.add(boatHead);
        for (int i = 1; i < tailleBateau; i++) {
            tmp = new Cellule(x, y+1);
            this.celullesBateau.add(tmp);
        }
        boatTail = celullesBateau.get(tailleBateau -1);
    }
    public ArrayList<Cellule> getCelullesBateau() {
        return celullesBateau;
    }
    //public Cellule positionBateau(Boolean esHorizontale) ;
}