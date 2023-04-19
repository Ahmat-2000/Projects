package cartes.model.classes;

import cartes.model.enums.Suit;
import cartes.model.enums.Value;
import cartes.model.interfaces.FactoryCard;

public class Factory52Cards implements FactoryCard {

    /*
     * (non-Javadoc)
     * 
     * @see cartes.model.interfaces.FactoryCard#createDeck()
     */
    @Override
    public Deck createDeck() {
        Deck deck = new Deck();
        for (Suit suit : Suit.values()) {
            for (Value value : Value.values()) {
                deck.addCard(new Card(suit, value));
            }
        }
        return deck;
    }

}
