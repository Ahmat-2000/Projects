package cartes.model.classes;

import cartes.model.enums.Suit;
import cartes.model.enums.Value;
import cartes.model.interfaces.ICard;
import cartes.util.observer.AbstractListenableModel;

public class Card extends AbstractListenableModel implements ICard {
    private Value cardValue;
    private Suit cardSuit;
    private boolean isFaceUp;

    public Card(Suit suit, Value value, boolean isFaceUp) {
        this.cardSuit = suit;
        this.cardValue = value;
        this.isFaceUp = isFaceUp;
    }

    public Card(Suit color, Value value) {
        this(color, value, false);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cartes.model.interfaces.ICard#getValue()
     */
    @Override
    public Value getValue() {
        return this.cardValue;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cartes.model.interfaces.ICard#getSuit()
     */
    public Suit getSuit() {
        return this.cardSuit;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cartes.model.interfaces.ICard#getIsFaceUp()
     */
    public boolean getIsFaceUp() {
        return this.isFaceUp;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cartes.model.interfaces.ICard#setIsFaceUp(boolean)
     */
    @Override
    public void setIsFaceUp(boolean faceUp) {
        this.isFaceUp = faceUp;
        fireChange();
    }

}
