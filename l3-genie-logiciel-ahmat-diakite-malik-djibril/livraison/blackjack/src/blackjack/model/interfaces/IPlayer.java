package blackjack.model.interfaces;

import blackjack.model.classes.Hand;
import cartes.model.classes.Card;
import cartes.model.classes.Deck;

public interface IPlayer {

    /**
     * Retourne la main du joueur
     * 
     * @return hand
     */
    public Hand getHand();

    /**
     * Vide la main du joueur en ajoutant toutes ses cartes a la defausse
     * 
     * @param defausse
     */
    public void clearHand(Deck defausse);

    /**
     * Verifie si la main du joueur est un blackjack
     * 
     * @return boolean
     */
    public boolean hasBlackJack();

    /**
     * Verifie si la main du joueur depasse la valeur 21
     * 
     * @return boolean
     */
    public boolean hasBust();

    /**
     * Accepte une carte et l'ajoute a sa main
     * 
     * @param card
     */
    public void acceptCard(Card card);

    /**
     * Verifie si le joueur est un humain
     * 
     * @return
     */
    public boolean isHuman();
}
