package blackjack.model.interfaces;

import java.util.*;

import cartes.model.classes.Card;

public interface IHand {
    /**
     * Retourne la liste des cartes en main
     * 
     * @return liste de Card
     */
    ArrayList<Card> getCards();

    /**
     * Ajoute une carte a la main
     * 
     * @param card
     */
    void addCard(Card card);

    /**
     * Calcule la valeur de la main
     * 
     * @return hand value
     */
    int computeHandValue();

    /**
     * Vide la main en retirant toutes les cartes
     */
    public void clearHand();

    /**
     * Retourne la mise de la main
     * 
     * @return bet
     */
    public int getBet();

    /**
     * Mets a jour la mise de la main
     * 
     * @param newBet
     */
    public void setBet(int newBet);
}
