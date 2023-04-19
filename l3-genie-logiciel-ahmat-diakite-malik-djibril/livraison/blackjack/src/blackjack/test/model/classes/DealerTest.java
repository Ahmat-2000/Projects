package blackjack.test.model.classes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert.*;

import blackjack.model.classes.Game;
import blackjack.model.classes.Hand;
import cartes.model.classes.Deck;

public class DealerTest {
    private static final int numberOfPlayers = 2;
    private Deck deck;
    private Game game;

    @Before
    public void setup() {
        game = new Game(numberOfPlayers);
        deck = new Deck();
    }

    @Test
    public void dealCard() throws Exception {
        game.getDealer().dealCard();
        assertEquals(52 - 1, game.getDealer().getDeck().getCards().size());
    }

    @Test
    public void takeCard() throws Exception {
        Hand hand = new Hand();
        for (int i = 0; i < game.getDealer().getHand().getCards().size(); i++) {
            hand.addCard(game.getDealer().getHand().getCards().get(i));
        }

        game.getDealer().takeCard(false);
        assertNotEquals(hand.getCards(), game.getDealer().getHand().getCards());
    }

    @Test
    public void getHand() throws Exception {
        assertNotNull(game.getDealer().getHand());
    }
}
