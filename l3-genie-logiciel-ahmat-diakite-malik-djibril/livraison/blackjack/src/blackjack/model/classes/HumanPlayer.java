package blackjack.model.classes;

import blackjack.model.strategies.*;

public class HumanPlayer extends Player {

    public HumanPlayer(Dealer dealer, float wallet, String name) {
        super(dealer, wallet, name);
        human = true;
        currentPlayer = false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see blackjack.model.classes.Player#play()
     */
    @Override
    public void play() {

        if (playerHand.computeHandValue() >= Game.BLACKJACK) {
            endPlayerTurn();
        }

        applyDecision();
    }

}
