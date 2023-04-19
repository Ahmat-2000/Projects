package cartes.model.interfaces;

import cartes.model.enums.Suit;
import cartes.model.enums.Value;

public interface ICard {
    /**
     * Retourne l'enseigne de la cartes
     * 
     * @return suit
     */
    Suit getSuit();

    /**
     * Retourne la valeur de la carte
     * 
     * @return
     */
    Value getValue();

    /**
     * Permet de retourner une cartes
     * 
     * @param isFaceUp
     */
    void setIsFaceUp(boolean isFaceUp);

    /**
     * Verifie si la carte est visible
     * 
     * @return boolean
     */
    boolean getIsFaceUp();
}
