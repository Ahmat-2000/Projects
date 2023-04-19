package blackjack.model.classes;

import cartes.model.classes.Card;
import cartes.model.classes.Deck;
import cartes.model.classes.Factory52Cards;

public class Dealer extends AbstractPlayer {
    private Deck deck;

    public Dealer() {
        super();
        Factory52Cards factory = new Factory52Cards();
        this.deck = factory.createDeck();
    }

    /**
     * Retourne le paquet du jeu
     * 
     * @return Deck
     */
    public Deck getDeck() {
        return this.deck;
    }

    /**
     * Retourne la carte visible du dealer
     * 
     * @return Card
     */
    public Card getCardVisible() {
        for (Card card : playerHand.getCards()) {
            if (card.getIsFaceUp())
                return card;
        }
        return null;
    }

    /**
     * Retourne toutes les cartes du dealer
     */
    public void flipUpAllCards() {
        for (Card card : this.playerHand.getCards()) {
            card.setIsFaceUp(true);
        }
    }

    /**
     * Donne une carte au dealer visible ou pas
     * 
     * @param isFaceUp
     */
    public void takeCard(boolean isFaceUp) {
        Card card = this.dealCard();
        card.setIsFaceUp(isFaceUp);
        this.getHand().addCard(card);
    }

    /**
     * Fusionne la defausse avec le paquet courant
     * 
     * @param defausse
     */
    public void mergeDeck(Deck defausse) {
        int length = defausse.getCards().size();
        for (int i = 0; i < length; i++) {
            this.deck.addCard(defausse.removeCard(0));
        }
    }

    /**
     * Tire une carte du paquet et la retourne
     * 
     * @return Card
     */
    public Card dealCard() {
        Card card = this.deck.removeCard(0);
        card.setIsFaceUp(true);
        return card;
    }

    /**
     * Melange le paquet
     */
    public void shuffleDeck() {
        this.deck.shuffle();
    }

    /*
     * (non-Javadoc)
     * 
     * @see blackjack.model.classes.AbstractPlayer#play()
     */
    @Override
    public void play() {
        if (this.playerHand.computeHandValue() >= Game.DEALER_LIMIT) {
            flipUpAllCards();
        } else {
            takeCard(true);
            play();
        }

    }
}
