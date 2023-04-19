package blackjack.model.classes;

import blackjack.model.strategies.DoubleDown;
import blackjack.model.strategies.Hit;
import blackjack.model.strategies.Stand;
import cartes.model.classes.Card;

public class BotPlayer extends Player {

    public BotPlayer(Dealer dealer, float wallet, String name) {
        super(dealer, wallet, name);
    }

    /**
     * Verifie si le robot a un AS
     * 
     * @return boolean
     */
    private boolean hasAs() {
        for (Card card : playerHand.getCards()) {
            if (BJCard.isAs(card)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifie si le robot doit tirer une nouvelle carte
     * 
     * @return boolean
     */
    private boolean shouldHit() {
        int handValue = playerHand.computeHandValue();
        int dealerValue = BJCard.getIntValue(dealer.getCardVisible());

        if (hasAs()) {
            return ((handValue >= 13 && handValue <= 17 && dealerValue > 6) ||
                    (handValue == 18 && dealerValue > 8) ||
                    (handValue == 17 && dealerValue == 2) ||
                    (handValue > 14 && handValue < 17 && dealerValue < 4) ||
                    (handValue > 12 && handValue < 15 && dealerValue < 5));
        }

        return ((handValue <= 8) ||
                (handValue == 9 && dealerValue >= 7) ||
                (handValue >= 12 && handValue <= 16 && dealerValue > 7) ||
                (handValue == 10 && dealerValue >= 10) ||
                (handValue == 12 && dealerValue >= 2 && dealerValue < 4));
    }

    /**
     * Verifie si le joueur doit rester
     * 
     * @return boolean
     */
    private boolean shouldStand() {
        int handValue = playerHand.computeHandValue();
        int dealerValue = BJCard.getIntValue(dealer.getCardVisible());

        if (hasAs()) {
            return ((handValue >= 19 && handValue < 22) ||
                    (handValue == 18 && (dealerValue == 2 || dealerValue == 7 || dealerValue == 8)));
        }

        return ((handValue == 12 && dealerValue > 3 && dealerValue < 6) ||
                (handValue >= 17 && handValue <= 21 && dealerValue < 7) ||
                (handValue >= 13 && handValue <= 21 && dealerValue >= 7));
    }

    /**
     * Verifie si le joueur doit doubler sa mise
     * 
     * @return boolean
     */
    private boolean shouldDouble() {
        int handValue = playerHand.computeHandValue();
        int dealerValue = BJCard.getIntValue(dealer.getCardVisible());

        if (hasAs()) {
            return ((handValue >= 13 && handValue <= 18 && dealerValue >= 5 && dealerValue < 7) ||
                    (dealerValue == 4 && handValue > 14 && handValue <= 18) ||
                    (dealerValue == 3 && (handValue == 17 || handValue == 18)));
        }

        return ((handValue > 8 && handValue < 12 && dealerValue < 7) ||
                (handValue == 10 && dealerValue < 10 && dealerValue > 6) ||
                (handValue == 11 && dealerValue > 6));
    }

    /**
     * Choisie l'action a effectuer
     */
    private void chooseActionToMake() {
        if (shouldHit()) {
            setSrategy(new Hit());
        } else if (shouldStand()) {
            setSrategy(new Stand());
        } else if (shouldDouble()) {
            setSrategy(new DoubleDown());
        } else {
            setSrategy(new Stand());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see blackjack.model.classes.Player#play()
     */
    @Override
    public void play() {
        while (!isEndPlayerTurn()) {
            chooseActionToMake();
            applyDecision();
        }
    }

}
