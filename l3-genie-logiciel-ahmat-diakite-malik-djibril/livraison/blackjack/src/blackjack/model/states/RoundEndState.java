package blackjack.model.states;

import blackjack.model.classes.Game;
import blackjack.model.interfaces.GameState;

public class RoundEndState implements GameState {

    /**
     * Designe les vainqueurs et met fin a la partie
     * 
     * @param game
     */
    @Override
    public void run(Game game) {
        game.setWinners();
        game.endRound();
    }

}
