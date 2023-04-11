package modele.arme;

public class Arme1 implements IArme {
    private int nbCartouche, puissance;
    private String nom;

    public Arme1(String nom, int nbCartouche, int puissance){
        this.nbCartouche = nbCartouche;
        this.puissance = puissance;
        this.nom = nom;
    }
    @Override 
    public String toString(){
        return nom + " est un arme de puissance " + puissance + " avec " + nbCartouche + " balles."; 
    }
    public int getPuissance() {
        return puissance;
    }
    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }
    public int getNbCartouche() {
        return nbCartouche;
    }
    public void setNbCartouche(int nbCartouche) {
        this.nbCartouche = nbCartouche;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
}
