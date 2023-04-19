package blackjack.model.classes;

import blackjack.model.interfaces.IPlayer;
import cartes.model.classes.Card;
import cartes.model.classes.Deck;
import cartes.util.observer.AbstractListenableModel;

public abstract class AbstractPlayer extends AbstractListenableModel implements IPlayer {

    protected Hand playerHand;
    protected boolean human;
    protected boolean currentPlayer;

    public AbstractPlayer() {
        init();
        this.human = false;
    }

    /**
     * Initialise la main du joueur
     */
    private void init() {
        this.playerHand = new Hand();
        this.currentPlayer = false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see blackjack.model.interfaces.IPlayer#getHand()
     */
    @Override
    public Hand getHand() {
        return this.playerHand;
    }

    /*
     * (non-Javadoc)
     * 
     * @see blackjack.model.interfaces.IPlayer#clearHand(cartes.model.classes.Deck)
     */
    @Override
    public final void clearHand(Deck defausse) {
        for (Card card : this.playerHand.getCards()) {
            defausse.addCard(card);
        }
        this.playerHand.clearHand();
        init();
    }

    /*
     * (non-Javadoc)
     * 
     * @see blackjack.model.interfaces.IPlayer#hasBlackJack()
     */
    @Override
    public final boolean hasBlackJack() {
        return Game.BLACKJACK == this.getHand().computeHandValue();
    }

    /*
     * (non-Javadoc)
     * 
     * @see blackjack.model.interfaces.IPlayer#hasBust()
     */
    @Override
    public final boolean hasBust() {
        return Game.BLACKJACK < this.getHand().computeHandValue();
    }

    /*
     * (non-Javadoc)
     * 
     * @see blackjack.model.interfaces.IPlayer#acceptCard(cartes.model.classes.Card)
     */
    @Override
    public final void acceptCard(Card card) {
        this.playerHand.addCard(card);
    }

    /*
     * (non-Javadoc)
     * 
     * @see blackjack.model.interfaces.IPlayer#isHuman()
     */
    @Override
    public final boolean isHuman() {
        return this.human;
    }

    /**
     * Joue pour le joueur
     */
    public abstract void play();

}
