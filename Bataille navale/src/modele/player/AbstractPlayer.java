package modele.player;

import java.util.ArrayList;

import modele.arme.IArme;
import modele.bateau.IBateau;

public abstract class AbstractPlayer implements IPlayer {
    private ArrayList<IArme> listArme ;
    private ArrayList<IBateau> listBateau ;
    private Grille grille;
    public Grille getGrille() {
        return grille;
    }

    public void setGrille(Grille grille) {
        this.grille = grille;
    }
    private int tailleGrille;

    public int getTailleGrille() {
        return tailleGrille;
    }

    public void setTailleGrille(int tailleGrille) {
        this.tailleGrille = tailleGrille;
    }

    public AbstractPlayer(int n)
    {
        listArme   = new ArrayList<>();
        listBateau = new ArrayList<>();
        tailleGrille = n;
    }

    public void addBateau(IBateau bateau)
    {
        listBateau.add(bateau);
    }
    
    public void removeBateau(IBateau bateau)
    {
        listBateau.remove(bateau);
    } 

    public void addArme(IArme arme)
    {
        listArme.add(arme);
    } 

    public void removeArme(IArme arme)
    {
        listArme.remove(arme);
    }

    abstract public void choixArme();
    abstract public void choixCible();
    @Override
    public void choixBateau() {
        // TODO Auto-generated method stub
        
    }
    
}
