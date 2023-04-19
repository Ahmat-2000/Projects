package blackjack.model.classes;

import java.util.ArrayList;

import blackjack.model.interfaces.IHand;
import cartes.model.classes.Card;
import cartes.util.observer.AbstractListenableModel;
import cartes.util.observer.ModelListener;

public class Hand extends AbstractListenableModel implements IHand, ModelListener {
    private int BLACKJACK = 21;
    private ArrayList<Card> cardsInHand;
    private int bet;

    public Hand() {
        this.cardsInHand = new ArrayList<>();
    }

    /*
     * (non-Javadoc)
     * 
     * @see blackjack.model.interfaces.IHand#getCards()
     */
    @Override
    public ArrayList<Card> getCards() {
        return this.cardsInHand;
    }

    /*
     * (non-Javadoc)
     * 
     * @see blackjack.model.interfaces.IHand#addCard(cartes.model.classes.Card)
     */
    @Override
    public void addCard(Card card) {
        this.cardsInHand.add(card);
        fireChange();
    }

    /*
     * (non-Javadoc)
     * 
     * @see blackjack.model.interfaces.IHand#computeHandValue()
     */
    @Override
    public int computeHandValue() {
        int handValue = 0;
        int numberOfAce = 0;

        for (Card card : this.cardsInHand) {
            if (BJCard.isAs(card)) {
                numberOfAce += 1;
            }
            handValue += BJCard.getIntValue(card);
        }

        while (handValue > Game.BLACKJACK && numberOfAce > 0) {
            handValue -= 10;
            numberOfAce--;
        }
        return handValue;
    }

    /*
     * (non-Javadoc)
     * 
     * @see blackjack.model.interfaces.IHand#clearHand()
     */
    @Override
    public void clearHand() {
        this.cardsInHand.clear();
        fireChange();
    }

    /*
     * (non-Javadoc)
     * 
     * @see blackjack.model.interfaces.IHand#getBet()
     */
    @Override
    public int getBet() {
        return this.bet;
    }

    /*
     * (non-Javadoc)
     * 
     * @see blackjack.model.interfaces.IHand#setBet(int)
     */
    @Override
    public void setBet(int newBet) {
        this.bet = newBet;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cartes.util.observer.ModelListener#somethinHasChanged(java.lang.Object)
     */
    @Override
    public void somethinHasChanged(Object arg0) {
        fireChange();
    }

}
