package modele.utile;
import java.util.ArrayList;
import java.util.List;

/**
 * AbstractModeleEcoutable
 */
public abstract class AbstractModeleEcoutable implements ModeleEcoutable {
    protected List<EcouteurModele> ecouteur ;
    public AbstractModeleEcoutable(){
        this.ecouteur = new ArrayList<>() ;
    }
    public void ajoutEcouteur(EcouteurModele e){
        this.ecouteur.add(e) ;
    }
    public void retraitEcouteur(EcouteurModele e){
        this.ecouteur.remove(e) ;
       
    }
    protected void fireChangement(){
        for (EcouteurModele e : this.ecouteur) {
            e.modeleMisAJour(this);
        }
    }

}
