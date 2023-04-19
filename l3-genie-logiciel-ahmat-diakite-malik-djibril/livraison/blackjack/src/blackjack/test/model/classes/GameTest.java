package blackjack.test.model.classes;

import static org.junit.Assert.*;

import org.junit.Test;

import blackjack.model.classes.Game;
import blackjack.model.classes.Player;

public class GameTest {

    @Test
    public void initializePlayers() {
        Game game = new Game(2);
        assertEquals(game.getPlayers().size(), 2);
    }

    @Test
    public void initializeBotBet() {
        Game game = new Game(2);
        game.initializeBotBet();
        for (Player p : game.getPlayers()) {
            if (!p.isHuman()) {
                assertNotEquals(p.getHand().getBet(), 0);
            }
        }
    }

    @Test
    public void initializeStates() {
        Game game = new Game(2);
        assertEquals(game.getCurrentState(), game.getSartGameState());
        game.run();
        assertEquals(game.getCurrentState(), game.getPlayerTurnState());
    }

    @Test
    public void initialDeal() {
        Game game = new Game(2);
        assertEquals(game.getDeck().getCards().size(), 52);
        game.initialDeal();
        assertEquals(game.getDeck().getCards().size(), 46);
    }

    @Test
    public void getHumPlayer() {
        Game game = new Game(2);
        Player human = game.getHumPlayer();
        assertEquals(human.isHuman(), true);
    }
}