package blackjack.model.strategies;

import blackjack.model.classes.Player;
import blackjack.model.interfaces.Strategy;

public class Stand implements Strategy {

    /*
     * (non-Javadoc)
     * 
     * @see
     * blackjack.model.interfaces.Strategy#applyDecision(blackjack.model.classes.
     * Player)
     */
    @Override
    public void applyDecision(Player player) {
        player.endPlayerTurn();
    }

}
