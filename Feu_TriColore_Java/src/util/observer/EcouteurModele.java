package src.util.observer;
/**
 * Cette interface est implementée par toutes les classes qui écouttent
 * le modele afin d'être informés d'une eventuelle changement de l'état
 * du modele.
 */
public interface EcouteurModele {
    /**
     * Cette méthode permet aux écoueurs de mettre à jour leurs données 
     * quand l'état du modele change.
     * Elle prend en paramètre un objet parmis nos modeles.
     * @param source
     */
    public void updateModelSomeThingHasChange(Object source);
}