package blackjack.model.classes;

import cartes.model.classes.Card;
import cartes.model.enums.Value;

public class BJCard {
    /**
     * Retourne la valeur en entier d'une carte
     * 
     * @param card
     * @return int
     */
    public static int getIntValue(Card card) {
        switch (card.getValue()) {
            case KING:
            case QUEEN:
            case JACK:
            case TEN:
                return 10;

            case NINE:
                return 9;

            case EIGHT:
                return 8;

            case SEVEN:
                return 7;

            case SIX:
                return 6;

            case FIVE:
                return 5;

            case FOUR:
                return 4;

            case THREE:
                return 3;

            case TWO:
                return 2;

            default:
                return 11;
        }
    }

    /**
     * Verifie si une carte est un as
     * 
     * @param card
     * @return boolean
     */
    public static boolean isAs(Card card) {
        return card.getValue() == Value.AS;
    }
}
