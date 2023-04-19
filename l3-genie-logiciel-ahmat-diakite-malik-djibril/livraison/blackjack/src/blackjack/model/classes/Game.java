package blackjack.model.classes;

import java.util.ArrayList;
import java.util.Random;

import blackjack.model.enums.Status;
import blackjack.model.interfaces.GameState;
import blackjack.model.interfaces.IGame;
import blackjack.model.states.DealerTurnState;
import blackjack.model.states.PlayerTurnState;
import blackjack.model.states.RoundEndState;
import blackjack.model.states.StartGameState;
import cartes.model.classes.Deck;
import cartes.util.observer.AbstractListenableModel;

public class Game extends AbstractListenableModel implements IGame {
    private final int NUMBER_OF_CARDS_PER_HANDS = 2;
    private final int MAXIMAL_NUMBER_OF_PLAYERS = 4;
    public final int DEFAULT_WALLET = 2000;
    public static final int DEALER_LIMIT = 17;
    public static final int BLACKJACK = 21;

    private ArrayList<Player> players;
    private Dealer dealer;
    private int numberOfPlayers = 1;
    private StartGameState startGameState;
    private PlayerTurnState playerTurnState;
    private DealerTurnState dealerTurnState;
    private RoundEndState roundEndState;
    private GameState currentState;
    private Deck defause;
    private boolean isEndRound;

    public Game(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        this.dealer = new Dealer();
        this.isEndRound = false;

        this.initializeDealer();
        this.initializePlayers();
        this.initializeStates();
    }

    /**
     * Méthodes pour initialiser les états du jeu
     */
    private void initializeStates() {
        startGameState = new StartGameState();
        playerTurnState = new PlayerTurnState();
        dealerTurnState = new DealerTurnState();
        roundEndState = new RoundEndState();
        currentState = startGameState;
    }

    /**
     * Initialise le dealer en melangeant les cartes
     */
    private void initializeDealer() {
        this.dealer.shuffleDeck();
    }

    /**
     * Fais la ditribution initiale des cartes
     */
    public void initialDeal() {
        for (int i = 0; i < NUMBER_OF_CARDS_PER_HANDS; i++) {
            this.dealer.takeCard(i != 0);
            for (int j = 0; j < numberOfPlayers; j++) {
                this.players.get(j).acceptCard(this.dealer.dealCard());
            }
        }
    }

    /**
     * Initialise le joueur humain et les robots
     */
    private void initializePlayers() {
        Random rand = new Random();
        int position = rand.nextInt(this.numberOfPlayers);
        this.players = new ArrayList<>();

        int j = 1;
        for (int i = 0; i < this.numberOfPlayers; i++) {
            if (i == position) {
                this.players.add(new HumanPlayer(this.dealer, DEFAULT_WALLET, "Humain"));
            } else {
                this.players.add(new BotPlayer(this.dealer, DEFAULT_WALLET, "Bot " + j));
                j++;
            }
        }
    }

    /**
     * Initialise les mises de facon aleatoire pour les robots
     */
    public void initializeBotBet() {
        int[] possiblesBet = { 1, 5, 10, 25, 50, 100 };
        Random rand = new Random();

        for (int i = 0; i < numberOfPlayers; i++) {
            if (!players.get(i).isHuman()) {
                int bet = possiblesBet[rand.nextInt(possiblesBet.length)];
                players.get(i).getHand().setBet(bet);
                players.get(i).setWallet(players.get(i).getWallet() - bet);
            }
        }
    }

    /**
     * @return Retourne l'etat courant du jeu
     */
    public GameState getCurrentState() {
        return currentState;
    }

    /**
     * @return Retourne l'etat start game
     */
    public GameState getSartGameState() {
        return startGameState;
    }

    /**
     * @return Retour l'etat player turn
     */
    public GameState getPlayerTurnState() {
        return playerTurnState;
    }

    /**
     * @return Retour l'etat dealer turn
     */
    public GameState getDealerTurnState() {
        return dealerTurnState;
    }

    /**
     * @return Retour l'etat round end
     */
    public GameState getRoundEndState() {
        return roundEndState;
    }

    /**
     * Mets a jour l'etat courant du jeu
     * 
     * @param state
     */
    public void setState(GameState state) {
        currentState = state;
    }

    /**
     * Reinisialise une partie
     */
    public void reset() {
        dealer.clearHand(this.defause);
        for (Player player : this.players) {
            player.clearHand(defause);
        }
        dealer.mergeDeck(defause);
        currentState = startGameState;
        isEndRound = false;
    }

    /**
     * Retourne le joueur humain
     * 
     * @return player
     */
    public Player getHumPlayer() {
        for (Player player : players) {
            if (player.isHuman())
                return player;
        }
        return null;
    }

    /**
     * Met a jour le nombre de joueur hors le dealer
     * 
     * @param numberOfPlayers le nouveau nombre de joueurs
     */
    public void setNumberOfPlayers(int numberOfPlayers) {
        if (numberOfPlayers > MAXIMAL_NUMBER_OF_PLAYERS || numberOfPlayers < 1) {
            this.numberOfPlayers = 1;
        }
    }

    /**
     * Designe les vainqueurs et les perdants a la fin de chaque partie
     */
    public void setWinners() {
        for (Player player : this.players) {
            if (player.hasBust()) {
                player.setWinningStatus(Status.LOSE);
            } else if (dealer.hasBust()) {
                player.setWinningStatus(Status.WIN);
            } else if (player.hasBlackJack() && dealer.hasBlackJack()) {
                player.setWinningStatus(Status.TIE);
            } else if (player.getHand().computeHandValue() > dealer.getHand().computeHandValue()) {
                player.setWinningStatus(Status.WIN);
            } else if (player.getHand().computeHandValue() < dealer.getHand().computeHandValue()) {
                player.setWinningStatus(Status.LOSE);
            } else {
                player.setWinningStatus(Status.TIE);
            }
        }
    }

    /**
     * Permet de savoir si la partie est terminer ou pas
     * 
     * @return retourn isEndRound
     */
    public boolean getIsEndRound() {
        return this.isEndRound;
    }

    /**
     * Mets fin a la partie courante
     */
    public void endRound() {
        this.isEndRound = true;
    }

    @Override
    public Deck getDeck() {
        return this.dealer.getDeck();
    }

    @Override
    public void run() {
        this.currentState.run(this);
    }

    @Override
    public Dealer getDealer() {
        return this.dealer;
    }

    @Override
    public ArrayList<Player> getPlayers() {
        return this.players;
    }

}
