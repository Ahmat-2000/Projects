package blackjack.model.states;

import org.hamcrest.core.IsInstanceOf;

import blackjack.model.classes.Game;
import blackjack.model.classes.Player;
import blackjack.model.interfaces.GameState;
import blackjack.model.strategies.Stand;

public class PlayerTurnState implements GameState {

    /**
     * Chaque joueur joue a tour de role
     */

    private int play(Game game) {
        int i = 0;
        for (Player player : game.getPlayers()) {
            player.play();
            if (player.isHuman() && !player.isEndPlayerTurn()) {
                System.out.println("Hello");
                break;
            }
            i++;
        }
        return i;
    }

    @Override
    public void run(Game game) {
        int i = play(game);
        Player human = game.getHumPlayer();

        if (human.hasBust() || human.hasBlackJack()) {
            human.endPlayerTurn();
            i = play(game);
        }

        if (human.isEndPlayerTurn()) {
            i = game.getPlayers().size();
        }

        System.out.println(" " + game.getPlayers().size());

        System.out.println("I: " + i);
        if (i == game.getPlayers().size()) {
            game.getDealer().flipUpAllCards();
            game.getDealer().play();
            game.setState(game.getRoundEndState());
        }
        System.out.println(game.getDealer().getHand().getCards().size());
    }

}
