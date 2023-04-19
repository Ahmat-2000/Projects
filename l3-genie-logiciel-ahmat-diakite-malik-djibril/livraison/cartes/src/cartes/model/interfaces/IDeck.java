package cartes.model.interfaces;

import java.util.ArrayList;

import cartes.model.classes.Card;

public interface IDeck {
    /**
     * Retourne les cartes contenues dans le paquet
     * 
     * @return cards
     */
    public ArrayList<Card> getCards();

    /**
     * Ajoute une carte au paquet
     * 
     * @param card
     */
    public void addCard(Card card);

    /**
     * Retire une carte du paquet a la position index et la retourne
     * 
     * @param index
     * @return card
     */
    public Card removeCard(int index);

    /**
     * Permets de couper le paquet a un niveau aleatoire
     * 
     * @return
     */
    public IDeck cut();

    /**
     * Permets de melanger le paquet
     */
    public void shuffle();
}
