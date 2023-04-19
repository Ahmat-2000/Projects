package blackjack.model.states;

import blackjack.model.classes.Game;
import blackjack.model.interfaces.GameState;

public class StartGameState implements GameState {

    /**
     * Fais la distrbution initiales
     * 
     * @param game
     */
    @Override
    public void run(Game game) {
        game.initialDeal();
        game.setState(game.getPlayerTurnState());
    }

}
