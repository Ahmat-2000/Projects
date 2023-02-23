package src.util.observer;
/**
 * Cette interface est implementée par les classes du modele pour 
 * pouvoir utiliser le pattern observateur et afin d'être un écoutable.
 */
public interface ModelEcoutable {

    public void ajoutEcouteur(EcouteurModele e);
    public void retraitEcouteur(EcouteurModele e);
    
}