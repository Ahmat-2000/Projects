package blackjack.model.states;

import blackjack.model.classes.Game;
import blackjack.model.interfaces.GameState;

public class DealerTurnState implements GameState {

    /**
     * Demande au dealer de jouer puis apres avoir retourner ses cartes
     * 
     * @param Game
     */
    @Override
    public void run(Game game) {
        game.getDealer().flipUpAllCards();
        game.getDealer().play();
        game.setState(game.getRoundEndState());
    }

}
