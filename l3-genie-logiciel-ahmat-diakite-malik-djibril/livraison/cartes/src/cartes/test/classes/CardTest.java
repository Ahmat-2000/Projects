package cartes.test.classes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cartes.model.classes.Card;
import cartes.model.enums.Suit;
import cartes.model.enums.Value;

public class CardTest {
    private Card card;

    @Before
    public void setup() {
        this.card = new Card(Suit.CLUB, Value.AS);
    }

    @Test
    public void isFaceUp() {
        assertFalse(card.getIsFaceUp());
    }

    @Test
    public void getValue() {
        assertEquals(card.getValue(), Value.AS);
    }
}
