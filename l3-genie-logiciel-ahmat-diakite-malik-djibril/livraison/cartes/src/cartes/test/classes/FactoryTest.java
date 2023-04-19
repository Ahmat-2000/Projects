package cartes.test.classes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cartes.model.classes.Deck;
import cartes.model.classes.Factory52Cards;
import cartes.model.interfaces.FactoryCard;

public class FactoryTest {

    @Test
    public void createDeck() {
        FactoryCard factory = new Factory52Cards();
        Deck deck = factory.createDeck();

        assertEquals(deck.getCards().size(), 52);
    }
}
