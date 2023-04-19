package blackjack.model.interfaces;

import blackjack.model.classes.Game;

public interface GameState {

    /**
     * Effectue un ensemble d'action selon l'etat
     * 
     * @param game
     */
    public void run(Game game);
}