package cartes.model.classes;

import java.util.ArrayList;
import java.util.Collections;

import cartes.model.interfaces.IDeck;
import cartes.util.observer.AbstractListenableModel;

public class Deck extends AbstractListenableModel implements IDeck {
    ArrayList<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
    }

    /*
     * (non-Javadoc)
     * 
     * @see cartes.model.interfaces.IDeck#getCards()
     */
    @Override
    public ArrayList<Card> getCards() {
        return this.cards;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cartes.model.interfaces.IDeck#addCard(cartes.model.classes.Card)
     */
    @Override
    public void addCard(Card card) {
        this.cards.add(card);
        fireChange();
    }

    /*
     * (non-Javadoc)
     * 
     * @see cartes.model.interfaces.IDeck#removeCard(int)
     */
    @Override
    public Card removeCard(int index) {
        int numberOfCards = this.cards.size();
        if (index < 0 || index >= numberOfCards) {
            throw new IndexOutOfBoundsException("Index non valide!");
        }
        Card cardToReturn = this.cards.get(index);
        this.cards.remove(index);
        fireChange();
        return cardToReturn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cartes.model.interfaces.IDeck#cut()
     */
    @Override
    public IDeck cut() {
        int len = this.cards.size();
        int rand = (int) Math.random() * (len - 2 - 1) + 1;
        Deck deck = new Deck();
        for (int i = 0; i <= rand; i++) {
            deck.addCard(this.removeCard(0));
        }
        fireChange();
        return deck;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cartes.model.interfaces.IDeck#shuffle()
     */
    @Override
    public void shuffle() {
        Collections.shuffle(this.cards);
    }
}