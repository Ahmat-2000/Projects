package blackjack.test.model.classes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import blackjack.model.classes.Hand;
import blackjack.model.enums.Status;
import cartes.model.classes.Card;
import cartes.model.enums.Suit;
import cartes.model.enums.Value;

public class HandTest {
    private Hand hand;
    private Card card1, card2;

    @Before
    public void setup() {
        hand = new Hand();

        card1 = new Card(Suit.CLUB, Value.AS);
        card2 = new Card(Suit.DIAMOND, Value.TWO);

        hand.addCard(card1);
        hand.addCard(card2);
    }

    @Test
    public void computeHandValue() {
        assertEquals(13, hand.computeHandValue());
    }
}
