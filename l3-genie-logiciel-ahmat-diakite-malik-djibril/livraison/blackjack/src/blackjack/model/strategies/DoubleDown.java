package blackjack.model.strategies;

import blackjack.model.classes.Player;
import blackjack.model.interfaces.Strategy;

public class DoubleDown implements Strategy {

    /*
     * (non-Javadoc)
     * 
     * @see
     * blackjack.model.interfaces.Strategy#applyDecision(blackjack.model.classes.
     * Player)
     */
    @Override
    public void applyDecision(Player player) {
        int bet = player.getHand().getBet();
        player.setWallet(player.getWallet() - bet);
        player.getHand().setBet(bet * 2);
        player.setSrategy(new Hit());
        player.applyDecision();
        player.setSrategy(new Stand());
        player.applyDecision();
    }

}
