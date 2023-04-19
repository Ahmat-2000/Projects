package blackjack.test.model.classes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import blackjack.model.classes.BotPlayer;
import blackjack.model.classes.Dealer;
import blackjack.model.classes.Game;
import blackjack.model.classes.HumanPlayer;
import blackjack.model.classes.Player;
import blackjack.model.interfaces.Strategy;
import blackjack.model.strategies.Hit;

public class PlayerTest {
    private static final int numberOfPlayers = 2;
    private static final int wallet = 200;
    private Player player;
    private static final int bet = 50;
    private Game game;

    @Before
    public void setUp() {
        game = new Game(numberOfPlayers);
        player = new BotPlayer(game.getDealer(), wallet, "BOt 1");
        player.getHand().setBet(bet);
    }

    @Test
    public void getDealer() throws Exception {
        assertNotNull(player.getDealer());
        assertEquals(game.getDealer(), player.getDealer());
    }

    @Test
    public void getHand() throws Exception {
        assertNotNull(player.getHand());
    }

    @Test
    public void setWallet() throws Exception {
        player.setWallet(wallet);

        assertEquals(wallet, (int) player.getWallet());
    }

    @Test
    public void setStrategy() throws Exception {
        Strategy strategy = new Hit();

        player.setSrategy(strategy);

        assertEquals(strategy, player.getStrategy());
    }

    @Test
    public void isEndPlayerTurn() throws Exception {
        assertFalse(player.isEndPlayerTurn());
    }
}
