package cartes.model.interfaces;

import cartes.model.classes.Deck;

public interface FactoryCard {
    /**
     * Cree un paquet et le retourne
     * 
     * @return Deck
     */
    public Deck createDeck();
}
