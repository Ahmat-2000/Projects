package modele.bateau;
public class Cellule {
    protected int ligne ;
    protected int colonne ;
    private boolean etat;
    
    public Cellule(int ligne ,int colone){
        this.ligne = ligne ;
        this.colonne = colone ;
        etat = true;
    }
    public int getligne(){
        return this.ligne ;
    }
    public int getcolone(){
        return this.colonne ;
    }
    /*public void placer(Bateau bateau){
        bateau.ajouterCellule(this);
    }*/

    public  String toString(){

        return "("+this.ligne+","+this.colonne+")";
    }
    public boolean isEtat() {
        return etat;
    }
    public void setEtat(boolean etat) {
        this.etat = etat;
    }
    /*public Boolean equalCellule(Cellule c){
        if(c.getligne()==this.ligne && c.getcolone()==this.colonne ){
            return true ;
        }
        return false ;
    }*/

    }