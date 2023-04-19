package cartes.test.classes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cartes.model.classes.Card;
import cartes.model.classes.Deck;
import cartes.model.classes.Factory52Cards;
import cartes.model.enums.Suit;
import cartes.model.enums.Value;

public class DeckTest {
    Deck deck;

    @Before
    public void setup() {
        deck = new Factory52Cards().createDeck();
    }

    @Test
    public void suffle() {
        Card card = deck.removeCard(0);
        Value val = card.getValue();
        Suit s = card.getSuit();

        deck.addCard(card);

        deck.shuffle();

        assertNotEquals(deck.removeCard(0).getValue(), val);
    }

}
