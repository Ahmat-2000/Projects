package src.util.observer;
import java.util.ArrayList;
import java.util.List;
/*
 * Cette classe abstraite implemente l'interface ModelEouteur du pattern 
 * Observateur.
 * Elle doit être héritée par les classes du modele afin d'utiliser ce 
 * pattern pour notifier les écouteurs du modele quand le modele change d'état
 */
public abstract class AbstractModeleEcoutable implements ModelEcoutable
{
    private List<EcouteurModele> ecouteurs ;
    public AbstractModeleEcoutable()
    {
        this.ecouteurs = new ArrayList<>();
    }
    @Override
    /**
     * Ajoute un écouteur à notre liste des écouteurs
     */
    public void ajoutEcouteur(EcouteurModele e) {
        this.ecouteurs.add(e);
    }

    @Override
    /**
     * Retire un écouteur de notre liste des écouteurs
     */
    public void retraitEcouteur(EcouteurModele e) {
        this.ecouteurs.remove(e);
    }
    /**
     * informe les écouteurs que l'état du modele est changé
     */
    protected void fireChangement()
    {
        for (EcouteurModele e : this.ecouteurs) {
            e.updateModelSomeThingHasChange(this);
        }
    }
    
}
